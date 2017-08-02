package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ConnectDeviceActivity extends BaseActivity {

    UI.TitleCallBackListener mTitleListener;
    UI.LogoCallBackListener mLogoListener;
    UI.FeeCallBackListener mFeeListener;
    TextView mTxtMessage;
    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        if (i != null) {
            setDefaultTitle(i.getStringExtra("title"));
            updateConnectionMessage(i.getStringExtra("deviceName"));
            mTitleListener = (UI.TitleCallBackListener) i.getSerializableExtra("titleListener");
            mLogoListener = (UI.LogoCallBackListener) i.getSerializableExtra("logoListener");
            mFeeListener = (UI.FeeCallBackListener) i.getSerializableExtra("feeListener");
        }
    }

    void updateConnectionMessage(String device_name) {
        if (mTxtMessage != null)
            mTxtMessage.setText(mTxtMessage.getText() + " " + device_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.connect_device, null));
            mTxtMessage = (TextView) content.findViewById(R.id.txt_connect);
            setProgressValue((ProgressBar) content.findViewById(R.id.bar_connect));
        }
    }

    public void setProgressValue(final ProgressBar v) {
        if (v != null) {
            final Handler handler = new Handler(Looper.getMainLooper());
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 5;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                v.setProgress(progressStatus);
                            }
                        });
                        try {
                            Thread.sleep(150);
                            if (progressStatus >= 100)
                                break;
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int idTitle = -1, idLogo = -1;
                    long fee = 0;
                    if (mTitleListener != null)
                        idTitle = mTitleListener.getTransactionTitleId();
                    if (mLogoListener != null)
                        idLogo = mLogoListener.getTransactionLogoId();
                    if (mFeeListener != null)
                        fee = mFeeListener.getFeeAmount();
                    Intent i = new Intent(getBaseContext(), TransactionFeeActivity.class);
                    i.putExtra("title", idTitle);
                    i.putExtra("iconID", idLogo);
                    i.putExtra("amountFee", fee);
                    startActivity(i);
                    finish();
                }
            });
            thread.start();
        }
    }
}

package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ConnectDeviceActivity extends BaseActivity {

    TextView mTxtMessage;
    String mTransactionTitle, mDeviceName, mAccountNumber, mFeeAmount;
    int progressStatus = 0, mLogoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        if (i != null) {
            mTransactionTitle = i.getStringExtra("title");
            mDeviceName = i.getStringExtra("deviceName");
            mAccountNumber = i.getStringExtra("accountNumber");
            mLogoId = i.getIntExtra("logoID", -1);
            mFeeAmount = i.getStringExtra("amountFee");
        }
        super.onCreate(savedInstanceState);
        setDefaultTitle(mTransactionTitle);
        updateConnectionMessage(mDeviceName);
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
    public void onCreateContent(FrameLayout content) {
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
                    Intent i = new Intent(getBaseContext(), TransactionFeeActivity.class);
                    i.putExtra("title", mTransactionTitle);
                    i.putExtra("accountNumber", mAccountNumber);
                    i.putExtra("iconID", mLogoId);
                    i.putExtra("amountFee", mFeeAmount);
                    startActivity(i);
                    finish();
                }
            });
            thread.start();
        }
    }
}

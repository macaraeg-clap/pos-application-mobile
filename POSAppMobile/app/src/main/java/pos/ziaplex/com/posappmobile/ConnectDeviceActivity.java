package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class ConnectDeviceActivity extends BaseActivity {

    ConnectDeviceListener mListener;
    String mDeviceName;
    int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        String title = null;
        if (i != null) {
            title = i.getStringExtra("title");
            mDeviceName = i.getStringExtra("deviceName");
            mListener = (ConnectDeviceListener) i.getSerializableExtra("listener");
        }
        super.onCreate(savedInstanceState);
        setDefaultTitle(title);
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
            TextView tv = (TextView) content.findViewById(R.id.txt_connect);
            if (tv != null)
                tv.setText(tv.getText() + " " + mDeviceName);
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
                            Thread.sleep(1000);
                            if (progressStatus >= 100) {
                                /*if (mListener != null) {
                                    mListener.onConnectDeviceFinish(getBaseContext());
                                    // FIXME:
                                    Intent i = new Intent(getBaseContext(), TransactionFeeActivity.class);
                                    i.putExtra("title", getString(R.string.balance_label));
                                    i.putExtra("iconID", R.drawable.ic_balance_round);
                                    startActivity(i);
                                    finish();
                                }*/
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
    }
}

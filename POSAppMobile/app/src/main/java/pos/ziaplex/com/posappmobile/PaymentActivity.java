package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class PaymentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.payment_label));
        if (BaseActivity.mHome != null)
            BaseActivity.mHome.hideProgressDisplay();
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.activity_payment, null));
            Button v = (Button) content.findViewById(R.id.btn_continue);
            if (v != null) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getBaseContext(), PaymentDetailActivity.class));
                        finish();
                    }
                });
            }
        }
    }
}

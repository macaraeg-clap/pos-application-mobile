package pos.ziaplex.com.posappmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class PaymentModeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.payment_label));
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.payment_mode, null));
            LinearLayout p = (LinearLayout) content.findViewById(R.id.mode_container);
            if (p != null) {
                LinearLayout tp = UI.createLinerLayout(this, LinearLayout.VERTICAL);
                tp.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                tp.addView(UI.createCustomImageButton(this, "Credit Card", R.drawable.bg_credit, null));
                tp.addView(UI.createCustomImageButton(this, "e Wallet", R.drawable.bg_wallet, null));
                p.addView(tp);
                LinearLayout bp = UI.createLinerLayout(this, LinearLayout.VERTICAL);
                bp.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1));
                bp.addView(UI.createCustomImageButton(this, "Debit Card", R.drawable.bg_debit, null));
                bp.addView(UI.createCustomImageButton(this, "Cash Payment", R.drawable.bg_cash, null));
                p.addView(bp);
            }
        }
    }
}

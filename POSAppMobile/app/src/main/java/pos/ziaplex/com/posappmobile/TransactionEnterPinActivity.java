package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TransactionEnterPinActivity extends BaseActivity implements View.OnClickListener {

    TextView mTextAccountNumber;
    String mTransactionTitle, mAccountNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        if (i != null) {
            mTransactionTitle = i.getStringExtra("title");
            mAccountNumber = i.getStringExtra("accountNumber");
        }
        super.onCreate(savedInstanceState);
        setDefaultTitle(mTransactionTitle);
        updateAccountNumber(mAccountNumber);
    }

    void updateAccountNumber(String value) {
        if (mTextAccountNumber != null)
            mTextAccountNumber.setText(Util.MaskedNumberFormat.instance(value).toStringFormat());
    }

    @Override
    public void onClick(View v) { // FIXME: temporary
        Intent i = new Intent(getBaseContext(), TransactionResultActivity.class);
        i.putExtra("title", mTransactionTitle);
        i.putExtra("accountNumber", mAccountNumber);
        startActivity(i);
        finish();
    }

    @Override
    public void onCreateContent(FrameLayout content) {
        if (content != null) {
            content.setOnClickListener(this); // FIXME: temporary
            content.addView(LayoutInflater.from(this)
                    .inflate(R.layout.transaction_process_view, null));
            RelativeLayout p = (RelativeLayout) content.findViewById(R.id.card_container);
            if (p != null) {
                LinearLayout v = (LinearLayout) LayoutInflater.from(this)
                        .inflate(R.layout.last_transaction_card, null);
                if (v != null) {
                    RelativeLayout.LayoutParams l =
                            UI.getRelativeLayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                                    RelativeLayout.LayoutParams.WRAP_CONTENT);
                    if (l != null)
                        l.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    v.setLayoutParams(l);
                    p.addView(v);
                }
            }
            mTextAccountNumber = (TextView) content.findViewById(R.id.txt_account_no);
        }
    }
}

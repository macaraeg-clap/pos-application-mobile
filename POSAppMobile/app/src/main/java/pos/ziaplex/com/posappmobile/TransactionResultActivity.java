package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jimmy.macaraeg on 04/08/2017.
 */

public class TransactionResultActivity extends BaseActivity implements View.OnClickListener {

    TextView mTextAccountNumber, mTextAmount;
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

    void updateAmount(long amount) {
        if (mTextAmount != null)
            mTextAmount.setText(Util.convertToCurrency(getString(R.string.amount_sign), amount));
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button btn = (Button) v;
            if (btn != null) {
                if (btn.getId() == R.id.btn_close) {
                    finish();
                    return;
                }
            }
        }
    }

    @Override
    public void onCreateContent(FrameLayout content) {
        if (content != null) {
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
            mTextAmount = (TextView) content.findViewById(R.id.txt_amount);
            TextView tv = (TextView) content.findViewById(R.id.txt_trans_message);
            if (tv != null)
                tv.setVisibility(View.GONE);
            Button btnClose = (Button) content.findViewById(R.id.btn_close);
            if (btnClose != null) {
                btnClose.setVisibility(View.VISIBLE);
                btnClose.setOnClickListener(this);
            }
        }
    }
}

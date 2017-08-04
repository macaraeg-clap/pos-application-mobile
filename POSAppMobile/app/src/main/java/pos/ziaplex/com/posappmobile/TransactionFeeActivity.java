package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TransactionFeeActivity extends BaseActivity implements View.OnClickListener {

    ImageView mLogo;
    TextView mTxtMessage;
    String mTransactionTitle, mAccountNumber, mAmountFee;
    int mIconId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        if (i != null) {
            mTransactionTitle = i.getStringExtra("title");
            mAccountNumber = i.getStringExtra("accountNumber");
            mIconId = i.getIntExtra("iconID", -1);
            mAmountFee = i.getStringExtra("amountFee");
        }
        super.onCreate(savedInstanceState);
        setDefaultTitle(mTransactionTitle);
        updateLogo(mIconId);
        updateFeeMessage(Long.valueOf(mAmountFee));
    }

    void updateLogo(int logo_id) {
        if (mLogo != null)
            mLogo.setImageResource(logo_id);
    }

    void updateFeeMessage(long amount) {
        if (mTxtMessage != null)
            mTxtMessage.setText(getString(R.string.transaction_fee_message_first) + " " +
                    Util.convertToCurrency(getString(R.string.amount_sign), amount) + " " +
                    getString(R.string.transaction_fee_message_last) + " "
                    + mTransactionTitle + "?");
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this)
                    .inflate(R.layout.transaction_logo_view, null));
            content.addView(LayoutInflater.from(this)
                    .inflate(R.layout.transaction_fee_view, null));
            mLogo = (ImageView) content.findViewById(R.id.img_icon);
            mTxtMessage = (TextView) content.findViewById(R.id.txt_fee_message);
            Button btnYes = (Button) content.findViewById(R.id.btn_yes);
            if (btnYes != null)
                btnYes.setOnClickListener(this);
            Button btnNo = (Button) content.findViewById(R.id.btn_no);
            if (btnNo != null)
                btnNo.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button btn = (Button) v;
            if (btn != null) {
                if (btn.getId() == R.id.btn_yes) {
                    Intent i = new Intent(getBaseContext(), TransactionSwipeCardActivity.class);
                    i.putExtra("title", mTransactionTitle);
                    i.putExtra("accountNumber", mAccountNumber);
                    startActivity(i);
                }
                finish();
            }
        }
    }
}

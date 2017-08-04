package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class TransactionSwipeCardActivity extends BaseActivity implements View.OnClickListener {

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
    }

    @Override
    public void onClick(View v) { // FIXME: temporary
        Intent i = new Intent(getBaseContext(), TransactionEnterPinActivity.class);
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
                    .inflate(R.layout.transaction_swipe_card_view, null));
        }
    }
}

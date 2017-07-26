package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.GridLayout;
import android.widget.ScrollView;

/**
 * Created by jimmy.macaraeg on 04/07/2017.
 */

public class HomeActivity extends BaseActivity implements UI.ButtonCallbackListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateContent(ScrollView content) {
        if (content != null) {
            content.setBackgroundColor(ContextCompat.getColor(this, R.color.colorViolet));
            GridLayout p = UI.createGridLayout(this, GridLayout.HORIZONTAL, 3, 2);
            p.setLayoutParams(UI.getGridLayoutParams(GridLayout.LayoutParams.MATCH_PARENT,
                    GridLayout.LayoutParams.MATCH_PARENT));
            int w = (int)(Util.getScreenWidth(this) * 0.5);
            int h = (int)(Util.getScreenHeight(this) / 3.45);
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_transaction_normal,
                    R.drawable.ic_transaction_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_transaction),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(0), GridLayout.spec(0)));
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_balance_normal,
                    R.drawable.ic_balance_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_balance),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(0), GridLayout.spec(1)));
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_withdrawal_normal,
                    R.drawable.ic_withdrawal_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_withdrawal),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(1), GridLayout.spec(0)));
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_payment_normal,
                    R.drawable.ic_payment_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_payment),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(1), GridLayout.spec(1)));
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_connection_normal,
                    R.drawable.ic_connection_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_connection),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(2), GridLayout.spec(0)));
            p.addView(UI.createCustomHomeItemWithIcon(this, R.drawable.ic_operator_normal,
                    R.drawable.ic_operator_pressed, R.color.colorViolet, R.color.colorLightGray,
                    R.color.colorLightGray, R.color.colorGray, getString(R.string.action_operator),
                    this),
                    UI.getGridLayoutParams(w, h, GridLayout.spec(2), GridLayout.spec(1)));
            content.addView(p);
        }
    }

    public void onItemClicked(String tag) {
        if (getString(R.string.action_transaction).equals(tag)) {
            startActivity(new Intent(getBaseContext(), TransactionActivity.class));
        }
        else if (getString(R.string.action_balance).equals(tag)) {
            startActivity(new Intent(getBaseContext(), BalanceActivity.class));
        }
        else if (getString(R.string.action_withdrawal).equals(tag)) {
            startActivity(new Intent(getBaseContext(), WithdrawalActivity.class));
        }
        else if (getString(R.string.action_payment).equals(tag)) {
            startActivity(new Intent(getBaseContext(), PaymentActivity.class));
        }
        else if (getString(R.string.action_connection).equals(tag)) {
            startActivity(new Intent(getBaseContext(), ConnectionActivity.class));
        }
        else if (getString(R.string.action_operator).equals(tag)) {
            startActivity(new Intent(getBaseContext(), OperatorInfoActivity.class));
        }
    }
}

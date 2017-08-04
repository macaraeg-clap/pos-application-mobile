package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.balance_label));
        if (BaseActivity.mHome != null)
            BaseActivity.mHome.hideProgressDisplay();
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this)
                    .inflate(R.layout.transaction_logo_view, null));
            content.addView(LayoutInflater.from(this)
                    .inflate(R.layout.transaction_select_account_view, null));
            ImageView v = (ImageView) content.findViewById(R.id.img_icon);
            if (v != null)
                v.setBackgroundResource(R.drawable.ic_balance_round);
            ImageView vv = (ImageView) content.findViewById(R.id.img_bancnet);
            if (vv != null)
                vv.setBackgroundResource(R.drawable.ic_bancnet);
            Button btnCon = (Button) content.findViewById(R.id.btn_continue);
            if (btnCon != null)
                btnCon.setOnClickListener(this);
        }
}

    @Override
    public void onClick(View v) {
        showBluetoothDeviceOptions();
    }

    @Override
    public void onDeviceItemSelected(AdapterView<?> parent, final View view, int position,
                                     long id) {
        Intent i = new Intent(getBaseContext(), ConnectDeviceActivity.class);
        i.putExtra("title", getString(R.string.balance_label));
        i.putExtra("deviceName", parent.getItemAtPosition(position).toString());
        i.putExtra("accountNumber", "1234123412340000"); // FIXME:
        i.putExtra("logoID", R.drawable.ic_balance_round);
        i.putExtra("amountFee", String.valueOf(15100)); // FIXME:
        startActivity(i);
    }
}

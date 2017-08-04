package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class WithdrawalActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.withdrawal_label));
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
                v.setBackgroundResource(R.drawable.ic_withdrawal_round);
            Button btnCon = (Button) content.findViewById(R.id.btn_continue);
            if (btnCon != null)
                btnCon.setOnClickListener(this);
            updateSelectorList(content);
        }
    }

    @Override
    public void onClick(View v) {
        showBluetoothDeviceOptions();
    }

    void updateSelectorList(View v) {
        if (v != null) {
            Spinner spinAccount = (Spinner) v.findViewById(R.id.spin_account);
            if (spinAccount != null) {
                ArrayList<String> l = new ArrayList<>();
                for (String s : getResources().getStringArray(R.array.account_array))
                    l.add(s);
                l.add("Cash Advance");
                ArrayAdapter<String> aA = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, l);
                aA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinAccount.setAdapter(aA);
            }
        }
    }

    @Override
    public void onDeviceItemSelected(AdapterView<?> parent, final View view, int position,
                                     long id) {
        Intent i = new Intent(getBaseContext(), ConnectDeviceActivity.class);
        i.putExtra("title", getString(R.string.withdrawal_label));
        i.putExtra("deviceName", parent.getItemAtPosition(position).toString());
        i.putExtra("logoID", R.drawable.ic_withdrawal_round);
        i.putExtra("amountFee", 15100);
        startActivity(i);
    }
}

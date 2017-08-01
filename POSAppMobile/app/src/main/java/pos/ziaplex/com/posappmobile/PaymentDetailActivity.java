package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentDetailActivity extends BaseActivity implements UI.ConnectDeviceListener {

    class PaymentDetail {

        PaymentDetail(String first_name, String payor, String transaction_number, String payment_for,
                             String transaction_amount, String transaction_fee, String total_amount) {
            mFirstName = first_name;
            mPayor = payor;
            mTransactionNumber = transaction_number;
            mPaymentFor = payment_for;
            mTransactionAmount = transaction_amount;
            mTransactionFee = transaction_fee;
            mTotalAmount = total_amount;
        }

        String mFirstName;
        String mPayor;
        String mPaymentFor;
        String mTransactionNumber;
        String mTransactionAmount;
        String mTransactionFee;
        String mTotalAmount;

        String getFirstName() {
            return mFirstName;
        }

        String getPayor() {
            return mPayor;
        }

        String getPaymentFor() {
            return mPaymentFor;
        }

        String getTransactionNumber() {
            return mTransactionNumber;
        }

        String getTransactionAmount() {
            return mTransactionAmount;
        }

        String getTransactionFee() {
            return mTransactionFee;
        }

        String getTotalAmount() {
            return mTotalAmount;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.payment_label));
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.payment_detail, null));
            Button btnCon = (Button) content.findViewById(R.id.btn_proceed);
            if (btnCon != null) {
                btnCon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showBluetoothDeviceOptions();
                    }
                });
            }
        }
        loadDetails();
    }

    @Override
    public void onDeviceItemSelected(AdapterView<?> parent, final View view, int position, long id) {
        Intent i = new Intent(getBaseContext(), ConnectDeviceActivity.class);
        i.putExtra("title", getString(R.string.payment_label));
        i.putExtra("deviceName", (String) parent.getItemAtPosition(position));
        i.putExtra("listener", this);
        startActivity(i);
    }

    @Override
    public void onConnectDeviceFinish(Context context) {
        // TODO
    }

    void loadDetails() {
        // FIXME: Temporary
        updateDetails(new PaymentDetail("Yves", "Yves Tayao", "123455958923423", "PEZA",
                "Php 15,000.00", "100", "15,100"));
    }

    public void updateDetails(PaymentDetail detail) {
        if (detail != null) {
            TextView txtName = (TextView) findViewById(R.id.txt_name);
            if (txtName != null)
                txtName.setText(detail.getFirstName());
            TextView txtPayor = (TextView) findViewById(R.id.txt_payor);
            if (txtPayor != null)
                txtPayor.setText(detail.getPayor());
            TextView txtTransNo = (TextView) findViewById(R.id.txt_trans_number);
            if (txtTransNo != null)
                txtTransNo.setText(detail.getTransactionNumber());
            TextView txtPayFor = (TextView) findViewById(R.id.txt_pay_for);
            if (txtPayFor != null)
                txtPayFor.setText(detail.getPaymentFor());
            TextView txtTransAmount = (TextView) findViewById(R.id.txt_trans_amount);
            if (txtTransAmount != null)
                txtTransAmount.setText(detail.getTransactionAmount());
            TextView txtTransFee = (TextView) findViewById(R.id.txt_trans_fee);
            if (txtTransFee != null)
                txtTransFee.setText(detail.getTransactionFee());
            TextView txtTotalAmount = (TextView) findViewById(R.id.txt_total_amount);
            if (txtTotalAmount != null)
                txtTotalAmount.setText(detail.getTotalAmount());
        }
    }
}

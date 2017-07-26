package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jimmy.macaraeg on 25/07/2017.
 */

public class TransactionAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Transaction> mDataSource;

    public TransactionAdapter(Context context) {
        mContext = context;
        mDataSource = new ArrayList<>();
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mDataSource == null)
            return 0;
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDataSource == null)
            return null;
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rv = mInflater.inflate(R.layout.transaction_list_item, parent, false);
        Transaction trans = (Transaction) getItem(position);
        if (trans != null) {
            ImageView imgStat = (ImageView) rv.findViewById(R.id.img_status);
            if ("offline".equalsIgnoreCase(trans.getStatus())) {
                if (imgStat != null)
                    imgStat.setImageResource(R.drawable.ic_offline);
            }
            else {
                if (imgStat != null)
                    imgStat.setImageResource(R.drawable.ic_online);
            }
            TextView tvAmount = (TextView) rv.findViewById(R.id.txt_amount);
            if (tvAmount != null)
                tvAmount.setText(mContext.getString(R.string.amount_sign) + " " + trans.getAmount());
            TextView tvTransRef = (TextView) rv.findViewById(R.id.txt_trans_reference);
            if (tvTransRef != null)
                tvTransRef.setText(trans.getTransReference());
            TextView tvAccountNo = (TextView) rv.findViewById(R.id.txt_account_no);
            if (tvAccountNo != null)
                tvAccountNo.setText(trans.getAccountNumber());
            TextView tvDate = (TextView) rv.findViewById(R.id.txt_time);
            if (tvDate != null)
                tvDate.setText(trans.getDate());
            TextView tvOperator = (TextView) rv.findViewById(R.id.txt_operator_name);
            if (tvOperator != null)
                tvOperator.setText(trans.getOperatorName());
            TextView tvTransType = (TextView) rv.findViewById(R.id.txt_trans_type);
            if (tvTransType != null)
                tvTransType.setText(trans.getTransType());
        }
        return rv;
    }

    public void addTransaction(Transaction trans) {
        if (mDataSource != null)
            mDataSource.add(trans);
        notifyDataSetChanged();
    }

    public void clear() {
        if (mDataSource != null)
            mDataSource.clear();
        notifyDataSetChanged();
    }
}
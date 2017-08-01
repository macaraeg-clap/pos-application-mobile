package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jimmy.macaraeg on 06/07/2017.
 */

public class OperatorInfoActivity extends BaseActivity {

    class Operator {

        Operator(OperatorInfo information, Util.Date last_login, Transaction transaction) {
            mInfo = information;
            mLastLogin = last_login;
            mTransaction = transaction;
        }

        OperatorInfo mInfo;
        Util.Date mLastLogin;
        Transaction mTransaction;

        OperatorInfo getOperatorInfo() {
            return mInfo;
        }

        Util.Date getLastLogin() {
            return mLastLogin;
        }

        Transaction getTransaction() {
            return mTransaction;
        }
    }

    static class OperatorListData {

        OperatorListData() {
            mData = new ArrayList<>();
        }

        static ArrayList<Operator> mData;

        static void updateListData(ArrayList<Operator> list) {
            mData = list;
        }

        static ArrayList<Operator> getListData() {
            return mData;
        }

        static ArrayList<Operator> getByOperatorName(String name) {
            ArrayList<Operator> l = new ArrayList<>();
            if (mData != null) {
                for (int i = 0; i < getSize(); i++) {
                    Operator ope = getOperatorData(i);
                    if (ope != null) {
                        OperatorInfo info = ope.getOperatorInfo();
                        if (info != null) {
                            if (info.getName().equalsIgnoreCase(name))
                                l.add(ope);
                        }
                    }
                }
            }
            return l;
        }

        static ArrayList<Operator> getByOperatorName(ArrayList<Operator> list, String name) {
            ArrayList<Operator> l = new ArrayList<>();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Operator ope = list.get(i);
                    if (ope != null) {
                        OperatorInfo info = ope.getOperatorInfo();
                        if (info != null) {
                            if (info.getName().equalsIgnoreCase(name))
                                l.add(ope);
                        }
                    }
                }
            }
            return l;
        }

        static void clearListData() {
            if (mData != null)
                mData.clear();
        }

        static Operator getOperatorData(int index) {
            if (mData != null)
                return mData.get(index);
            return null;
        }

        static ArrayList<String> getOperatorNameList(boolean has_all) {
            ArrayList<String> l = new ArrayList<>();
            if (has_all)
                l.add("All");
            if (mData != null) {
                for (int i = 0; i < mData.size(); i++) {
                    Operator o = mData.get(i);
                    if (o != null) {
                        OperatorInfo in = o.getOperatorInfo();
                        if (in != null) {
                            String n = in.getName();
                            if (!l.contains(n))
                                l.add(n);
                        }
                    }
                }
            }
            return l;
        }

        static int getSize() {
            if (mData != null)
                return mData.size();
            return 0;
        }
    }

    class OperatorAdapter extends BaseAdapter {

        OperatorAdapter(Context context) {
            mContext = context;
            mDataSource = new ArrayList<>();
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        Context mContext;
        LayoutInflater mInflater;
        ArrayList<Operator> mDataSource;

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
            View rv = mInflater.inflate(R.layout.operator_list_item, parent, false);
            Operator ope = (Operator) getItem(position);
            if (ope != null) {
                OperatorInfo info = ope.getOperatorInfo();
                if (info != null) {
                    ImageView imgPic = (ImageView) rv.findViewById(R.id.img_pic);
                    if (imgPic != null) {
                        BitmapDrawable img = info.getImage();
                        if (img != null) {
                            imgPic.setImageDrawable(img);
                        }
                        else {
                            imgPic.setImageResource(R.drawable.ic_printer);
                        }
                    }
                    TextView txtName = (TextView) rv.findViewById(R.id.txt_name);
                    if (txtName != null)
                        txtName.setText(info.getName());
                }
                Util.Date date = ope.getLastLogin();
                if (date != null) {
                    TextView txtTime = (TextView) rv.findViewById(R.id.txt_date_time);
                    if (txtTime != null)
                        txtTime.setText(date.getTime());
                }
                Transaction trans = ope.getTransaction();
                if (trans != null) {
                    TextView txtType = (TextView) rv.findViewById(R.id.txt_trans_type);
                    if (txtType != null)
                        txtType.setText(trans.getTransactionType());
                }
            }
            return rv;
        }

        void addOperator(Operator operator) {
            if (mDataSource != null)
                mDataSource.add(operator);
            notifyDataSetChanged();
        }

        void clear() {
            if (mDataSource != null)
                mDataSource.clear();
            notifyDataSetChanged();
        }
    }

    class OperatorListTask extends AsyncTask<ArrayList<Operator>, Void, ArrayList<Operator>> {

        OperatorListTask(OperatorAdapter list_adapter, ListView list_view,
                            LinearLayout progress_container, LinearLayout no_found_container) {
            mListAdapter = list_adapter;
            mListView = list_view;
            mProgressContainer = progress_container;
            mNoFoundContainer = no_found_container;
        }

        OperatorAdapter mListAdapter;
        ListView mListView;
        LinearLayout mProgressContainer, mNoFoundContainer;

        @Override
        protected void onPreExecute() {
            if (mListView != null)
                mListView.setVisibility(View.GONE);
            if (mNoFoundContainer != null)
                mNoFoundContainer.setVisibility(View.GONE);
            if (mProgressContainer != null)
                mProgressContainer.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(ArrayList<Operator> result) {
            if (mListAdapter != null) {
                mListAdapter.clear();
                int sz = result.size();
                if (sz > 0) {
                    if (mListView != null)
                        mListView.setVisibility(View.VISIBLE);
                    if (mNoFoundContainer != null)
                        mNoFoundContainer.setVisibility(View.GONE);
                    for (int i = 0; i < sz; i++)
                        mListAdapter.addOperator(result.get(i));
                }
                else {
                    if (mListView != null)
                        mListView.setVisibility(View.GONE);
                    if (mNoFoundContainer != null)
                        mNoFoundContainer.setVisibility(View.VISIBLE);
                }
            }
            if (mProgressContainer != null)
                mProgressContainer.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
        }

        @Override
        protected ArrayList<Operator> doInBackground(ArrayList<Operator>... params) {
            return params[0];
        }
    }

    class OperatorInfo {

        OperatorInfo(BitmapDrawable image, String name, String code) {
            mImage = image;
            mName = name;
            mCode = code;
        }

        BitmapDrawable mImage;
        String mName, mCode;

        BitmapDrawable getImage() {
            return mImage;
        }

        String getName() {
            return mName;
        }

        String getCode() {
            return mCode;
        }
    }

    class MerchantInfo {

        MerchantInfo(String name, String code, String currency, String email) {
            mName = name;
            mCode = code;
            mCurrency = currency;
            mEmail = email;
        }

        String mName, mCode, mCurrency, mEmail;

        String getName() {
            return mName;
        }

        String getCode() {
            return mCode;
        }

        String getCurrency() {
            return mCurrency;
        }

        String getEmail() {
            return mEmail;
        }
    }

    class PrintTransactionTab extends LinearLayout implements View.OnClickListener,
            UI.DatePickerCallbackListener, AdapterView.OnItemSelectedListener {

        PrintTransactionTab(Context context) {
            super(context);
            initialize();
        }

        OperatorAdapter mListAdapter;
        LinearLayout mProgressContainer, mNoFoundContainer;
        ListView mListView;
        Button mButtonFrom, mButtonTo;
        Util.Date mDateFrom = null, mDateTo = null, mDateToday = null;
        Spinner mSpinName;

        void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            LinearLayout v = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.print_transaction_list, null);
            addView(v);
            if (v != null) {
                mButtonFrom = (Button) v.findViewById(R.id.btn_from);
                if (mButtonFrom != null)
                    mButtonFrom.setOnClickListener(this);
                mButtonTo = (Button) v.findViewById(R.id.btn_to);
                if (mButtonTo != null)
                    mButtonTo.setOnClickListener(this);
                mSpinName = (Spinner) v.findViewById(R.id.spin_operator_name);
                mSpinName.setOnItemSelectedListener(this);
                ArrayAdapter<String> aA = new ArrayAdapter<>(getBaseContext(),
                        android.R.layout.simple_spinner_item,
                        OperatorListData.getOperatorNameList(true));
                aA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinName.setAdapter(aA);
                mProgressContainer = (LinearLayout) v.findViewById(R.id.progress_container);
                mProgressContainer.setVisibility(View.VISIBLE);
                mNoFoundContainer = (LinearLayout) v.findViewById(R.id.no_found_container);
                mListView = (ListView) v.findViewById(R.id.print_list);
                mListView.setAdapter(mListAdapter = new OperatorAdapter(getBaseContext()));
                updateListByName();
            }
        }

        void updateListByName() {
            if (mSpinName != null)
                updateOperatorList(mSpinName.getSelectedItem().toString());
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent != null)
                updateOperatorList(parent.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

        public void updateOperatorList(String name) {
            if (mListAdapter != null)
                mListAdapter.clear();
            ArrayList<Operator> v = OperatorListData.getListData(), res = new ArrayList<>();
            if (!"all".equalsIgnoreCase(name))
                v = OperatorListData.getByOperatorName(v, name);
            for (int i = 0; i < v.size(); i++) {
                Operator ope = v.get(i);
                if (ope != null) {
                    if (mDateFrom != null && mDateTo != null) {
                        if (Util.isWithinDates(mDateFrom, mDateTo, ope.getLastLogin()))
                            res.add(ope);
                    }
                    else {
                        res.add(ope);
                    }
                }
            }
            new OperatorListTask(mListAdapter, mListView, mProgressContainer,
                    mNoFoundContainer).execute(res);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof Button) {
                mDateToday = Util.getDateToday();
                Button btn = (Button) v;
                if (btn != null) {
                    UI.CustomDatePicker dp;
                    int tag;
                    if (btn.getId() == R.id.btn_from) {
                        if (mDateFrom == null && mDateTo == null) {
                            dp = UI.createCustomDatePickerWithMaxDate(mDateToday,
                                    Util.getDateInMillis(mDateToday));
                        }
                        else if (mDateFrom != null && mDateTo == null) {
                            dp = UI.createCustomDatePickerWithMaxDate(mDateFrom,
                                    Util.getDateInMillis(mDateToday));
                        }
                        else if (mDateFrom == null && mDateTo != null) {
                            dp = UI.createCustomDatePickerWithMaxDate(mDateToday,
                                    Util.getDateInMillis(mDateTo));
                        }
                        else {
                            dp = UI.createCustomDatePickerWithMaxDate(mDateFrom,
                                    Util.getDateInMillis(mDateTo));
                        }
                        tag = UI.CustomDatePicker.DATE_FROM;
                    }
                    else {
                        if (mDateTo == null && mDateFrom == null){
                            dp = UI.createCustomDatePickerWithMaxDate(mDateToday,
                                    Util.getDateInMillis(mDateToday));
                        }
                        else if (mDateTo != null && mDateFrom == null) {
                            dp = UI.createCustomDatePickerWithMaxDate(mDateTo,
                                    Util.getDateInMillis(mDateToday));
                        }
                        else if (mDateTo == null && mDateFrom != null) {
                            dp = UI.createCustomDatePickerWithMinMaxDate(mDateToday,
                                    Util.getDateInMillis(mDateFrom),
                                    Util.getDateInMillis(mDateToday));
                        }
                        else {
                            dp = UI.createCustomDatePickerWithMinMaxDate(mDateTo,
                                    Util.getDateInMillis(mDateFrom),
                                    Util.getDateInMillis(mDateToday));
                        }
                        tag = UI.CustomDatePicker.DATE_TO;
                    }
                    if(dp != null) {
                        dp.setDatePickerListener(this);
                        dp.show(getSupportFragmentManager(), tag);
                    }
                }
            }
        }

        public void setDateFromValue(Util.Date date) {
            mDateFrom = date;
            if (mButtonFrom != null && mDateFrom != null)
                mButtonFrom.setText(mDateFrom.toMMDDYYYYStringFormat("/"));
            updateListByName();
        }

        public void setDateToValue(Util.Date date) {
            mDateTo = date;
            if (mButtonTo != null && mDateTo != null)
                mButtonTo.setText(mDateTo.toMMDDYYYYStringFormat("/"));
            updateListByName();
        }

        @Override
        public void onDateSelected(int tag, Util.Date date) {
            if (tag == UI.CustomDatePicker.DATE_FROM) {
                setDateFromValue(date);
                return;
            }
            setDateToValue(date);
        }
    }

    class OperatorInfoTab extends LinearLayout {

        OperatorInfoTab(Context context) {
            super(context);
            initialize();
        }

        void initialize() {
            setBackgroundColor(Color.WHITE);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.operator_info, null));
        }

        public void updateOperatorInfo(OperatorInfo info) {
            if (info != null) {
                CircleImageView imgPic = (CircleImageView) findViewById(R.id.img_operator);
                if (imgPic != null) {
                    BitmapDrawable v = info.getImage();
                    if (v != null)
                        imgPic.setImageDrawable(v);
                }
                TextView tvName = (TextView) findViewById(R.id.txt_operator_name);
                if (tvName != null)
                    tvName.setText(info.getName());
                TextView tvCode = (TextView) findViewById(R.id.txt_operator_code);
                if (tvCode != null)
                    tvCode.setText(info.getCode());
            }
        }

        public void updateMerchantInfo(MerchantInfo info) {
            if (info != null) {
                TextView tvName = (TextView) findViewById(R.id.txt_merchant_name);
                if (tvName != null)
                    tvName.setText(info.getName());
                TextView tvCode = (TextView) findViewById(R.id.txt_merchant_code);
                if (tvCode != null)
                    tvCode.setText(info.getCode());
                TextView tvCurrency = (TextView) findViewById(R.id.txt_merchant_currency);
                if (tvCurrency != null)
                    tvCurrency.setText(info.getCurrency());
                TextView tvEmail = (TextView) findViewById(R.id.txt_merchant_email);
                if (tvEmail != null)
                    tvEmail.setText(info.getEmail());
            }
        }
    }

    OperatorInfoTab info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.operator_info_label));
        createOperatorListData();
    }

    @Override
    public void onCreateContent(final LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.activity_operator_info, null));
            TabHost host = (TabHost) content.findViewById(android.R.id.tabhost);
            if (host != null) {
                host.setup();
                setupTab(createContent((LinearLayout) content.findViewById(R.id.first_tab),
                        info = new OperatorInfoTab(this)), getString(R.string.operator_information_label), host);
                setupTab(findViewById(R.id.second_tab), getString(R.string.operator_print_trans), host);
                host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                    @Override
                    public void onTabChanged(String tabId) {
                        if (getString(R.string.operator_print_trans).equals(tabId)) {
                            createContent((LinearLayout) content.findViewById(R.id.second_tab),
                                    new PrintTransactionTab(getBaseContext()));
                        }
                    }
                });
                updateOperatorInfoDetails();
            }
        }
    }

    View createContent(LinearLayout parent, View child) {
        if (parent != null) {
            if (parent.getChildCount() > 0)
                parent.removeAllViews();
            parent.addView(child);
            return parent;
        }
        return null;
    }

    void setupTab(final View view, final String tag, TabHost host) {
        if (host != null) {
            TabHost.TabSpec i = host.newTabSpec(tag);
            if (i != null) {
                i.setIndicator(createTabView(host.getContext(), tag));
                i.setContent(new TabHost.TabContentFactory() {
                    public View createTabContent(String tag) {
                        return view;
                    }
                });
                host.addTab(i);
            }
        }
    }

    View createTabView(final Context context, final String text) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        v.setBackgroundResource(R.drawable.tab_blue_background);
        if (v != null) {
            TextView tv = (TextView)v.findViewById(R.id.tab_label);
            if (tv != null)
                tv.setText(text);
        }
        return v;
    }

    void updateOperatorInfoDetails() {
        // FIXME: Static values
        if (info != null) {
            info.updateOperatorInfo(new OperatorInfo(null, "Geromel Yves E. Tayao", "1111"));
            info.updateMerchantInfo(new MerchantInfo("Company", "00000000000", "Philippine Peso",
                    "email@yahoo.com"));
        }
    }

    void createOperatorListData() {
        // FIXME: Dummy Values
        ArrayList<Operator> list = new ArrayList<>();
        list.add(new Operator(new OperatorInfo(null, "Geromel Yves E. Tayao", "1111"),
                Util.getDateTimeToday(), TransactionListData.getLastTransaction()));
        list.add(new Operator(new OperatorInfo(null, "Geromel Yves E. Tayao", "1111"),
                Util.getDateTimeToday(), TransactionListData.getLastTransaction()));
        list.add(new Operator(new OperatorInfo(null, "Geromel Yves E. Tayao", "1111"),
                Util.getDateTimeToday(), TransactionListData.getLastTransaction()));
        list.add(new Operator(new OperatorInfo(null, "Tayao Geromel E. Yves", "1111"),
                Util.getDateTimeToday(), TransactionListData.getLastTransaction()));
        list.add(new Operator(new OperatorInfo(null, "Yves Tayao E. Geromel", "1111"),
                Util.getDateTimeToday(), TransactionListData.getLastTransaction()));
        OperatorListData.updateListData(list);
    }
}

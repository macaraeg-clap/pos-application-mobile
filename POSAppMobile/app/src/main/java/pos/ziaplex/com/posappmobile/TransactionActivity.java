package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jimmy.macaraeg on 06/07/2017.
 */

public class TransactionActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    public class StatisticTab extends LinearLayout implements AdapterView.OnItemSelectedListener,
            View.OnClickListener {

        public StatisticTab(Context context) {
            super(context);
            initialize();
        }

        Util.Date mStatisticFrom, mStatisticTo;

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.statistic_summary, null));
            Spinner v = (Spinner) findViewById(R.id.spin_date_range);
            if (v != null)
                v.setOnItemSelectedListener(this);
            setDefaultDateRange(1);
            Spinner vv = (Spinner) findViewById(R.id.spin_mode);
            if (vv != null)
                vv.setOnItemSelectedListener(this);
            ImageButton balance_link = (ImageButton) findViewById(R.id.img_btn_balance);
            if (balance_link != null)
                balance_link.setOnClickListener(this);
            ImageButton withdrawal_link = (ImageButton) findViewById(R.id.img_btn_withdrawal);
            if (withdrawal_link != null)
                withdrawal_link.setOnClickListener(this);
            ImageButton cash_advance_link = (ImageButton) findViewById(R.id.img_btn_cash_advance);
            if (cash_advance_link != null)
                cash_advance_link.setOnClickListener(this);
            ImageButton payment_link = (ImageButton) findViewById(R.id.img_btn_payment);
            if (payment_link != null)
                payment_link.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof ImageButton) {
                ImageButton link = (ImageButton) v;
                if (link != null) {
                    if (link.getId() == R.id.img_btn_balance) {
                        Toast.makeText(getBaseContext(), "R.id.img_btn_balance",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (link.getId() == R.id.img_btn_withdrawal) {
                        Toast.makeText(getBaseContext(), "R.id.img_btn_withdrawal",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (link.getId() == R.id.img_btn_cash_advance) {
                        Toast.makeText(getBaseContext(), "R.id.img_btn_cash_advance",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (link.getId() == R.id.img_btn_payment) {
                        Toast.makeText(getBaseContext(), "R.id.img_btn_payment",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent instanceof Spinner) {
                TextView vf = (TextView) findViewById(R.id.txt_from_statistic);
                TextView vt = (TextView) findViewById(R.id.txt_to_statistic);
                Spinner v = (Spinner) parent;
                if (v != null) {
                    if (v.getId() == R.id.spin_date_range) {
                        if (vf != null && vt != null) {
                            if ("Last Week".equalsIgnoreCase(parent.getSelectedItem().toString())) {
                                HashMap date = Util.getDateLastWeek();
                                if (date != null) {
                                    vf.setText(getString(R.string.from_label) + " " + ((Util.Date) date.get("from"))
                                            .toMMDDYYYYStringFormat("/"));
                                    vt.setText(getString(R.string.to_label) + " " + ((Util.Date) date.get("to"))
                                            .toMMDDYYYYStringFormat("/"));
                                }
                            }
                            else if ("Last Month".equalsIgnoreCase(parent.getSelectedItem()
                                    .toString())) {
                                HashMap date = Util.getDateLastMonth();
                                if (date != null) {
                                    vf.setText(getString(R.string.from_label) + " " + ((Util.Date) date.get("from"))
                                            .toMMDDYYYYStringFormat("/"));
                                    vt.setText(getString(R.string.to_label) + " " + ((Util.Date) date.get("to"))
                                            .toMMDDYYYYStringFormat("/"));
                                }
                            }
                            else {
                                vf.setText(getString(R.string.from_label) + " " + Util.getDateToday()
                                        .toMMDDYYYYStringFormat("/"));
                                vt.setText(getString(R.string.to_label) + " " + Util.getDateToday()
                                        .toMMDDYYYYStringFormat("/"));
                            }
                        }
                    }
                    else if (v.getId() == R.id.spin_mode) {
                        if (vf != null && vt != null) {
                            updateStatisticDetails(parent.getSelectedItem().toString(), vf, vt);
                        }
                    }
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

        private void updateStatisticDetails(String label, TextView vf, TextView vt) { // FIXME
            String fDate = vf.getText().toString();
            String tDate = vt.getText().toString();
            if ("Balance".equalsIgnoreCase(label)) {
                // TODO
                updateTotalBalanceInquiryCount(25);
            }
            else if ("Cash Advance".equalsIgnoreCase(label)) {
                // TODO
                updateTotalCashAdvanceCount(1);
                updateTotalCashAdvanceAmount("5,000.00");
            }
            else if ("Payment".equalsIgnoreCase(label)) {
                // TODO
                updateTotalPaymentCount(146);
                updateTotalPaymentAmount("52,000.00");
            }
            else if ("Withdrawal".equalsIgnoreCase(label)) {
                // TODO
                updateTotalWithdrawalCount(12);
                updateTotalWithdrawalAmount("10,000.00");
            }
            else {
                // TODO
                updateTotalBalanceInquiryCount(0);
                updateTotalCashAdvanceCount(0);
                updateTotalCashAdvanceAmount("0.00");
                updateTotalPaymentCount(0);
                updateTotalPaymentAmount("0.00");
                updateTotalWithdrawalCount(0);
                updateTotalWithdrawalAmount("0.00");
            }
        }

        public void setDefaultDateRange(int index) {
            Spinner v = (Spinner) findViewById(R.id.spin_date_range);
            if (v != null)
                v.setSelection(index);
        }

        public void updateTotalBalanceInquiryCount(int total) {
            TextView v = (TextView) findViewById(R.id.txt_bal_total);
            if (v != null)
                v.setText(String.valueOf(total));
        }

        public void updateTotalWithdrawalCount(int total) {
            TextView v = (TextView) findViewById(R.id.txt_withdrawal_total);
            if (v != null)
                v.setText(String.valueOf(total));
        }

        public void updateTotalWithdrawalAmount(String amount) {
            TextView v = (TextView) findViewById(R.id.txt_withdrawal_amount);
            if (v != null)
                v.setText(getString(R.string.amount_sign) + " " + amount);
        }

        public void updateTotalCashAdvanceCount(int total) {
            TextView v = (TextView) findViewById(R.id.txt_advance_total);
            if (v != null)
                v.setText(String.valueOf(total));
        }

        public void updateTotalCashAdvanceAmount(String amount) {
            TextView v = (TextView) findViewById(R.id.txt_advance_amount);
            if (v != null)
                v.setText(getString(R.string.amount_sign) + " " + amount);
        }

        public void updateTotalPaymentCount(int total) {
            TextView v = (TextView) findViewById(R.id.txt_payment_total);
            if (v != null)
                v.setText(String.valueOf(total));
        }

        public void updateTotalPaymentAmount(String amount) {
            TextView v = (TextView) findViewById(R.id.txt_payment_amount);
            if (v != null)
                v.setText(getString(R.string.amount_sign) + " " + amount);
        }
    }

    public class HistoryTab extends LinearLayout implements TabHost.OnTabChangeListener {

        public class UntransmittedTab extends TransmittedTab {

            public UntransmittedTab(Context context) {
                super(context);
            }
        }

        public class TransmittedTab extends LinearLayout implements UI.DatePickerCallbackListener,
                View.OnClickListener{

            public TransmittedTab(Context context) {
                super(context);
                mInstance = this;
                initialize();
            }

            TransmittedTab mInstance;
            Button mButtonFrom, mButtonTo;
            Util.Date mDateToday, mDateFrom, mDateTo;

            private void initialize() {
                setBackgroundColor(Color.WHITE);
                setOrientation(LinearLayout.VERTICAL);
                LinearLayout v = (LinearLayout) LayoutInflater.from(getContext())
                        .inflate(R.layout.history_transmitted_list, null);
                addView(v);
                if (v != null) {
                    mButtonFrom = (Button) v.findViewById(R.id.btn_from);
                    if (mButtonFrom != null)
                        mButtonFrom.setOnClickListener(this);
                    mButtonTo = (Button) v.findViewById(R.id.btn_to);
                    if (mButtonTo != null)
                        mButtonTo.setOnClickListener(this);
                }
            }

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    mDateToday = Util.getDateToday();
                    Button btn = (Button) v;
                    if (btn != null) {
                        Util.Date selectedDate;
                        Util.Date selectedMaxDate;
                        UI.CustomDatePicker dp;
                        int tag;
                        if (btn.getId() == R.id.btn_from) {
                            Toast.makeText(getBaseContext(), "mDateFrom", Toast.LENGTH_SHORT).show();
                            if (mDateFrom == null) {
                                Toast.makeText(getBaseContext(), "mDateFrom == null",
                                        Toast.LENGTH_SHORT).show();
                                selectedDate = mDateToday;
                            }
                            else {
                                Toast.makeText(getBaseContext(), "mDateFrom: else",
                                        Toast.LENGTH_SHORT).show();
                                selectedDate = mDateFrom;
                            }
                            if (mDateTo == null) {
                                Toast.makeText(getBaseContext(), "mDateTo == null",
                                        Toast.LENGTH_SHORT).show();
                                selectedMaxDate = Util.Date.forMinusMonth(mDateToday, 1);
                            }
                            else {
                                Toast.makeText(getBaseContext(), "mDateTo: else",
                                        Toast.LENGTH_SHORT).show();
                                selectedMaxDate = mDateTo;
                            }
                            dp = UI.createCustomDatePickerWithMaxDate(selectedDate,
                                    Util.getDateInMillis(selectedMaxDate));
                            tag = UI.CustomDatePicker.DATE_FROM;
                        }
                        else {
                            Toast.makeText(getBaseContext(), "mDateTo", Toast.LENGTH_SHORT).show();
                            if (mDateFrom == null && mDateTo == null) {
                                Toast.makeText(getBaseContext(), "mDateFrom == null && mDateTo == null",
                                        Toast.LENGTH_SHORT).show();
                                selectedDate = mDateToday;
                                selectedMaxDate = Util.Date.forMinusMonth(selectedDate, 1);
                            }
                            else if (mDateFrom == null && mDateTo != null) {
                                Toast.makeText(getBaseContext(), "mDateFrom == null && mDateTo != null",
                                        Toast.LENGTH_SHORT).show();
                                selectedDate = mDateTo;
                                selectedMaxDate = Util.Date.forMinusMonth(mDateToday, 1);
                            }
                            else {
                                Toast.makeText(getBaseContext(), "else",
                                        Toast.LENGTH_SHORT).show();
                                selectedDate = mDateTo;
                                selectedMaxDate = mDateTo;
                            }
                            if (mDateFrom == null) {
                                Toast.makeText(getBaseContext(), "dp: mDateFrom == null",
                                        Toast.LENGTH_SHORT).show();
                                dp = UI.createCustomDatePickerWithMaxDate(selectedDate,
                                        Util.getDateInMillis(selectedMaxDate));
                            }
                            else {
                                Toast.makeText(getBaseContext(), "dp: else",
                                        Toast.LENGTH_SHORT).show();
                                dp = UI.createCustomDatePickerWithMinMaxDate(selectedDate,
                                        Util.getDateInMillis(mDateFrom),
                                        Util.getDateInMillis(selectedMaxDate));
                            }
                            tag = UI.CustomDatePicker.DATE_TO;
                        }
                        if(dp != null) {
                            dp.setDatePickerListener(mInstance);
                            dp.show(getSupportFragmentManager(), tag);
                        }
                    }
                }
            }

            public void onDateSelected(int tag, DatePicker view, int year, int month, int day) {
                if (tag == UI.CustomDatePicker.DATE_FROM) {
                    mDateFrom = Util.Date.instance(String.valueOf(day), String.valueOf(month),
                            String.valueOf(year));
                    if (mButtonFrom != null)
                        mButtonFrom.setText(Util.Date.instance(String.valueOf(day),
                                String.valueOf(month + 1),
                                String.valueOf(year)).toMMDDYYYYStringFormat("/"));
                }
                else if (tag == UI.CustomDatePicker.DATE_TO) {
                    mDateTo = Util.Date.instance(String.valueOf(day), String.valueOf(month),
                            String.valueOf(year));
                    if (mButtonTo != null)
                        mButtonTo.setText(Util.Date.instance(String.valueOf(day),
                                String.valueOf(month + 1),
                                String.valueOf(year)).toMMDDYYYYStringFormat("/"));
                }
            }
        }

        public HistoryTab(Context context) {
            super(context);
            initialize();
        }

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.history_list, null));
            TabHost host = (TabHost) findViewById(android.R.id.tabhost);
            if (host != null) {
                host.setup();
                setupTab(createContent((LinearLayout) findViewById(R.id.first_tab),
                        new TransmittedTab(getBaseContext())), getString(R.string.trans_transmitted_label), host);
                setupTab(findViewById(R.id.second_tab), getString(R.string.trans_untransmitted_label), host);
                host.setOnTabChangedListener(this);
            }
        }

        @Override
        public void onTabChanged(String tabId) {
            if (getString(R.string.trans_untransmitted_label).equals(tabId)) {
                createContent((LinearLayout) findViewById(R.id.second_tab),
                        new UntransmittedTab(getBaseContext()));
            }
        }

        private View createContent(LinearLayout parent, View child) {
            if (parent != null) {
                if (parent.getChildCount() < 1)
                    parent.addView(child);
                return parent;
            }
            return null;
        }

        private void setupTab(final View view, final String tag, TabHost host) {
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

        private View createTabView(final Context context, final String text) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
            v.setBackgroundResource(R.drawable.tab_blue_background);
            if (v != null) {
                TextView tv = (TextView)v.findViewById(R.id.tab_label);
                if (tv != null)
                    tv.setText(text);
            }
            return v;
        }
    }

    public class DailyTab extends LinearLayout implements AdapterView.OnItemSelectedListener {

        TransactionAdapter mListAdapter;
        LinearLayout mProgressContainer;
        ListView mListView;

        public DailyTab(Context context) {
            super(context);
            initialize();
        }

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.daily_list, null));
            mProgressContainer = (LinearLayout) findViewById(R.id.progress_container);
            mProgressContainer.setVisibility(View.VISIBLE);
            mListView = (ListView) findViewById(R.id.daily_list);
            mListView.setAdapter(mListAdapter = new TransactionAdapter(getBaseContext()));
            updateDateDisplay();
            Spinner v = (Spinner) findViewById(R.id.spin_mode);
            if (v != null) {
                v.setOnItemSelectedListener(this);
                updateTransactionList(v.getSelectedItem().toString());
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (parent != null)
                updateTransactionList(parent.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

        public void updateTransactionList(String mode) {
            if (mListAdapter != null)
                mListAdapter.clear();
            // FIXME: Dummy data
            ArrayList<Transaction> res_list = new ArrayList<>();
            ArrayList<Transaction> temp_list = new ArrayList<>();
            temp_list.add(new Transaction("offline", "100.00", "123456789", "Cash",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Payment"));
            temp_list.add(new Transaction("offline", "200.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Payment"));
            temp_list.add(new Transaction("approved", "0.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Balance"));
            temp_list.add(new Transaction("approved", "0.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Withdrawal"));
            temp_list.add(new Transaction("approved", "1,500.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Cash Advance"));
            temp_list.add(new Transaction("offline", "100.00", "123456789", "Cash",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Payment"));
            temp_list.add(new Transaction("offline", "200.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Payment"));
            temp_list.add(new Transaction("approved", "0.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Balance"));
            temp_list.add(new Transaction("approved", "0.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Withdrawal"));
            temp_list.add(new Transaction("approved", "1,500.00", "123456789", "xxxx xxxx xxxx 0000",
                    Util.getDateTime(new java.util.Date()).getTime(), "Yves Tayao", "Cash Advance"));
            if("all".equalsIgnoreCase(mode)) {
                res_list = temp_list;
            }
            else {
                for (int i = 0; i < temp_list.size(); i++) {
                    Transaction trans = temp_list.get(i);
                    if (trans != null) {
                        if (trans.getTransType().equalsIgnoreCase(mode))
                            res_list.add(trans);
                    }
                }
            }
            TransactionListTask task = new TransactionListTask(mListAdapter, mListView,
                    mProgressContainer);
            if (task != null)
                task.execute(res_list);
        }

        public void updateDateDisplay() {
            TextView v = (TextView) findViewById(R.id.txt_date_today);
            if (v != null)
                v.setText(Util.getDateToday().toString());
        }
    }

    public class LastTransactionTab extends ScrollView {

        public LastTransactionTab(Context context) {
            super(context);
            initialize();
        }

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            LinearLayout p = UI.createLinerLayout(getContext(), LinearLayout.VERTICAL);
            int plr = (int) getResources().getDimension(R.dimen._20sdp);
            p.setPadding(plr, (int) getResources().getDimension(R.dimen._10sdp), plr, 0);
            LinearLayout card = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.last_transaction_card, null);
            if (card != null)
                p.addView(card);
            LinearLayout details = (LinearLayout) LayoutInflater.from(getContext())
                    .inflate(R.layout.last_transaction_summary, null);
            if (details != null)
                p.addView(details);
            addView(p);
        }

        public void updateDetails(Transaction trans) {
            if (trans != null) {
                String status = trans.getStatus();
                String type = trans.getTransType();
                String mode = trans.getMode();
                LinearLayout bgCard = (LinearLayout) findViewById(R.id.bg_status);
                TextView txtStatus = (TextView) findViewById(R.id.txt_status);
                if ("offline".equalsIgnoreCase(status)) {
                    if (bgCard != null)
                        bgCard.setBackgroundResource(R.drawable.bg_offline);
                    if (txtStatus != null)
                        txtStatus.setTextColor(ContextCompat.getColor(getBaseContext(),
                                R.color.colorRed));
                }
                else {
                    if (bgCard != null)
                        bgCard.setBackgroundResource(R.drawable.bg_online);
                    if (txtStatus != null)
                        txtStatus.setTextColor(ContextCompat.getColor(getBaseContext(),
                                R.color.colorGreen));
                }
                if (txtStatus != null)
                    txtStatus.setText(status);
                TextView txtAmount = (TextView) findViewById(R.id.txt_amount);
                if (txtAmount != null)
                    txtAmount.setText(getString(R.string.amount_sign) + " " + trans.getAmount());
                TextView txtAccountNo = (TextView) findViewById(R.id.txt_account_no);
                if ("Cash".equalsIgnoreCase(mode)) {
                    if (txtAccountNo != null)
                        txtAccountNo.setVisibility(INVISIBLE);
                    LinearLayout cardHolder = (LinearLayout) findViewById(R.id.card_holder_details);
                    if (cardHolder != null)
                        cardHolder.setVisibility(INVISIBLE);
                }
                else {
                    if (txtAccountNo != null)
                        txtAccountNo.setText(trans.getAccountNumber());
                    TextView txtExpiryDate = (TextView) findViewById(R.id.txt_expiry_date);
                    if (txtExpiryDate != null)
                        txtExpiryDate.setText(trans.getExpiryDate());
                }
                TextView txtDateTime = (TextView) findViewById(R.id.txt_date_time);
                if (txtDateTime != null)
                    txtDateTime.setText(trans.getDate());
                TextView txtMethod = (TextView) findViewById(R.id.txt_method);
                if (txtMethod != null)
                    txtMethod.setText(trans.getMethod());
                TextView txtMode = (TextView) findViewById(R.id.txt_mode);
                if (txtMode != null)
                    txtMode.setText(mode);
                TextView txtTransType = (TextView) findViewById(R.id.txt_type);
                if (txtTransType != null)
                    txtTransType.setText(type);
                TextView txtTransReference = (TextView) findViewById(R.id.txt_reference);
                if (txtTransReference != null)
                    txtTransReference.setText(trans.getTransReference().toString());
                if ("payment".equalsIgnoreCase(type)) {
                    TextView txtPaymentFor = (TextView) findViewById(R.id.txt_payment_for);
                    if (txtPaymentFor != null)
                        txtPaymentFor.setText(trans.getPaymentFor().toString());
                }
                else {
                    LinearLayout v = (LinearLayout) findViewById(R.id.pay_for_details);
                    if (v != null)
                        v.setVisibility(INVISIBLE);
                }
                TextView txtOperatorName = (TextView) findViewById(R.id.txt_operator_name);
                if (txtOperatorName != null)
                    txtOperatorName.setText(trans.getOperatorName().toString());
                TextView txtOperatorNumber = (TextView) findViewById(R.id.txt_operator_number);
                if (txtOperatorNumber != null)
                    txtOperatorNumber.setText(trans.getmOperatorNumber().toString());
            }
        }

        public void updateQRCode(Drawable img) {
            ImageView v = (ImageView) findViewById(R.id.img_offline_validation);
            if (v != null && img != null)
                v.setImageDrawable(img);
        }
    }

    public TransactionActivity() {
        mInstance = this;
    }

    public static TransactionActivity mInstance = null;
    static int LAST = 0;
    static int DAILY = 1;
    static int HISTORY = 2;
    static int STATISTICS = 3;
    int mSelectedTab = 0;
    LastTransactionTab mLast;

    @Override
    public void onResume() {
        super.onResume();
        mInstance = this;
    }

    @Override
    public void onPause() {
        super.onPause();
        mInstance = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.transaction_label));
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            final LinearLayout v = (LinearLayout) LayoutInflater.from(this)
                    .inflate(R.layout.activity_transaction, null);
            if (v != null) {
                TabHost host = (TabHost) v.findViewById(android.R.id.tabhost);
                if (host != null) {
                    host.setup();
                    setupTab(createContent((LinearLayout) v.findViewById(R.id.first_tab),
                            mLast = new LastTransactionTab(this)), getString(R.string.trans_last_label), host);
                    setupTab(v.findViewById(R.id.second_tab), getString(R.string.trans_daily_label), host);
                    setupTab(v.findViewById(R.id.third_tab), getString(R.string.trans_history_label), host);
                    setupTab(v.findViewById(R.id.fourth_tab), getString(R.string.trans_statistics_label), host);
                    host.setOnTabChangedListener(this);
                }
                content.addView(v);
            }
            updateLastTransactionDetails();
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        if (getString(R.string.trans_daily_label).equals(tabId)) {
            mSelectedTab = DAILY;
            createContent((LinearLayout) findViewById(R.id.second_tab),
                    new DailyTab(getBaseContext()));
        }
        else if (getString(R.string.trans_history_label).equals(tabId)) {
            mSelectedTab = HISTORY;
            createContent((LinearLayout) findViewById(R.id.third_tab),
                    new HistoryTab(getBaseContext()));
        }
        else if (getString(R.string.trans_statistics_label).equals(tabId)) {
            mSelectedTab = STATISTICS;
            createContent((LinearLayout) findViewById(R.id.fourth_tab),
                    new StatisticTab(getBaseContext()));
        }
        else {
            mSelectedTab = LAST;
        }
        if (TransactionActivity.mInstance != null)
            TransactionActivity.mInstance.invalidateOptionsMenu();
    }

    public void updateLastTransactionDetails() {
        // FIXME: change static values
        if (mLast != null) {
            mLast.updateDetails(new Transaction("APPROVED", "1,000.00", "xxxx xxxx xxxx 0000",
                    Util.getDateMonthYear("MM", "YY").toMonthYearStringFormat("/"),
                    Util.getDateToday().toMMDDYYYYStringFormat("/") + " @ " +
                            Util.getDateTime().getTime(),
                    "Contact", "Debit Card - RCBC", "Withdrawal", "123456789",
                    "BIR", "Juan Dela Cruz", "11111111"));
            mLast.updateQRCode(null);
        }
    }

    private View createContent(LinearLayout parent, View child) {
        if (parent != null) {
            if (parent.getChildCount() > 0)
                parent.removeAllViews();
            parent.addView(child);
            return parent;
        }
        return null;
    }

    private void setupTab(final View view, final String tag, TabHost host) {
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

    private View createTabView(final Context context, final String text) {
        View v = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        v.setBackgroundResource(R.drawable.tab_violet_background);
        if (v != null) {
            TextView tv = (TextView)v.findViewById(R.id.tab_label);
            if (tv != null)
                tv.setText(text);
        }
        return v;
    }

    private void showPrintDialogPopup() {
        String title = null;
        String message = null;
        if (mSelectedTab == DAILY) {
            title = getString(R.string.trans_daily_title);
            message = getString(R.string.trans_daily_message);
        }
        else if (mSelectedTab == HISTORY) {
            title = getString(R.string.trans_history_title);
            message = getString(R.string.trans_history_message);
        }
        else if (mSelectedTab == STATISTICS) {
            // TODO:
        }
        final UI.CustomDialogPopup dialog = UI.createDialogPopup(this, title);
        LinearLayout p = (LinearLayout) LayoutInflater.from(this)
                .inflate(R.layout.custom_icon_with_text, null);
        if (p != null) {
            ImageView iv = (ImageView) p.findViewById(R.id.img_icon);
            if (iv != null)
                iv.setImageResource(R.drawable.ic_printer);
            TextView tv = (TextView) p.findViewById(R.id.txt_text);
            if (tv != null)
                tv.setText(message);
        }
        dialog.setDialogContent(p);
        LinearLayout v = UI.createLinerLayout(this, LinearLayout.HORIZONTAL);
        v.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Button btnPos = UI.createCustomButton(this, getString(R.string.action_print_label));
        btnPos.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
        btnPos.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        btnPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add print functionality...
            }
        });
        v.addView(btnPos);
        Button btnNeg = UI.createCustomButton(this, getString(R.string.action_cancel_label));
        btnNeg.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
        btnNeg.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        btnNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        v.addView(btnNeg);
        dialog.setDialogButton(v);
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_print:
                showPrintDialogPopup();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (menu != null) {
            if (mSelectedTab == LAST) {
                menu.clear();
                inflater.inflate(R.menu.menu_base, menu);
            }
            else {
                menu.clear();
                inflater.inflate(R.menu.menu_transaction, menu);
            }
        }
        return true;
    }
}

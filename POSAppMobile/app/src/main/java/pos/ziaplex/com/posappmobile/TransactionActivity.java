package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jimmy.macaraeg on 06/07/2017.
 */

public class TransactionActivity extends BaseActivity implements TabHost.OnTabChangeListener {

    public class StatisticTab extends LinearLayout implements AdapterView.OnItemSelectedListener,
            UI.ImageLinkCallbackListener {

        public StatisticTab(Context context) {
            super(context);
            initialize();
        }

        Util.Date mStatisticFrom, mStatisticTo;
        UI.CustomImageLink balance_link, withdrawal_link, cash_advance_link, payment_link;
        Spinner mSpinnerMode;

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.statistic_summary, null));
            Spinner v = (Spinner) findViewById(R.id.spin_date_range);
            if (v != null)
                v.setOnItemSelectedListener(this);
            setDateRangeValue(1);
            mSpinnerMode = (Spinner) findViewById(R.id.spin_mode);
            if (mSpinnerMode != null)
                mSpinnerMode.setOnItemSelectedListener(this);
            LinearLayout tContent = (LinearLayout) findViewById(R.id.top_content);
            if (tContent != null) {
                tContent.addView(UI.createCustomVerticalSeparator(getContext()));
                balance_link = UI.createCustomImageLink(getContext(),
                        R.drawable.bg_statistics_balance,
                        getString(R.string.trans_statistics_balance), false, this);
                balance_link.setLayoutParams(UI.getLinearLayoutParams(0, LayoutParams.MATCH_PARENT,
                        1));
                tContent.addView(balance_link);
                tContent.addView(UI.createCustomVerticalSeparator(getContext()));
                withdrawal_link = UI.createCustomImageLink(getContext(),
                        R.drawable.bg_statistics_withdrawal,
                        getString(R.string.trans_statistics_withdrawal), true, this);
                withdrawal_link.setLayoutParams(UI.getLinearLayoutParams(0, LayoutParams.MATCH_PARENT,
                        1));
                tContent.addView(withdrawal_link);
                tContent.addView(UI.createCustomVerticalSeparator(getContext()));
            }
            LinearLayout bContent = (LinearLayout) findViewById(R.id.bottom_content);
            if (bContent != null) {
                bContent.addView(UI.createCustomVerticalSeparator(getContext()));
                cash_advance_link = UI.createCustomImageLink(getContext(),
                        R.drawable.bg_statistics_cash_advance,
                        getString(R.string.trans_statistics_cash_advance), true, this);
                cash_advance_link.setLayoutParams(UI.getLinearLayoutParams(0, LayoutParams.MATCH_PARENT,
                        1));
                bContent.addView(cash_advance_link);
                bContent.addView(UI.createCustomVerticalSeparator(getContext()));
                payment_link = UI.createCustomImageLink(getContext(),
                        R.drawable.bg_statistics_payment,
                        getString(R.string.trans_statistics_payment), true, this);
                payment_link.setLayoutParams(UI.getLinearLayoutParams(0, LayoutParams.MATCH_PARENT,
                        1));
                bContent.addView(payment_link);
                bContent.addView(UI.createCustomVerticalSeparator(getContext()));
            }
        }

        @Override
        public void onItemClicked(UI.CustomImageLink link) {
            if (link != null) {
                TabHost host = TransactionActivity.mTabHost;
                if (host != null) {
                    host.setCurrentTab(2);
                    HistoryTab tab = TransactionActivity.mHistoryTab;
                    if (tab != null) {
                        HistoryTab.TransmittedTab v = tab.mTransmittedTab;
                        if (v != null) {
                            v.setModeValue(link.getLabel());
                            v.setDateFromValue(mStatisticFrom);
                            v.setDateToValue(mStatisticTo);
                        }
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
                                    mStatisticFrom = (Util.Date) date.get("from");
                                    if (mStatisticFrom != null)
                                        vf.setText(getString(R.string.from_label) + " " +
                                                (mStatisticFrom).toMMDDYYYYStringFormat("/"));
                                    mStatisticTo = (Util.Date) date.get("to");
                                    if (mStatisticTo != null)
                                        vt.setText(getString(R.string.to_label) + " " +
                                                (mStatisticTo).toMMDDYYYYStringFormat("/"));
                                }
                            }
                            else if ("Last Month".equalsIgnoreCase(parent.getSelectedItem()
                                    .toString())) {
                                HashMap date = Util.getDateLastMonth();
                                if (date != null) {
                                    mStatisticFrom = (Util.Date) date.get("from");
                                    if (mStatisticFrom != null)
                                        vf.setText(getString(R.string.from_label) + " " +
                                                (mStatisticFrom).toMMDDYYYYStringFormat("/"));
                                    mStatisticTo = (Util.Date) date.get("to");
                                    if (mStatisticTo != null)
                                        vt.setText(getString(R.string.to_label) + " " +
                                                (mStatisticTo).toMMDDYYYYStringFormat("/"));
                                }
                            }
                            else {
                                mStatisticFrom = Util.getDateToday();
                                mStatisticTo = Util.getDateToday();
                                if (mStatisticFrom != null && mStatisticTo != null) {
                                    vf.setText(getString(R.string.from_label) + " " +
                                            mStatisticFrom.toMMDDYYYYStringFormat("/"));
                                    vt.setText(getString(R.string.to_label) + " " +
                                            mStatisticTo.toMMDDYYYYStringFormat("/"));
                                }
                            }
                        }
                    }
                    updateStatisticResult();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }

        public void setDateRangeValue(int index) {
            Spinner v = (Spinner) findViewById(R.id.spin_date_range);
            if (v != null)
                v.setSelection(index);
        }

        public void updateStatisticResult() {
            ArrayList<Transaction> v = TransactionListData.getListData(), res = new ArrayList<>();
            for (int i = 0; i < v.size(); i++) {
                Transaction trans = v.get(i);
                if (trans != null) {
                    if (Util.isWithinDates(mStatisticFrom, mStatisticTo, trans.getDateTime()))
                        res.add(trans);
                }
            }
            String type = "all";
            if (mSpinnerMode != null)
                type = mSpinnerMode.getSelectedItem().toString();
            if ("balance".equalsIgnoreCase(type)) {
                updateBalanceInquiryStatisticDetails(res);
            }
            else if ("cash advance".equalsIgnoreCase(type)) {
                updateCashAdvanceStatisticDetails(res);
            }
            else if ("payment".equalsIgnoreCase(type)) {
                updatePaymentStatisticDetails(res);
            }
            else if ("withdrawal".equalsIgnoreCase(type)) {
                updateWithdrawalStatisticDetails(res);
            }
            else {
                updateBalanceInquiryStatisticDetails(res);
                updateWithdrawalStatisticDetails(res);
                updateCashAdvanceStatisticDetails(res);
                updatePaymentStatisticDetails(res);
            }
        }

        private void updateBalanceInquiryStatisticDetails(ArrayList<Transaction> data) {
            if (balance_link != null && data != null) {
                ArrayList<Transaction> v = TransactionListData
                        .getByTransactionType(data, "balance");
                if (v != null)
                    balance_link.setTotal(v.size());
            }
        }

        private void updateWithdrawalStatisticDetails(ArrayList<Transaction> data) {
            if (withdrawal_link != null && data != null) {
                ArrayList<Transaction> v = TransactionListData
                        .getByTransactionType(data, "withdrawal");
                if (v != null) {
                    withdrawal_link.setTotal(v.size());
                    withdrawal_link.setAmount(TransactionListData.getTotalAmount(v));
                }
            }
        }

        private void updateCashAdvanceStatisticDetails(ArrayList<Transaction> data) {
            if (cash_advance_link != null && data != null) {
                ArrayList<Transaction> v = TransactionListData
                        .getByTransactionType(data, "cash advance");
                if (v != null) {
                    cash_advance_link.setTotal(v.size());
                    cash_advance_link.setAmount(TransactionListData.getTotalAmount(v));
                }
            }
        }

        private void updatePaymentStatisticDetails(ArrayList<Transaction> data) {
            if (payment_link != null && data != null) {
                ArrayList<Transaction> v = TransactionListData
                        .getByTransactionType(data, "payment");
                if (v != null) {
                    payment_link.setTotal(v.size());
                    payment_link.setAmount(TransactionListData.getTotalAmount(v));
                }
            }
        }
    }

    public class HistoryTab extends LinearLayout implements TabHost.OnTabChangeListener {

        public class UntransmittedTab extends TransmittedTab {

            public UntransmittedTab(Context context) {
                super(context);
            }
        }

        public class TransmittedTab extends LinearLayout implements UI.DatePickerCallbackListener,
                View.OnClickListener {

            public TransmittedTab(Context context) {
                super(context);
                mInstance = this;
                initialize();
            }

            TransmittedTab mInstance;
            Button mButtonFrom, mButtonTo;
            Util.Date mDateToday = null, mDateFrom = null, mDateTo = null;

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

            public void setDateFromValue(Util.Date date) {
                mDateFrom = date;
                if (mButtonFrom != null)
                    mButtonFrom.setText(mDateFrom.toMMDDYYYYStringFormat("/"));
            }

            public void setDateToValue(Util.Date date) {
                mDateTo = date;
                if (mButtonTo != null)
                    mButtonTo.setText(mDateTo.toMMDDYYYYStringFormat("/"));
            }

            public void setModeValue(String label) {
                Spinner v = (Spinner) findViewById(R.id.spin_mode);
                if (v != null) {
                    for(int i = 0; i < v.getCount(); i++){
                        String value = v.getItemAtPosition(i).toString();
                        if (label.contains(value)) {
                            v.setSelection(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onClick(View v) {
                if (v instanceof Button) {
                    mDateToday = Util.getDateToday();
                    Button btn = (Button) v;
                    if (btn != null) {
                        UI.CustomDatePicker dp = null;
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
                            dp.setDatePickerListener(mInstance);
                            dp.show(getSupportFragmentManager(), tag);
                        }
                    }
                }
            }

            @Override
            public void onDateSelected(int tag, Util.Date date) {
                if (tag == UI.CustomDatePicker.DATE_FROM) {
                    setDateFromValue(date);
                }
                else {
                    setDateToValue(date);
                }
            }
        }

        public HistoryTab(Context context) {
            super(context);
            initialize();
        }

        public TransmittedTab mTransmittedTab;

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            setOrientation(LinearLayout.VERTICAL);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.history_list, null));
            TabHost host = (TabHost) findViewById(android.R.id.tabhost);
            if (host != null) {
                host.setup();
                setupTab(createContent((LinearLayout) findViewById(R.id.first_tab),
                        mTransmittedTab = new TransmittedTab(getBaseContext())), getString(R.string.trans_transmitted_label), host);
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
            if (v != null) {
                v.setBackgroundResource(R.drawable.tab_blue_background);
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
            ArrayList<Transaction> v = TransactionListData.getListData(), res = new ArrayList<>();
            if (!"all".equalsIgnoreCase(mode))
                v = TransactionListData.getByTransactionType(mode);
            for (int i = 0; i < v.size(); i++) {
                Transaction trans = v.get(i);
                if (trans != null) {
                    if (Util.getDateToday().toString().equals(trans.getDateTime().toString()))
                        res.add(trans);
                }
            }
            new TransactionListTask(mListAdapter, mListView, mProgressContainer).execute(res);
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
            p.addView(LayoutInflater.from(getContext())
                    .inflate(R.layout.last_transaction_card, null));
            p.addView(LayoutInflater.from(getContext())
                        .inflate(R.layout.last_transaction_summary, null));
            addView(p);
            updateDetails();
        }

        public void updateDetails() {
            Transaction trans = TransactionListData.getLastTransaction();
            if (trans != null) {
                String status = trans.getStatus();
                String type = trans.getTransactionType();
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
                    txtStatus.setText(status.toUpperCase());
                TextView txtAmount = (TextView) findViewById(R.id.txt_amount);
                if (txtAmount != null)
                    txtAmount.setText(Util.convertToCurrency(getString(R.string.amount_sign),
                            trans.getAmount()));
                TextView txtAccountNo = (TextView) findViewById(R.id.txt_account_no);
                if ("cash".equalsIgnoreCase(mode)) {
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
                    if (txtExpiryDate != null) {
                        Util.Date d = trans.getExpiryDate();
                        if (d != null)
                            txtExpiryDate.setText(d.toMonthYearStringFormat("/"));
                    }
                }
                TextView txtDateTime = (TextView) findViewById(R.id.txt_date_time);
                if (txtDateTime != null) {
                    Util.Date d = trans.getDateTime();
                    if (d != null)
                        txtDateTime.setText(d.toMMDDYYYYStringFormat("/") + " @ " + d.getTime());
                }
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
                    txtTransReference.setText(trans.getTransactionReference());
                if ("payment".equalsIgnoreCase(type)) {
                    TextView txtPaymentFor = (TextView) findViewById(R.id.txt_payment_for);
                    if (txtPaymentFor != null)
                        txtPaymentFor.setText(trans.getPaymentFor());
                }
                else {
                    LinearLayout v = (LinearLayout) findViewById(R.id.pay_for_details);
                    if (v != null)
                        v.setVisibility(INVISIBLE);
                }
                TextView txtOperatorName = (TextView) findViewById(R.id.txt_operator_name);
                if (txtOperatorName != null)
                    txtOperatorName.setText(trans.getOperatorName());
                TextView txtOperatorNumber = (TextView) findViewById(R.id.txt_operator_number);
                if (txtOperatorNumber != null)
                    txtOperatorNumber.setText(trans.getOperatorNumber());
                ImageView v = (ImageView) findViewById(R.id.img_offline_validation);
                if (v != null) {
                    BitmapDrawable img = trans.getOfflineValidation();
                    if (img != null)
                        v.setImageDrawable(img);
                }
            }
        }
    }

    public TransactionActivity() {
        mInstance = this;
    }

    public static TransactionActivity mInstance = null;
    public static TabHost mTabHost;
    public static HistoryTab mHistoryTab;
    final static int LAST = 0, DAILY = 1, HISTORY = 2, STATISTICS = 3;
    int mSelectedTab = 0;

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
                mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
                if (mTabHost != null) {
                    mTabHost.setup();
                    setupTab(createContent((LinearLayout) v.findViewById(R.id.first_tab),
                            new LastTransactionTab(this)),
                            getString(R.string.trans_last_label), mTabHost);
                    setupTab(v.findViewById(R.id.second_tab),
                            getString(R.string.trans_daily_label), mTabHost);
                    setupTab(v.findViewById(R.id.third_tab),
                            getString(R.string.trans_history_label), mTabHost);
                    setupTab(v.findViewById(R.id.fourth_tab),
                            getString(R.string.trans_statistics_label), mTabHost);
                    mTabHost.setOnTabChangedListener(this);
                }
                content.addView(v);
            }
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
                    mHistoryTab = new HistoryTab(getBaseContext()));
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
        String title = null, message = null;
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
        final UI.CustomDialogPopup dialog = UI.createCustomDialogPopup(this, title);
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

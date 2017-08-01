package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ConnectionActivity extends BaseActivity {

    class ConnectionListTask extends AsyncTask<ArrayList<Connection>, Void, ArrayList<Connection>> {

        ConnectionListTask(ConnectionAdapter list_adapter, ListView list_view,
                                   LinearLayout progress_container, LinearLayout no_found_container) {
            mListAdapter = list_adapter;
            mListView = list_view;
            mProgressContainer = progress_container;
            mNoFoundContainer = no_found_container;
        }

        ConnectionAdapter mListAdapter;
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
        protected void onPostExecute(ArrayList<Connection> result) {
            if (mListAdapter != null) {
                mListAdapter.clear();
                int sz = result.size();
                if (sz > 0) {
                    if (mListView != null)
                        mListView.setVisibility(View.VISIBLE);
                    if (mNoFoundContainer != null)
                        mNoFoundContainer.setVisibility(View.GONE);
                    for (int i = 0; i < sz; i++)
                        mListAdapter.addConnection(result.get(i));
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
        protected ArrayList<Connection> doInBackground(ArrayList<Connection>... params) {
            return params[0];
        }
    }

    class ConnectionAdapter extends BaseAdapter {

        Context mContext;
        LayoutInflater mInflater;
        ArrayList<Connection> mDataSource;

        ConnectionAdapter(Context context) {
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
            View rv = mInflater.inflate(R.layout.connection_list_item, parent, false);
            Connection connection = (Connection) getItem(position);
            if (connection != null) {
                TextView tvName = (TextView) rv.findViewById(R.id.txt_device_name);
                if (tvName != null)
                    tvName.setText(getString(R.string.name_label) + " " + connection.getDeviceName());
                TextView tvHardware = (TextView) rv.findViewById(R.id.txt_hardware_no);
                if (tvHardware != null)
                    tvHardware.setText(connection.getHardware());
                TextView tvLastUsed = (TextView) rv.findViewById(R.id.txt_last_used);
                if (tvLastUsed != null)
                    tvLastUsed.setText(getString(R.string.last_used_label) + " " + connection.getLastUsed());
                TextView tvFirmware = (TextView) rv.findViewById(R.id.txt_firmware);
                if (tvFirmware != null)
                    tvFirmware.setText(connection.getFirmware());
            }
            return rv;
        }

        void clear() {
            if (mDataSource != null)
                mDataSource.clear();
            notifyDataSetChanged();
        }

        void addConnection(Connection con) {
            if (mDataSource != null)
                mDataSource.add(con);
            notifyDataSetChanged();
        }
    }

    class Connection {

        Connection(String device_name, String hardware, String last_used, String firmware) {
            mDeviceName = device_name;
            mHardware = hardware;
            mLastUsed = last_used;
            mFirmware = firmware;
        }

        String mDeviceName, mHardware, mLastUsed, mFirmware;

        String getDeviceName() {
            return mDeviceName;
        }

        String getHardware() {
            return mHardware;
        }

        String getLastUsed() {
            return mLastUsed;
        }

        String getFirmware() {
            return mFirmware;
        }
    }

    ListView mListView;
    ConnectionAdapter mListAdapter;
    LinearLayout mProgressContainer, mNoFoundContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.connections_label));
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.activity_connection, null));
            mListView = (ListView) content.findViewById(R.id.connection_list);
            if (mListView != null)
                mListView.setAdapter(mListAdapter = new ConnectionAdapter(getBaseContext()));
            mProgressContainer = (LinearLayout) content.findViewById(R.id.progress_container);
            mProgressContainer.setVisibility(View.VISIBLE);
            mNoFoundContainer = (LinearLayout) content.findViewById(R.id.no_found_container);
            new ConnectionListTask(mListAdapter, mListView, mProgressContainer, mNoFoundContainer)
                    .execute(createConnectionList());
        }
    }

    ArrayList<Connection> createConnectionList() {
        ArrayList<Connection> l = new ArrayList<>(); // FIXME:
        l.add(new Connection("12345646513", "Hardware 6.0", "24/02/2016", "Firmware 3.42"));
        l.add(new Connection("12345646555", "Hardware 6.0", "24/02/2016", "Firmware 3.42"));
        return l;
    }
}

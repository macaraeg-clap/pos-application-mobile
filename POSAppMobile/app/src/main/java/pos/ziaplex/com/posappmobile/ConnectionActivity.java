package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class ConnectionActivity extends BaseActivity {

    public class ConnectionAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<Connection> mDataSource;

        public ConnectionAdapter(Context context, ArrayList<Connection> items) {
            mContext = context;
            mDataSource = items;
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.connections_label));
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.activity_connection, null));
            ListView v = (ListView) content.findViewById(R.id.connection_list);
            if (v != null)
                v.setAdapter(new ConnectionAdapter(getBaseContext(), createConnectionList()));
        }
    }

    private ArrayList<Connection> createConnectionList() {
        ArrayList<Connection> l = new ArrayList<>();
        l.add(new Connection("12345646513", "Hardware 6.0", "24/02/2016", "Firmware 3.42"));
        l.add(new Connection("12345646555", "Hardware 6.0", "24/02/2016", "Firmware 3.42"));
        return l;
    }
}

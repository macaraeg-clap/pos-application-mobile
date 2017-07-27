package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jimmy.macaraeg on 03/07/2017.
 */

public class BaseActivity extends AppCompatActivity {

    public static Class mHome;
    ActionBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onSetBackgroundImage((ImageView) findViewById(R.id.image_background));
        onCreateContent((LinearLayout) findViewById(R.id.view_content));
        onCreateContent((ScrollView) findViewById(R.id.view_content_scroll));
        onCreateHeader(mBar = getSupportActionBar());
        if (mBar != null) {
            View p = mBar.getCustomView();
            if (p != null)
                onHomeClickedEvent((ImageButton) p.findViewById(R.id.btn_home));
        }
    }

    public void onSetBackgroundImage(ImageView background) {
        if (background != null)
            background.setBackgroundColor(Color.WHITE);
    }

    public void onCreateHeader(ActionBar bar) {
        if (bar != null) {
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            bar.setDisplayShowCustomEnabled(true);
            bar.setCustomView(R.layout.header_base);
            bar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.header_background_green));
        }
    }

    public void onHomeClickedEvent(ImageButton btn) {
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mHome != null) {
                        startActivity(new Intent(v.getContext(), mHome));
                        finish();
                    }
                }
            });
        }
    }

    public void setDefaultTitle(String title) {
        if (mBar != null) {
            View v = mBar.getCustomView();
            if (v != null) {
                TextView tv = (TextView) v.findViewById(R.id.txt_title);
                if (tv != null)
                    tv.setText(title);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.action_log_out:
                final UI.CustomDialogPopup dialog = UI.createCustomDialogPopup(this, getString(R.string.logout_title));
                LinearLayout p = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_icon_with_text, null);
                if (p != null) {
                    ImageView iv = (ImageView) p.findViewById(R.id.img_icon);
                    if (iv != null)
                        iv.setImageResource(R.drawable.ic_question);
                    TextView tv = (TextView) p.findViewById(R.id.txt_text);
                    if (tv != null)
                        tv.setText(getString(R.string.logout_message));
                }
                dialog.setDialogContent(p);
                LinearLayout v = UI.createLinerLayout(this, LinearLayout.HORIZONTAL);
                v.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                Button btnPos = UI.createCustomButton(this, getString(R.string.action_yes));
                btnPos.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
                btnPos.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                btnPos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // FIXME: add logout functionality...
                        startActivity(new Intent(getBaseContext(), LoginActivity.class));
                        finish();
                    }
                });
                v.addView(btnPos);
                Button btnNeg = UI.createCustomButton(this, getString(R.string.action_no));
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
                break;
            default:
                break;
        }
        return true;
    }

    public void onCreateContent(ScrollView content) {
    }

    public void onCreateContent(LinearLayout content) {
    }

    private ArrayList<String> getBluetoothDeviceOptions() {
        // FIXME:
        String[] devices = new String[] { "WP000000000000000000001", "WP000000000000000000002",
                "WP000000000000000000003" };
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < devices.length; ++i) {
            list.add(devices[i]);
        }
        return list;
    }

    public void onDeviceItemSelected(AdapterView<?> parent, final View view, int position, long id) {
    }

    public void showBluetoothDeviceOptions() {
        final UI.CustomDialogPopup dialog = UI.createCustomDialogPopup(this, getString(R.string.select_device_title));
        dialog.setDialogContent(LayoutInflater.from(this).inflate(R.layout.device_list, null));
        ListView v = (ListView) dialog.findViewById(R.id.list_device);
        if (v != null) {
            v.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, getBluetoothDeviceOptions()));
            v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    dialog.dismiss();
                    onDeviceItemSelected(parent, view, position, id);
                }
            });
        }
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
        dialog.setDialogButton(btnNeg);
        dialog.show();
    }
}

package pos.ziaplex.com.posappmobile;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jimmy.macaraeg on 06/07/2017.
 */

public class OperatorInfoActivity extends BaseActivity {

    public class PrintTransactionTab extends LinearLayout {

        public PrintTransactionTab(Context context) {
            super(context);
        }
    }

    public class OperatorInfoTab extends LinearLayout {

        public OperatorInfoTab(Context context) {
            super(context);
            initialize();
        }

        private void initialize() {
            setBackgroundColor(Color.WHITE);
            addView(LayoutInflater.from(getContext()).inflate(R.layout.operator_info, null));
        }

        public void updateOperatorInfo(OperatorInfo info) {
            if (info != null) {
                CircleImageView imgPic = (CircleImageView) findViewById(R.id.img_operator);
                if (imgPic != null) {
                    Image v = info.getImage();
                    if (v != null)
                        imgPic.setImageDrawable(null); // FIXME:
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

    private void updateOperatorInfoDetails() {
        // FIXME: Static values
        if (info != null) {
            info.updateOperatorInfo(new OperatorInfo(null, "Geromel Yves E. Tayao", "1111"));
            info.updateMerchantInfo(new MerchantInfo("Company", "00000000000", "Philippine Peso", "email@yahoo.com"));
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

package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by jimmy.macaraeg on 03/07/2017.
 */

public class LoginActivity extends BaseActivity {

    static int EMPTY = 0;
    static int NOT_MATCHED = 1;

    UI.CustomTextEditWithIcon mCode, oCode, oPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSetBackgroundImage(ImageView background) {
        if (background != null)
            background.setBackgroundResource(R.drawable.bg_login);
    }

    @Override
    public void onCreateHeader(ActionBar bar) {
        if (bar != null) {
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            bar.setDisplayShowCustomEnabled(true);
            bar.setCustomView(R.layout.header_login);
            bar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.header_background_green));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        return true;
    }

    @Override
    public void onCreateContent(ScrollView content) {
        if (content != null) {
            LinearLayout v = UI.createLinerLayout(this, LinearLayout.VERTICAL);
            v.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, 0));
            int p = (int) getResources().getDimension(R.dimen._25sdp);
            int pp = (int) getResources().getDimension(R.dimen._80sdp);
            v.setPadding(p, pp, p, p);
            mCode = UI.createCustomTextEditWithIcon(this, R.drawable.ic_merchant_code, "Merchant Code",
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
                    UI.CustomTextEditWithIcon.ALPHA_NUMERIC, 25);
            int m = (int) getResources().getDimension(R.dimen._15sdp);
            mCode.setMargins(0, 0, 0, m);
            v.addView(mCode);
            oCode = UI.createCustomTextEditWithIcon(this, R.drawable.ic_operator_code, "Operator Code",
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
                    UI.CustomTextEditWithIcon.ALPHA_NUMERIC, 25);
            oCode.setMargins(0, 0, 0, m);
            v.addView(oCode);
            oPin = UI.createCustomTextEditWithIcon(this, R.drawable.ic_operator_pin, "Operator Pin",
                    InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD,
                    UI.CustomTextEditWithIcon.ALPHA_NUMERIC, 25);
            oPin.setMargins(0, 0, 0, m);
            v.addView(oPin);
            Button b = UI.createCustomButton(this, "LOGIN");
            b.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 0,
                    (int) getResources().getDimension(R.dimen._150sdp), 0, 0));
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String merchant_code = mCode.getValue();
                    String operator_code = oCode.getValue();
                    String operator_pin = oPin.getValue();
                    if (merchant_code.isEmpty() || operator_code.isEmpty() || operator_pin.isEmpty()) {
                        showErrorMessage(getString(R.string.login_error_title), EMPTY);
                    }
                    /*else if() {
                        // FIXME: Add login validation
                        showErrorMessage(getString(R.string.login_error_title), NOT_MATCHED);
                    }*/
                    else {
                        // Successful Login Validation
                        clearFields();
                        goToHome();
                    }
                }
            });
            v.addView(b);
            content.addView(v);
        }
    }

    void goToHome() {
        startActivity(new Intent(getBaseContext(), HomeActivity.class));
        finish();
    }

    void showErrorMessage(String title, int error_type) {
        final UI.CustomDialogPopup dialog = UI.createCustomDialogPopup(this, title);
        if (dialog != null) {
            dialog.setDialogTitleBackground(ContextCompat.getColor(this, R.color.colorRed));
            String msg;
            if (error_type == EMPTY) {
                msg = getString(R.string.login_field_empty);
            }
            else {
                msg = getString(R.string.login_not_matched);
            }
            LinearLayout p = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_icon_with_text, null);
            if (p != null) {
                ImageView iv = (ImageView) p.findViewById(R.id.img_icon);
                if (iv != null)
                    iv.setImageResource(R.drawable.ic_error);
                TextView tv = (TextView) p.findViewById(R.id.txt_text);
                if (tv != null)
                    tv.setText(msg);
            }
            dialog.setDialogContent(p);
            Button btnOk = UI.createCustomButton(this, getString(R.string.action_ok));
            btnOk.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
            btnOk.setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setDialogButton(btnOk);
            dialog.show();
        }
    }

    void clearFields() {
        if (mCode != null)
            mCode.clearValue();
        if (oCode != null)
            oCode.clearValue();
        if (oPin != null)
            oPin.clearValue();
    }
}

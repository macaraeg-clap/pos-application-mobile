package pos.ziaplex.com.posappmobile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jimmy.macaraeg on 03/07/2017.
 */

public class UI {

    interface ButtonCallbackListener {

        void onItemClicked(String tag);
    }

    interface DatePickerCallbackListener {

        void onDateSelected(int tag, DatePicker view, int year, int month, int day);
    }

    public static class CustomDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public static int DATE_FROM = 0;
        public static int DATE_TO = 1;
        DatePickerCallbackListener mListener;
        Util.Date mDate = null;
        long mMinDate = -1;
        long mMaxDate = -1;
        int mTag = -1;

        public static CustomDatePicker forMaxDate(Util.Date date, long max_date) {
            CustomDatePicker v = new CustomDatePicker();
            v.mDate = date;
            v.mMaxDate = max_date;
            return v;
        }

        public static CustomDatePicker forMinDate(Util.Date date, long min_date) {
            CustomDatePicker v = new CustomDatePicker();
            v.mDate = date;
            v.mMinDate = min_date;
            return v;
        }

        public static CustomDatePicker instance(Util.Date date, long min_date, long max_date) {
            CustomDatePicker v = new CustomDatePicker();
            v.mDate = date;
            v.mMinDate = min_date;
            v.mMaxDate = max_date;
            return v;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DatePickerDialog v = new DatePickerDialog(getActivity(), this,
                    Integer.valueOf(mDate.getYear()), Integer.valueOf(mDate.getMonth()),
                    Integer.valueOf(mDate.getDay()));
            DatePicker dp = v.getDatePicker();
            if (dp != null) {
                dp.updateDate(Integer.valueOf(mDate.getYear()), Integer.valueOf(mDate.getMonth()),
                        Integer.valueOf(mDate.getDay()));
                if (mMinDate > 0)
                    dp.setMinDate(mMinDate);
                if (mMaxDate > 0)
                    dp.setMaxDate(mMaxDate);
            }
            return v;
        }

        public void setDatePickerListener(DatePickerCallbackListener listener) {
            mListener = listener;
        }

        public void onDateSet(DatePicker v, int y, int m, int d) {
            if (mListener != null)
                mListener.onDateSelected(mTag, v, y, m, d);
        }

        public void show(FragmentManager manager, int tag) {
            if (tag < 0)
                return;
            mTag = tag;
            show(manager, "datePicker");
        }
    }

    public static class CustomTextEditWithIcon extends LinearLayout {

        public static int ALPHA_NUMERIC = 0;

        public CustomTextEditWithIcon(Context context) {
            super(context);
        }

        public void onCreate(int icon_id, String placeholder, int input_type, int data_type, int max_length) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(R.layout.custom_edit_text_with_icon, this, true);
            ImageView iv = (ImageView) findViewById(R.id.cetwi_icon);
            if (iv != null)
                iv.setImageResource(icon_id);
            mText = (EditText) findViewById(R.id.cetwi_input);
            if (mText != null) {
                mText.setHint(placeholder);
                mText.setInputType(input_type);
                InputFilter[] filters = new InputFilter[2];
                filters[0] = new InputFilter.LengthFilter(max_length);
                if (data_type == ALPHA_NUMERIC) {
                    filters[1] = new InputFilter() {
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                                   int dstart, int dend) {
                            for (int i = start; i < end; i++) {
                                if (!Character.isLetterOrDigit(source.charAt(i))) {
                                    return "";
                                }
                            }
                            return null;
                        }
                    };
                }
                mText.setFilters(filters);
            }
        }

        EditText mText;

        public void setMargins(int lm, int tm, int rm, int bm) {
            setLayoutParams(UI.getLinearLayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, lm, tm, rm, bm));
        }

        public String getValue() {
            if (mText != null)
                return mText.getText().toString();
            return null;
        }

        public void clearValue() {
            if (mText != null)
                mText.setText("");
        }
    }

    public static class CustomHomeItemWithIcon extends LinearLayout {

        public CustomHomeItemWithIcon(Context context) {
            super(context);
        }

        ImageView mIcon;
        TextView mLabel;
        int icon_id_normal;
        int icon_id_pressed;
        int bg_color_normal;
        int bg_color_pressed;
        int text_color_normal;
        int text_color_pressed;

        public void onCreate(int icon_id_normal, int icon_id_pressed, int bg_color_normal,
                             int bg_color_pressed, int text_color_normal, int text_color_pressed,
                             final String label, final ButtonCallbackListener listener) {
            this.icon_id_normal = icon_id_normal;
            this.icon_id_pressed = icon_id_pressed;
            this.bg_color_normal = bg_color_normal;
            this.bg_color_pressed = bg_color_pressed;
            this.text_color_normal = text_color_normal;
            this.text_color_pressed = text_color_pressed;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(R.layout.custom_grid_item_image, this, true);
            mIcon = (ImageView) findViewById(R.id.img_icon);
            if (mIcon != null)
                mIcon.setImageResource(icon_id_normal);
            mLabel = (TextView) findViewById(R.id.txt_title);
            if (mLabel != null) {
                mLabel.setText(label);
                mLabel.setTextColor(ContextCompat.getColor(getContext(), text_color_normal));
            }
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (v instanceof CustomHomeItemWithIcon) {
                        CustomHomeItemWithIcon p = (CustomHomeItemWithIcon) v;
                        if (p != null) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                p.setBackgroundColor(ContextCompat.getColor(getContext(), p.bg_color_pressed));
                                if (mIcon != null)
                                    mIcon.setImageResource(p.icon_id_pressed);
                                if (mLabel != null)
                                    mLabel.setTextColor(ContextCompat.getColor(getContext(), p.text_color_pressed));
                            }
                            else if (event.getAction() == MotionEvent.ACTION_UP) {
                                p.setBackgroundColor(ContextCompat.getColor(getContext(), p.bg_color_normal));
                                if (mIcon != null)
                                    mIcon.setImageResource(p.icon_id_normal);
                                if (mLabel != null)
                                    mLabel.setTextColor(ContextCompat.getColor(getContext(), p.text_color_normal));
                                if (listener != null)
                                    listener.onItemClicked(label);
                            }
                            else {
                                p.setBackgroundColor(ContextCompat.getColor(getContext(), p.bg_color_normal));
                                if (mIcon != null)
                                    mIcon.setImageResource(p.icon_id_normal);
                                if (mLabel != null)
                                    mLabel.setTextColor(ContextCompat.getColor(getContext(), p.text_color_normal));
                            }
                        }
                    }
                    return true;
                }
            });
            setBackgroundColor(ContextCompat.getColor(getContext(), bg_color_normal));
        }
    }

    public static class CustomImageButton extends LinearLayout {

        public CustomImageButton(Context context) {
            super(context);
        }

        public void onCreate(String label, int background_id, final ButtonCallbackListener listener) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            li.inflate(R.layout.custom_image_button, this, true);
            ImageView iv = (ImageView) findViewById(R.id.img_background);
            if (iv != null)
                iv.setImageResource(background_id);
            TextView tv = (TextView) findViewById(R.id.txt_label);
            if (tv != null)
                tv.setText(label);
        }
    }

    public static class CustomDialogPopup extends Dialog {

        public CustomDialogPopup(Context context) {
            super(context);
        }

        public static CustomDialogPopup instance(Context context, String title) {
            CustomDialogPopup v = new CustomDialogPopup(context);
            v.requestWindowFeature(Window.FEATURE_NO_TITLE);
            v.setContentView(R.layout.custom_dialog_popup);
            v.setDialogTitle(title);
            Window w = v.getWindow();
            if (w != null)
                w.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            return v;
        }

        public void setDialogTitleBackground(int color_id) {
            TextView v = (TextView) findViewById(R.id.txt_title);
            if (v != null)
                v.setBackgroundColor(color_id);
        }

        public void setDialogTitle(String title) {
            TextView v = (TextView) findViewById(R.id.txt_title);
            if (v != null)
                v.setText(title);
        }

        public CustomDialogPopup setDialogContent(View v) {
            if (v != null) {
                LinearLayout p = (LinearLayout) findViewById(R.id.popup_content);
                if (p != null)
                    p.addView(v);
            }
            return this;
        }

        public CustomDialogPopup setDialogButton(View v) {
            if (v != null) {
                LinearLayout p = (LinearLayout) findViewById(R.id.popup_button);
                if (p != null)
                    p.addView(v);
            }
            return this;
        }
    }

    public static CustomDialogPopup createDialogPopup(Context context, String title) {
        return CustomDialogPopup.instance(context, title);
    }

    public static CustomTextEditWithIcon createCustomTextEditWithIcon(Context context, int icon_id,
                                                                      String placeholder, int input_type,
                                                                      int data_type, int max_length) {
        CustomTextEditWithIcon v = new CustomTextEditWithIcon(context);
        v.onCreate(icon_id, placeholder, input_type, data_type, max_length);
        return v;
    }

    public static CustomHomeItemWithIcon createCustomHomeItemWithIcon(Context context, int icon_id_normal,
                                                                      int icon_id_pressed, int bg_color_normal,
                                                                      int bg_color_pressed, int text_color_normal,
                                                                      int text_color_pressed, String label,
                                                                      ButtonCallbackListener listener) {
        CustomHomeItemWithIcon v = new CustomHomeItemWithIcon(context);
        v.onCreate(icon_id_normal, icon_id_pressed, bg_color_normal, bg_color_pressed,
                text_color_normal, text_color_pressed, label, listener);
        return v;
    }

    public static CustomDatePicker createCustomDatePicker() {
        return new CustomDatePicker();
    }

    public static CustomDatePicker createCustomDatePickerWithMinMaxDate(Util.Date date, long min_date, long max_date) {
        return CustomDatePicker.instance(date, min_date, max_date);
    }

    public static CustomDatePicker createCustomDatePickerWithMinDate(Util.Date date, long min_date) {
        return CustomDatePicker.forMinDate(date, min_date);
    }

    public static CustomDatePicker createCustomDatePickerWithMaxDate(Util.Date date, long max_date) {
        return CustomDatePicker.forMaxDate(date, max_date);
    }

    public static Button createCustomButton(Context context, String text) {
        Button v = (Button) LayoutInflater.from(context).inflate(R.layout.custom_button, null);
        v.setText(text);
        return v;
    }

    public static LinearLayout createLinerLayout(Context context, int orientation) {
        LinearLayout v = new LinearLayout(context);
        v.setOrientation(orientation);
        return v;
    }

    public static GridLayout createGridLayout(Context context, int orientation, int row_count,
                                              int column_count) {
        GridLayout v = new GridLayout(context);
        v.setOrientation(orientation);
        v.setRowCount(row_count);
        v.setColumnCount(column_count);
        return v;
    }

    public static LinearLayout.LayoutParams getLinearLayoutParams(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }

    public static LinearLayout.LayoutParams getLinearLayoutParams(int w, int h, int wt) {
        LinearLayout.LayoutParams v = getLinearLayoutParams(w, h);
        v.weight = wt;
        return v;
    }

    public static LinearLayout.LayoutParams getLinearLayoutParams(int w, int h, int lm, int tm,
                                                                      int rm, int bm) {
        LinearLayout.LayoutParams v = getLinearLayoutParams(w, h);
        v.setMargins(lm, tm, rm, bm);
        return v;
    }

    public static GridLayout.LayoutParams getGridLayoutParams(int w, int h) {
        GridLayout.LayoutParams v = new GridLayout.LayoutParams();
        v.width = w;
        v.height = h;
        return v;
    }

    public static GridLayout.LayoutParams getGridLayoutParams(int w, int h, GridLayout.Spec row,
                                                              GridLayout.Spec col) {
        GridLayout.LayoutParams v = new GridLayout.LayoutParams(row, col);
        v.width = w;
        v.height = h;
        return v;
    }

    public static CustomImageButton createCustomImageButton(Context context, String label,
                                                            int background_id, ButtonCallbackListener listener) {
        CustomImageButton v = new CustomImageButton(context);
        v.onCreate(label, background_id, listener);
        return v;
    }
}

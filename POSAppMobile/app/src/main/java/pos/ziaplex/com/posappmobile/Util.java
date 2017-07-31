package pos.ziaplex.com.posappmobile;

import android.app.Activity;
import android.graphics.Point;
import android.text.format.DateFormat;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by jimmy.macaraeg on 06/07/2017.
 */

public class Util {

    public static class Date {

        public static Date forTime(String time) {
            Date v = new Date();
            v.mTime = time;
            return v;
        }

        public static Date forDay(String day) {
            Date v = new Date();
            v.mDay = day;
            return v;
        }

        public static Date forMonth(String month) {
            Date v = new Date();
            v.mMonth = month;
            return v;
        }

        public static Date forYear(String year) {
            Date v = new Date();
            v.mYear = year;
            return v;
        }

        public static Date forMonthYear(String month, String year) {
            Date v = new Date();
            v.mMonth = month;
            v.mYear = year;
            return v;
        }

        public static Date instance(String day, String month, String year) {
            Date v = new Date();
            v.mDay = day;
            v.mMonth = month;
            v.mYear = year;
            return v;
        }

        public static Date instance(String day, String month, String year, String time) {
            Date v = new Date();
            v.mDay = day;
            v.mMonth = month;
            v.mYear = year;
            v.mTime = time;
            return v;
        }

        public static Date forPlusDay(Date date, int n) {
            return Date.instance(String.valueOf(Integer.valueOf(date.getDay()) + n),
                    date.getMonth(), date.getYear(), date.getTime());
        }

        public static Date forPlusMonth(Date date, int n) {
            return Date.instance(date.getDay(),
                    String.valueOf(Integer.valueOf(date.getMonth()) + n), date.getYear(),
                    date.getTime());
        }

        public static Date forPlusYear(Date date, int n) {
            return Date.instance(date.getDay(), date.getMonth(),
                    String.valueOf(Integer.valueOf(date.getYear()) + n), date.getTime());
        }

        public static Date forMinusDay(Date date, int n) {
            return Date.instance(String.valueOf(Integer.valueOf(date.getDay()) - n),
                    date.getMonth(), date.getYear(), date.getTime());
        }

        public static Date forMinusMonth(Date date, int n) {
            return Date.instance(date.getDay(),
                    String.valueOf(Integer.valueOf(date.getMonth()) - n), date.getYear(),
                    date.getTime());
        }

        public static Date forMinusYear(Date date, int n) {
            return Date.instance(date.getDay(), date.getMonth(),
                    String.valueOf(Integer.valueOf(date.getYear()) - n),
                    date.getTime());
        }

        String mDay = "", mMonth = "", mYear = "", mTime = "";

        public Date duplicate() {
            return Date.instance(getDay(), getMonth(), getYear(), getTime());
        }

        public String getDay() {
            return mDay;
        }

        public String getMonth() {
            return mMonth;
        }

        public String getYear() {
            return mYear;
        }

        public String getTime() {
            return mTime;
        }

        public String getDate() {
            return mMonth + "-" + mDay + "-" + mYear;
        }

        public String toString() {
            return mMonth + "-" + mDay + "-" + mYear + " " + mTime;
        }

        public String toMonthYearStringFormat(String sep) {
            return mMonth + sep + mYear;
        }

        public String toMMDDYYYYStringFormat(String sep) {
            String dd, mm;
            int d = Integer.valueOf(mDay);
            if (d < 10) {
                dd = "0" + d;
            }
            else {
                dd = String.valueOf(d);
            }
            int m = Integer.valueOf(mMonth);
            if (m < 10) {
                mm = "0" + m;
            }
            else {
                mm = String.valueOf(m);
            }
            return mm + sep + dd + sep + Integer.valueOf(mYear);
        }
    }

    static java.util.Date mDate;

    public static int getScreenWidth(Activity activity) {
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.x;
    }

    public static int getScreenHeight(Activity activity) {
        Point p = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(p);
        return p.y;
    }

    public static HashMap getDateLastMonth() {
        if (mDate == null)
            mDate = new java.util.Date();
        HashMap<String, Date> hm = new HashMap();
        GregorianCalendar gc = new GregorianCalendar(Integer.valueOf(getDateYear().getYear()),
                Integer.valueOf(getDateMonth().getMonth()) - 2, 1);
        java.util.Date date = gc.getTime();
        hm.put("from", Date.instance(DateFormat.format("dd", date).toString(),
                DateFormat.format("MM", date).toString(),
                DateFormat.format("yyyy", date).toString(),
                getDateTime(date).getTime()));
        hm.put("to", Date.instance(String.valueOf(gc.getActualMaximum(Calendar.DAY_OF_MONTH)),
                DateFormat.format("MM", date).toString(),
                DateFormat.format("yyyy", date).toString(),
                getDateTime(date).getTime()));
        return hm;
    }

    public static long getDateInMillis(Date date) {
        GregorianCalendar v;
        if (date == null) {
            v = new GregorianCalendar(Integer.valueOf(getDateYear().getYear()),
                    Integer.valueOf(getDateMonth().getMonth()) - 1,
                    Integer.valueOf(getDateDay().getDay()));
        }
        else {
            v = new GregorianCalendar(Integer.valueOf(date.getYear()),
                    Integer.valueOf(date.getMonth()) - 1,
                    Integer.valueOf(date.getDay()));
        }
        return v.getTimeInMillis();
    }

    public static HashMap getDateLastWeek() {
        if (mDate == null)
            mDate = new java.util.Date();
        HashMap<String, Date> hm = new HashMap();
        java.util.Date fDate = new java.util.Date(mDate.getTime() - (getDayDOWStamp(mDate) +
                ((24 * 60 * 60 * 1000) * 6)));
        hm.put("from", Util.Date.instance(DateFormat.format("dd", fDate).toString(),
                DateFormat.format("MM", fDate).toString(),
                DateFormat.format("yyyy", fDate).toString(),
                getDateTime(fDate).getTime()));
        java.util.Date tDate = new java.util.Date(mDate.getTime() - getDayDOWStamp(mDate));
        hm.put("to", Util.Date.instance(DateFormat.format("dd", tDate).toString(),
                DateFormat.format("MM", tDate).toString(),
                DateFormat.format("yyyy", tDate).toString(),
                getDateTime(tDate).getTime()));
        return hm;
    }

    private static long getDayDOWStamp(java.util.Date date) {
        if (date != null) {
            String dow = DateFormat.format("EEE", date).toString();
            if ("Mon".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 2);
            }
            else if ("Tue".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 3);
            }
            else if ("Wed".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 4);
            }
            else if ("Thu".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 5);
            }
            else if ("Fri".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 6);
            }
            else if ("Sat".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 7);
            }
            else if ("Sun".equalsIgnoreCase(dow)) {
                return ((24 * 60 * 60 * 1000) * 8);
            }
            else {
                return (24 * 60 * 60 * 1000);
            }
        }
        return 0;
    }

    public static Date getDateTimeToday() {
        return Date.instance(getDateDay().getDay(), getDateMonth().getMonth(),
                getDateYear().getYear(), getDateTime().getTime());
    }

    public static Date getDateToday() {
        return Date.instance(getDateDay().getDay(), getDateMonth().getMonth(),
                getDateYear().getYear());
    }

    public static Date getDateDay() {
        if (mDate == null)
            mDate = new java.util.Date();
        return Date.forDay(DateFormat.format("dd", mDate).toString());
    }

    public static Date getDateMonth() {
        if (mDate == null)
            mDate = new java.util.Date();
        return Date.forMonth(DateFormat.format("MM", mDate).toString());
    }

    public static Date getDateYear() {
        if (mDate == null)
            mDate = new java.util.Date();
        return Date.forYear(DateFormat.format("yyyy", mDate).toString());
    }

    public static Date getDateMonthYear(String month_format, String year_format) {
        if (mDate == null)
            mDate = new java.util.Date();
        return Date.forMonthYear(DateFormat.format(month_format, mDate).toString(),
                DateFormat.format(year_format, mDate).toString());
    }

    public static Date getDateTime() {
        if (mDate == null)
            mDate = new java.util.Date();
        return Date.forTime(getMeridiem(mDate));
    }

    public static Date getDateTime(java.util.Date date) {
        if (date == null)
            date = new java.util.Date();
        return Date.forTime(getMeridiem(date));
    }

    private static String getMeridiem(java.util.Date date) {
        String m = "AM";
        int h = Integer.valueOf(DateFormat.format("HH", date).toString());
        if (h > 11)
            m = "PM";
        return DateFormat.format("hh:mm", date).toString() + " " + m;
    }

    public static boolean isWithinDates(Date from_date, Date to_date, Date value) {
        java.util.Date vDate = new java.util.Date(Util.getDateInMillis(value));
        return vDate.after(new java.util.Date(Util.getDateInMillis(
                Util.Date.forMinusDay(from_date, 1)))) && vDate.before(new java.util.Date(
                Util.getDateInMillis(Util.Date.forPlusDay(to_date, 1))));
    }

    public static String convertToCurrency(String sign, long value) {
        return sign + " " + NumberFormat.getCurrencyInstance().format(value).substring(1);
    }
}

package pos.ziaplex.com.posappmobile;

import android.media.Image;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class Operator {

    public Operator(String name, String lastLogin) {
        mName = name;
        mLastLogin = lastLogin;
    }

    String mName;
    String mLastLogin;

    public String getName() {
        return mName;
    }

    public String getLastLogin() {
        return mLastLogin;
    }
}

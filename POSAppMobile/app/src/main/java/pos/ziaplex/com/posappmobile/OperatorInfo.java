package pos.ziaplex.com.posappmobile;

import android.media.Image;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class OperatorInfo {

    public OperatorInfo(Image image, String name, String code) {
        mImage = image;
        mName = name;
        mCode = code;
    }

    Image mImage;
    String mName;
    String mCode;

    public Image getImage() {
        return mImage;
    }

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }
}

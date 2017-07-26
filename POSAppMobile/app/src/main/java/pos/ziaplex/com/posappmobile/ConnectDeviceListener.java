package pos.ziaplex.com.posappmobile;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public interface ConnectDeviceListener extends Serializable {

    public void onConnectDeviceFinish(Context context);
}

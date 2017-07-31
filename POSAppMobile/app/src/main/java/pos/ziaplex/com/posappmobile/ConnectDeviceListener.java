package pos.ziaplex.com.posappmobile;

import android.content.Context;

import java.io.Serializable;

/**
 * Created by jimmy.macaraeg on 31/07/2017.
 */

interface ConnectDeviceListener extends Serializable {

    void onConnectDeviceFinish(Context context);
}
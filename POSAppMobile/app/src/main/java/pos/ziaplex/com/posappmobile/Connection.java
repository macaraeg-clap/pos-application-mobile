package pos.ziaplex.com.posappmobile;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class Connection {

    public Connection(String deviceName, String hardware, String lastUsed, String firmware) {
        mDeviceName = deviceName;
        mHardware = hardware;
        mLastUsed = lastUsed;
        mFirmware = firmware;
    }

    String mDeviceName;
    String mHardware;
    String mLastUsed;
    String mFirmware;

    public String getDeviceName() {
        return mDeviceName;
    }

    public String getHardware() {
        return mHardware;
    }

    public String getLastUsed() {
        return mLastUsed;
    }

    public String getFirmware() {
        return mFirmware;
    }
}

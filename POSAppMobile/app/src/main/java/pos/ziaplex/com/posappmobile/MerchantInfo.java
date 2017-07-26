package pos.ziaplex.com.posappmobile;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class MerchantInfo {

    public MerchantInfo(String name, String code, String currency, String email) {
        mName = name;
        mCode = code;
        mCurrency = currency;
        mEmail = email;
    }

    String mName;
    String mCode;
    String mCurrency;
    String mEmail;

    public String getName() {
        return mName;
    }

    public String getCode() {
        return mCode;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public String getEmail() {
        return mEmail;
    }
}

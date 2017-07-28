package pos.ziaplex.com.posappmobile;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by jimmy.macaraeg on 10/07/2017.
 */

public class Transaction {

    public Transaction(long amount, String account_number, boolean card_holder,
                       Util.Date expiry_date, String status, Util.Date date_time, String method,
                       String mode, String transaction_type, String transaction_reference,
                       String payment_for, String operator_name, String operator_number,
                       BitmapDrawable offline_validation) {
        mAmount = amount;
        mAccountNumber = account_number;
        mCardHolder = card_holder;
        mExpiryDate = expiry_date;
        mStatus = status;
        mDateTime = date_time;
        mMethod = method;
        mMode = mode;
        mTransactionType = transaction_type;
        mTransactionReference = transaction_reference;
        mPaymentFor = payment_for;
        mOperatorName = operator_name;
        mOperatorNumber = operator_number;
        mOfflineValidation = offline_validation;
    }

    Util.Date mExpiryDate, mDateTime;
    String mAccountNumber, mStatus, mMethod, mMode, mTransactionType, mTransactionReference,
        mPaymentFor, mOperatorName, mOperatorNumber;
    BitmapDrawable mOfflineValidation;
    boolean mCardHolder;
    long mAmount;

    public long getAmount() {
        return mAmount;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public boolean getCardHolder() {
        return mCardHolder;
    }

    public Util.Date getExpiryDate() {
        return mExpiryDate;
    }

    public String getStatus() {
        return mStatus;
    }

    public Util.Date getDateTime() {
        return mDateTime;
    }

    public String getMethod() {
        return mMethod;
    }

    public String getMode() {
        return mMode;
    }

    public String getTransactionType() {
        return mTransactionType;
    }

    public String getTransactionReference() {
        return mTransactionReference;
    }

    public String getPaymentFor() {
        return mPaymentFor;
    }

    public String getOperatorName() {
        return mOperatorName;
    }

    public String getOperatorNumber() {
        return mOperatorNumber;
    }

    public BitmapDrawable getOfflineValidation() {
        return mOfflineValidation;
    }
}

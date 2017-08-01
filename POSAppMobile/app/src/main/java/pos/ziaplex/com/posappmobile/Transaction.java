package pos.ziaplex.com.posappmobile;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by jimmy.macaraeg on 10/07/2017.
 */

class Transaction {

    Transaction(long amount, String account_number, boolean card_holder,
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

    long getAmount() {
        return mAmount;
    }

    String getAccountNumber() {
        return mAccountNumber;
    }

    boolean getCardHolder() {
        return mCardHolder;
    }

    Util.Date getExpiryDate() {
        return mExpiryDate;
    }

    String getStatus() {
        return mStatus;
    }

    Util.Date getDateTime() {
        return mDateTime;
    }

    String getMethod() {
        return mMethod;
    }

    String getMode() {
        return mMode;
    }

    String getTransactionType() {
        return mTransactionType;
    }

    String getTransactionReference() {
        return mTransactionReference;
    }

    String getPaymentFor() {
        return mPaymentFor;
    }

    String getOperatorName() {
        return mOperatorName;
    }

    String getOperatorNumber() {
        return mOperatorNumber;
    }

    BitmapDrawable getOfflineValidation() {
        return mOfflineValidation;
    }
}

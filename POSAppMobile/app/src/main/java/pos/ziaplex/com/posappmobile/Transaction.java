package pos.ziaplex.com.posappmobile;

/**
 * Created by jimmy.macaraeg on 10/07/2017.
 */

public class Transaction {

    public Transaction(String status, String amount, String transReference, String accountNumber,
                       String date, String operatorName, String transType) {
        mStatus = status;
        mAmount = amount;
        mTransReference = transReference;
        mAccountNumber = accountNumber;
        mDate = date;
        mOperatorName = operatorName;
        mTransType = transType;
    }

    public Transaction(String status, String amount, String accountNumber, String expiryDate, String date,
                       String method, String mode, String transType, String transReference, String paymentFor,
                       String operatorName, String operatorNumber) {
        mStatus = status;
        mAmount = amount;
        mAccountNumber = accountNumber;
        mExpiryDate = expiryDate;
        mDate = date;
        mMethod = method;
        mMode = mode;
        mTransType = transType;
        mTransReference = transReference;
        mPaymentFor = paymentFor;
        mOperatorName = operatorName;
        mOperatorNumber = operatorNumber;
    }

    String mStatus;
    String mAmount;
    String mTransReference;
    String mAccountNumber;
    String mDate;
    String mOperatorName;
    String mTransType;
    String mExpiryDate;
    String mMethod;
    String mMode;
    String mPaymentFor;
    String mOperatorNumber;

    public String getStatus() {
        return mStatus;
    }

    public String getAmount() {
        return mAmount;
    }

    public String getTransReference() {
        return mTransReference;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public String getDate() {
        return mDate;
    }

    public String getOperatorName() {
        return mOperatorName;
    }

    public String getTransType() {
        return mTransType;
    }

    public String getExpiryDate() {
        return mExpiryDate;
    }

    public String getMethod() {
        return mMethod;
    }

    public String getMode() {
        return mMode;
    }

    public String getPaymentFor() {
        return mPaymentFor;
    }

    public String getmOperatorNumber() {
        return mOperatorNumber;
    }
}

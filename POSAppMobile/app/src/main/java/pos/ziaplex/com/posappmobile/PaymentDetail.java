package pos.ziaplex.com.posappmobile;

/**
 * Created by jimmy.macaraeg on 17/07/2017.
 */

public class PaymentDetail {

    public PaymentDetail(String first_name, String payor, String transaction_number, String payment_for,
                                         String transaction_amount, String transaction_fee, String total_amount) {
        mFirstName = first_name;
        mPayor = payor;
        mTransactionNumber = transaction_number;
        mPaymentFor = payment_for;
        mTransactionAmount = transaction_amount;
        mTransactionFee = transaction_fee;
        mTotalAmount = total_amount;
    }

    String mFirstName;
    String mPayor;
    String mPaymentFor;
    String mTransactionNumber;
    String mTransactionAmount;
    String mTransactionFee;
    String mTotalAmount;

    public String getFirstName() {
        return mFirstName;
    }

    public String getPayor() {
        return mPayor;
    }

    public String getPaymentFor() {
        return mPaymentFor;
    }

    public String getTransactionNumber() {
        return mTransactionNumber;
    }

    public String getTransactionAmount() {
        return mTransactionAmount;
    }

    public String getTransactionFee() {
        return mTransactionFee;
    }

    public String getTotalAmount() {
        return mTotalAmount;
    }
}

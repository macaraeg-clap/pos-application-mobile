package pos.ziaplex.com.posappmobile;

import java.util.ArrayList;

import pos.ziaplex.com.posappmobile.Transaction;

/**
 * Created by jimmy.macaraeg on 27/07/2017.
 */

public class TransactionListData {

    public TransactionListData() {
        mData = new ArrayList<>();
    }

    public static TransactionListData create() {
        return new TransactionListData();
    }

    public static TransactionListData create(ArrayList<Transaction> data) {
        TransactionListData v = create();
        v.updateListData(data);
        return v;
    }

    static ArrayList<Transaction> mData;

    public static ArrayList<Transaction> getListData() {
        return mData;
    }

    public static void clearListData() {
        if (mData != null)
            mData.clear();
    }

    public static Transaction getLastTransaction() {
        if (mData != null)
            return mData.get(0);
        return null;
    }

    public static void updateListData(ArrayList<Transaction> list) {
        mData = list;
    }

    public static Transaction getTransactionData(int index) {
        if (mData != null)
            return mData.get(index);
        return null;
    }

    public static void removeTransactionData(int index) {
        if (mData != null)
            mData.remove(index);
    }

    public static void addTransactionData(Transaction data) {
        if (mData != null)
            mData.add(data);
    }

    public static ArrayList<Transaction> getByTransactionType(String type) {
        ArrayList<Transaction> v = new ArrayList<>();
        if (mData != null) {
            for (int i = 0; i < getSize(); i++) {
                Transaction trans = getTransactionData(i);
                if (trans != null) {
                    if (trans.getTransactionType().toLowerCase().contains(type.toLowerCase()))
                        v.add(trans);
                }
            }
        }
        return v;
    }

    public static ArrayList<Transaction> getByTransactionType(ArrayList<Transaction> data,
                                                              String type) {
        ArrayList<Transaction> v = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                Transaction trans = data.get(i);
                if (trans != null) {
                    if (trans.getTransactionType().toLowerCase().contains(type.toLowerCase()))
                        v.add(trans);
                }
            }
        }
        return v;
    }

    public static int getByTransactionTypeTotalCount(String type) {
        return getByTransactionType(type).size();
    }

    public static long getTotalAmount(ArrayList<Transaction> list) {
        long amount = 0;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Transaction trans = list.get(i);
                if (trans != null)
                    amount += trans.getAmount();
            }
        }
        return amount;
    }

    public static long getByTransactionTypeTotalAmount(String type) {
        long amount = 0;
        ArrayList<Transaction> v = getByTransactionType(type);
        if (v != null) {
            for (int i = 0; i < v.size(); i++) {
                Transaction trans = v.get(i);
                if (trans != null)
                    amount += trans.getAmount();
            }
        }
        return amount;
    }

    public static int getSize() {
        if (mData != null)
            return mData.size();
        return 0;
    }
}

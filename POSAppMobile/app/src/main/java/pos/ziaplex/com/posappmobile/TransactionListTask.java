package pos.ziaplex.com.posappmobile;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jimmy.macaraeg on 25/07/2017.
 */

public class TransactionListTask extends AsyncTask<ArrayList<Transaction>, Void, ArrayList<Transaction>> {

    public TransactionListTask(TransactionAdapter list_adapter, ListView list_view,
                               LinearLayout progress_container, LinearLayout no_found_container) {
        mListAdapter = list_adapter;
        mListView = list_view;
        mProgressContainer = progress_container;
        mNoFoundContainer = no_found_container;
    }

    TransactionAdapter mListAdapter;
    ListView mListView;
    LinearLayout mProgressContainer, mNoFoundContainer;

    @Override
    protected void onPreExecute() {
        if (mListView != null)
            mListView.setVisibility(View.GONE);
        if (mNoFoundContainer != null)
            mNoFoundContainer.setVisibility(View.GONE);
        if (mProgressContainer != null)
            mProgressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Transaction> result) {
        if (mListAdapter != null) {
            mListAdapter.clear();
            int sz = result.size();
            if (sz > 0) {
                if (mListView != null)
                    mListView.setVisibility(View.VISIBLE);
                if (mNoFoundContainer != null)
                    mNoFoundContainer.setVisibility(View.GONE);
                for (int i = 0; i < sz; i++)
                    mListAdapter.addTransaction(result.get(i));
            }
            else {
                if (mListView != null)
                    mListView.setVisibility(View.GONE);
                if (mNoFoundContainer != null)
                    mNoFoundContainer.setVisibility(View.VISIBLE);
            }

        }
        if (mProgressContainer != null)
            mProgressContainer.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Void... progress) {
    }

    @Override
    protected ArrayList<Transaction> doInBackground(ArrayList<Transaction>... params) {
        return params[0];
    }
}
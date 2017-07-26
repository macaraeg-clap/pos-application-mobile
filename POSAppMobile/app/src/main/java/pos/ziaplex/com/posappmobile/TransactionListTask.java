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
                               LinearLayout progress_container) {
        mListAdapter = list_adapter;
        mListView = list_view;
        mProgressContainer = progress_container;
    }

    TransactionAdapter mListAdapter;
    ListView mListView;
    LinearLayout mProgressContainer;

    @Override
    protected void onPreExecute() {
        if (mListView != null)
            mListView.setVisibility(View.GONE);
        if (mProgressContainer != null)
            mProgressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(ArrayList<Transaction> result) {
        if (mListAdapter != null) {
            mListAdapter.clear();
            if (mListView != null)
                mListView.setVisibility(View.VISIBLE);
            for (int i = 0; i < result.size(); i++)
                mListAdapter.addTransaction(result.get(i));
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
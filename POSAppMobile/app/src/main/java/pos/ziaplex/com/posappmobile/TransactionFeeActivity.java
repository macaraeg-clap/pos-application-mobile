package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by jimmy.macaraeg on 11/07/2017.
 */

public class TransactionFeeActivity extends BaseActivity {

    int mRoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        String title = null;
        if (i != null) {
            title = i.getStringExtra("title");
            mRoundId = i.getIntExtra("iconID", 0);
        }
        super.onCreate(savedInstanceState);
        setDefaultTitle(title);
    }

    @Override
    public void onCreateContent(LinearLayout content) {
        if (content != null) {
            content.addView(LayoutInflater.from(this).inflate(R.layout.transaction_view, null));
            ImageView v = (ImageView) content.findViewById(R.id.img_icon);
            if (v != null)
                v.setBackgroundResource(mRoundId);
        }
    }
}

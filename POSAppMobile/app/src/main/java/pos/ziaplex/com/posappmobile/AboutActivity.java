package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTitle(getString(R.string.about_label));
    }

    @Override
    public void onCreateContent(ScrollView content) {
        if (content != null)
            content.addView(LayoutInflater.from(this).inflate(R.layout.activity_about, null));
    }

    @Override
    public void onHomeClickedEvent(ImageButton btn) {
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                finish();
                break;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return true;
    }
}

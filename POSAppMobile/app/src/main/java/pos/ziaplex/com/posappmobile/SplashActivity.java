package pos.ziaplex.com.posappmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jimmy.macaraeg on 03/07/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseActivity.mHome = HomeActivity.class;
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}

package vn.itplus.quanlysinhvien.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import vn.itplus.quanlysinhvien.R;

public class SplashUTT extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_utt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashUTT.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
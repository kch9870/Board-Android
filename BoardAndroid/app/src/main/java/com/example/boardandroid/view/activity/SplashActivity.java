package com.example.boardandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boardandroid.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new SplashHandler(),3000);
    }

    // handler 스레드 적용
    private class SplashHandler implements Runnable {
        @Override
        public void run() {
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
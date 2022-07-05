package com.example.boardandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boardandroid.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // join 버튼
        Button loginBtn = findViewById(R.id.btnJoin);
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickJoinActivity();}
        });

    }

    private void onClickJoinActivity(){
        // 다이얼로그 추가

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
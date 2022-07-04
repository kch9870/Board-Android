package com.example.boardandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boardandroid.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 로그인 버튼
        Button loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickLoginActivity();}
        });

        // 회원가입 버튼
        Button signupBtn = findViewById(R.id.btnSignup);
        signupBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickSignUpActivity();}
        });
    }

    private void onClickLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void onClickSignUpActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
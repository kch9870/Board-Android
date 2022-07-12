package com.example.boardandroid.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.BaseResponse;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // View Model 설정
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.activityLoginResponse().observe(this,this::responseResult);

        // 로그인 버튼
        Button loginBtn = findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventLogin();
                if(Boolean.TRUE.equals(loginViewModel.activityCheckLogin().getValue())){
                    if(loginViewModel.activityLoginResponse().getValue() == null){
                        onClickLoginActivity();
                    }
                }
                else Log.d("ERROR","activityCheckLogin");
            }
        });

        // 회원가입 버튼
        Button signupBtn = findViewById(R.id.btnSignup);
        signupBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickSignUpActivity();}
        });
    }

    /**
     * 로그인 이벤트
     */
    public void eventLogin() {
        EditText editEmail = findViewById(R.id.editEmail);
        EditText password = findViewById(R.id.editPassword);
        loginViewModel.Login(editEmail.getText().toString(),password.getText().toString());

    }

    public void responseResult(LoginResponse loginResponse) {
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
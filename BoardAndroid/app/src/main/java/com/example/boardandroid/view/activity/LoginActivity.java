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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.UserInfo;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.editEmail);
        EditText password = findViewById(R.id.editPassword);
        Button loginBtn = findViewById(R.id.btnLogin);

        // View Model 설정
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse.responseCode == 200){
                    UserInfo userInfo = UserInfo.getInstance();
                    Log.d("200SUCCESS",String.valueOf(loginResponse.userInfo.userId));
                    userInfo.setUserId(loginResponse.userInfo.userId);
                    userInfo.setNickName(loginResponse.userInfo.nickName);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(loginResponse.responseCode == 401) {
                    showMessage("아이디 혹은 비밀번호가 틀립니다. 다시 입력해주세요.");
                    Log.d("HTTP ERROR","Not match User");
                }
                else if(loginResponse.responseCode == 400) {
                    showMessage("아이디 와 비밀번호를 입력해주세요.");
                    Log.d("HTTP ERROR","Bad Request");
                }
                else Log.d("FAIL LOGIN","fail login");
            }
        });
        // 로그인 버튼
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.login(email.getText().toString(),password.getText().toString());
            }
        });

        // 회원가입 버튼
        Button signupBtn = findViewById(R.id.btnSignup);
        signupBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {onClickSignUpActivity();}
        });
    }

    private void onClickSignUpActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
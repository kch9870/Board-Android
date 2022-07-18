package com.example.boardandroid.view.activity;

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
import com.example.boardandroid.repository.model.response.BaseResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;
import com.example.boardandroid.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    public SignupViewModel signupViewModel;

    private Boolean checkEmail = false;
    private Boolean checkNickName = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button joinBtn = findViewById(R.id.btnJoin);
        Button checkEmailBtn = findViewById(R.id.btnCheckEmail);

        // View Model 설정
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        /**
         * View Model Observe
         *
         * 1. Main Observe - 회원가입 포함
         * 2. Email 중복 체크 observe
         */

        // 1. Main Observe
        signupViewModel.getSignupResult().observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
                if (checkEmail) {
                    if (signupResponse.responseCode == 200) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        showMessage("회원 가입에 실패하였습니다.");
                        Log.d("FAIL SIGNUP", "fail signup");
                    }
                } else {
                    showMessage("아이디 중복 체크 버튼을 확인하세요.");
                    Log.d("FAIL CHECK EMAIL", "check email button");
                }
            }
        });

        // 2. Email 중복체크
        signupViewModel.getCheckEmailResult().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse checkEmailResponse) {
                if (checkEmailResponse.responseCode == 200) {
                    checkEmail = true;
                    checkEmailBtn.setEnabled(false);
                    checkEmailBtn.setBackgroundResource(R.color.logo_color);
                } else if (checkEmailResponse.responseCode == 401) {
                    checkEmail = false;
                    showMessage("아이디가 중복됩니다. 다시 설정해주세요.");
                    Log.d("FAIL CHECK EMAIL", "401 exist user email");
                } else {
                    checkEmail = false;
                    showMessage("아이디 중복체크에 실패하셨습니다.400");
                    Log.d("FAIL CHECK EMAIL", "400 bad request");
                }
            }
        });

        /**
         * Button Event
         *
         * 1. 회원가입 버튼
         * 2. Email 중복 체크 버튼
         * 3. NickName 중복 체크 버튼
         */

        // 회원가입 버튼
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 변수 string 으로 변환
                EditText editEmail = findViewById(R.id.email);
                EditText editPassword = findViewById(R.id.joinPassWord);
                EditText editName = findViewById(R.id.name);
                EditText editNickName = findViewById(R.id.nickName);

                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String name = editName.getText().toString();
                String nickName = editNickName.getText().toString();

                signupViewModel.signup(email, password, name, nickName);
            }
        });

        // Email 중복체크 버튼
        checkEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 변수 string 으로 변환
                EditText editEmail = findViewById(R.id.email);

                signupViewModel.checkEmail(editEmail.getText().toString());
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
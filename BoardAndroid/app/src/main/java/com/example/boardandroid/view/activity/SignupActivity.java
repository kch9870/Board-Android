package com.example.boardandroid.view.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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
        Button checkNickNameBtn = findViewById(R.id.btnCheckNickName);

        EditText joinPassWord = findViewById(R.id.joinPassWord);
        EditText checkPassWord = findViewById(R.id.checkPassWord);

        TextView changeTextPassWord = findViewById(R.id.changeTextPassWord);


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
                if (checkEmail && checkNickName) {
                    if (signupResponse.responseCode == 200) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        showMessage("회원 가입에 실패하였습니다.");
                        Log.d("FAIL SIGNUP", "fail signup");
                    }
                } else {
                    showMessage("아이디 중복 체크 혹은 닉네임 중복체크를 확인하세요.");
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
                    checkEmailBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.logo_color));
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

        // 3. 닉네임 중복체크
        signupViewModel.getCheckNickNameResult().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse checkNickNameResponse) {
                if (checkNickNameResponse.responseCode == 200) {
                    checkNickName = true;
                    checkNickNameBtn.setEnabled(false);
                    checkNickNameBtn.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.logo_color));
                } else if (checkNickNameResponse.responseCode == 401) {
                    checkNickName = false;
                    showMessage("닉네임이 중복됩니다. 다시 설정해주세요.");
                    Log.d("FAIL CHECK NICKNAME", "401 exist user email");
                } else {
                    checkNickName = false;
                    showMessage("닉네임이 중복체크에 실패하셨습니다.400");
                    Log.d("FAIL CHECK NICKNAME", "400 bad request");
                }
            }
        });

        // password 확인 변동
        checkPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeTextPassWord.setText("비밀번호를 한번 더 확인해주세요.");
                changeTextPassWord.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeTextPassWord.setText("일치하지 않습니다.");
                changeTextPassWord.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (joinPassWord.getText().toString().equals(checkPassWord.getText().toString())) {
                    changeTextPassWord.setText("비밀번호가 확인 되었습니다.");
                    changeTextPassWord.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.logo_color));
                } else {
                    changeTextPassWord.setText("비밀번호가 일치하지 않습니다.");
                    changeTextPassWord.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
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

        // NickName 중복체크 버튼
        checkNickNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 변수 string 으로 변환
                EditText editNickName = findViewById(R.id.nickName);

                signupViewModel.checkNickName(editNickName.getText().toString());
            }
        });
    }

    // Toast Message 이용
    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
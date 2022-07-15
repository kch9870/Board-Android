package com.example.boardandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.response.SignupResponse;
import com.example.boardandroid.viewmodel.LoginViewModel;
import com.example.boardandroid.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {
    public SignupViewModel signupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button joinBtn = findViewById(R.id.btnJoin);

        // View Model 설정
        signupViewModel= new ViewModelProvider(this).get(SignupViewModel.class);

        signupViewModel.getSignupResult().observe(this, new Observer<SignupResponse>() {
            @Override
            public void onChanged(SignupResponse signupResponse) {
                if(signupResponse.responseCode == 200){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else Log.d("FAIL SIGNUP","fail signup");
            }
        });

        // 회원가입 버튼
        joinBtn.setOnClickListener( new View.OnClickListener() {
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

                signupViewModel.signup(email,password,name,nickName);
            }
        });
    }

    /**
     * 회원가입 후
     */
    private void onClickJoinActivity(){
        // 다이얼로그 추가

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
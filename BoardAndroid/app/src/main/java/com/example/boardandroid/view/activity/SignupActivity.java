package com.example.boardandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.boardandroid.R;
import com.example.boardandroid.repository.model.BaseResponse;
import com.example.boardandroid.repository.model.UserInfo;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;
import com.example.boardandroid.repository.service.ForumusClient;
import com.example.boardandroid.viewmodel.LoginViewModel;
import com.example.boardandroid.viewmodel.SignupViewModel;

import java.util.Base64;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private SignupViewModel signupViewModel;

    ForumusClient forumusClient = new ForumusClient();
    BaseResponse baseResponse = BaseResponse.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 변수 string 으로 변환
        EditText editEmail = findViewById(R.id.email);
        EditText editPassword = findViewById(R.id.joinPassWord);
        EditText editName = findViewById(R.id.name);
        EditText editNickName = findViewById(R.id.nickName);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String name = editName.getText().toString();
        String nickName = editNickName.getText().toString();

        // View Model 설정
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        // 회원가입 버튼
        Button joinBtn = findViewById(R.id.btnJoin);
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {checkEmptyText(email,password,name,nickName);}
        });

    }

    /**
     * 입력값 빈 값 체크 view model 에서
     */
    public void checkEmptyText(String email,String password,String name,String nickName){
        signupViewModel.checkSignupEvent(email,password,name,nickName);
        if(Boolean.TRUE.equals(signupViewModel.getCheckSignup().getValue())){
            signupViewModel.checkIdEvent(email);
            signupViewModel.checkNickNameEvent(nickName);
            if(Boolean.TRUE.equals(signupViewModel.getCheckId().getValue()) &&
                    Boolean.TRUE.equals(signupViewModel.getCheckNickName().getValue())){
                sighupEvent(email,password,name,nickName);
            }
            else Log.d("CHECK SIGNUP TEST","아이디 닉네임 중복 체 오류");

        }
        else Log.d("CHECK SIGNUP TEST","빈값이 있습니다.");
    }


    /**
     * 회원가입 통신
     *
     * @param email
     * @param password
     * @param name
     * @param nickName
     */
    public void sighupEvent(String email, String password, String name, String nickName) {

    }

    /**
     * 아이디 중복 체크
     */
    public void checkId(String email){

    }

    /**
     * 닉네임 중복 체크
     *
     * @param nickName
     */
    public void checkNickName(String nickName)
    {

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
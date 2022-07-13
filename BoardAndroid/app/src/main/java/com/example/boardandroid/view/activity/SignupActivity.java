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
    UserInfo userInfo = UserInfo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // View Model 설정
        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        // 회원가입 버튼
        Button joinBtn = findViewById(R.id.btnJoin);
        joinBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {checkEmptyText();}
        });

    }
    /**
     * 입력값 빈 값 체크 view model 에서
     */
    public void checkEmptyText(){
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.joinPassWord);
        EditText name = findViewById(R.id.name);
        EditText nickName = findViewById(R.id.nickName);

        signupViewModel.checkSignupEvent(email.getText().toString(),password.getText().toString(),
                name.getText().toString(),nickName.getText().toString());
        if(Boolean.TRUE.equals(signupViewModel.getCheckSignup().getValue())){
            sighupEvent(email.getText().toString(),password.getText().toString(),
                    name.getText().toString(),nickName.getText().toString());
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
        SignupRequest signupRequest = new SignupRequest(email,password,name,nickName);
        // ForumusService -> signService
        Call<SignupResponse> call = forumusClient.forumusService.signupService(signupRequest);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(!response.isSuccessful()){
                    // http 오류 설정 (3xx,4xx,5xx)
                    Log.d("HTTP ERROR","response fail");
                }
                SignupResponse signupResponse = response.body();
                if(signupResponse != null){
                    switch(signupResponse.responseCode){
                        case 200:
                            onClickJoinActivity();
                            break;
                        case 400:
                            Log.d("ERROR400","BAD REQUEST");
                            break;
                    }
                }
                else {
                    // Data Error
                    Log.d("ERROR HTTP", "Http retrofit");
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Log.d("ERROR HTTP","Http retrofit");
            }
        });
    }

    public void checkId(String email){

    }

    public void checkNickName(String nickName)
    {

    }
    private void onClickJoinActivity(){
        // 다이얼로그 추가

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
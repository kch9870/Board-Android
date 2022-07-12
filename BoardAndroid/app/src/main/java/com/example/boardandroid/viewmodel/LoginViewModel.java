package com.example.boardandroid.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.error.ForumusException;
import com.example.boardandroid.repository.model.BaseResponse;
import com.example.boardandroid.repository.model.UserInfo;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.service.ForumusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginViewModel extends ViewModel {
    public ForumusService forumusService;

    public MutableLiveData<Boolean> checkLogin = new MutableLiveData<>();
    public MutableLiveData<LoginResponse> checkLoginResponse = new MutableLiveData<>();

    public int resultResponseCode;
    private String baseUrl = "http://54.213.3.105:3000/";
    /**
     * 로그인 서비스 결과
     *
     */
    public LiveData <Boolean> activityCheckLogin() { return checkLogin;}
    public LiveData <LoginResponse> activityLoginResponse() { return checkLoginResponse; }


    public void setBaseUrl(String baseUrl) {
        forumusService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForumusService.class);
    }

    /**
     * 로그인 통신
     *  @param email
     *  @param password
     */
    public void Login (String email, String password){
        setBaseUrl(baseUrl);

        if(email.isEmpty() || password.isEmpty()){
            checkLogin.setValue(false);
        }
        else{
            checkLogin.setValue(true);
            LoginRequest loginRequest = new LoginRequest(email,password);
            // ForumusService -> loginService
            Call<LoginResponse> call = forumusService.loginService(loginRequest);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(!response.isSuccessful()){
                        // http 오류 설정 (3xx,4xx,5xx)
                        Log.d("HTTP ERROR","response fail");
                    }
                    LoginResponse loginResponse = response.body();
                    UserInfo userInfo = new UserInfo();

                    if(loginResponse != null){
                        switch(loginResponse.responseCode){
                            case 200:
                                userInfo.setUserInfo(loginResponse.userInfo.email,loginResponse.userInfo.name,loginResponse.userInfo.nickName);
                                Log.d("SUCCESS","success retrofit");
                                break;
                            case 400:
                                Log.d("ERROR400","bad request");
                                break;
                            case 401:
                                Log.d("ERROR401","Not match User Password");
                                break;
                            default:
                                Log.d("ERROR500","error");
                                break;
                        }
                    }
                    else{
                        // Data Error
                        Log.d("ERROR HTTP","Http retrofit");
                    }
                   resultResponseCode = loginResponse.responseCode;
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("ERROR HTTP","Http retrofit");
                }
            });
        }
    }

    /**
     * 회원가입
     *
     * @param email
     * @param password
     * @param name
     * @param nickName
     */
    public void Signup(String email,String password,String name, String nickName){

    }

    /**
     * Interface - Response Listener
     */
    public interface ResponseListener<T> {
        void onResponse(T response);
    }

    /**
     * Interface - Error Listener
     */
    public interface ErrorListener {
        void onError(ForumusException error);
    }
}

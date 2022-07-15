package com.example.boardandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.LoginRepository;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;

public class LoginViewModel extends ViewModel {
    MutableLiveData<Integer> mLoginResultMutableData = new MutableLiveData<>();

    LoginRepository mLoginRepository;

    public LoginViewModel() {
        mLoginResultMutableData.postValue(500);
        mLoginRepository = new LoginRepository();
    }

    public void login(String email, String password){
        mLoginRepository.loginRemote(new LoginRequest(email, password), new LoginRepository.ILoginResponse() {
            @Override
            public void onResponse(LoginResponse loginResponse) {
                if(loginResponse.responseCode == 200){
                    mLoginResultMutableData.postValue(loginResponse.responseCode);
                }
                else if (loginResponse.responseCode == 401){
                    mLoginResultMutableData.postValue(loginResponse.responseCode);
                }
                else mLoginResultMutableData.postValue(loginResponse.responseCode);
            }

            @Override
            public void onFailure(Throwable t) {
                mLoginResultMutableData.postValue(500);
            }
        });
    }

    public LiveData<Integer> getLoginResult() {return mLoginResultMutableData; }
}

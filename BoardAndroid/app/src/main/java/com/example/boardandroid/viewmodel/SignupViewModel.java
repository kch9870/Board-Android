package com.example.boardandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.LoginRepository;
import com.example.boardandroid.repository.SignupRepository;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;

public class SignupViewModel extends ViewModel {
    MutableLiveData<SignupResponse> mSignupResultMutableData = new MutableLiveData<>();

    SignupRepository mSignupRepository;

    public SignupViewModel() {
        mSignupRepository = new SignupRepository();
    }

    public void signup(String email, String password, String name, String nickName){
        mSignupRepository.signupRemote(new SignupRequest(email, password, name, nickName), new SignupRepository.ISignupResponse() {
            @Override
            public void onResponse(SignupResponse signupResponse) {
                mSignupResultMutableData.postValue(signupResponse);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public LiveData<SignupResponse> getSignupResult() {return mSignupResultMutableData; }
}

package com.example.boardandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.SignupRepository;
import com.example.boardandroid.repository.model.request.CheckEmailRequest;
import com.example.boardandroid.repository.model.request.CheckNickNameRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.BaseResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;

public class SignupViewModel extends ViewModel {
    MutableLiveData<SignupResponse> mSignupResultMutableData = new MutableLiveData<>();
    MutableLiveData<BaseResponse> mCheckEmailResultMutableData = new MutableLiveData<>();
    MutableLiveData<BaseResponse> mCheckNickNameResultMutableData = new MutableLiveData<>();

    SignupRepository mSignupRepository;

    public SignupViewModel() {
        mSignupRepository = new SignupRepository();
    }

    /**
     * 회원가입 View model
     *
     * @param email
     * @param password
     * @param name
     * @param nickName
     */
    public void signup(String email, String password, String name, String nickName) {
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

    /**
     * 아이디 중복체크 View model
     *
     * @param email
     */
    public void checkEmail(String email) {
        mSignupRepository.checkEmailRemote(new CheckEmailRequest(email), new SignupRepository.ICheckResponse() {
            @Override
            public void onResponse(BaseResponse checkEmailResponse) {
                mCheckEmailResultMutableData.postValue(checkEmailResponse);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    /**
     * 닉네임 중복체크 View model
     *
     * @param nickName
     */
    public void checkNickName(String nickName) {
        mSignupRepository.checkNickNameRemote(new CheckNickNameRequest(nickName), new SignupRepository.ICheckResponse() {
            @Override
            public void onResponse(BaseResponse checkNickNameResponse) {
                mCheckNickNameResultMutableData.postValue(checkNickNameResponse);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public LiveData<SignupResponse> getSignupResult() {return mSignupResultMutableData;}
    public LiveData<BaseResponse> getCheckEmailResult() {return mCheckEmailResultMutableData;}
    public LiveData<BaseResponse> getCheckNickNameResult() {return mCheckNickNameResultMutableData;}
}

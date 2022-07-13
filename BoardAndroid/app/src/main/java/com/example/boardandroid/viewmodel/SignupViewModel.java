package com.example.boardandroid.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.boardandroid.repository.error.ForumusException;
import com.example.boardandroid.repository.service.ForumusService;
import com.example.boardandroid.view.activity.SignupActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupViewModel extends ViewModel {
    public SignupActivity signupActivity;

    /**
     * 회원가입 체크 livedata
     * 아이디 중복체크 livedata
     * 닉네임 중복체크 livedata
     */
    private MutableLiveData<Boolean> checkSignup = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkId = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkNickName = new MutableLiveData<>();

    public LiveData<Boolean> getCheckSignup() { return checkSignup; }
    public LiveData<Boolean> getCheckId() { return checkId; }
    public LiveData<Boolean> getCheckNickName() { return checkNickName; }

    /**
     * 회원가입
     * 아이디 중복체크 확인
     * 닉네임 중복체크 확인
     * 비밀번호 2차 검증 확인
     */
    public void checkSignupEvent(String email, String password, String name, String nickName) {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || nickName.isEmpty()) {
            checkSignup.setValue(false);
            checkId.setValue(true);
        } else checkSignup.setValue(true);
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

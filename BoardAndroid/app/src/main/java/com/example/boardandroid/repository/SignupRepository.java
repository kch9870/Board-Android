package com.example.boardandroid.repository;

import android.util.Log;

import com.example.boardandroid.repository.model.request.CheckEmailRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.BaseResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;
import com.example.boardandroid.repository.service.ForumusClient;
import com.example.boardandroid.repository.service.ForumusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {

    public SignupRepository() {

    }

    /**
     * 회원가입 통신
     *
     * @param signupRequest
     * @param signupResponse
     */
    public void signupRemote(SignupRequest signupRequest, ISignupResponse signupResponse) {
        ForumusService forumusService = ForumusClient.getInstance().create(ForumusService.class);
        Call<SignupResponse> initiateSignup = forumusService.signupService(signupRequest);

        initiateSignup.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()) {
                    signupResponse.onResponse(response.body());
                } else {
                    signupResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });
    }

    /**
     * 아이디 중복 체크
     *
     * @param checkEmailRequest
     * @param checkEmailResponse
     */
    public void checkEmailRemote(CheckEmailRequest checkEmailRequest, ICheckEmailResponse checkEmailResponse) {
        ForumusService forumusService = ForumusClient.getInstance().create(ForumusService.class);
        Call<BaseResponse> checkEmailCall = forumusService.checkEmailService(checkEmailRequest);

        checkEmailCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    checkEmailResponse.onResponse(response.body());
                } else {
                    Log.d("RESPONSE BODY", "FAIL");
                    checkEmailResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Response listener view model로 전달
     */
    public interface ISignupResponse {
        void onResponse(SignupResponse signupResponse);

        void onFailure(Throwable t);
    }

    public interface ICheckEmailResponse {
        void onResponse(BaseResponse baseResponse);

        void onFailure(Throwable t);
    }
}




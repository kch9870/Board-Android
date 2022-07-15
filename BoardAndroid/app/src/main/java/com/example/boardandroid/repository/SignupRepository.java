package com.example.boardandroid.repository;

import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;
import com.example.boardandroid.repository.service.ForumusClient;
import com.example.boardandroid.repository.service.ForumusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupRepository {

    public SignupRepository() {

    }

    public void signupRemote(SignupRequest signupRequest, ISignupResponse signupResponse) {
        ForumusService forumusService = ForumusClient.getInstance().create(ForumusService.class);
        Call<SignupResponse> initiateSignup = forumusService.signupService(signupRequest);

        initiateSignup.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.isSuccessful()){
                    signupResponse.onResponse(response.body());
                }else{
                    signupResponse.onFailure(new Throwable(response.message()));
                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

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
}




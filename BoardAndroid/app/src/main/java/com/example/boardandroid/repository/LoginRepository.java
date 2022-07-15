package com.example.boardandroid.repository;

import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.service.ForumusClient;
import com.example.boardandroid.repository.service.ForumusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    public LoginRepository() {

    }

    public void loginRemote(LoginRequest loginRequest, ILoginResponse loginResponse) {
        ForumusService forumusService = ForumusClient.getInstance().create(ForumusService.class);
        Call<LoginResponse> initiateLogin = forumusService.loginService(loginRequest);

        initiateLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    loginResponse.onResponse(response.body());
                }else{
                    loginResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    public interface ILoginResponse {
        void onResponse(LoginResponse loginResponse);
        void onFailure(Throwable t);
    }
}




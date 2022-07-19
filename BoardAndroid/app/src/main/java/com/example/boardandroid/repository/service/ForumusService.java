package com.example.boardandroid.repository.service;

import com.example.boardandroid.repository.model.request.CheckEmailRequest;
import com.example.boardandroid.repository.model.request.CheckNickNameRequest;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.request.SignupRequest;
import com.example.boardandroid.repository.model.response.BaseResponse;
import com.example.boardandroid.repository.model.response.GetNickNameResponse;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.model.response.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Forumus 서비스
 */
public interface ForumusService {
    // POST
    @POST("users/signin")
    Call<LoginResponse> loginService(@Body LoginRequest loginRequest);

    @POST("users/signup")
    Call<SignupResponse> signupService(@Body SignupRequest signupRequest);

    @POST("users/checkEmail")
    Call<BaseResponse> checkEmailService(@Body CheckEmailRequest checkEmailRequest);

    @POST("users/checkNickName")
    Call<BaseResponse> checkNickNameService(@Body CheckNickNameRequest checkNickNameRequest);

    // GET
    @GET("users/checkEmail")
    Call<GetNickNameResponse> getNickNameService(@Query("userId") int userId);
}

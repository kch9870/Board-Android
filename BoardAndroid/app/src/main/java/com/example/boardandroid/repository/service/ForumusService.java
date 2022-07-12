package com.example.boardandroid.repository.service;

import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Forumus 서비스
 */
public interface ForumusService {
    @POST("users/signin")
    Call<LoginResponse> loginService(@Body LoginRequest loginRequest);
}

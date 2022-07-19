package com.example.boardandroid.repository;

import com.example.boardandroid.repository.model.request.GetNickNameRequest;
import com.example.boardandroid.repository.model.request.LoginRequest;
import com.example.boardandroid.repository.model.response.GetNickNameResponse;
import com.example.boardandroid.repository.model.response.LoginResponse;
import com.example.boardandroid.repository.service.ForumusClient;
import com.example.boardandroid.repository.service.ForumusService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public MainRepository() {

    }

    /**
     * 유저 아이디로 닉네임 불러오기 통신
     *
     * @param getNickNameRequest
     * @param getNickNameResponse
     */
    public void getNickNameRemote(GetNickNameRequest getNickNameRequest, IGetNickNameResponse getNickNameResponse) {
        ForumusService forumusService = ForumusClient.getInstance().create(ForumusService.class);
        Call<GetNickNameResponse> initiateLogin = forumusService.getNickNameService(getNickNameRequest.userId);

        initiateLogin.enqueue(new Callback<GetNickNameResponse>() {
            @Override
            public void onResponse(Call<GetNickNameResponse> call, Response<GetNickNameResponse> response) {
                if(response.isSuccessful()){
                    getNickNameResponse.onResponse(response.body());
                }else{
                    getNickNameResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<GetNickNameResponse> call, Throwable t) {

            }
        });
    }

    public interface IGetNickNameResponse {
        void onResponse(GetNickNameResponse getNickNameResponse);
        void onFailure(Throwable t);
    }
}




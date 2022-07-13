package com.example.boardandroid.repository.service;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Forumus Builder
 */
public class ForumusClient {
    public ForumusService forumusService;
    private String baseUrl = "http://54.213.3.105:3000/";

    public void setBaseUrl() {
        forumusService = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ForumusService.class);
    }

}

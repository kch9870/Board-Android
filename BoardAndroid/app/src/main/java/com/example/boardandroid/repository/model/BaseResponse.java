package com.example.boardandroid.repository.model;

public class BaseResponse {
    private int responseCode;
    private static BaseResponse instance = null;

    public static synchronized BaseResponse getInstance(){
        if(instance == null){
            instance = new BaseResponse();
        }

        return instance;
    }

    // getter, setter
    public void setResponseCode(Integer responseCode){
        this.responseCode = responseCode;
    }
    public int getResponseCode(){
        return responseCode;
    }
}

package com.example.boardandroid.repository.model.request;

public class SignupRequest {
    public String email;
    public String password;
    public String name;
    public String nickName;

    public SignupRequest(String email, String password, String name, String nickName){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }
}

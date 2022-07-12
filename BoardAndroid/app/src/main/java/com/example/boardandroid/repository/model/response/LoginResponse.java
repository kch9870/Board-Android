package com.example.boardandroid.repository.model.response;


public class LoginResponse {

    public int responseCode;
    public String responseMsg;
    public UserInfo userInfo;

    public class UserInfo{
        public int userId;
        public String email;
        public String name;
        public String nickName;
    }
}
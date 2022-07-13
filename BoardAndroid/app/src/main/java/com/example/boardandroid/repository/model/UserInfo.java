package com.example.boardandroid.repository.model;

public class UserInfo {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private int userId;
    private static UserInfo instance = null;

    public static synchronized UserInfo getInstance(){
        if(instance == null){
            instance = new UserInfo();
        }

        return instance;
    }

    public UserInfo(){ }

    public void setUserInfo(String email,String password,String name,String nickName){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }

    public void setUserPublicInfo(String email,String name,String nickName){
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }

    public UserInfo getUserInfo(){
        return this;
    }
}

package com.example.boardandroid.repository.model;

public class UserInfo {
    public String email;
    public String name;
    public String nickName;

    public UserInfo(){
        super();
    }

    public UserInfo(String email,String name,String nickName){
        super();
         this.email = email;
         this.name = name;
         this.nickName = nickName;
    }

    public void setUserInfo(String email,String name,String nickName){
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }
    public String getEmail(){
        return email;
    }
}

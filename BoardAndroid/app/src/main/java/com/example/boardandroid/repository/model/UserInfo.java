package com.example.boardandroid.repository.model;

/**
 * 싱글톤으로 구현 및 유지
 */
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

    public void setUserId(int userId){
        this.userId = userId;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

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
    public int getUserId(){ return userId; }
    public String getNickName(){ return nickName; }
}

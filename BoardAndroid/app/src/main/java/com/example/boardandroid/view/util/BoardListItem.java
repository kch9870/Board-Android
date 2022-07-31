package com.example.boardandroid.view.util;


public class BoardListItem {

    public String title;
    public String nickName;
    public String date;
    public int views;
    public int commentCount;

    public BoardListItem(String title, String nickName, String date, int views, int commentCount){
        this.title= title;
        this.nickName= nickName;
        this.date= date;
        this.views= views;
        this.commentCount= commentCount;
    }

}
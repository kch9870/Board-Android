package com.example.boardandroid.repository.model.response;


public class BoardListResponse {

    public int responseCode;
    public String responseMsg;
    public int pageNo;
    public int totalCount;
    public int lastPage;
    public BoardList boardList;

    public class BoardList{
        public int boardId;
        public String title;
        public String nickName;
        public String date;
        public int views;
        public int commentCount;
    }
}
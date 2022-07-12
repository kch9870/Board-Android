package com.example.boardandroid.repository.error;

public class ForumusException extends Exception{
    public int code;
    public int msg;

    public ForumusException(int code){
        super();
        code = this.code;
    }

    public ForumusException(int code, String msg){
        super(msg);
        code = this.code;
    }
}

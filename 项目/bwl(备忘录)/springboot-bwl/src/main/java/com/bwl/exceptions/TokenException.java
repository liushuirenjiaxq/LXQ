package com.bwl.exceptions;

public class TokenException extends Exception{

    private String msg;

    public String getMsg(){
        return msg;
    }

    public TokenException(String msg){
        super(msg);
        this.msg = msg;
    }
}

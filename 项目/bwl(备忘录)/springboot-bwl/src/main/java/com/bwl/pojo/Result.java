package com.bwl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result data(Object data){
        Result temp = new Result();
        temp.code = 200;
        temp.data = data;
        temp.msg = "success";
        return temp;
    }
    public static Result success(){
        Result temp = new Result();
        temp.code = 200;
        temp.data = null;
        temp.msg = "success";
        return temp;
    }
    public static Result error(String msg){
        Result temp = new Result();
        temp.code = 400;
        temp.data = null;
        temp.msg = msg;
        return temp;
    }
}

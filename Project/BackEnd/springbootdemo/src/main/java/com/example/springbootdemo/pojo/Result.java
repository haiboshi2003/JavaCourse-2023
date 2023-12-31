package com.example.springbootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result <T>{
    private Integer code;
    private String message;
    private String success;
    private T data;

    public static <E> Result<E> success(E data){
        return new Result<>(200, "操作成功","success", data);
    }
    public static Result success(){
        return new Result(200, "操作成功","success", null);
    }
    public static Result error(String message){
        return new Result(400, message,"fail", null);
    }
}

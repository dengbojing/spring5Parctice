package com.abba.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.function.Predicate;

/**
 * @author dengbojing
 * @param <T>
 */
@Data
@Builder
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer status;

    private String message;

    private T data;

    private BaseResponse(){}

    public BaseResponse<T> success(String message, T t){
        this.status = 200;
        this.message = message;
        this.data = t;
        return this;
    }

    public BaseResponse<T> failure(String message, T t){
        this.status = 500;
        this.message = message;
        this.data = t;
        return this;
    }

    public BaseResponse<T> adaptive(Predicate<T> predicate, T t){
        if(predicate.test(t)){
            return success("success",t);
        }else{
            return failure("failure", t);
        }
    }
}
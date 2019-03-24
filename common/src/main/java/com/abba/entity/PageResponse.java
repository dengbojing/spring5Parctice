package com.abba.entity;

import com.abba.entity.vo.BaseVO;
import com.abba.util.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PageResponse<T extends BaseVO> {

    private Integer status;

    private String message;

    private List<T> data;

    private PageResponse(){}

    public PageResponse<T> success(String message, List<T> list){
        this.status = 200;
        if(StringHelper.isEmpty(message)){
            this.message = "success";
        }else{
            this.message = message;
        }
        this.data = list;
        return this;
    }

    public PageResponse<T> adaptive(Predicate<List<T>> p, List<T> list) {
        if(p.test(list)){
            return success("",list);
        }else{
            return faliure("","");
        }

    }

    public PageResponse<T> faliure(String message,String status) {
        if(StringHelper.isEmpty(status)){
            this.status = 500;
        }else{
            this.status = Integer.parseInt(status);
        }
        if(StringHelper.isEmpty(message)){
            this.message = "fail";
        }else{
            this.message = message;
        }
        return this;
    }
}

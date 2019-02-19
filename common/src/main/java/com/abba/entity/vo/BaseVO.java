package com.abba.entity.vo;

import com.abba.util.ObjectJudge;
import org.springframework.beans.BeanUtils;

/**
 * @author dengbojing
 * @param <T>
 */
public abstract class BaseVO<T> {
    public BaseVO(T t,String...ignoreProperties){
        this.copyProperties(t);
    }
    private void copyProperties(T t,String...ignoreProperties){
        if(ObjectJudge.isNotEmpty(t)){
            BeanUtils.copyProperties(t,this,ignoreProperties);
        }
    }

}

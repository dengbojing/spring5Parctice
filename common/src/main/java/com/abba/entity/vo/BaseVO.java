package com.abba.entity.vo;

import com.abba.util.ObjectHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author dengbojing
 * @param <T>
 */
public abstract class BaseVO<T> {
    public BaseVO(T t,String...ignoreProperties){
        this.copyProperties(t);
    }
    private void copyProperties(T t,String...ignoreProperties){
        if(ObjectHelper.isNotEmpty(t)){
            BeanUtils.copyProperties(t,this,ignoreProperties);
        }
    }

}

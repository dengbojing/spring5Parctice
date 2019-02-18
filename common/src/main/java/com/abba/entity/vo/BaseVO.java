package com.abba.entity.vo;

import com.abba.util.ObjectJudge;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author dengbojing
 * @param <T>
 */
@Data
@JsonIgnoreProperties(value = {"entity"})
public abstract class BaseVO<T> {

    protected T entity;

    public BaseVO copyProperties(){
        if(ObjectJudge.isNotEmpty(entity)){
            BeanUtils.copyProperties(entity, this);
        }
        return this;
    }

    protected abstract BaseVO setEntity(T t);
}

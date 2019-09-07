package com.abba.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @author dengbojing
 */
@Getter
@Setter
public abstract class AbstractParam {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组织ID
     */
    private String companyId;


    public void copyTo(Object obj){
        BeanUtils.copyProperties(this, obj);
    }

}

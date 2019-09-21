package com.abba.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

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


    public void copyTo(Object obj,String...ignoreProperties){
        BeanUtils.copyProperties(this, obj, ignoreProperties);
    }

    public <T> T copyTo(Object t,Class<T> c) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(JSON.toJSONString(t),c);
    }

}

package com.abba.entity;

import com.abba.util.ObjectHelper;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author dengbojing
 */
@Slf4j
@NoArgsConstructor
public abstract class AbstractDTO<T extends Serializable> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AbstractDTO(T t, String...ignoreProperties){
        this.copyProperties(t,ignoreProperties);

    }

    private void copyProperties(T t,String...ignoreProperties){
        if(ObjectHelper.isNotEmpty(t)){
            BeanUtils.copyProperties(t,this,ignoreProperties);
        }
    }

    public <E extends AbstractDTO> E fullCopy(T t,Class<E> c) {
        try {
            return objectMapper.readValue(JSON.toJSONString(t),c);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }

}

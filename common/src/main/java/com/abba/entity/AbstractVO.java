package com.abba.entity;

import com.abba.util.ObjectHelper;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.IOException;

/**
 * @author dengbojing
 * @param <T>
 */
@NoArgsConstructor
public abstract class AbstractVO<T extends AbstractDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public AbstractVO(T t, String...ignoreProperties){
        this.copyProperties(t,ignoreProperties);
    }
    private void copyProperties(T t,String...ignoreProperties){
        if(ObjectHelper.isNotEmpty(t)){
            BeanUtils.copyProperties(t,this,ignoreProperties);
        }
    }

    public AbstractVO fullCopy(T t) throws IOException {
        return objectMapper.readValue(JSON.toJSONString(t),AbstractVO.class);
    }


}

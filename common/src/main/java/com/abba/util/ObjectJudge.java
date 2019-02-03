package com.abba.util;

import org.springframework.util.ObjectUtils;

/**
 * @author dengbojing
 */
public class ObjectJudge {

    public static boolean isEmpty(Object obj){
        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}

package com.abba.util;

import org.springframework.util.StringUtils;

/**
 * @author dengbojing
 */
public class StringJudge {

    public static boolean isEmpty(CharSequence seq){
        return StringUtils.isEmpty(seq);
    }

    public static boolean isNotEmpty(CharSequence seq){
        return !isEmpty(seq);
    }

    public static boolean isNull(CharSequence seq){
        return seq == null;
    }

    public static boolean isNotNull(CharSequence seq){
        return seq != null;
    }
}

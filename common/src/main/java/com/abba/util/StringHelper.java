package com.abba.util;


import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author dengbojing
 */
public class StringHelper {

    public static boolean isEmpty(CharSequence seq){
        return org.springframework.util.StringUtils.isEmpty(seq);
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

    public static List<String> split(final CharSequence seq, final String separator){
        Splitter splitter = Splitter.on(separator);
        return splitter.trimResults().splitToList(seq);
    }
}

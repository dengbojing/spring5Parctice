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

    public static boolean isNullValue(CharSequence seq){
        int nullLength = 4;
        if(seq.length() != nullLength){
            return false;
        }else{
            String s1 = String.valueOf(seq.charAt(0));
            String s2 = String.valueOf(seq.charAt(1));
            String s3 = String.valueOf(seq.charAt(2));
            String s4 = String.valueOf(seq.charAt(3));
            if(s1.equalsIgnoreCase("n") && s2.equalsIgnoreCase("u") &&
            s3.equalsIgnoreCase("l") && s4.equalsIgnoreCase("l")){
                return true;
            }else{
                return false;
            }
        }
    }



}

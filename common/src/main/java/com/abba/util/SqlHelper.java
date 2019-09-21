package com.abba.util;

import com.abba.exception.SqlWhereConditionMissingException;

/**
 * @author dengbojing
 */
public class SqlHelper {

    private final static String WHITE_SPACE = " ";

    private final static String WHERE = "where";
    public static void requireWhere(CharSequence cs) throws SqlWhereConditionMissingException {
        if(!StringHelper.split(cs, WHITE_SPACE).contains(WHERE)){
            throw new SqlWhereConditionMissingException();
        }
    }
}

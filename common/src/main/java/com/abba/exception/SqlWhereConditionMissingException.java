package com.abba.exception;

import java.sql.SQLException;

/**
 * @author dengbojing
 */
public class SqlWhereConditionMissingException extends RuntimeException {

    @Override
    public String getMessage(){
        return "where condition is required";
    }

}

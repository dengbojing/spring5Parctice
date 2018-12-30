package com.abba.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author dengbojing
 */
@Data
@Builder
public class User {
    private String id;
    private String name;
    private String gender;
}

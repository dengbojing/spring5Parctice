package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class LoginParam extends AbstractParam {

    private String name;

    private String pwd;
}

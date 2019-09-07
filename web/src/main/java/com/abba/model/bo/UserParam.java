package com.abba.model.bo;

import com.abba.entity.AbstractParam;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class UserParam extends AbstractParam {

    private String id;

    private String loginName;

    private String userName;

    private String gender;

    private String phoneNum;

    private LocalDate birth;

    private String avatar;

    private String address;

    private List<RoleParam> roles;
}

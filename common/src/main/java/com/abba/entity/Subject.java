package com.abba.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class Subject {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 组织ID
     */
    private String companyId;

    /**
     * 组织类型
     */
    private CompanyType companyType;

    @Getter
    public enum CompanyType {
        /**
         * 个人
         */
        PERSONAL,
        /**
         * 企业
         */
        COMPANY
    }
}

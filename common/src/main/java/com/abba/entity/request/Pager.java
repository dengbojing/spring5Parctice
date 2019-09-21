package com.abba.entity.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class Pager<T>{


    /**
     * 每页条数
     */
    private Integer pageSize=10;

    /**
     * 页码
     */
    private Integer pageNum=1;

    /**
     * 总页数
     */
    private Integer pageCount;

    /**
     * 总条数
     */
    private Long totalCount;

    /**
     * 数据集合
     */
    private List<T> data;
}

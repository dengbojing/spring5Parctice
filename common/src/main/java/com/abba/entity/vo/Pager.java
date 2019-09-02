package com.abba.entity.vo;

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
    private int pageSize;

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 总页数
     */
    private int pageCount;

    /**
     * 总条数
     */
    private int totalCount;

    /**
     * 数据集合
     */
    private List<T> data;
}

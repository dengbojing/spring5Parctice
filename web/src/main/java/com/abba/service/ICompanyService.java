package com.abba.service;

import com.abba.entity.request.Pager;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface ICompanyService<T> {


    /**
     * 添加公司
     * @param t
     * @return
     */
    Optional<Integer> add(T t);

    /**
     * 批量添加
     * @param list
     * @return
     */
    Optional<Integer> add(List<T> list);
    /**
     * 转换公司经纬度
     */
    void parseLocation();

    void locationParse1(Pager pager);

    Optional<Integer> excelParse2 () throws IOException;



}


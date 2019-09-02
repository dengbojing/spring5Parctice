package com.abba.service;

import com.abba.entity.vo.Pager;
import com.abba.model.Company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author dengbojing
 */
public interface ICompanyService<T> {


    Optional<Integer> add(T t);
    Optional<Integer> add(List<T> list);
    /**
     * 转换公司经纬度
     */
    void parseLocation();

    void locationParse1(Pager pager);

    Optional<Integer> excelParse2 () throws IOException;



}


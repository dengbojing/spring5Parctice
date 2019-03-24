package com.abba.util;

import com.abba.entity.vo.BaseVO;
import com.abba.entity.vo.Pager;

import java.util.List;

public interface PageHelper<T extends BaseVO> {
    List<T> doPage();
}

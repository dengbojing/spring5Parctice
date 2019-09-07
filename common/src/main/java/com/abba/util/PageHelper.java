package com.abba.util;

import com.abba.entity.AbstractVO;

import java.util.List;

public interface PageHelper<T extends AbstractVO> {
    List<T> doPage();
}

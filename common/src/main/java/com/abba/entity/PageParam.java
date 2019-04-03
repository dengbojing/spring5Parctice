package com.abba.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PageParam<T> {

    private Integer pageSize = 10;

    private Integer pageNum = 1;

    private T t;
}

package com.abba.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest<T> {
    private T data;

    private String ip;

}

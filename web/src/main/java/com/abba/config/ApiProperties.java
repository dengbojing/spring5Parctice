package com.abba.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengbojing
 */
@Getter
@Setter
public class ApiProperties {

    private Boolean enabled = true;

    private Integer step = 20;

    private Integer ms = 400;

    private Boolean log = true;
}

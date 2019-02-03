package com.abba.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengbojing
 */
@Configuration
@ComponentScan({"com.abba.service","com.abba.filter","com.abba.handler"})
public class RootConfig {
}

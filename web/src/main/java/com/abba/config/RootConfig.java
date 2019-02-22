package com.abba.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dengbojing
 */
@Configuration
@ComponentScan({"com.abba.service","com.abba.filter","com.abba.handler"})
public class RootConfig {
}

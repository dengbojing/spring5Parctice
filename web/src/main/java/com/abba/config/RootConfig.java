package com.abba.config;

import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * @author dengbojing
 */
@Configuration
@ComponentScan({"com.abba.service","com.abba.filter","com.abba.handler", "com.abba.repository"})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class RootConfig {

    /**
     * ./encrypt.sh input='Qwer!234' password='jasypt' algorithm='PBEWithMD5AndTripleDES'
     * @return 加密器
     */
    @Bean
    public static BasicTextEncryptor basicTextEncryptor() {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("jas");
        return basicTextEncryptor;
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {
        EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(basicTextEncryptor());
        propertyConfigurer.setLocation(new ClassPathResource("db.properties"));
        return propertyConfigurer;
    }
}

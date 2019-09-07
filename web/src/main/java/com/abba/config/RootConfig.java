package com.abba.config;

import com.abba.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * @author dengbojing
 */
@Configuration
@ComponentScan({"com.abba.service", "com.abba.global.*", "com.abba.repository"})
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL_FORMS)
public class RootConfig {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.clientId}")
    private String clientId;

    @Value("${jwt.timeout}")
    private Long timeout;


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

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
        propertyConfigurer.setLocation(new ClassPathResource("config.properties"));
        return propertyConfigurer;
    }

    @Bean(name = "jwtUtil")
    public JwtUtil jwtBean(){
        return new JwtUtil(key,clientId,timeout,objectMapper());
    }

}

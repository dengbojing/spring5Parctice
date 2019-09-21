package com.abba.config;

import com.abba.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
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

import java.util.Properties;

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

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}

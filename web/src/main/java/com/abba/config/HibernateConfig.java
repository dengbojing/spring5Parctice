package com.abba.config;

import com.zaxxer.hikari.HikariDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author dengbojing
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.abba.dao"})
@PropertySource({"classpath:hibernate.properties","classpath:db.properties"})
public class HibernateConfig {

    private Environment env;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    @Autowired
    HibernateConfig(final Environment env){
        this.env = env;
    }

    @Bean
    public DataSource hikaricpDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(15);
        dataSource.setMinimumIdle(5);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl( "jdbc:mysql://49.4.71.128:3306/test?autoReconnect=true&useSSL=false");
        dataSource.setUsername( "root");
        dataSource.setPassword( "Qwer!234");
        dataSource.setAutoCommit(true);
        dataSource.setIdleTimeout(740000);
        dataSource.setMaxLifetime(1740000);
        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(hikaricpDataSource());
        sessionFactory.setPackagesToScan("com.abba.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache",env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.region.factory_class",env.getProperty("hibernate.cache.region.factory_class"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache",env.getProperty("hibernate.cache.use_query_cache"));
        hibernateProperties.setProperty("hibernate.cache.ehcache.missing_cache_strategy",env.getProperty("hibernate.cache.ehcache.missing_cache_strategy"));
        return hibernateProperties;
    }


    @Bean
    public static EnvironmentStringPBEConfig environmentVariablesConfiguration() {
        EnvironmentStringPBEConfig environmentVariablesConfiguration = new EnvironmentStringPBEConfig();
        environmentVariablesConfiguration.setAlgorithm("PBEWithMD5AndTripleDES");
        //environmentVariablesConfiguration.setPasswordEnvName("CAS_PBE_PASSWORD");
        //super.setPassword(System.getenv(passwordEnvName));
        environmentVariablesConfiguration.setPassword("jasypt");
        return environmentVariablesConfiguration;
    }

    /**
     * ./encrypt.sh input='Qwer!234' password='jasypt' algorithm='PBEWithMD5AndTripleDES'
     * @return
     */
    @Bean
    public static StringEncryptor configurationEncryptor() {
        StandardPBEStringEncryptor configurationEncryptor = new StandardPBEStringEncryptor();
        configurationEncryptor.setConfig(environmentVariablesConfiguration());
        String s = configurationEncryptor.encrypt("Qwer!234");
        System.out.println(s);
        return configurationEncryptor;
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {
        EncryptablePropertyPlaceholderConfigurer propertyConfigurer = new EncryptablePropertyPlaceholderConfigurer(configurationEncryptor());
        propertyConfigurer.setLocation(new ClassPathResource("database.properties"));
        // propertyConfigurer.setLocation(resource);
        return propertyConfigurer;
    }
}



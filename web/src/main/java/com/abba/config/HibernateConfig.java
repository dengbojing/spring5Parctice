package com.abba.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.hikaricp.internal.HikariCPConnectionProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author dengbojing
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.abba.dao"})
@PropertySource({"classpath:hibernate.properties"})
@Slf4j
public class HibernateConfig {

    private final Environment env;

    @Value("${hibernate.connection.username}")
    private String username;

    @Value("${hibernate.connection.password}")
    private String password;

    @Value("${hibernate.connection.url}")
    private String url;


    @Autowired
    HibernateConfig(final Environment env){
        this.env = env;
    }


    /**
     * 使用hibernate默认选取数据源策略,不显示配置dataSource
     *
     * If hibernate.connection.provider_class is set, it takes precedence
     *
     * else if hibernate.connection.datasource is set → Using DataSources
     *
     * else if any setting prefixed by hibernate.c3p0. is set → Using c3p0
     *
     * else if any setting prefixed by hibernate.proxool. is set → Using Proxool
     *
     * else if any setting prefixed by hibernate.hikari. is set → Using Hikari
     *
     * else if hibernate.connection.url is set → Using Hibernate’s built-in (and unsupported) pooling
     *
     * else → User-provided Connections
     *
     * @return
     *
     *
     *  */
    @Bean
    public PersistenceProvider manager(){
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        return provider;

    }

    /**
     *      * 添加hibernate-hikari jar包
     *      * hibernate直接可以设置hikari属性
     *      * @return hikariDataSource
     *
     *
     *    */
    @Bean
    @Deprecated
    public DataSource hikaricpDataSource(){
        HikariDataSource hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(15);
        hikari.setMinimumIdle(5);
        hikari.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikari.setJdbcUrl( "jdbc:mysql://49.4.71.128:3306/test?autoReconnect=true&useSSL=false");
        hikari.setUsername( username);
        hikari.setPassword( password);
        hikari.setAutoCommit(true);
        hikari.setIdleTimeout(740000);
        hikari.setMaxLifetime(1740000);
        hikari.addDataSourceProperty("cachePrepStmts", true);
        hikari.addDataSourceProperty("prepStmtCacheSize", 250);
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikari.addDataSourceProperty("useServerPrepStmts", true);
        return hikari;
    }

    /**
     * LocalSessionFactoryBean 可以直接替代JPA LocalContainerEntityManagerFactoryBean
     * 对外暴露EntityManagerFactory以及直接使用HibernateTransactionManager作为jpaTransaction
     * 详见LocalSessionFactoryBean 注释
     * @return sessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
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

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        //sql config
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
        //cache config
        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache",env.getProperty("hibernate.cache.use_second_level_cache"));
        hibernateProperties.setProperty("hibernate.cache.redisson.config",env.getProperty("hibernate.cache.redisson.config"));
        hibernateProperties.setProperty("hibernate.cache.region.factory_class",env.getProperty("hibernate.cache.region.factory_class"));
        hibernateProperties.setProperty("hibernate.cache.use_query_cache",env.getProperty("hibernate.cache.use_query_cache"));
        hibernateProperties.setProperty("hibernate.cache.ehcache.missing_cache_strategy",env.getProperty("hibernate.cache.ehcache.missing_cache_strategy"));
        //connection config
        hibernateProperties.setProperty("hibernate.connection.driver_class",env.getProperty("hibernate.connection.driver_class"));
        hibernateProperties.setProperty("hibernate.connection.provider_class",env.getProperty("hibernate.connection.provider_class"));
        hibernateProperties.setProperty("hibernate.connection.url",url);
        hibernateProperties.setProperty("hibernate.connection.username",username);
        hibernateProperties.setProperty("hibernate.connection.password",password);
        hibernateProperties.setProperty("hibernate.connection.autocommit",env.getProperty("hibernate.connection.autocommit"));
        // datasource config
        hibernateProperties.setProperty("hibernate.hikari.minimumIdle",env.getProperty("hibernate.hikari.minimumIdle"));
        hibernateProperties.setProperty("hibernate.hikari.maximumPoolSize",env.getProperty("hibernate.hikari.maximumPoolSize"));
        hibernateProperties.setProperty("hibernate.hikari.idleTimeout",env.getProperty("hibernate.hikari.idleTimeout"));
        return hibernateProperties;
    }

}



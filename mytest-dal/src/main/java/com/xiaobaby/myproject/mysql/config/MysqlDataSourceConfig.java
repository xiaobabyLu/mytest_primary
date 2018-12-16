package com.xiaobaby.myproject.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import javax.sql.DataSource;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午3:53
 */

@Configuration
public class MysqlDataSourceConfig {


    @Value("${mysql.datasource.driver}")
    private String driverClass;

    @Value("${mysql.datasource.url}")
    private String url;

    @Value("${mysql.datasource.username}")
    private String user;

    @Value("${mysql.datasource.password}")
    private String password;


    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
}

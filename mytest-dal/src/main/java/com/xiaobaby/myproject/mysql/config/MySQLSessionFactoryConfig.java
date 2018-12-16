package com.xiaobaby.myproject.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午4:27
 */

@Configuration
@MapperScan(basePackages = "com.xiaobaby.myproject.mysql.mapper", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@EnableTransactionManagement
public class MySQLSessionFactoryConfig {

    @Resource(name ="mysqlDataSource")
    private DataSource mysqlDataSource;

    @Bean(name ="mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory getMysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource);
        ResourcePatternResolver resolver  = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager buildPopTx() {
        return new DataSourceTransactionManager(mysqlDataSource);
    }
}

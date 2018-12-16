package com.xiaobaby.myproject.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午4:34
 */
@Configuration
public class MySQLSessionTemplateConfig {


    @Resource(name = "mysqlSqlSessionFactory")
    private SqlSessionFactory mysqlSqlSessionFactory;



    @Bean(name = "mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(mysqlSqlSessionFactory);
    }

}

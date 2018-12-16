package com.xiaobaby.myproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lu Yufeng
 * @date 2018/7/12 下午2:46
 */

@SpringBootApplication
@MapperScan("com.xiaobaby.myproject.mysql.mapper")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

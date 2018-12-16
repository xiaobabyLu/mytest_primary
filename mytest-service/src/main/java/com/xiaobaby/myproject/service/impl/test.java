package com.xiaobaby.myproject.service.impl;


import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

/**
 * @author Lu Yufeng
 * @date 2018/9/20 下午9:28
 */

public class test {

    @Value("#{ systemProperties['java.version'] }")
    private static String javaVersion;

    @Value("#{ T(java.lang.math).random() }")
    private static double number;


    public static void main(String[] args) {
        String data1 = "1.1079463895E10";


        BigDecimal big = new BigDecimal(data1);
        System.out.println(big.setScale( 2));

        System.out.println(Double.parseDouble(data1));

        System.out.println(BigDecimal.valueOf(Double.parseDouble(data1)).setScale( 2));


        System.out.println(javaVersion);
        System.out.println(number);
    }

}

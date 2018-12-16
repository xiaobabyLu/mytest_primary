package com.xiaobaby.myproject.service.server.impl;

import com.xiaobaby.myproject.service.server.HelloWorld;
import org.apache.thrift.TException;

/**
 * @author Lu Yufeng
 * @date 2018/9/29 下午5:47
 */
public class HelloWorldServiceImpl implements HelloWorld.Iface {
    @Override
    public String sendString(String para) throws TException {


        System.out.println("this is a test");
        return "信息接收成功！";
    }
}

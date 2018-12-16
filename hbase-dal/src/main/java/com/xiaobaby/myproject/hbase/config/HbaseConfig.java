package com.xiaobaby.myproject.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lu Yufeng
 * @date 2018/7/22 下午2:45
 */
@Configuration
public class HbaseConfig {


    @Value("${hbase.address}")
    private String hbaseAddress;

    @Value("${hbase.port}")
    private String hbasePort;

    private HConnection hConnection = null;

    @Bean(name = "hConnection")
    public HConnection getHconnection() throws Exception {
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", hbaseAddress);
        conf.set("hbase.zookeeper.property.clientPort", hbasePort);

        if(hConnection == null) {
            hConnection = HConnectionManager.createConnection(conf);
        }
        return hConnection;
    }
}

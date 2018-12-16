package com.xiaobaby.myproject.hbase.component;


import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/22 下午3:52
 */

@Component
public class HbaseQueryWrapper  {
    public static final Logger LOGGER = LoggerFactory.getLogger(HbaseQueryWrapper.class);

    @Autowired
    private HConnection hConnection ;

    /**
     * 根据开始结束key值批量查询hbase数据
     * @param tableName hbase表名
     * @param startRowkey 查询开始的key值
     * @param stopRowkey 结束查询的key值
     * */

    public ResultScanner queryByRowkeyPrefix(String tableName,String startRowkey,String stopRowkey){
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRowkey));
        scan.setStopRow(Bytes.toBytes(stopRowkey));
        scan.setBatch(10);
        ResultScanner resultScanner = null;
        HTableInterface hTableInterface =null;
        try {
            hTableInterface = hConnection.getTable(tableName);
            resultScanner = hTableInterface.getScanner(scan);
        } catch (IOException e) {
            LOGGER.error("query failed :" + e);
        }finally {
            try {
                hTableInterface.close();
            } catch (IOException e) {
                LOGGER.error("habse table close failed :" + e);
            }
        }
        return resultScanner;
    }

    /**
     * 根据单值key查询数据
     * @param tableName hbase表名
     * @param familyName 列簇
     * @param qualifier 列修饰符对应列名
     * @param rowkey 查询的key
     * */
    public Result queryByRowkeyQualifier(String tableName,String familyName,String qualifier,String rowkey){
        Result result = null;
        HTableInterface hTableInterface = null;

        try {
            hTableInterface = hConnection.getTable(tableName);
            if(hTableInterface != null){
                Get get = new Get(Bytes.toBytes(rowkey));
                get.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(qualifier));
                result = hTableInterface.get(get);
            }
        } catch (IOException e) {
            LOGGER.error("query failed :" + e);
        }finally {
            try {
                hTableInterface.close();
            } catch (IOException e) {
                LOGGER.error("habse table close failed :" + e);
            }
        }
        return result;
    }

    /**
     * 根据List<key> 列簇、列修饰符 查询批量数据
     * @param tableName hbase表名
     * @param rowkeyList 查询的key值列表
     * */
    public Result[] queryByBathRowkey(String tableName,String familyName,String qualifier,List<String> rowkeyList){

        List<Get> getList = new ArrayList<>();

        HTableInterface hTableInterface = null;
        Result[] results = null;

        try {
            hTableInterface = hConnection.getTable(tableName);
            for(String rowkey:rowkeyList){
                Get get = new Get(Bytes.toBytes(rowkey));
                get.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(qualifier));
                getList.add(get);
            }
            results = hTableInterface.get(getList);

        } catch (IOException e) {
            LOGGER.error("query failed :" + e);
        }finally {
            try {
                hTableInterface.close();
            } catch (IOException e) {
                LOGGER.error("habse table close failed :" + e);
            }
        }
        return results;
    }

    /**
     * 根据List<key>查询数据
     * @param tableName hbase表名
     * @param rowkeyList 查询的key值列表
     * */
    public Result[] queryByBathRowkey(String tableName,List<String> rowkeyList){

        List<Get> getList = new ArrayList<>();

        HTableInterface hTableInterface = null;
        Result[] results = null;

        try {
            hTableInterface = hConnection.getTable(tableName);
            for(String rowkey:rowkeyList){
                Get get = new Get(Bytes.toBytes(rowkey));
                getList.add(get);
            }
            results = hTableInterface.get(getList);

        } catch (IOException e) {
            LOGGER.error("query failed :" + e);
        }finally {
            try {
                hTableInterface.close();
            } catch (IOException e) {
                LOGGER.error("habse table close failed :" + e);
            }
        }
        return results;
    }

    /**
     * 根据单值key查询数据
     * @param tableName hbase表名
     * @param rowkey  查询的key值
     * */
    public Result queryByRowkey(String tableName,String rowkey){

        List<Get> getList = new ArrayList<>();

        HTableInterface hTableInterface = null;
        Result result = null;

        try {
            hTableInterface = hConnection.getTable(tableName);
            Get get = new Get(Bytes.toBytes(rowkey));
            result = hTableInterface.get(get);
        } catch (IOException e) {
            LOGGER.error("query failed :" + e);
        }finally {
            try {
                hTableInterface.close();
            } catch (IOException e) {
                LOGGER.error("habse table close failed :" + e);
            }
        }
        return result;
    }


}


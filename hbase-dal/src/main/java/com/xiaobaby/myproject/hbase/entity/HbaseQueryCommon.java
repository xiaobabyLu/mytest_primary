package com.xiaobaby.myproject.hbase.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author Lu Yufeng
 * @date 2018/7/24 下午4:43
 */
public class HbaseQueryCommon {

    private String rowkey;
    private String familyName;
    private String qualifier;
    private String value;
    private long  timestamp;


    public String getRowkey() {
        return rowkey;
    }

    public void setRowkey(String rowkey) {
        this.rowkey = rowkey;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

package com.xiaobaby.myproject.hbase.po;

import com.alibaba.fastjson.JSON;

/**
 * @author Lu Yufeng
 * @date 2018/7/24 上午10:24
 */
public class  RealtimeOrderSumPO {

    private Integer staticDate;

    private Double income;

    @Override
    public String toString() {

        return JSON.toJSONString(this);

    }

    public Integer getStaticDate() {
        return staticDate;
    }

    public void setStaticDate(Integer staticDate) {
        this.staticDate = staticDate;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}

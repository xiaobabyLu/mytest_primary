package com.xiaobaby.myproject.hbase.dao;

import com.xiaobaby.myproject.hbase.po.RealtimeOrderSumPO;


/**
 * @author Lu Yufeng
 * @date 2018/7/24 上午10:21
 */
public interface HbaseRealtimeDAO {

   RealtimeOrderSumPO queryOrderRealtimeSumPO(Integer startDate);


}

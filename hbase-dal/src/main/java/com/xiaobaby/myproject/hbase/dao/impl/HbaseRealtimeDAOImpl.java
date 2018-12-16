package com.xiaobaby.myproject.hbase.dao.impl;


import com.xiaobaby.myproject.hbase.component.HbaseQueryWrapper;
import com.xiaobaby.myproject.hbase.component.QueryResultPaser;
import com.xiaobaby.myproject.hbase.dao.HbaseRealtimeDAO;
import com.xiaobaby.myproject.hbase.entity.HbaseQueryCommon;
import com.xiaobaby.myproject.hbase.po.RealtimeOrderSumPO;
import com.xiaobaby.myproject.hbase.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/24 上午10:22
 */
@Service
public class HbaseRealtimeDAOImpl implements HbaseRealtimeDAO {

    @Autowired
    private HbaseQueryWrapper hbaseQueryWrapper;

    @Autowired
    private QueryResultPaser queryResultPaser;

    @Override
    public RealtimeOrderSumPO queryOrderRealtimeSumPO(Integer startDate) {

        RealtimeOrderSumPO realtimeOrderSumPO = new RealtimeOrderSumPO();

        String rowkey= "MTA:D:".concat(String.valueOf(startDate));

        System.out.println(rowkey);

        List<HbaseQueryCommon> maps = queryResultPaser.result2Map(hbaseQueryWrapper.queryByRowkey(Constants.HBASE_AM_REALTIME_ORDER_TABLE,rowkey));


        for(HbaseQueryCommon map:maps){

            if("column_name".equals(map.getQualifier())){
                realtimeOrderSumPO.setIncome(Double.valueOf(map.getValue()));
            }
        }

        if(realtimeOrderSumPO.getIncome()!=null){
            realtimeOrderSumPO.setStaticDate(startDate);
        }

        return realtimeOrderSumPO;
    }


}

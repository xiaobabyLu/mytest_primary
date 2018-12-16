package com.xiaobaby.myproject.hbase.component;

import com.google.common.collect.Lists;
import com.xiaobaby.myproject.hbase.entity.HbaseQueryCommon;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lu Yufeng
 * @date 2018/7/24 下午2:24
 */
@Component
public class QueryResultPaser {
    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param results 输入Hbase返回数据列表
     * */
    public List<HbaseQueryCommon> results2Map(Result[] results, String typeName){

        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();

        for (Result result : results) {
            if(result!=null && result.listCells()!=null){

                hbaseQueryCommonList.addAll(result2Map(result,typeName));
            }
        }
        return hbaseQueryCommonList;
    }

    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param results 输入Hbase返回数据列表
     * */
    public List<HbaseQueryCommon> results2Map(Result[] results){

        String typeName ="Long";
        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();

        for (Result result : results) {
            if(result!=null && result.listCells()!=null){

                hbaseQueryCommonList.addAll(result2Map(result,typeName));
            }
        }
        return hbaseQueryCommonList;
    }

    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param results 输入Hbase返回数据列表
     * */
    public List<HbaseQueryCommon> results2Map(ResultScanner results, String typeName){

        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();

        for (Result result : results) {

            if(result!=null && result.listCells()!=null){

                hbaseQueryCommonList.addAll(result2Map(result,typeName));
            }
        }
        return hbaseQueryCommonList;
    }

    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param results 输入Hbase返回数据列表
     * */
    public List<HbaseQueryCommon> results2Map(ResultScanner results){

        String typeName ="Long";

        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();

        for (Result result : results) {

            if(result!=null && result.listCells()!=null){

                hbaseQueryCommonList.addAll(result2Map(result,typeName));
            }
        }
        return hbaseQueryCommonList;
    }


    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param result 输入Hbase返回数据
     * */
    public List<HbaseQueryCommon> result2Map(Result result) {
        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();
        String typeName ="Long";

        if(result!=null && result.listCells()!=null){
            for(Cell cell:result.rawCells()){

                hbaseQueryCommonList.add(parserCell2Map(cell,typeName));

            }
        }
        return hbaseQueryCommonList;
    }


    /**
     * 根据Hbase返回的结果数据进行解析
     *
     * @param result 输入Hbase返回数据
     * */
    public List<HbaseQueryCommon> result2Map(Result result,String typeName) {
        List<HbaseQueryCommon> hbaseQueryCommonList = Lists.newArrayList();


        if(result!=null && result.listCells()!=null){
            for(Cell cell:result.rawCells()){

                hbaseQueryCommonList.add(parserCell2Map(cell,typeName));
            }
        }


        return hbaseQueryCommonList;
    }



    public HbaseQueryCommon parserCell2Map(Cell cell,String typeName){

        HbaseQueryCommon hbaseQueryCommon = new HbaseQueryCommon();

        hbaseQueryCommon.setRowkey(Bytes.toString(CellUtil.cloneRow(cell)));

        hbaseQueryCommon.setFamilyName(Bytes.toString(CellUtil.cloneFamily(cell)));

        hbaseQueryCommon.setQualifier(Bytes.toString(CellUtil.cloneQualifier(cell)));

        hbaseQueryCommon.setTimestamp(cell.getTimestamp());

        switch (typeName){
            case "String":
                hbaseQueryCommon.setValue(Bytes.toString(CellUtil.cloneValue(cell)));
                break;
            case "Integer":
                hbaseQueryCommon.setValue(String.valueOf(Bytes.toInt(CellUtil.cloneValue(cell))));
                break;
            case "Long":
                hbaseQueryCommon.setValue(String.valueOf(Bytes.toLong(CellUtil.cloneValue(cell))));
                break;
            case "Double":
                hbaseQueryCommon.setValue(String.valueOf(Bytes.toDouble(CellUtil.cloneValue(cell))));
                break;
            case "Short":
                hbaseQueryCommon.setValue(String.valueOf(Bytes.toShort(CellUtil.cloneValue(cell))));
                break;
            case "Float":
                hbaseQueryCommon.setValue(String.valueOf(Bytes.toFloat(CellUtil.cloneValue(cell))));
                break;
        }

        return hbaseQueryCommon;
    }
}

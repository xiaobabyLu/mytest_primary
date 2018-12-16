package com.xiaobaby.myproject.mysql.dao;

import com.xiaobaby.myproject.mysql.model.OfflineDataEntry;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:11
 */
public interface OfflineDataEntryDAO {

    List<OfflineDataEntry> queryOfflineDataEntry(String id);

}

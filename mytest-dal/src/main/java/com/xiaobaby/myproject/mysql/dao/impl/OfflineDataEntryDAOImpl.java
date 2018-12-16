package com.xiaobaby.myproject.mysql.dao.impl;

import com.xiaobaby.myproject.mysql.dao.OfflineDataEntryDAO;
import com.xiaobaby.myproject.mysql.mapper.OfflineDataEntryMapper;
import com.xiaobaby.myproject.mysql.model.OfflineDataEntry;
import com.xiaobaby.myproject.mysql.model.OfflineDataEntryExample;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:14
 */
@Service
public class OfflineDataEntryDAOImpl implements OfflineDataEntryDAO{

    public static final Logger LOGGER = LogManager.getLogger(OfflineDataEntryDAOImpl.class);


    @Autowired
    private OfflineDataEntryMapper offlineDataEntryMapper;

    @Override
    public List<OfflineDataEntry> queryOfflineDataEntry(String id) {


        OfflineDataEntryExample offlineDataEntryExample = new OfflineDataEntryExample();
        OfflineDataEntryExample.Criteria criteria = offlineDataEntryExample.createCriteria();

        criteria.andStoreIdEqualTo(id);


        return offlineDataEntryMapper.selectByExample(offlineDataEntryExample);
    }
}

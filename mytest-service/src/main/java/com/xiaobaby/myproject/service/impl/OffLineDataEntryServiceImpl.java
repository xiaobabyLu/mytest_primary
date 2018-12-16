package com.xiaobaby.myproject.service.impl;

import com.google.common.collect.Lists;
import com.xiaobaby.myproject.mysql.dao.OfflineDataEntryDAO;
import com.xiaobaby.myproject.mysql.model.OfflineDataEntry;
import com.xiaobaby.myproject.service.OffLineDataEntryService;
import com.xiaobaby.myproject.vo.OffLineDataEntryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:24
 */

@Service
public class OffLineDataEntryServiceImpl implements OffLineDataEntryService {

    @Autowired
    private OfflineDataEntryDAO offlineDataEntryDAO;


    @Override
    public List<OffLineDataEntryVO> getOffLineDataEntryList(String id) {


        List<OfflineDataEntry> offlineDataEntryList = offlineDataEntryDAO.queryOfflineDataEntry(id);

        List<OffLineDataEntryVO> offLineDataEntryVOList = Lists.newArrayList();

        for(OfflineDataEntry offlineDataEntry:offlineDataEntryList){
            OffLineDataEntryVO offLineDataEntryVO = new OffLineDataEntryVO();
            BeanUtils.copyProperties(offlineDataEntry,offLineDataEntryVO);

            offLineDataEntryVOList.add(offLineDataEntryVO);

        }

        return offLineDataEntryVOList;
    }
}

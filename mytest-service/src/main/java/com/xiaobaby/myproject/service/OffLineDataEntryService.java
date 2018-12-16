package com.xiaobaby.myproject.service;

import com.xiaobaby.myproject.vo.OffLineDataEntryVO;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:23
 */
public interface OffLineDataEntryService {

    List<OffLineDataEntryVO> getOffLineDataEntryList(String id);
}

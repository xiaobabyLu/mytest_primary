package com.xiaobaby.myproject.controller;

import com.google.common.collect.Lists;
import com.xiaobaby.myproject.common.RestResponse;
import com.xiaobaby.myproject.common.StatusMsg;
import com.xiaobaby.myproject.service.OffLineDataEntryService;
import com.xiaobaby.myproject.vo.OffLineDataEntryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lu Yufeng
 * @date 2018/7/16 下午2:35
 */

@RestController

@RequestMapping(value = "/api")
public class OffLineDataEntryController {


    public static final Logger LOGGER = LoggerFactory.getLogger(OffLineDataEntryController.class);

    @Autowired
    private OffLineDataEntryService offLineDataEntryService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public RestResponse<List<OffLineDataEntryVO>> getOffLineDataEntryData(@RequestParam(value = "id") String id){

        RestResponse<List<OffLineDataEntryVO>> restResponse;

        List<OffLineDataEntryVO> offLineDataEntryVOList = Lists.newArrayList();

        offLineDataEntryVOList = offLineDataEntryService.getOffLineDataEntryList(id);

        restResponse = RestResponse.result(offLineDataEntryVOList, StatusMsg.OK);

        return restResponse;

    }


}

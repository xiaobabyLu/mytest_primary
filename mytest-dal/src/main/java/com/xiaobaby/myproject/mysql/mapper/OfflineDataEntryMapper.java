package com.xiaobaby.myproject.mysql.mapper;

import com.xiaobaby.myproject.mysql.model.OfflineDataEntry;
import com.xiaobaby.myproject.mysql.model.OfflineDataEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OfflineDataEntryMapper {
    long countByExample(OfflineDataEntryExample example);

    int deleteByExample(OfflineDataEntryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OfflineDataEntry record);

    int insertSelective(OfflineDataEntry record);

    List<OfflineDataEntry> selectByExampleWithRowbounds(OfflineDataEntryExample example, RowBounds rowBounds);

    List<OfflineDataEntry> selectByExample(OfflineDataEntryExample example);

    OfflineDataEntry selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OfflineDataEntry record, @Param("example") OfflineDataEntryExample example);

    int updateByExample(@Param("record") OfflineDataEntry record, @Param("example") OfflineDataEntryExample example);

    int updateByPrimaryKeySelective(OfflineDataEntry record);

    int updateByPrimaryKey(OfflineDataEntry record);
}
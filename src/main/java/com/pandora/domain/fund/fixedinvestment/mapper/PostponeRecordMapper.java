package com.pandora.domain.fund.fixedinvestment.mapper;

import com.pandora.domain.fund.fixedinvestment.model.PostponeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostponeRecordMapper {

    int batchInsert(@Param("records")List<PostponeRecord> records);
}

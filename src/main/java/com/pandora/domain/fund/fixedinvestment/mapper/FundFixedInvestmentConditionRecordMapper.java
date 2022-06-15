package com.pandora.domain.fund.fixedinvestment.mapper;


import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface FundFixedInvestmentConditionRecordMapper {

    int insert(FundFixedInvestmentConditionRecord record);

    Integer findCountByConditionIdAndIsMaintain(@Param("conditionId")String conditionId,
                                                @Param("isMaintain") Boolean isMaintain);

    FundFixedInvestmentConditionRecord findLatestRecordByConditionId(String conditionId);

    List<FundFixedInvestmentConditionRecord> findByFundCodeAndIsLiquidationAndDate(
            @Param("fundCode")String fundCode, @Param("isLiquidation")Boolean isLiquidation,
            @Param("beginDateTime")LocalDateTime beginDateTime,
            @Param("endDateTime")LocalDateTime endDateTime);

    Integer updateIsLiquidationById(@Param("id") String id, @Param("isLiquidation")Boolean isLiquidation);
}
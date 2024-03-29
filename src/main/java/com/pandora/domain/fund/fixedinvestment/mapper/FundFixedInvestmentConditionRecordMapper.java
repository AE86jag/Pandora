package com.pandora.domain.fund.fixedinvestment.mapper;


import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    Integer updateIsLiquidationAndConfirmNavById(@Param("id") String id, @Param("isLiquidation")Boolean isLiquidation,
                                                 @Param("confirmNav")BigDecimal confirmNav);

    BigDecimal findHasLiquidationSumAmountByUserIdAndFundCode(@Param("userId") String userId, @Param("fundCode") String fundCode);

    Integer findUnLiquidationCountByUserIdAndFundCode(@Param("userId") String userId, @Param("fundCode") String fundCode);

    List<FundFixedInvestmentConditionRecord> findByUserIdAndFundCode(@Param("userId") String userId,
                                                                     @Param("fundCode") String fundCode);
}
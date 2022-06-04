package com.pandora.domain.fund.fixedinvestment.mapper;


import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundFixedInvestmentConditionMapper {
    int insert(FundFixedInvestmentCondition condition);

    int findCountByUserIdAndFundCode(@Param("fundCode")String fundCode, @Param("userId") String userId);

    List<FundFixedInvestmentCondition> findPageByUserId(@Param("offset")int offset, @Param("size")int size,
                                                        @Param("userId")String userId);

    FundFixedInvestmentCondition findById(String id);

    int updateStatusById(@Param("status")Integer status, @Param("id") String id);
}
package com.pandora.domain.user.mapper;


import com.pandora.domain.user.model.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface PositionMapper {

    Position getPositionByFundCodeAndUserId(@Param("userId")String userId, @Param("fundCode")String fundCode);

    Integer updateShareByUserIdAndFundCode(@Param("userId")String userId, @Param("fundCode")String fundCode,
                                           @Param("share")BigDecimal share);
}
package com.pandora.domain.user.mapper;


import com.pandora.domain.user.model.Position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface PositionMapper {

    Integer insert(Position position);

    Position getPositionByFundCodeAndUserId(@Param("userId")String userId, @Param("fundCode")String fundCode);

    Integer updateShareByUserIdAndFundCode(@Param("userId")String userId, @Param("fundCode")String fundCode,
                                           @Param("share")BigDecimal share);

    List<Position> findByUserId(@Param("userId")String userId);

    Integer findCountByUserIdAndFundCode(@Param("userId")String userId, @Param("fundCode")String fundCode);
}
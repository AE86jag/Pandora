package com.pandora.domain.convertiblebond.mapper;

import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ConvertibleBondShotRegisterMapper {
    int insert(ConvertibleBondShotRegister register);

    Integer findCountByUserIdAndSubscriptionBondCode(@Param("userId") String userId,
                                                     @Param("subscriptionCode") String subscriptionCode);

    List<ConvertibleBondShotRegister> findPageByUserId(@Param("offset") int offset, @Param("size")int size,
                                                       @Param("userId") String userId);

    List<ConvertibleBondShotRegister> findNeedNotifyByDate(@Param("date") LocalDate date);

    Integer updateIsSendByIds(@Param("registers") List<ConvertibleBondShotRegister> registers);
}

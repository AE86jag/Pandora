package com.pandora.domain.convertiblebond.mapper;

import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConvertibleBondShotRegisterMapper {
    int insert(ConvertibleBondShotRegister register);

    Integer findCountByUserIdAndBondCode(@Param("userId") String userId, @Param("bondCode") String bondCode);

    List<ConvertibleBondShotRegister> findPageByUserId(@Param("offset") int offset, @Param("size")int size,
                                                       @Param("userId") String userId);
}

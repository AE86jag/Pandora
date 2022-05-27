package com.pandora.domain.convertiblebond.mapper;

import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConvertibleBondShotRegisterMapper {
    int insert(ConvertibleBondShotRegister po);
}

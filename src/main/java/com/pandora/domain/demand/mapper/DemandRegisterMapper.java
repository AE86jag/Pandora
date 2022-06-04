package com.pandora.domain.demand.mapper;

import com.pandora.domain.demand.model.DemandRegister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface DemandRegisterMapper {

    int insert(DemandRegister demandRegister);

    List<DemandRegister> findPageByUserId(@Param("offset") int offset, @Param("size") int size, @Param("userId") String userId);
}

package com.pandora.domain.convertiblebond.mapper;

import com.pandora.domain.convertiblebond.model.ConvertibleBond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConvertibleBondMapper {

    int batchInsertOrUpdate(List<ConvertibleBond> convertibleBondPOS);

    List<ConvertibleBond> findByCodeOrShortNameLike(String key);

    ConvertibleBond findByCode(String bondCode);

    ConvertibleBond findBySubscriptionCode(String bondCode);
}

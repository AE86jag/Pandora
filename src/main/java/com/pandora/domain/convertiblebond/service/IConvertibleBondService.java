package com.pandora.domain.convertiblebond.service;

import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotPamerter;

import java.util.List;

public interface IConvertibleBondService {

    void syncConvertibleBonds();

    List<ConvertibleBondSearchResult> searchByKey(String key);

    void shotRegister(ConvertibleBondShotPamerter register);
}

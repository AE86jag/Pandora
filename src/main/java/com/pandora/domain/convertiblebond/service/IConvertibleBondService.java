package com.pandora.domain.convertiblebond.service;

import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;

import java.util.List;

public interface IConvertibleBondService {

    void syncConvertibleBonds();

    List<ConvertibleBondSearchResult> searchByKey(String key);

    void shotRegister(String bondCode, String email);

    List<ConvertibleBondShotRegister> convertibleBondShotRegisterList(int page, int size);

    void convertibleBondShotNotify();
}

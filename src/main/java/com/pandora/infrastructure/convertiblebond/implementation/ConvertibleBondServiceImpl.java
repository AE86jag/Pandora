package com.pandora.infrastructure.convertiblebond.implementation;

import com.pandora.domain.convertiblebond.mapper.ConvertibleBondMapper;
import com.pandora.domain.convertiblebond.model.ConvertibleBond;
import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotPamerter;
import com.pandora.domain.convertiblebond.service.IConvertibleBondService;
import com.pandora.infrastructure.convertiblebond.EastMoneyClient;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyConvertibleBond;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConvertibleBondServiceImpl implements IConvertibleBondService {

    @Autowired
    private EastMoneyClient eastMoneyClient;

    @Autowired
    private ConvertibleBondMapper convertibleBondMapper;

    @Override
    public void syncConvertibleBonds() {
        int page = 0, allPageCount = 0, size = 100;
        while (page <= allPageCount) {
            EastMoneyDTO.Result<EastMoneyConvertibleBond> result = eastMoneyClient.getConvertibleBondList(page, size);
            allPageCount = result.getPages();
            if (CollectionUtils.isEmpty(result.getData())) {
                return;
            }

            List<ConvertibleBond> convertibleBondPOS = result.getData().stream()
                    .map(ConvertibleBond::from).collect(Collectors.toList());
            convertibleBondMapper.batchInsertOrUpdate(convertibleBondPOS);
            page++;
        }

    }

    @Override
    public List<ConvertibleBondSearchResult> searchByKey(String key) {
        List<ConvertibleBond> pos = convertibleBondMapper.findByCodeOrShortNameLike(key);
        return pos.stream().map(ConvertibleBond::to).collect(Collectors.toList());
    }

    @Override
    public void shotRegister(ConvertibleBondShotPamerter register) {

    }
}

package com.pandora.infrastructure.convertiblebond.implementation;

import com.pandora.domain.convertiblebond.mapper.ConvertibleBondMapper;
import com.pandora.domain.convertiblebond.mapper.ConvertibleBondShotRegisterMapper;
import com.pandora.domain.convertiblebond.model.ConvertibleBond;
import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import com.pandora.domain.convertiblebond.service.IConvertibleBondService;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyConvertibleBond;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyBondDTO;
import com.pandora.infrastructure.eastmoney.EastMoneyClient;
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

    @Autowired
    private ConvertibleBondShotRegisterMapper convertibleBondShotRegisterMapper;

    @Override
    public void syncConvertibleBonds() {
        int page = 0, allPageCount = 0, size = 100;
        while (page <= allPageCount) {
            EastMoneyBondDTO.Result<EastMoneyConvertibleBond> result = eastMoneyClient.getConvertibleBondList(page, size);
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
    public void shotRegister(String bondCode, String email) {
        ConvertibleBond bond = convertibleBondMapper.findByCode(bondCode);
        if (bond == null) {
            throw new RuntimeException("债券代码不存在");
        }
        Integer registers = convertibleBondShotRegisterMapper.findCountByUserIdAndBondCode(
                CurrentUserUtils.currentUserId(), bondCode);
        if (registers != null && registers > 0) {
            throw new RuntimeException("该债券已登记");
        }

        ConvertibleBondShotRegister register = ConvertibleBondShotRegister.from(bondCode, bond.getBondShortName(), email);
        convertibleBondShotRegisterMapper.insert(register);

    }

    @Override
    public List<ConvertibleBondShotRegister> convertibleBondShotRegisterList(int page, int size) {
        return convertibleBondShotRegisterMapper.findPageByUserId(page * size, size, CurrentUserUtils.currentUserId());
    }
}

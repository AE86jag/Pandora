package com.pandora.infrastructure.demand.implementation;

import com.pandora.domain.demand.mapper.DemandRegisterMapper;
import com.pandora.domain.demand.model.DemandRegister;
import com.pandora.domain.demand.service.IDemandService;
import com.pandora.infrastructure.common.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandServiceImpl implements IDemandService {

    @Autowired
    private DemandRegisterMapper demandRegisterMapper;

    @Override
    public void demandRegister(String name, String contact, String demandDetail) {
        DemandRegister register = DemandRegister.from(name, contact, demandDetail);
        demandRegisterMapper.insert(register);
    }

    @Override
    public List<DemandRegister> getDemandRegisterList(int page, int size) {
        return demandRegisterMapper.findPageByUserId(page * size, size, CurrentUserUtils.currentUserId());
    }
}

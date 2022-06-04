package com.pandora.domain.demand.service;

import com.pandora.domain.demand.model.DemandRegister;

import java.util.List;

public interface IDemandService {

    void demandRegister(String name, String contact, String demandDetail);

    List<DemandRegister> getDemandRegisterList(int page, int size);
}

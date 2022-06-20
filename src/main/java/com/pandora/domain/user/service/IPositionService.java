package com.pandora.domain.user.service;

import com.pandora.domain.fund.product.model.UserPosition;

import java.util.List;

public interface IPositionService {
    List<UserPosition> calculateUserPosition(String userId);
}

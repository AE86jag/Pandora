package com.pandora;

import com.pandora.domain.demand.service.IDemandService;
import com.pandora.domain.user.model.User;
import com.pandora.infrastructure.interceptor.UserInfoContextHolder;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

public class DemandApiTest extends BaseApiTest{

    @Autowired
    private IDemandService iDemandService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void user_can_add_demand() {
        System.out.println("测试方法的线程ID: " + Thread.currentThread().getId());
        assertContract();

    }
}

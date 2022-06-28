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
    @Ignore
    public void user_can_add_demand() {
        UserInfoContextHolder.set(User.mock());
        iDemandService.demandRegister("22", "223", "22234545");
        System.out.println(jdbcTemplate.queryForMap("select * from demand_register where name = '22'"));

        System.out.println("Test的线程ID: " + Thread.currentThread().getId());
        assertContract();
    }
}

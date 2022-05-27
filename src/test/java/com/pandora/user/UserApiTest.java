package com.pandora.user;

import com.pandora.BaseApiTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

public class UserApiTest extends BaseApiTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void user_can_login(){
        assertContract();
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT last_login_ip FROM user WHERE id = 'user_id_1'");
        Assert.assertThat("127.0.0.1", is(result.get("last_login_ip")));
    }
}

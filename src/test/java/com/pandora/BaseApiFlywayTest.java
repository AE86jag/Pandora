package com.pandora;

import com.github.macdao.moscow.ContractAssertion;
import com.github.macdao.moscow.ContractContainer;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PandoraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiFlywayTest {

    private static final ContractContainer container = new ContractContainer(Paths.get("contract/api"));

    @Rule
    public final TestName name  = new TestName();

    @LocalServerPort
    private int port;

    private int timeout = 2000;

    //TODO 提高跑测试性速度，后期改用事务回滚的测试类，不适用flyway
    //TODO 解决异步测试问题
    @Autowired
    private Flyway flyway;

    @Before
    public void init(){

    }

    protected Map<String, String> assertContract(boolean necessity){
        return new ContractAssertion(container.findContracts(name.getMethodName()))
                .setPort(port)
                .setExecutionTimeout(timeout)
                .setNecessity(necessity)
                .assertContract();
    }

    protected Map<String, String> assertContract(){
        return new ContractAssertion(container.findContracts(name.getMethodName()))
                .setPort(port)
                .setExecutionTimeout(timeout)
                .setNecessity(false)
                .assertContract();
    }

    @After
    public void destroy(){
        //对数据库数据有修改的测试需要重建数据库，不影响其他测试正确性
        if(!name.getMethodName().contains("get")){
            flyway.clean();
            flyway.migrate();
        }
    }
}

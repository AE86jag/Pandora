package com.pandora;

import com.github.macdao.moscow.ContractAssertion;
import com.github.macdao.moscow.ContractContainer;
import com.github.macdao.moscow.json.JsonConverter;
import com.github.macdao.moscow.json.JsonConverterFactory;
import com.github.macdao.moscow.model.Contract;
import com.github.macdao.moscow.model.ContractRequest;
import com.github.macdao.moscow.model.ContractResponse;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PandoraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"isRollback=true"})
//@ConfigurationProperties(prefix = "emall.test")
@Slf4j
public abstract class BaseApiTest extends AbstractTransactionalJUnit4SpringContextTests {

    // 需要用到的契约路径
    private static final ContractContainer CONTAINER = new ContractContainer(
            Paths.get("contract")
    );

    private final JsonConverter jsonConverter = JsonConverterFactory.getJsonConverter();

    private static boolean firstExecution = true;


    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(
            wireMockConfig().port(8888)/*.extensions(new ConnectionCloseExtension())*/);


    @Rule
    public final TestName name = new TestName();

    /*@Rule
    public final ExecutionTimeoutRule timeout = new ExecutionTimeoutRule();*/

    @Value("${local.server.port}")
    protected int port;

    private int executionTimeout;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() {
        setCurrentConnectionHolder();
    }

    protected void setCurrentConnectionHolder() {
        CurrentConnectionHolder.setConnection(DataSourceUtils.getConnection(this.dataSource));
    }

    /*protected void givenCmbHeadPeerJwt() {
        givenJwtHeader(jwtHelper.createCmbHeadPeerJwt());
    }

    protected void givenCmbHeadEnterpriseJwt() {
        givenJwtHeader(jwtHelper.createCmbHeadEnterpriseJwt());
    }

    protected void givenCmbBranchPeerJwt() {
        givenJwtHeader(jwtHelper.createCmbBranchPeerJwt());
    }

    protected void givenCmbBranchEnterpriseJwt() {
        givenJwtHeader(jwtHelper.createCmbBranchEnterpriseJwt());
    }

    protected void givenInstitutionUserJwt() {
        givenJwtHeader(jwtHelper.createInstitutionUserJwt());
    }

    protected void givenInstitutionUserJwt(List<String> authorities, List<String> businessIds) {
        givenJwtHeader(jwtHelper.createInstitutionUserJwt(authorities, businessIds));
    }

    protected void givenTeamManagerJwt() {
        givenJwtHeader(jwtHelper.createTeamManagerJwt());
    }*/

   /* private void givenJwtHeader(String jwt) {
        List<Contract> contracts = CONTAINER.findContracts(name.getMethodName());
        contracts.forEach(contract -> {
            ContractRequest request = contract.getRequest();
            Map<String, String> headers = request.getHeaders();
            String jwtTokenString = "Bearer " + jwt;
            headers.put(AUTHORIZATION, jwtTokenString);
            request.setHeaders(headers);
        });
    }*/

    @After
    public void destroy() {
        wireMockRule.resetAll();
        CurrentConnectionHolder.clear();
    }

    protected Map<String, String> assertContract() {
        return assertContract(name.getMethodName());
    }

    protected Map<String, String> assertContract(String description) {
        final boolean necessity = description.contains("_cannot_");
        Map<String, String> result = new ContractAssertion(CONTAINER.findContracts(description))
                .setPort(port)
                //.setExecutionTimeout(executionTimeout())
                .setNecessity(necessity)
                .assertContract();
        clearCache();
        return result;
    }

    protected Map<String, String> assertContract(boolean necessity) {
        Map<String, String> result = new ContractAssertion(CONTAINER.findContracts(name.getMethodName()))
                .setPort(port)
                //.setExecutionTimeout(executionTimeout())
                .setNecessity(necessity)
                .assertContract();
        clearCache();
        return result;
    }

    /*private int executionTimeout() {
        int executionTimeoutSec = this.executionTimeout * timeout.getTimeout();
        if (firstExecution) {
            executionTimeoutSec *= 2;
            firstExecution = false;
        }
        return executionTimeoutSec;
    }*/

    public void setExecutionTimeout(int executionTimeout) {
        this.executionTimeout = executionTimeout;
    }

    protected void givenStub(String stubbedContractDescription) {
        List<Contract> contracts = CONTAINER.findContracts(stubbedContractDescription);
        contracts.forEach(this::doGivenStub);
    }

    protected void doGivenStub(Contract contract) {
        // 契约中的request
        ContractRequest request = contract.getRequest();
        // 获取请求方式和路径
        MappingBuilder mappingBuilder = request(request.getMethod(), urlEqualTo(buildRequestUri(request)));

        request.getHeaders().forEach((k, v) -> mappingBuilder.withHeader(k, equalTo(v)));
        request.getQueries().forEach((k, v) -> mappingBuilder.withQueryParam(k, equalTo(v)));
        if (Objects.nonNull(request.getJson())) {
            mappingBuilder.withRequestBody(equalToJson(jsonConverter.serialize(request.getJson())));
        }
        if (Objects.nonNull(request.getFile())) {
            if (request.getFile().endsWith(".json")) {
                mappingBuilder.withRequestBody(
                        equalToJson(new String(readFile(contract.getBase().toString(), request.getFile()))));
            } else if (request.getFile().endsWith(".xml")) {
                mappingBuilder.withRequestBody(
                        equalToXml(new String(readFile(contract.getBase().toString(), request.getFile()))));
            } else {
                mappingBuilder.withRequestBody(
                        equalTo(new String(readFile(contract.getBase().toString(), request.getFile()))));
            }
        }
        ResponseDefinitionBuilder responseDefinitionBuilder = buildResponseDefinitionBuilder(contract);
        mappingBuilder.willReturn(responseDefinitionBuilder);
        stubFor(mappingBuilder);
    }

    private String buildRequestUri(ContractRequest request) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        request.getQueries().forEach(params::add);
        return UriComponentsBuilder.fromUriString(request.getUri()).queryParams(params).toUriString();
    }

    private ResponseDefinitionBuilder buildResponseDefinitionBuilder(Contract contract) {
        ContractResponse response = contract.getResponse();
        List<HttpHeader> headers = response.getHeaders().entrySet()
                .stream().map(header -> new HttpHeader(header.getKey(), header.getValue()))
                .collect(Collectors.toList());
        boolean hasContentType = headers.stream().anyMatch(httpHeader -> httpHeader.key().equals(CONTENT_TYPE));
        if (!hasContentType) {
            headers.add(new HttpHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE));
        }
        if (Objects.nonNull(response.getFile())) {
            return aResponse()
                    .withHeaders(new HttpHeaders(headers))
                    .withStatus(response.getStatus())
                    .withBody(readFile(contract.getBase().toString(), response.getFile()));
        }
        return aResponse()
                .withHeaders(new HttpHeaders(headers))
                .withStatus(response.getStatus())
                .withBody(jsonConverter.serialize(response.getJson()));
    }

    private byte[] readFile(String path, String fileName) {
        String fileNameInstitution = getFileName(path, fileName);
        try {
            FileInputStream inputStreamInstitution = new FileInputStream(fileNameInstitution);
            byte[] bytesInstitution = new byte[inputStreamInstitution.available()];
            inputStreamInstitution.read(bytesInstitution);
            inputStreamInstitution.close();
            return bytesInstitution;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getFileName(String path, String fileName) {
        if (path.endsWith("index.json")) {
            path = path.substring(0, path.length() - 11);
        }
        return path + "/" + fileName;
    }

    private void clearCache() {
        Optional.ofNullable(SqlSessionUtils.getSqlSession(sqlSessionFactory))
                .ifPresent(SqlSession::clearCache);
    }
}


    
    
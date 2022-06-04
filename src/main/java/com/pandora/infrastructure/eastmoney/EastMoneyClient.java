package com.pandora.infrastructure.eastmoney;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyBondDTO;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyConvertibleBond;
import com.pandora.infrastructure.util.HttpUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "pandora.eastmoney")
@Slf4j
public class EastMoneyClient {

    @Setter
    private String bondUrl;

    @Setter
    private String fundUrl;

    @Autowired
    private Gson gson;

    public List<FundSearchResultDTO> searchByKey(String key) {
        String encodeKey;
        try {
            encodeKey = URLEncoder.encode(key, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("key encode error", e);
            throw new RuntimeException("关键字转码异常，请重试");
        }
        String callback = "callback";
        String uri = fundUrl +
                String.format("/FundSearch/api/FundSearchAPI.ashx?callback=%s&m=1&key=%s", callback, encodeKey);
        String responseString  = HttpUtil.get(uri, String.class);

        if (!responseString.contains(callback)) {
            throw new RuntimeException("基金搜索异常");
        }

        responseString = responseString.substring(callback.length() + 1, responseString.length() - 1);

        EastMoneyFundDTO<List<FundSearchResultDTO>> result = gson.fromJson(responseString,
                new TypeToken<EastMoneyFundDTO<List<FundSearchResultDTO>>>() {}.getType());

        if (result == null || !result.isSuccess()) {
            throw new RuntimeException("基金搜索失败");
        }
        return result.getData();
    }

    public EastMoneyBondDTO.Result<EastMoneyConvertibleBond> getConvertibleBondList(int page, int size) {
        String callback = "ae86";
        String uri = String.format(bondUrl + "/api/data/v1/get?callback=%s&sortColumns=PUBLIC_START_DATE&sortTypes=-1" +
                        "&pageSize=%s&pageNumber=%s&reportName=RPT_BOND_CB_LIST&columns=ALL&source=WEB&client=WEB",
                callback, size, page);
        //如果失败是不带callback的，直接是完整的json格式
        // [{"version":null,"result":null,"success":false,"message":"行情参数格式错误","code":9501}]
        String responseString = HttpUtil.get(uri, String.class);
        if (responseString.contains(callback)) {
            responseString = responseString.substring(callback.length() + 1, responseString.length() - 2);
        }

        EastMoneyBondDTO<EastMoneyConvertibleBond> data = gson.fromJson(responseString,
                new TypeToken<EastMoneyBondDTO<EastMoneyConvertibleBond>>() {}.getType());
        if (!data.isSuccess()) {
            log.error("get convertible bond list error");
            throw new RuntimeException(data.getMessage());
        }

        return data.getResult();
    }

}

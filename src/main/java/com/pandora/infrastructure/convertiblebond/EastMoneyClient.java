package com.pandora.infrastructure.convertiblebond;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyConvertibleBond;
import com.pandora.infrastructure.convertiblebond.model.EastMoneyDTO;
import com.pandora.infrastructure.util.HttpUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pandora.eastmoney")
@Slf4j
public class EastMoneyClient {

    @Setter
    private String url;

    //实例类中日期的类型必须是这几种类型java.util.Date, java.sql.Timestamp, java.sql.Date, setDateFormat才能生效
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public EastMoneyDTO.Result<EastMoneyConvertibleBond> getConvertibleBondList(int page, int size) {
        String callback = "ae86";
        String uri = String.format(url + "/api/data/v1/get?callback=%s&sortColumns=PUBLIC_START_DATE&sortTypes=-1" +
                "&pageSize=%s&pageNumber=%s&reportName=RPT_BOND_CB_LIST&columns=ALL&source=WEB&client=WEB",
                callback, size, page);
        //如果失败是不带callback的，直接是完整的json格式
        // [{"version":null,"result":null,"success":false,"message":"行情参数格式错误","code":9501}]
        String responseString = HttpUtil.get(uri, String.class);
        if (responseString.contains(callback)) {
            responseString = responseString.substring(callback.length() + 1, responseString.length() - 2);
        }

        EastMoneyDTO<EastMoneyConvertibleBond> data = gson.fromJson(responseString,
                new TypeToken<EastMoneyDTO<EastMoneyConvertibleBond>>() {}.getType());
        if (!data.isSuccess()) {
            log.error("get convertible bond list error");
            throw new RuntimeException(data.getMessage());
        }

        return data.getResult();
    }
}

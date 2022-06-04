package com.pandora.ui.convertiblebond.vo;

import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import lombok.Data;

@Data
public class ConvertibleBondSearchResultVO {
    private String bondCode;
    private String bondShortName;

    public static ConvertibleBondSearchResultVO from(ConvertibleBondSearchResult result) {
        ConvertibleBondSearchResultVO vo = new ConvertibleBondSearchResultVO();
        vo.setBondCode(result.getBondCode());
        vo.setBondShortName(result.getBondShortName());
        return vo;
    }
}

package com.pandora.ui.fund.fixedinvestment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FundFixedInvestmentConditionRecordVO {
    private FundFixedInvestmentConditionRecord.Direction direction;
    private String fundName;
    private BigDecimal amount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createdTime;

    public static FundFixedInvestmentConditionRecordVO from(FundFixedInvestmentConditionRecord record) {
        FundFixedInvestmentConditionRecordVO vo = new FundFixedInvestmentConditionRecordVO();
        vo.setDirection(record.getDirection());
        vo.setFundName(record.getFundName());
        vo.setAmount(record.getAmount());
        vo.setCreatedTime(record.getCreatedTime());
        return vo;
    }

}
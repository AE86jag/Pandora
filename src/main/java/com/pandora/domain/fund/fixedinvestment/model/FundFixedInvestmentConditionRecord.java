package com.pandora.domain.fund.fixedinvestment.model;

import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FundFixedInvestmentConditionRecord {
    private String id;
    private String conditionId;
    private String userId;
    private LocalDateTime triggerTime;
    private Direction direction;
    private String fundCode;
    private String fundName;
    private BigDecimal amount;
    /**
     * 是否是延迟的记录，触发时间为非交易日，延长到下一交易日触发的场景
     */
    private Boolean isPostpone;
    private Boolean isMaintain;
    /**
     * 是否清算，触发买入时，如果是三点前，基金未清算，无法确定买入份额，需要后续清算完计算份额到持仓中，并更新该字段
     */
    private Boolean isLiquidation;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime createdTime;

    public static FundFixedInvestmentConditionRecord from(FundFixedInvestmentCondition condition, Boolean isPostpone,
                                                          BigDecimal toBuyAmount, Boolean isMaintain) {
        FundFixedInvestmentConditionRecord record = new FundFixedInvestmentConditionRecord();
        record.setId(CommonUtil.generateId());
        record.setConditionId(condition.getId());
        record.setUserId(condition.getUserId());
        record.setTriggerTime(LocalDateTime.now());
        record.setDirection(toBuyAmount.compareTo(BigDecimal.ZERO) > 0 ? Direction.I : Direction.O);
        record.setFundCode(condition.getFundCode());
        record.setFundName(condition.getFundName());
        record.setAmount(toBuyAmount);
        record.setIsPostpone(isPostpone);
        record.setIsMaintain(isMaintain);
        record.setIsLiquidation(false);
        return record;

    }

    public enum Direction {
        I("买入"),
        O("卖出");

        private String description;

        Direction(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
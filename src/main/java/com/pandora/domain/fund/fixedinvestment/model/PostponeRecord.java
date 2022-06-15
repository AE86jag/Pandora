package com.pandora.domain.fund.fixedinvestment.model;

import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostponeRecord {

    private String id;

    private String conditionId;

    private LocalDate triggerDate;

    private LocalDateTime createdTime;

    private LocalDateTime lastModifiedTime;


    public static List<PostponeRecord> from(List<FundFixedInvestmentCondition> conditions, LocalDate triggerDate) {
        return conditions.stream().map(p -> PostponeRecord.from(p, triggerDate)).collect(Collectors.toList());
    }

    public static PostponeRecord from(FundFixedInvestmentCondition condition, LocalDate triggerDate) {
        PostponeRecord record = new PostponeRecord();
        record.setId(CommonUtil.generateId());
        record.setConditionId(condition.getId());
        record.setTriggerDate(triggerDate);
        return record;
    }
}

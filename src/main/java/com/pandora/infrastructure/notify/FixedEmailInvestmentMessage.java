package com.pandora.infrastructure.notify;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FixedEmailInvestmentMessage implements IMessage {

    private List<String> destinationAddress;

    private String title;

    private String content;

    public FixedEmailInvestmentMessage(List<String> destinationAddress, String title, String content) {
        this.destinationAddress = destinationAddress;
        this.title = title;
        this.content = content;
    }

    public static FixedEmailInvestmentMessage from(FundFixedInvestmentCondition condition,
                                                   BigDecimal amount) {
        FixedEmailInvestmentMessage message = new FixedEmailInvestmentMessage();
        message.setContent(condition.getMessageContent(amount));
        message.setTitle("基金定投条件单通知");
        message.setDestinationAddress(Lists.newArrayList(condition.getEmail()));
        return message;
    }

    @Override
    public List<String> getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getTitle() {
        return title;
    }
}

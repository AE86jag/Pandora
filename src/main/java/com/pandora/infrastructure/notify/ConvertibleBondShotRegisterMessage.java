package com.pandora.infrastructure.notify;

import com.google.common.collect.Lists;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ConvertibleBondShotRegisterMessage implements IMessage {

    private List<String> destinationAddress;

    private String title;

    private String content;

    public ConvertibleBondShotRegisterMessage() {

    }

    public ConvertibleBondShotRegisterMessage(List<String> destinationAddress, String title, String content) {
        this.destinationAddress = destinationAddress;
        this.title = title;
        this.content = content;
    }

    public static ConvertibleBondShotRegisterMessage from(ConvertibleBondShotRegister register) {
        ConvertibleBondShotRegisterMessage message = new ConvertibleBondShotRegisterMessage();
        message.setContent(register.getEmailNotifyContent());
        message.setTitle(String.format("可转债(%s)上市通知", register.getBondName()));
        message.setDestinationAddress(Lists.newArrayList(register.getEmail()));
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

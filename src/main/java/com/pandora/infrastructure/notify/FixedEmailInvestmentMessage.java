package com.pandora.infrastructure.notify;

import java.util.List;

public class FixedEmailInvestmentMessage implements IMessage {

    private List<String> destinationAddress;

    private String title;

    private String content;

    public FixedEmailInvestmentMessage(List<String> destinationAddress, String title, String content) {
        this.destinationAddress = destinationAddress;
        this.title = title;
        this.content = content;
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

package com.pandora.infrastructure.notify;

import java.util.List;

public class EmailMessage implements Message {

    private List<String> destinationAddress;

    private String title;

    private String content;

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

package com.pandora.infrastructure.notify;

import java.util.List;

public interface IMessage {
    List<String> getDestinationAddress();

    String getContent();

    String getTitle();
}

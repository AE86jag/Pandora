package com.pandora.infrastructure.notify;

import java.util.List;

public interface Message {
    List<String> getDestinationAddress();

    String getContent();

    String getTitle();
}

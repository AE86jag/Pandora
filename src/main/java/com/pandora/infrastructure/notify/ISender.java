package com.pandora.infrastructure.notify;

import java.util.List;

public interface ISender {

    boolean send(IMessage message);

    boolean send(List<IMessage> messages);
}

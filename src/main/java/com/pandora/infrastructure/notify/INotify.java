package com.pandora.infrastructure.notify;

import java.util.List;

public interface INotify {

    boolean send(Message message);

    boolean send(List<Message> messages);
}

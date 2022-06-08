package com.pandora.infrastructure.notify;

import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class ISend {

    protected List<INotify> iNotifies;

    boolean send(List<Message> messages) {
        //TODO 部分发送失败
        if (CollectionUtils.isEmpty(iNotifies)) {
            throw new RuntimeException("请初始化发送");
        }

        if (CollectionUtils.isEmpty(messages)) {
            throw new RuntimeException("请输入发送内容");
        }
        for (INotify iNotify : iNotifies) {
            iNotify.send(messages);
        }
        return true;
    }
}

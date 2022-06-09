package com.pandora.infrastructure.notify;

import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class INotify {

    protected List<ISender> iSenders;

    public INotify(List<ISender> iSenders) {
        this.iSenders = iSenders;
    }

    public boolean send(List<IMessage> messages) {
        //TODO 部分发送失败
        if (CollectionUtils.isEmpty(iSenders)) {
            throw new RuntimeException("请初始化发送");
        }

        if (CollectionUtils.isEmpty(messages)) {
            throw new RuntimeException("请输入发送内容");
        }
        for (ISender iSender : iSenders) {
            iSender.send(messages);
        }
        return true;
    }
}

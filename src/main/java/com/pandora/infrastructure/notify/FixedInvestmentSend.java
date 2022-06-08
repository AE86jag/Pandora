package com.pandora.infrastructure.notify;

import java.util.List;

public class FixedInvestmentSend extends ISend {

    FixedInvestmentSend(List<INotify> iNotifies) {
        super.iNotifies = iNotifies;
    }


}

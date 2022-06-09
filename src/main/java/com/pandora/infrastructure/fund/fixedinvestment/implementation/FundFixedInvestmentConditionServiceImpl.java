package com.pandora.infrastructure.fund.fixedinvestment.implementation;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionMapper;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.service.IFundFixedInvestmentConditionService;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.notify.EmailSender;
import com.pandora.infrastructure.notify.FixedEmailInvestmentMessage;
import com.pandora.infrastructure.notify.FixedInvestmentEmailNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundFixedInvestmentConditionServiceImpl implements IFundFixedInvestmentConditionService {

    @Autowired
    private FundFixedInvestmentConditionMapper fundFixedInvestmentConditionMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void createFundFixedInvestmentCondition(FundFixedInvestmentCondition fundFixedInvestmentCondition) {
        int count = fundFixedInvestmentConditionMapper.findCountByUserIdAndFundCode(
                fundFixedInvestmentCondition.getFundCode(), CurrentUserUtils.currenUserId());
        if (count != 0) {
            throw new RuntimeException("该基金条件单已存在");
        }
        fundFixedInvestmentConditionMapper.insert(fundFixedInvestmentCondition);
    }

    @Override
    public List<FundFixedInvestmentCondition> getUserConditionsPage(int page, int size) {
        return fundFixedInvestmentConditionMapper.findPageByUserId(page * size, size, CurrentUserUtils.currenUserId());
    }

    @Override
    public void changeStatusById(String id, Integer status) {
        FundFixedInvestmentCondition condition = fundFixedInvestmentConditionMapper.findById(id);
        if (condition == null) {
            throw new RuntimeException("条件单不存在，请刷新重试");
        }

        if (!condition.getUserId().equals(CurrentUserUtils.currenUserId())) {
            throw new RuntimeException("该条件单不属于您");
        }
        if (status.equals(condition.getStatus())) {
            throw new RuntimeException("该条件单已修改，无需修改");
        }
        fundFixedInvestmentConditionMapper.updateStatusById(status, id);
    }

    @Override
    public void messageNotify() {
        FixedEmailInvestmentMessage message = new FixedEmailInvestmentMessage(
                Lists.newArrayList("754244523@qq.com", "15717003806@163.com"),
                "消息通知测试", "这是一个内容，内容很长很长很长很长很长长长长长长长长长长长长");
        EmailSender sender = new EmailSender(javaMailSender);
        new FixedInvestmentEmailNotify(Lists.newArrayList(sender)).send(Lists.newArrayList(message));
    }
}

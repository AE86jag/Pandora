CREATE TABLE `fund_fixed_investment_condition_record` (
    `id`                   VARCHAR(32) NOT NULL COMMENT 'id',
    `condition_id`         VARCHAR(32) NOT NULL COMMENT '条件单ID',
    `user_id`              VARCHAR(32) NOT NULL COMMENT '用户ID',
    `trigger_time`         DATETIME NOT NULL COMMENT '触发时间',
    `direction`            CHAR(1) NOT NULL COMMENT '买卖方向，I: 买入, O: 卖出',
    `fund_code`            CHAR(6) NOT NULL COMMENT '基金代码',
    `fund_name`            VARCHAR(50) NOT NULL COMMENT '基金名称',
    `amount`               DECIMAL(10, 2) NOT NULL COMMENT '交易金额，负的为卖，正的为买',
    `is_postpone`          TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否是顺延的，如果是顺延的，原定投时间一定是上个交易日',
    `is_maintain`          TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否是维持市值恒定阶段，1：是，0：否',
    `is_liquidation`       TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已经清算，计算好份额并计入持仓，1：是，0：否',
    `last_modified_time`   DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `created_time`         DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '定投记录表';
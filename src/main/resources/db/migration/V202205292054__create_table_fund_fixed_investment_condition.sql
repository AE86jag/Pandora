CREATE TABLE IF NOT EXISTS `fund_fixed_investment_condition` (
    `id`                        VARCHAR(32) NOT NULL COMMENT 'ID主键',
    `fund_code`                 CHAR(6) NOT NULL COMMENT '基金代码',
    `fund_name`                 VARCHAR(50) NOT NULL COMMENT '基金名称',
    `target_market_value`       DECIMAL(12,2) NOT NULL COMMENT '目标市值',
    `first_category_of_cycle`   VARCHAR(20) NOT NULL COMMENT '周期大类',
    `second_category_of_cycle`  VARCHAR(20) NOT NULL COMMENT '周期子类',
    `positive_increase`         DECIMAL(8,2) NOT NULL COMMENT '正波动幅度',
    `negative_increase`         DECIMAL(8,2) NOT NULL COMMENT '负波动幅度',
    `per_amount`                DECIMAL(12, 2) NOT NULL COMMENT '每笔委托',
    `email`                     VARCHAR(50) NOT NULL COMMENT '通知邮箱',
    `user_id`                   VARCHAR(32) NOT NULL COMMENT '用户ID',
    `status`                    TINYINT(1) DEFAULT 1 NOT NULL COMMENT '条件单状态，1正常，0暂停',
    `created_time`              DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `last_modified_time`        DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '基金定投条件单';
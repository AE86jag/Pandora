CREATE TABLE trade_day (
    `date`                  DATE NOT NULL COMMENT '日期',
    `is_work_day`           TINYINT(1) NOT NULL COMMENT '是否交易日，交易日为工作日减去周末',
    `last_modified_time`    DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `created_time`          DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`date`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '交易日表，工作日减去周末';
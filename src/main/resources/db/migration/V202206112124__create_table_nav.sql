CREATE TABLE nav (
    `fund_code`            CHAR(6) NOT NULL COMMENT '基金代码',
    `nav_date`             DATE NOT NULL COMMENT '净值日期',
    `nav`                  DECIMAL(10,4) NOT NULL COMMENT '基金净值',
    `last_modified_time`   DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `created_time`         DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`fund_code`, nav_date)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '基金净值表';
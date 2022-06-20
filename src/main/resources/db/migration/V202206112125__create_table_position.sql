CREATE TABLE position (
    user_id              VARCHAR(32) NOT NULL COMMENT 'ID主键',
    fund_code            CHAR(6) NOT NULL COMMENT '基金代码',
    fund_name            VARCHAR(50) NOT NULL COMMENT '基金名称',
    share                DECIMAL(12,2) NOT NULL COMMENT '持仓份额',
    last_modified_time   DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    created_time         DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '个人持仓表';
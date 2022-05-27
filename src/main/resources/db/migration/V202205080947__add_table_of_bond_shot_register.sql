CREATE TABLE convertible_bond_shot_register(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    bond_code CHAR(6) NOT NULL COMMENT '债券代码',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    email VARCHAR(50) NOT NULL COMMENT '邮箱',
    is_send TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否已发送邮件',
    send_time DATETIME COMMENT '邮件发送时间',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '可转债中签登记表';
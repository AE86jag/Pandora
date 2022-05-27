CREATE TABLE user(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    mobile CHAR(11) COMMENT '手机号',
    email VARCHAR(50) COMMENT '邮箱',
    open_id VARCHAR(50) NOT NULL COMMENT '微信OpenId',
    union_id VARCHAR(50) COMMENT '微信UnionId',
    status TINYINT(1) DEFAULT 1 COMMENT '用户状态, 0:无效, 1:正常',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '用户表';
CREATE TABLE authorities(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    user_id VARCHAR(32) COMMENT '用户ID',
    authority VARCHAR(20) COMMENT '用户权限',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '用户权限表';
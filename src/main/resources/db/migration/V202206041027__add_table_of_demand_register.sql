CREATE TABLE demand_register(
    id VARCHAR(32) NOT NULL COMMENT '主键',
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    name VARCHAR(20) NOT NULL COMMENT '名称',
    contact VARCHAR(50) NOT NULL COMMENT '联系方式',
    demand_detail TEXT NOT NULL COMMENT '需求详情',
    status TINYINT(1) NOT NULL COMMENT '状态, 0:待处理，1:处理中, 2:已完成',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`id`)
) COMMENT = '需求登记表';
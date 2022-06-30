CREATE TABLE exception (
    `id`                    INT(12) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `clazz`                 VARCHAR(100) NOT NULL COMMENT '',
    `message`               VARCHAR(500) COMMENT '异常信息',
    `stack`                 VARCHAR(1000) COMMENT '异常堆栈信息',
    `url`                   VARCHAR(100) COMMENT '异常接口',
    `user_id`               CHAR(32) COMMENT '用户ID',
    `is_attention`          TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否需要关注',
    `last_modified_time`    DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `created_time`          DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '异常信息表';
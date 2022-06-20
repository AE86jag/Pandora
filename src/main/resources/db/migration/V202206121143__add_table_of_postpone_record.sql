CREATE TABLE postpone_record (
    `id`                   VARCHAR(32) NOT NULL COMMENT 'id',
    `condition_id`         VARCHAR(32) NOT NULL COMMENT '条件单ID',
    `trigger_date`         DATE NOT NULL COMMENT '下次触发时间',
    `last_modified_time`    DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后修改时间',
    `created_time`          DATETIME NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '定投顺延记录表';
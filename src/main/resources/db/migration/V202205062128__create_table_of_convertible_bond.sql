CREATE TABLE convertible_bond(
    bond_code CHAR(6) NOT NULL COMMENT '债券代码',
    bond_short_name VARCHAR(50) NOT NULL COMMENT '债券简称',
    subscription_date DATE COMMENT '申购日期',
    subscription_code CHAR(6) NOT NULL COMMENT '申购代码',
    stock_code CHAR(6) COMMENT '正股代码',
    stock_short_name VARCHAR(50) COMMENT '正股简称',
    transfer_stock_price DECIMAL(10,2) COMMENT '转股价',
    current_bond_price DECIMAL(10,2) COMMENT '债现值',
    issue_scale DECIMAL(8,2) COMMENT '发行规模，单位亿元',
    shot_result_publish_date DATE COMMENT '中签号发布日',
    shot_rate DECIMAL(9,6) COMMENT '中签率',
    listed_date DATE COMMENT '上市时间',
    last_modify_time DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '最后一次更新时间',
    create_time DATETIME DEFAULT NOW() NOT NULL COMMENT '创建时间',
    PRIMARY KEY(`bond_code`)
) COMMENT = '可转债信息表';
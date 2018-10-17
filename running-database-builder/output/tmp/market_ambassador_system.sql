CREATE TABLE `market_ambassador_system` (
  `market_ambassador_id` int(10) NOT NULL COMMENT '大使ID',
  `market_id` int(10) NOT NULL COMMENT '市场',
  `market_ambassador_order` tinyint(2) NOT NULL COMMENT '顺序',
  `market_ambassador_sex` tinyint(2) NOT NULL COMMENT '艺人性别限制',
  `market_ambassador_nation` tinyint(2) NOT NULL COMMENT '艺人国籍限制',
  `market_ambassador_factions` tinyint(2) NOT NULL COMMENT '艺人派别限制',
  `market_ambassador_type` tinyint(2) NOT NULL COMMENT '艺人年龄型限制',
  `market_ambassador_att` tinyint(2) NOT NULL COMMENT '艺人属性限制',
  `market_ambassador_att_para` int(10) NOT NULL COMMENT '艺人属性值要求',
  PRIMARY KEY (`market_ambassador_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场大使表';
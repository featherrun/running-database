CREATE TABLE `publicity_system` (
  `publicity_level` tinyint(2) NOT NULL COMMENT '关注等级',
  `publicity_recurring_income_pro` int(10) NOT NULL COMMENT '持续收益加成',
  `publicity_up_cost1` int(10) NOT NULL COMMENT '一般消耗',
  `publicity_up_cost2` int(10) NOT NULL COMMENT '良好消耗',
  `publicity_up_cost3` int(10) NOT NULL COMMENT '大卖消耗',
  `publicity_up_cost4` int(10) NOT NULL COMMENT '超级消耗',
  `publicity_up_cost5` int(10) NOT NULL COMMENT '传说消耗',
  `publicity_up_cost6` int(10) NOT NULL COMMENT '神话消耗',
  PRIMARY KEY (`publicity_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电影关注度表';
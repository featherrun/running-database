CREATE TABLE `production_system` (
  `production_id` int(10) NOT NULL COMMENT '生产线',
  `production_grade` tinyint(2) NOT NULL COMMENT '档次',
  `production_order` tinyint(2) NOT NULL COMMENT '顺序',
  `production_text` varchar(50) NOT NULL COMMENT '描述',
  `production_gold` int(10) NOT NULL COMMENT '解锁金币',
  `production_level` tinyint(2) NOT NULL COMMENT '解锁等级',
  `production_build` tinyint(2) NOT NULL COMMENT '解锁建筑',
  `production_item` varchar(255) NOT NULL COMMENT '生产道具',
  PRIMARY KEY (`production_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场基础表';
CREATE TABLE `build_level_system` (
  `build_level_id` int(10) NOT NULL COMMENT 'ID',
  `build_level_department` int(10) NOT NULL COMMENT '部门',
  `build_level` tinyint(2) NOT NULL COMMENT '等级',
  `build_unlock_type1` tinyint(2) NOT NULL COMMENT '解锁条件1',
  `build_unlock_para1` varchar(255) NOT NULL COMMENT '解锁条件参数1',
  `build_unlock_type2` tinyint(2) NOT NULL COMMENT '解锁条件2',
  `build_unlock_para2` varchar(255) NOT NULL COMMENT '解锁条件参数2',
  `build_level_cost` varchar(255) NOT NULL COMMENT '消耗道具',
  `build_level_cost_num` int(10) NOT NULL COMMENT '消耗道具数量',
  `build_level_cost_gold` int(10) NOT NULL COMMENT '消耗金币',
  `build_level_effect_type1` tinyint(2) NOT NULL COMMENT '效果类型1',
  `build_level_effect_para1` tinyint(2) NOT NULL COMMENT '效果类型参数1',
  `build_level_effect_type2` tinyint(2) NOT NULL COMMENT '效果类型2',
  `build_level_effect_para2` tinyint(2) NOT NULL COMMENT '效果类型参数2',
  PRIMARY KEY (`build_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='建筑升级表';
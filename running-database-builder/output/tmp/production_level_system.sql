CREATE TABLE `production_level_system` (
  `production_level` int(10) NOT NULL COMMENT '生产线等级',
  `production_level_gold` int(10) NOT NULL COMMENT '升级金币',
  PRIMARY KEY (`production_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场等级表';
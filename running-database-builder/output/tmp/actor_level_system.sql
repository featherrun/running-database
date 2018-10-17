CREATE TABLE `actor_level_system` (
  `actor_level` int(10) NOT NULL COMMENT '等级',
  `actor_level_gold` int(10) NOT NULL COMMENT '升级金币',
  `actor_level_gift_num1` int(10) NOT NULL COMMENT '1星资产上限',
  `actor_level_gift_num2` int(10) NOT NULL COMMENT '2星资产上限',
  `actor_level_gift_num3` int(10) NOT NULL COMMENT '3星资产上限',
  `actor_level_gift_num4` int(10) NOT NULL COMMENT '4星资产上限',
  `actor_level_gift_num5` int(10) NOT NULL COMMENT '5星资产上限',
  `actor_level_gift_num6` int(10) NOT NULL COMMENT '6星资产上限',
  PRIMARY KEY (`actor_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员等级表';
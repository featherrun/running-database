CREATE TABLE `company_level_system` (
  `company_level` int(10) NOT NULL COMMENT '等级',
  `company_level_exp` int(10) NOT NULL COMMENT '升级粉丝数',
  `company_level_reward` int(10) NOT NULL COMMENT '升级奖励',
  `company_level_drama` varchar(255) NOT NULL COMMENT '升级解锁剧本',
  PRIMARY KEY (`company_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司等级表';
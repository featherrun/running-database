CREATE TABLE `actor_loyal_system` (
  `actor_loyal_level` int(10) NOT NULL COMMENT '忠诚度等级',
  `actor_loyal_level_exp1` int(10) NOT NULL COMMENT '1星经验',
  `actor_loyal_level_exp2` int(10) NOT NULL COMMENT '2星经验',
  `actor_loyal_level_exp3` int(10) NOT NULL COMMENT '3星经验',
  `actor_loyal_level_exp4` int(10) NOT NULL COMMENT '4星经验',
  `actor_loyal_level_exp5` int(10) NOT NULL COMMENT '5星经验',
  `actor_loyal_level_exp6` int(10) NOT NULL COMMENT '6星经验',
  `actor_loyal_limit_level` int(10) NOT NULL COMMENT '限制演员等级上限',
  PRIMARY KEY (`actor_loyal_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员忠诚表';
CREATE TABLE `actor_fit_system` (
  `actor_fit_level` int(10) NOT NULL COMMENT '契合等级',
  `actor_fit_exp` int(10) NOT NULL COMMENT '升级需要契合度',
  PRIMARY KEY (`actor_fit_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员契合等级表';
CREATE TABLE `reward_system` (
  `reward_id` int(10) NOT NULL COMMENT 'ID',
  `reward_type` int(10) NOT NULL COMMENT '奖励类型',
  `reward_para1` int(10) NOT NULL COMMENT '奖励参数1',
  `reward_para2` int(10) NOT NULL COMMENT '奖励参数2',
  `reward_para3` int(10) NOT NULL COMMENT '奖励参数3',
  `reward_drop` int(10) NOT NULL COMMENT '奖励',
  PRIMARY KEY (`reward_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='奖励模板表';
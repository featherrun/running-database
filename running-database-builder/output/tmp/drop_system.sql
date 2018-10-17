CREATE TABLE `drop_system` (
  `drop_id` int(10) NOT NULL COMMENT '掉落数据id',
  `drop_group` int(10) NOT NULL COMMENT '掉落组',
  `drop_chance` int(10) NOT NULL COMMENT '掉落概率',
  `drop_data` varchar(255) NOT NULL COMMENT '掉落',
  `drop_num` int(10) NOT NULL COMMENT '数量',
  `drop_formula` varchar(255) NOT NULL COMMENT '掉落公式',
  `drop_level` int(10) NOT NULL COMMENT '等级限制',
  `drop_para_1` varchar(255) NOT NULL COMMENT '掉落参数1',
  `drop_para_2` varchar(255) NOT NULL COMMENT '掉落参数2',
  `drop_para_3` varchar(255) NOT NULL COMMENT '掉落参数3',
  `drop_para_4` varchar(255) NOT NULL COMMENT '掉落参数4',
  `drop_bulletin` int(10) NOT NULL COMMENT '触发公告',
  PRIMARY KEY (`drop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='掉落表';
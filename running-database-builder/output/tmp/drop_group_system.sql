CREATE TABLE `drop_group_system` (
  `drop_group_id` int(10) NOT NULL COMMENT '掉落分组id',
  `drop_group_type` tinyint(3) NOT NULL COMMENT '分组类型',
  PRIMARY KEY (`drop_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='掉落分组表';
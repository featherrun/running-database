CREATE TABLE `casting_director_system` (
  `casting_director_id` int(10) NOT NULL COMMENT '奖励ID',
  `casting_director_item` varchar(255) NOT NULL COMMENT '奖励道具',
  `casting_director_item_num` int(10) NOT NULL COMMENT '奖励道具数量',
  `casting_director_weight` int(10) NOT NULL COMMENT '权重',
  PRIMARY KEY (`casting_director_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='星探模板表';
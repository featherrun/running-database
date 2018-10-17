CREATE TABLE `theatre_chain_level_system` (
  `theatre_chain_level_id` int(10) NOT NULL COMMENT 'ID',
  `theatre_chain_id` int(10) NOT NULL COMMENT '院线',
  `theatre_chain_level` tinyint(2) NOT NULL COMMENT '签约等级',
  `theatre_chain_level_cinema` int(10) NOT NULL COMMENT '影院数量',
  `theatre_chain_level_grade` tinyint(2) NOT NULL COMMENT '签约档次',
  PRIMARY KEY (`theatre_chain_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='院线等级表';
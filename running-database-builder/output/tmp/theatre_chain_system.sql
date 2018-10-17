CREATE TABLE `theatre_chain_system` (
  `theatre_chain_id` int(10) NOT NULL COMMENT '院线ID',
  `theatre_chain_name` varchar(255) NOT NULL COMMENT '院线名称',
  `theatre_chain_group` int(10) NOT NULL COMMENT '院线组',
  `theatre_chain_icon` varchar(255) NOT NULL COMMENT '院线ICON',
  `theatre_chain_cinema` int(10) NOT NULL COMMENT '旗下影院数量',
  `theatre_chain_pf_sex` tinyint(2) NOT NULL COMMENT '偏好性别',
  `theatre_chain_pf_nation` tinyint(2) NOT NULL COMMENT '偏好国籍',
  `theatre_chain_pf_factions` tinyint(2) NOT NULL COMMENT '偏好类型',
  `theatre_chain_pf_type` tinyint(2) NOT NULL COMMENT '偏好年龄型',
  `theatre_chain_grade` tinyint(2) NOT NULL COMMENT '签约要求档次',
  PRIMARY KEY (`theatre_chain_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='院线基础表';
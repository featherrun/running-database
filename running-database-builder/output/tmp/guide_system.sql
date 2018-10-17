CREATE TABLE `guide_system` (
  `guide_id` int(10) NOT NULL COMMENT 'ID',
  `guide_type` int(10) NOT NULL COMMENT '触发类型',
  `guide_value` varchar(255) NOT NULL COMMENT '触发参数',
  `guide_script_id` int(10) NOT NULL COMMENT '触发引导ID',
  PRIMARY KEY (`guide_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='引导模板表';
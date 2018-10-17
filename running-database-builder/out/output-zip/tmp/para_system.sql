CREATE TABLE `para_system` (
  `para_name` varchar(255) NOT NULL COMMENT '系统参数名',
  `para_value` varchar(500) NOT NULL COMMENT '系统参数值',
  `para_des` varchar(500) NOT NULL COMMENT '参数备注',
  PRIMARY KEY (`para_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';
CREATE TABLE `formula_system` (
  `formula_name` varchar(255) NOT NULL COMMENT '系统公式名',
  `formula_content` varchar(500) NOT NULL COMMENT '系统公式',
  `formula_variable` varchar(500) NOT NULL COMMENT '公式包含变量',
  `formula_des` varchar(500) NOT NULL COMMENT '公式备注',
  PRIMARY KEY (`formula_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统公式表';
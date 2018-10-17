CREATE TABLE `enum_system` (
  `enum_id` int(10) NOT NULL COMMENT '枚举类型',
  `enum_movie_BT_name` varchar(255) NOT NULL COMMENT '电影大类名称',
  `enum_role_type_name` varchar(255) NOT NULL COMMENT '角色类型名称',
  `enum_build_type_name` varchar(255) NOT NULL COMMENT '建筑类型名称',
  `enum_market_type_name` varchar(255) NOT NULL COMMENT '市场国家名称',
  PRIMARY KEY (`enum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='枚举表';
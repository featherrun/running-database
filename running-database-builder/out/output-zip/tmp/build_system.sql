CREATE TABLE `build_system` (
  `build_floor` tinyint(2) NOT NULL COMMENT '层',
  `build_need_brick` int(10) NOT NULL COMMENT '开启砖头',
  `build_need_gold` int(10) NOT NULL COMMENT '开启金币',
  PRIMARY KEY (`build_floor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='建筑基础表';
CREATE TABLE `filmtype_system` (
  `filmtype_id` int(10) NOT NULL COMMENT '拍摄类型',
  `filmtype_name` varchar(255) NOT NULL COMMENT '类型名称',
  `filmtype_open_level` int(10) NOT NULL COMMENT '开启等级',
  `filmtype_pro1` int(10) NOT NULL COMMENT '科幻片',
  `filmtype_pro2` int(10) NOT NULL COMMENT '犯罪片',
  `filmtype_pro3` int(10) NOT NULL COMMENT '现代片',
  `filmtype_pro4` int(10) NOT NULL COMMENT '谍战片',
  `filmtype_pro5` int(10) NOT NULL COMMENT '奇幻片',
  `filmtype_pro6` int(10) NOT NULL COMMENT '励志片',
  `filmtype_pro7` int(10) NOT NULL COMMENT '战争片',
  `filmtype_pro8` int(10) NOT NULL COMMENT '冒险片',
  `filmtype_pro9` int(10) NOT NULL COMMENT '灾难片',
  `filmtype_pro10` int(10) NOT NULL COMMENT '古装片',
  PRIMARY KEY (`filmtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拍摄类型表';
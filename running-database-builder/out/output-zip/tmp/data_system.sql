CREATE TABLE `data_system` (
  `data_id` varchar(255) NOT NULL COMMENT 'ID',
  `data_color` tinyint(2) NOT NULL COMMENT '品质',
  `data_grade` tinyint(2) NOT NULL COMMENT '评级',
  `data_name` varchar(255) NOT NULL COMMENT '名称',
  `data_random_name` varchar(255) NOT NULL COMMENT '随机名称',
  `data_text` varchar(500) NOT NULL COMMENT '描述',
  `data_title` varchar(255) NOT NULL COMMENT '称谓',
  `data_icon` varchar(255) NOT NULL COMMENT 'ICON',
  `data_model` varchar(255) NOT NULL COMMENT '模型',
  `data_effect` varchar(255) NOT NULL COMMENT 'ICON特效',
  `data_type` int(10) NOT NULL COMMENT '类型',
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据总表';
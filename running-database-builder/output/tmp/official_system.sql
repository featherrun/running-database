CREATE TABLE `official_system` (
  `official_id` int(10) NOT NULL COMMENT '公务ID',
  `official_sender` varchar(255) NOT NULL COMMENT '发件人',
  `official_name` varchar(255) NOT NULL COMMENT '公务名称',
  `official_text` varchar(255) NOT NULL COMMENT '公务描述',
  PRIMARY KEY (`official_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公务模板表';
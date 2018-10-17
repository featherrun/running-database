CREATE TABLE `meeting_system` (
  `meeting_id` int(10) NOT NULL COMMENT '会议ID',
  `meeting_name` varchar(255) NOT NULL COMMENT '会议公司名称',
  `meeting_text` varchar(255) NOT NULL COMMENT '会议描述',
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议模板表';
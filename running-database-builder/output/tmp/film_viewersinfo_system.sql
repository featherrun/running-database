CREATE TABLE `film_viewersinfo_system` (
  `film_ViewersInfo_id` tinyint(2) NOT NULL COMMENT '点评人ID',
  `film_ViewersInfo_icon` tinyint(2) NOT NULL COMMENT '点评人ICON ID',
  `film_ViewersInfo_name` varchar(255) NOT NULL COMMENT '点评人姓名',
  PRIMARY KEY (`film_ViewersInfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='观众信息表';
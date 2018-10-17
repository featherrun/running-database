CREATE TABLE `market_name_system` (
  `market_name_id` int(10) NOT NULL COMMENT 'ID',
  `market_name_nation` tinyint(2) NOT NULL COMMENT '国家ID',
  `market_name_order` tinyint(2) NOT NULL COMMENT '顺序',
  `market_name_city` varchar(255) NOT NULL COMMENT '城市名称',
  `market_name_icon` varchar(255) NOT NULL,
  `market_name_landmark1` varchar(255) NOT NULL COMMENT '地标1名称',
  `market_name_landmark2` varchar(255) NOT NULL COMMENT '地标2名称',
  `market_name_landmark3` varchar(255) NOT NULL COMMENT '地标3名称',
  `market_name_landmark4` varchar(255) NOT NULL COMMENT '地标4名称',
  `market_name_landmark5` varchar(255) NOT NULL COMMENT '地标5名称',
  `market_name_landmark6` varchar(255) NOT NULL COMMENT '地标6名称',
  `market_name_landmark7` varchar(255) NOT NULL COMMENT '地标7名称',
  `market_name_landmark8` varchar(255) NOT NULL COMMENT '地标8名称',
  `market_name_landmark9` varchar(255) NOT NULL COMMENT '地标9名称',
  `market_name_landmark10` varchar(255) NOT NULL COMMENT '地标10名称',
  `market_name_landmark11` varchar(255) NOT NULL COMMENT '地标11名称',
  `market_name_landmark12` varchar(255) NOT NULL COMMENT '地标12名称',
  `market_name_landmark13` varchar(255) NOT NULL COMMENT '地标13名称',
  `market_name_landmark14` varchar(255) NOT NULL COMMENT '地标14名称',
  `market_name_landmark15` varchar(255) NOT NULL COMMENT '地标15名称',
  PRIMARY KEY (`market_name_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场名称表';
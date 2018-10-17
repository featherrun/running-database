CREATE TABLE `film_mediascore_system` (
  `film_MediaScore_ID` tinyint(2) NOT NULL COMMENT '评价ID',
  `film_MediaScore_Min` tinyint(2) NOT NULL COMMENT '评分Min值',
  `ViewerSscore_Max` tinyint(2) NOT NULL COMMENT '评分Max值',
  `ViewerSscore_Evaluation` varchar(255) NOT NULL COMMENT '评价内容',
  PRIMARY KEY (`film_MediaScore_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体评价表';
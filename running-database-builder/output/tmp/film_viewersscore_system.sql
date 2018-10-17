CREATE TABLE `film_viewersscore_system` (
  `ViewerSscore_ID` tinyint(2) NOT NULL COMMENT '点评ID',
  `ViewerSscore_Min` tinyint(2) NOT NULL COMMENT '评分Min值',
  `ViewerSscore_Max` tinyint(2) NOT NULL COMMENT '评分Max值',
  `ViewerSscore_Evaluation` varchar(255) NOT NULL COMMENT '点评内容',
  PRIMARY KEY (`ViewerSscore_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='观众点评表';
CREATE TABLE `film_mediainfo_system` (
  `film_MediaInfo_id` tinyint(2) NOT NULL COMMENT '媒体ID',
  `film_MediaInfo_category` tinyint(2) NOT NULL COMMENT '媒体类别',
  `film_MediaInfo_name` varchar(255) NOT NULL COMMENT '媒体名称',
  `film_MediaInfo_title` varchar(255) NOT NULL COMMENT '媒体评论标题',
  PRIMARY KEY (`film_MediaInfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='媒体信息表';
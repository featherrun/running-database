CREATE TABLE `actor_grow_system` (
  `actor_grow_id` varchar(255) NOT NULL COMMENT '演员ID',
  `actor_grow_scene` int(10) NOT NULL COMMENT '场面成长',
  `actor_grow_act` int(10) NOT NULL COMMENT '表演成长',
  `actor_grow_plot` int(10) NOT NULL COMMENT '剧情成长',
  `actor_grow_art` int(10) NOT NULL COMMENT '艺术成长',
  `actor_grow_entertainment` int(10) NOT NULL COMMENT '娱乐成长',
  PRIMARY KEY (`actor_grow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员成长表';
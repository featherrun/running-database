CREATE TABLE `actor_system` (
  `actor_id` varchar(255) NOT NULL COMMENT 'ID',
  `actor_nation` tinyint(2) NOT NULL COMMENT '国籍',
  `actor_sex` tinyint(2) NOT NULL COMMENT '性别',
  `actor_factions` tinyint(2) NOT NULL COMMENT '所属派别',
  `actor_type` tinyint(2) NOT NULL COMMENT '类型',
  `actor_scene` tinyint(2) NOT NULL COMMENT '场面',
  `actor_act` tinyint(2) NOT NULL COMMENT '表演',
  `actor_plot` tinyint(2) NOT NULL COMMENT '剧情',
  `actor_art` tinyint(2) NOT NULL COMMENT '艺术',
  `actor_entertainment` tinyint(2) NOT NULL COMMENT '娱乐',
  `actor_goodat_drama` varchar(255) NOT NULL COMMENT '擅长剧情',
  `actor_goodat_role` varchar(255) NOT NULL COMMENT '擅长角色',
  `actor_skill` varchar(255) NOT NULL COMMENT '技能',
  `actor_assets` varchar(255) NOT NULL COMMENT '资产',
  `actor_assistant` varchar(255) NOT NULL COMMENT '助理',
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员模板表';
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
  `actor_assets1` varchar(255) NOT NULL COMMENT '资产1',
  `actor_assets_att1` tinyint(2) NOT NULL COMMENT '资产1增加属性',
  `actor_assets2` varchar(255) NOT NULL COMMENT '资产2',
  `actor_assets_att2` tinyint(2) NOT NULL COMMENT '资产2增加属性',
  `actor_assets3` varchar(255) NOT NULL COMMENT '资产3',
  `actor_assets_att3` tinyint(2) NOT NULL COMMENT '资产3增加属性',
  `actor_assets4` varchar(255) NOT NULL COMMENT '资产4',
  `actor_assets_att4` tinyint(2) NOT NULL COMMENT '资产4增加属性',
  `actor_skill` varchar(255) NOT NULL COMMENT '技能',
  `actor_assistant` varchar(255) NOT NULL COMMENT '助理',
  `actor_weight` int(10) NOT NULL COMMENT '星探权重',
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员模板表';
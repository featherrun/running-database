CREATE TABLE `drama_role_system` (
  `drama_role_id` int(10) NOT NULL COMMENT 'ID',
  `drama_role_dramaid` varchar(255) NOT NULL COMMENT '剧本ID',
  `drama_role_play` varchar(255) NOT NULL COMMENT '角色担当',
  `drama_role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `drama_role_text` varchar(255) NOT NULL COMMENT '角色介绍',
  `drama_role_type` varchar(255) NOT NULL COMMENT '角色类型',
  `drama_role_nation` tinyint(2) NOT NULL COMMENT '艺人国籍限制',
  `drama_role_sex` tinyint(2) NOT NULL COMMENT '艺人性别限制',
  `drama_role_factions` tinyint(2) NOT NULL COMMENT '艺人派别限制',
  `drama_role_actortype` tinyint(2) NOT NULL COMMENT '艺人类型限制',
  `drama_role_att_A` int(10) NOT NULL COMMENT '角色出演属性A',
  `drama_role_att_B` int(10) NOT NULL COMMENT '角色出演属性B',
  `drama_role_weight` int(10) NOT NULL COMMENT '角色权重',
  PRIMARY KEY (`drama_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='剧本角色表';
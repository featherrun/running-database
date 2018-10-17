CREATE TABLE `actor_assets_level_system` (
  `actor_assets_level` int(10) NOT NULL COMMENT '资产等级',
  `actor_assets_level_star` tinyint(2) NOT NULL COMMENT '道具星级',
  `actor_assets_level_num1` int(10) NOT NULL COMMENT '1星艺人需要数量',
  `actor_assets_level_num2` int(10) NOT NULL COMMENT '2星艺人需要数量',
  `actor_assets_level_num3` int(10) NOT NULL COMMENT '3星艺人需要数量',
  `actor_assets_level_num4` int(10) NOT NULL COMMENT '4星艺人需要数量',
  `actor_assets_level_num5` int(10) NOT NULL COMMENT '5星艺人需要数量',
  `actor_assets_level_num6` int(10) NOT NULL COMMENT '6星艺人需要数量',
  `actor_assets_level_att1` int(10) NOT NULL COMMENT '1星增加属性值',
  `actor_assets_level_att2` int(10) NOT NULL COMMENT '2星增加属性值',
  `actor_assets_level_att3` int(10) NOT NULL COMMENT '3星增加属性值',
  `actor_assets_level_att4` int(10) NOT NULL COMMENT '4星增加属性值',
  `actor_assets_level_att5` int(10) NOT NULL COMMENT '5星增加属性值',
  `actor_assets_level_att6` int(10) NOT NULL COMMENT '6星增加属性值',
  PRIMARY KEY (`actor_assets_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员资产等级表';
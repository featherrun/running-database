CREATE TABLE `actor_assets_system` (
  `actor_assets_id` int(10) NOT NULL COMMENT '资产ID',
  `actor_assets_actor` varchar(255) NOT NULL COMMENT '演员ID',
  `actor_assets_item` int(10) NOT NULL COMMENT '捐赠道具',
  `actor_assets_att` int(10) NOT NULL COMMENT '增加属性类型',
  PRIMARY KEY (`actor_assets_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='演员资产表';
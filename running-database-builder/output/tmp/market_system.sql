CREATE TABLE `market_system` (
  `market_id` int(10) NOT NULL COMMENT '市场ID',
  `market_nation` tinyint(2) NOT NULL COMMENT '国家',
  `market_occupy_reward` int(10) NOT NULL COMMENT '占领奖励',
  `market_gold` int(10) NOT NULL COMMENT '金币持续收益',
  `market_fans` int(10) NOT NULL COMMENT '粉丝持续收益',
  `market_item1` varchar(255) NOT NULL COMMENT '持续收益道具1',
  `market_item_per1` int(10) NOT NULL COMMENT '产出概率1',
  `market_item2` varchar(255) NOT NULL COMMENT '持续收益道具2',
  `market_item_per2` int(10) NOT NULL COMMENT '产出概率2',
  `market_item3` varchar(255) NOT NULL COMMENT '持续收益道具3',
  `market_item_per3` int(10) NOT NULL COMMENT '产出概率3',
  `market_item4` varchar(255) NOT NULL COMMENT '持续收益道具4',
  `market_item_per4` int(10) NOT NULL COMMENT '产出概率4',
  `market_item5` varchar(255) NOT NULL COMMENT '持续收益道具5',
  `market_item_per5` int(10) NOT NULL COMMENT '产出概率5',
  `market_roadshow_money` int(10) NOT NULL COMMENT '路演消耗',
  `market_roadshow_att` int(10) NOT NULL COMMENT '路演最大属性',
  `market_roadshow_need_att` int(10) NOT NULL COMMENT '路演属性需求',
  PRIMARY KEY (`market_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场基础表';
CREATE TABLE `market_landmark_system` (
  `market_landmark_id` int(10) NOT NULL COMMENT '地标ID',
  `market_id` int(10) NOT NULL COMMENT '市场ID',
  `market_landmark_order` tinyint(2) NOT NULL COMMENT '顺序',
  `market_landmark_cost` int(10) NOT NULL COMMENT '宣传消耗钞票',
  `market_landmark_num` int(10) NOT NULL COMMENT '宣传次数',
  `market_landmark_gold` int(10) NOT NULL COMMENT '每次宣传奖励金钱',
  `market_landmark_sex` tinyint(2) NOT NULL COMMENT '代言人性别限制',
  `market_landmark_nation` tinyint(2) NOT NULL COMMENT '代言人国籍限制',
  `market_landmark_factions` tinyint(2) NOT NULL COMMENT '代言人派别限制',
  `market_landmark_type` tinyint(2) NOT NULL COMMENT '代言人年龄型限制',
  `market_landmark_att` tinyint(2) NOT NULL COMMENT '代言人属性限制',
  `market_landmark_att_para` int(10) NOT NULL COMMENT '代言人属性值要求',
  `market_landmark_reward` int(10) NOT NULL COMMENT '宣传完场奖励',
  PRIMARY KEY (`market_landmark_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市场地标表';
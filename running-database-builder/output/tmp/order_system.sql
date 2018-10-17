CREATE TABLE `order_system` (
  `order_id` int(10) NOT NULL COMMENT '订单ID',
  `order_icon` varchar(255) NOT NULL COMMENT '订单ICON',
  `order_color` tinyint(2) NOT NULL COMMENT '订单品质',
  `order_name` varchar(255) NOT NULL COMMENT '订单名称',
  `order_text` varchar(255) NOT NULL COMMENT '订单描述',
  `order_item1` varchar(255) NOT NULL COMMENT '需求道具1',
  `order_item_num1` int(10) NOT NULL COMMENT '需求道具数量1',
  `order_item2` varchar(255) NOT NULL COMMENT '需求道具2',
  `order_item_num2` int(10) NOT NULL COMMENT '需求道具数量2',
  `order_reward` int(10) NOT NULL COMMENT '奖励',
  `order_Evalue` int(10) NOT NULL COMMENT '增加期待值',
  `order_grade` tinyint(2) NOT NULL COMMENT '需要比赛档次',
  `order_weight` int(10) NOT NULL COMMENT '权重',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单基础表';
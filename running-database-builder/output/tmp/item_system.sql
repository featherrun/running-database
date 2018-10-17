CREATE TABLE `item_system` (
  `item_id` varchar(255) NOT NULL COMMENT '物品ID',
  `item_type` tinyint(3) NOT NULL COMMENT '物品类型',
  `item_uselv` tinyint(3) NOT NULL COMMENT '使用等级',
  `item_stacknum` int(10) NOT NULL COMMENT '堆叠上限',
  `item_para_a` varchar(255) NOT NULL COMMENT '复用字段A',
  `item_para_b` varchar(255) NOT NULL COMMENT '复用字段B',
  `item_para_c` varchar(255) NOT NULL COMMENT '复用字段C',
  `item_para_d` varchar(255) NOT NULL COMMENT '复用字段D',
  `item_para_e` varchar(255) NOT NULL COMMENT '复用字段E',
  `item_sell` varchar(255) NOT NULL COMMENT '出售货币',
  `item_sell_num` int(10) NOT NULL COMMENT '出售价格',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品模板表';
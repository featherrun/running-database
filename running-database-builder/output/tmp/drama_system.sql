CREATE TABLE `drama_system` (
  `drama_id` varchar(255) NOT NULL COMMENT 'ID',
  `drama_type` varchar(255) NOT NULL COMMENT '剧本类型',
  `drama_cost_money` int(10) NOT NULL COMMENT '消耗美元',
  `drama_need_male` int(10) NOT NULL COMMENT '男性需求',
  `drama_need_female` int(10) NOT NULL COMMENT '女性需求',
  `drama_need_child` int(10) NOT NULL COMMENT '儿童需求',
  `drama_add_att` int(10) NOT NULL COMMENT '额外属性类型',
  `drama_add_att_per` int(10) NOT NULL COMMENT '额外属性百分比',
  `drama_scene_max` int(10) NOT NULL COMMENT '场面属性上限',
  `drama_act_max` int(10) NOT NULL COMMENT '表演属性上限',
  `drama_plot_max` int(10) NOT NULL COMMENT '剧情属性上限',
  `drama_art_max` int(10) NOT NULL COMMENT '艺术属性上限',
  `drama_entertainment_max` int(10) NOT NULL COMMENT '娱乐属性上限',
  PRIMARY KEY (`drama_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='剧本模板表';
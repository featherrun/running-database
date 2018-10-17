CREATE TABLE `filmtype_pro_system` (
  `filmtype_pro_jd` int(10) NOT NULL COMMENT '契合等级',
  `filmtype_pro_name` varchar(255) NOT NULL COMMENT '契合等级名称',
  `filmtype_pro_text` varchar(255) NOT NULL COMMENT '评语',
  `filmtype_pro` int(10) NOT NULL COMMENT '契合度加成',
  PRIMARY KEY (`filmtype_pro_jd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拍摄契合度表';
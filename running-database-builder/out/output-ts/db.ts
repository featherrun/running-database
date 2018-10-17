namespace db {
	export interface ActorSystem {
		actorId:string; //ID
		actorNation:number; //国籍
		actorSex:number; //性别
		actorFactions:number; //所属派别
		actorType:number; //类型
		actorScene:number; //场面
		actorAct:number; //表演
		actorPlot:number; //剧情
		actorArt:number; //艺术
		actorEntertainment:number; //娱乐
		actorGoodatDrama:string; //擅长剧情
		actorGoodatRole:string; //擅长角色
		actorSkill:string; //技能
		actorAssets:string; //资产
		actorAssistant:string; //助理
	}
	export interface BuildLevelSystem {
		buildLevelId:number; //ID
		buildLevelDepartment:number; //部门
		buildLevel:number; //等级
		buildUnlockType1:number; //解锁条件1
		buildUnlockPara1:string; //解锁条件参数1
		buildUnlockType2:number; //解锁条件2
		buildUnlockPara2:string; //解锁条件参数2
		buildLevelCost:string; //消耗道具
		buildLevelCostNum:number; //消耗道具数量
		buildLevelCostGold:number; //消耗金币
		buildLevelEffectType1:number; //效果类型1
		buildLevelEffectPara1:number; //效果类型参数1
		buildLevelEffectType2:number; //效果类型2
		buildLevelEffectPara2:number; //效果类型参数2
	}
	export interface BuildSystem {
		buildFloor:number; //层
		buildNeedBrick:number; //开启砖头
		buildNeedGold:number; //开启金币
	}
	export interface CompanyLevelSystem {
		companyLevel:number; //等级
		companyLevelExp:number; //升级粉丝数
		companyLevelReward:number; //升级奖励
		companyLevelDrama:string; //升级解锁剧本
	}
	export interface DataSystem {
		dataId:string; //ID
		dataColor:number; //品质
		dataGrade:number; //评级
		dataName:string; //名称
		dataRandomName:string; //随机名称
		dataText:string; //描述
		dataTitle:string; //称谓
		dataIcon:string; //ICON
		dataModel:string; //模型
		dataEffect:string; //ICON特效
		dataType:number; //类型
	}
	export interface DramaSystem {
		dramaId:string; //ID
		dramaType:string; //剧本类型
		dramaCostMoney:number; //消耗美元
		dramaNeedMale:number; //男性需求
		dramaNeedFemale:number; //女性需求
		dramaNeedChild:number; //儿童需求
		dramaAddAtt:number; //额外属性类型
		dramaAddAttPer:number; //额外属性百分比
		dramaRole1:number; //角色1
		dramaRole1Name:string; //角色1名称
		dramaRole1Text:string; //角色1介绍
		dramaRole1Type:string; //角色1类型
		dramaRole1Limit:string; //角色1出演要求1
		dramaRole1AttA:number; //角色1出演属性A
		dramaRole1AttB:number; //角色1出演属性B
		dramaRole1Weight:number; //角色1权重
		dramaRole2:number; //角色2
		dramaRole2Name:string; //角色2名称
		dramaRole2Text:string; //角色2介绍
		dramaRole2Type:string; //角色2类型
		dramaRole2Limit:string; //角色2出演要求2
		dramaRole2AttA:number; //角色2出演属性A
		dramaRole2AttB:number; //角色2出演属性B
		dramaRole2Weight:number; //角色2权重
		dramaRole3:number; //角色3
		dramaRole3Name:string; //角色3名称
		dramaRole3Text:string; //角色3介绍
		dramaRole3Type:string; //角色3类型
		dramaRole3Limit:string; //角色3出演要求3
		dramaRole3AttA:number; //角色3出演属性A
		dramaRole3AttB:number; //角色3出演属性B
		dramaRole3Weight:number; //角色3权重
		dramaRole4:number; //角色4
		dramaRole4Name:string; //角色4名称
		dramaRole4Text:string; //角色4介绍
		dramaRole4Type:string; //角色4类型
		dramaRole4Limit:string; //角色4出演要求4
		dramaRole4AttA:number; //角色4出演属性A
		dramaRole4AttB:number; //角色4出演属性B
		dramaRole4Weight:number; //角色4权重
	}
	export interface FormulaSystem {
		formulaName:string; //系统公式名
		formulaContent:string; //系统公式
		formulaVariable:string; //公式包含变量
		formulaDes:string; //公式备注
	}
	export interface ParaSystem {
		paraName:string; //系统参数名
		paraValue:string; //系统参数值
		paraDes:string; //参数备注
	}
}

const LocalCache = {
	BuildLevelSystem: new Running.LocalTable<db.BuildLevelSystem>("buildLevelId"),
	BuildSystem: new Running.LocalTable<db.BuildSystem>("buildFloor"),
	FormulaSystem: new Running.LocalTable<db.FormulaSystem>("formulaName"),
	ActorSystem: new Running.LocalTable<db.ActorSystem>("actorId"),
	ParaSystem: new Running.LocalTable<db.ParaSystem>("paraName"),
	DramaSystem: new Running.LocalTable<db.DramaSystem>("dramaId"),
	DataSystem: new Running.LocalTable<db.DataSystem>("dataId"),
	CompanyLevelSystem: new Running.LocalTable<db.CompanyLevelSystem>("companyLevel"),
};

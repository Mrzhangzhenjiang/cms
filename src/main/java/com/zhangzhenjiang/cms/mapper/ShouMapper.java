package com.zhangzhenjiang.cms.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Shou;
public interface ShouMapper {
	List<Map> getShouListByUid(@Param("uid")Integer uid);
	Shou isShou(@Param("uid")Integer uid,@Param("aid")Integer aid);
	int insertShou(@Param("uid")Integer uid,@Param("aid")Integer aid);
	int delShou(@Param("uid")Integer uid,@Param("aid")Integer aid);
}

package com.zhangzhenjiang.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.History;

public interface HistoryMapper {
	List<Map> getHistoryListByUid(@Param("uid")Integer uid);
	History isHistory(@Param("uid")Integer uid,@Param("aid")Integer aid);
	int insertHistory(@Param("uid")Integer uid,@Param("aid")Integer aid);
	int updateHtime(@Param("uid")Integer uid,@Param("aid")Integer aid);
}

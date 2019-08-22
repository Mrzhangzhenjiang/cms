package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import com.zhangzhenjiang.cms.bean.History;

public interface HistoryService {
	List<Map> getHistoryListByUid(Integer uid);
	History isHistory(Integer uid,Integer aid);
	boolean insertHistory(Integer uid,Integer aid);
	boolean updateHtime(Integer uid,Integer aid);
}

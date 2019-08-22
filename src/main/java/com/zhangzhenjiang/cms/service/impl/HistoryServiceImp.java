package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.History;
import com.zhangzhenjiang.cms.mapper.HistoryMapper;
import com.zhangzhenjiang.cms.service.HistoryService;
@Service
public class HistoryServiceImp implements HistoryService{
	@Resource
	private HistoryMapper historyMapper;
	@Override
	public History isHistory(Integer uid, Integer aid) {
		return historyMapper.isHistory(uid, aid);
	}
	@Override
	public boolean insertHistory(Integer uid, Integer aid) {
		return historyMapper.insertHistory(uid, aid)>0;
	}
	@Override
	public boolean updateHtime(Integer uid, Integer aid) {
		return historyMapper.updateHtime(uid, aid)>0;
	}
	@Override
	public List<Map> getHistoryListByUid(Integer uid) {
		return historyMapper.getHistoryListByUid(uid);
	}

}

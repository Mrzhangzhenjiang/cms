package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Shou;
import com.zhangzhenjiang.cms.mapper.ShouMapper;
import com.zhangzhenjiang.cms.service.ShouService;
@Service
public class ShouServiceImp implements ShouService{
	@Resource
	private ShouMapper shouMapper;
	@Override
	public Shou isShou(Integer uid, Integer aid) {
		return shouMapper.isShou(uid, aid);
	}
	@Override
	public boolean insertShou(Integer uid, Integer aid) {
		return shouMapper.insertShou(uid, aid)>0;
	}
	@Override
	public boolean delShou(Integer uid, Integer aid) {
		return shouMapper.delShou(uid, aid)>0;
	}
	@Override
	public List<Map> getShouListByUid(Integer uid) {
		return shouMapper.getShouListByUid(uid);
	}

}

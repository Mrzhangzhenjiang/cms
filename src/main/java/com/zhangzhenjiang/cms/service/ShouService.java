package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import com.zhangzhenjiang.cms.bean.Shou;

public interface ShouService {
	List<Map> getShouListByUid(Integer uid);
	Shou isShou(Integer uid,Integer aid);
	boolean insertShou(Integer uid,Integer aid);
	boolean delShou(Integer uid,Integer aid);
}

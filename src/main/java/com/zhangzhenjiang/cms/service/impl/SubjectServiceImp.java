package com.zhangzhenjiang.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Article;
import com.zhangzhenjiang.cms.bean.Subject;
import com.zhangzhenjiang.cms.mapper.ArticleMapper;
import com.zhangzhenjiang.cms.mapper.SubjectMapper;
import com.zhangzhenjiang.cms.service.SubjectService;

@Service
public class SubjectServiceImp implements SubjectService{
	@Resource
	private SubjectMapper subjectmapper;
	
	@Resource
	private ArticleMapper articlemapper;

	@Override
	public List<Map> selectSubjectList() {
		return subjectmapper.selectSubjectList();
	}

	@Override
	public int insert(Integer aid, Integer sid) {
		return subjectmapper.insert(aid, sid);
	}

	@Override
	public List<Map> selectArtilceListBySid(Integer sid) {
		return subjectmapper.selectArtilceListBySid(sid);
	}

	@Override
	public boolean insert2(Integer[] aid, Integer sid) {
		try {
			// 1先删除专题已有的文章.
			subjectmapper.deleteBySid(sid);
			//2. 在执行插入
			 if(aid!=null) {
				for (int i = 0; i < aid.length; i++)
					subjectmapper.insert(aid[i], sid);
			 }
			 return true;
		} catch (Exception e) {
			throw new RuntimeException("添加失败!");
		}
	}

	@Override
	public List<Map> selectSubjectIdByAid(Integer aid) {
		return subjectmapper.selectSubjectIdByAid(aid);
	}

	@Override
	public int addSubject(Subject subject) {
		return subjectmapper.addSubject(subject);
	}

	@Override
	public Subject selectSubjectBySid(Integer sid) {
		return (Subject) subjectmapper.selectSubjectBySid(sid);
	}

	@Override
	public int updateSubject(Subject subject) {
		return subjectmapper.updateSubject(subject);
	}

	@Override
	public boolean addArticle(Integer aid, Integer sid) {
		Article article = articlemapper.selectArticleByAid(aid);
		if(article!=null) {
			subjectmapper.insert(aid, sid);
			return true;
		}else {
			return false;
		}
	}
}

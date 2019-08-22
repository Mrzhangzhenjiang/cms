package com.zhangzhenjiang.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Subject;

public interface SubjectMapper {
	List<Map> selectSubjectList();
	
	int insert(@Param("aid")Integer aid,@Param("sid")Integer sid);
	
	int  deleteBySid(@Param("sid")Integer sid);
	
	List<Map> selectArtilceListBySid(Integer sid);
	
	List<Map> selectSubjectIdByAid(Integer aid);
	
	int addSubject(Subject subject);
	
	Subject selectSubjectBySid(Integer sid);
	
	int updateSubject(Subject subject);
}

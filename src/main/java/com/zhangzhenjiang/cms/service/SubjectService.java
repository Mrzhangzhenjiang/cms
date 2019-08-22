package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Subject;

public interface SubjectService {
	/**
	 * 
	 * <br>Description:TODO 查询专题列表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @return
	 */
	List<Map> selectSubjectList();
	/**
	 * 
	 * <br>Description:TODO 补全专题表与文章表的中间表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param aid
	 * @param sid
	 * @return
	 */
	int insert(Integer aid,Integer sid);
	/**
	 * 
	 * <br>Description:TODO 补全专题表与文章表的中间表    先删除再更新调用上一个方法循环添加
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param aid
	 * @param sid
	 * @return
	 */
	boolean insert2(Integer aid[],Integer sid);
	/**
	 * 
	 * <br>Description:TODO 通过专题id查询专题下的所有文章   sql中通过中间表连接
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param sid
	 * @return
	 */
	List<Map> selectArtilceListBySid(Integer sid);
	/**
	 * 
	 * <br>Description:TODO 通过文章id查询该文章所在的专题用于选中修改页面的下拉框
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param aid
	 * @return
	 */
	List<Map> selectSubjectIdByAid(Integer aid);
	
	int addSubject(Subject subject);
	
	Subject selectSubjectBySid(Integer sid);
	
	int updateSubject(Subject subject);
	
	boolean addArticle(Integer aid,Integer sid);
}

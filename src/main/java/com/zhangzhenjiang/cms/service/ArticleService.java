package com.zhangzhenjiang.cms.service;

import java.util.List;
import java.util.Map;

import com.zhangzhenjiang.cms.bean.Article;

public interface ArticleService {
	/**
	 * 
	 * <br>Description:TODO 向文章表中添加数据    sid用于补全专题中间表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param article
	 * @param sid
	 * @return
	 */
	boolean insert(Article article,Integer[] sid);
	/**
	 * 
	 * <br>Description:TODO 通过用户id查询个人文章列表  id从session中获取
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param userId
	 * @return
	 */
	List<Map> listByUserId(Integer userId) ;
	/**
	 * 
	 * <br>Description:TODO 通过文章id查看文章明细
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param id
	 * @return
	 */
	Map get(Integer id);
	/**
	 * 
	 * <br>Description:TODO 通过状态查询文章列表   状态的传递依靠下拉框
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param status
	 * @return
	 */
	List<Map> selectList(String status);
	/**
	 * 
	 * <br>Description:TODO 修改文章   请保证各个字段完整
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param article
	 * @return
	 */
	int update(Article article);
	/**
	 * 
	 * <br>Description:TODO 查询热门文章列表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @return
	 */
	List<Map> selectHotList(String titleorcontent);
	/**
	 * 
	 * <br>Description:TODO 通过栏目id查询文章列表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param cid
	 * @return
	 */
	List<Map> selectListByCId(Integer cid);
	/**
	 * 
	 * <br>Description:TODO 通过分类id查询文章列表
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param catid
	 * @return
	 */
	List<Map> selectListByCatId(Integer catid);
	/**
	 * 
	 * <br>Description:TODO 根据栏目或分类查询所有文章
	 * <br>Author:Mr.ZhangZhenJiang(2395132803@qq.com)
	 * <br>Date:2019年7月2日
	 * @param cid
	 * @param catId
	 * @return
	 */
	List<Map> selectListByCIdCatId(String cid ,String catId);
	
	Article selectArticleByAid(Integer aid);
	//浏览次数+1
	boolean incrHits(Integer aid);
	//最新文章
	List<Article> getNewsArticle();
	//删除最新文章以及更新redis中最新文章
	boolean delnew(int nid);
}

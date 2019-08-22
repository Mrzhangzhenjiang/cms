package com.zhangzhenjiang.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhangzhenjiang.cms.bean.Comment;

public interface CommentMapper {
	List<Comment> getCommentListByAid(@Param("aid")Integer aid,@Param("uid")Integer uid);
	int addComment(Comment comment);
	int delComment(@Param("cid")Integer cid);
}

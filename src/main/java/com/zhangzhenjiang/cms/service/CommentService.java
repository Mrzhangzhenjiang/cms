package com.zhangzhenjiang.cms.service;

import java.util.List;

import com.zhangzhenjiang.cms.bean.Comment;

public interface CommentService {
	List<Comment> getCommentListByAid(Integer aid,Integer uid);
	boolean addComment(Comment comment);
	boolean delComment(Integer cid);
}

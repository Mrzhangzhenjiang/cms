package com.zhangzhenjiang.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhangzhenjiang.cms.bean.Comment;
import com.zhangzhenjiang.cms.mapper.CommentMapper;
import com.zhangzhenjiang.cms.service.CommentService;

@Service
public class CommentServiceImp implements CommentService{
	@Resource
	private CommentMapper commentMapper;
	@Override
	public boolean addComment(Comment comment) {
		return commentMapper.addComment(comment)>0;
	}
	@Override
	public List<Comment> getCommentListByAid(Integer aid,Integer uid) {
		return commentMapper.getCommentListByAid(aid,uid);
	}
	@Override
	public boolean delComment(Integer cid) {
		return commentMapper.delComment(cid)>0;
	}
	
}

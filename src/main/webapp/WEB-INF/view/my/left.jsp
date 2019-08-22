<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="avatar">
	<img alt="" src="/images/default_avatar.png" class="img-thumbnail">
</div>
<br />
<div>
	<div class="list-group">
		<ul class="list-group">
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)"  data="/article/listByUserId">我的文章
			</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/article/toPublish"
				class="list-group-item">发布文章</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/getcommentlist" class="list-group-item">我的评论</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" class="list-group-item">上传头像</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/user/toUpdateUser" class="list-group-item">个人设置</a></li>
				<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/getshoulist" class="list-group-item">我的收藏</a></li>
			<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/gethistorylist" class="list-group-item">浏览历史</a></li>
		
		<li class="list-group-item  text-center"><a class="channel"
				href="javascript:void(0)" data="/blog/toList" class="list-group-item">博客管理</a></li>
		
		
		
		</ul>
	</div>
</div>
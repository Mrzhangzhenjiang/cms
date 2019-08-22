<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<input type="hidden" value="${sessionScope.user.username}" id="uname">
		<input type="hidden" value="${sessionScope.user.id}" id="uid">
		<dl>
			<dt>${article.title }<input type="button" id="shoubtn" value="点击收藏" onclick="shou()"></dt>
			<dt>浏览次数：${article.hits }</dt>
			<hr>

			<dd>${article.content }</dd>
		</dl>
		
		<h3>网友评论</h3>
		<textarea rows="3" cols="50" name="ccomment" id="ccomment"></textarea>
		<br>
		<button type="button" onclick="addcomment()">发表评论</button>
		<br>
		<h5 style="color: red">最新评论：</h5>
		<br>
		<c:forEach items="${commentlist}" var="cl">
			<dl>
				<dd>评论内容：${cl.ccontent }</dd>
				<dt>评论人：${cl.username}   评论时间：${cl.ctime}</dt>
				<hr>
			</dl>
		</c:forEach>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		//点击量+1
		$.post("/article/incrhits", {
			aid : '${article.id}'
		}, function() {

		})
		//判断是否登陆以及初始化收藏按钮
		if($("#uname").val() != ""){
			$.post("/isshou",{uid:$("#uid").val(),aid:'${article.id}'},function(flag){
				if(flag){
					$("#shoubtn").val("取消收藏");
				}
			})
			//判断是否是更新浏览时间还是新增浏览历史
			$.post("/ishistory",{uid:$("#uid").val(),aid:'${article.id}'},function(flag){
				if(flag){
					//已经浏览过了   更新浏览时间
					$.post("/updatehtime",{uid:$("#uid").val(),aid:'${article.id}'},function(){})
				}else{
					//还没有浏览过这篇文章，添加浏览记录
					$.post("/inserthistory",{uid:$("#uid").val(),aid:'${article.id}'},function(){})
				}
			})
		}
	})
	//评论功能
	function addcomment() {
		//定义变量用于验证评论长度是否大于200
		var checklen;
		if($("#ccomment").val().length>=200){
			checklen=false;
		}else{
			checklen=true;
		}
		if ($("#uname").val() != "") {
			if(checklen){
				$.post("/addcomment", {
					aid : '${article.id}',
					ccomment : $("#ccomment").val()
				}, function(flag) {
					if (flag) {
						alert("评论成功！");
						window.history.go(0);
					} else {
						alert("评论失败！");
					}
				})
			}else{
				alert("评论内容不能大于200个字哦！");
			}
		} else {
			if (confirm("点击确定登陆以后才有评论权限哦！")) {
				location.href = "/user/toLogin";
			}
		}
	}
	//收藏功能
	function shou(){
		if($("#uname").val() != ""){
			var btnval=$("#shoubtn").val();
			if(btnval=="点击收藏"){
				$.post("/insertshou",{uid:$("#uid").val(),aid:'${article.id}'},function(flag){
					if(flag){
						alert("收藏成功！");
						window.history.go(0);
					}else{
						alert("收藏失败！");
					}
				})
			}else{
				$.post("/delshou",{uid:$("#uid").val(),aid:'${article.id}'},function(flag){
					if(flag){
						alert("取消收藏成功！");
						window.history.go(0);
					}else{
						alert("取消收藏失败！");
					}
				})
			}
		}else {
			if (confirm("点击确定登陆以后才能收藏哦！")) {
				location.href = "/user/toLogin";
			}
		}
	}
</script>
</html>
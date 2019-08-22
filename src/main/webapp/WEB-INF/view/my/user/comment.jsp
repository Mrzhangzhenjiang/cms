<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>我的评论界面</title>
</head>
<body>
	<c:forEach items="${commentlist}" var="cl">
		<dl>
			<dt>评论内容：${cl.ccontent}</dt>
			<dd>评论时间：${cl.ctime }<span style="float: right"><input type="button" value="删除" onclick="delcomment('${cl.cid}')"></span></dd>
		</dl>
		<hr>
	</c:forEach>
	${pageInfo}
</body>
<script type="text/javascript">
	$(function(){
		$(".page-link").click(function(){
			var url=$(this).attr("data");
			$('#center').load(url);
		})
	})
	function delcomment(cid){
		$.post("/delcomment",{cid:cid},function(flag){
			if(flag){
				alert("删除评论成功！");
				$('#center').load("/getcommentlist");
			}else{
				alert("删除评论失败！");
			}
		})
	}
</script>
</html>
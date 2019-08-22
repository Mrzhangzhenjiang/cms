<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>我的文章界面</title>
</head>
<body>
	<c:forEach items="${list}" var="a">
		<dl>
			<dt><a href="javascript:myopen(${a.id})">${a.title}</a></dt>
			<dd>作者:张三,发布时间:${a.created}<span style="float: right"><a class="channel" href="javascript:void(0)" data="/article/toUpdate?id=${a.id }">编辑</a></span></dd>
		</dl>
		<hr>
	</c:forEach>
	${pageInfo}
</body>
<script type="text/javascript">
function myopen(id){
	window.open("/article/getDetail?id="+id,"_blank");
}
$(function(){
	$(".page-link").click(function(){
		var url=$(this).attr("data");
		$('#center').load(url);
	})
})
</script>
</html>
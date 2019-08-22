<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>我的收藏列表</title>
</head>
<body>
<body>
	<c:forEach items="${shoulist}" var="sl">
		<dl>
			<dt>文章标题：${sl.title}</dt>
			<dd>收藏时间：${sl.stime }<span style="float: right"><input type="button" value="删除" onclick="delshou('${sl.sid}')"></span></dd>
		</dl>
		<hr>
	</c:forEach>
	${pageInfo}
</body>
</body>
</html>
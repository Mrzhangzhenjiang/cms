<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>我的浏览记录</title>
</head>
<body>
<body>
	<c:forEach items="${historylist}" var="hl">
		<dl>
			<dt>文章标题：${hl.title}</dt>
			<dd>浏览时间：${hl.htime }<span style="float: right"><input type="button" value="清除" onclick="delhistory('${hl.hid}')"></span></dd>
		</dl>
		<hr>
	</c:forEach>
	${pageInfo}
</body>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>文章详情</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
</head>
<body>
<div class="container">
		<dl>
			<dt>${article.title }</dt>
				<hr>
			
			<dd>${article.content }</dd>
		</dl>
</div>
</body>
</html>
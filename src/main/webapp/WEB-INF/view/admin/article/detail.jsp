<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理员查看文章详情</title>
</head>
<body>  
<div class="container">
		<dl>
			<dt>${article.title }</dt>
				<hr>
			
			<dd>${article.content }</dd>
		</dl>
	<button type="button" onclick="pass(1)" class="btn btn-info">通过</button>
	<button type="button" onclick="pass(-1)" class="btn btn-warning">不通过</button>
	<button type="button" onclick="goBack()" class="btn btn-green">返回</button>
</div>
</body>
<script type="text/javascript">
	function pass(status){
		$.post("/article/pass",{status:status,id:'${article.id}'},function(obj){
			if(obj){
				alert("操作成功!")
				$("#content-wrapper").load("/article/checkList")
			}
		})
	}
	function goBack(){
		$("#content-wrapper").load("/article/checkList")
	}
</script>
</html>
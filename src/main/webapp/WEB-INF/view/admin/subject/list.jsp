<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>专题管理界面</title>
</head>
<body>
	<div class="container-fluid">
		<table class="table table-sm  table-hover table-bordered ">
			<thead class="thead-light">
				<tr align="center">
					<td>序号</td>
					<td>专题名称</td>
					<td>操作</td>
				</tr>
			</thead>
			<c:forEach items="${subList }" var="s" varStatus="index">
				<tr align="center">
					<td>${index.index+1 }</td>
					<td>${s.name}</td>
					<td><button onclick="toAdd(${s.id})" type="button" class="btn btn-info" type="button"> 追加文章</button> 
						<button onclick="toUpdateSubject(${s.id})" type="button" class="btn btn-info" type="button">修改专题</button> </td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="11">
					<button onclick="toAddSubject()" class="btn btn-info" type="button">添加专题</button>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript">
function toAdd(sid){
	 //在中间区域显示地址的内容
    $('#content-wrapper').load("/subject/toAddArticle?sid="+sid);
}
function toAddSubject(){
	//在中间区域显示添加专题界面
	$('#content-wrapper').load("/subject/toAddSubject");
}
function toUpdateSubject(sid){
	//在中间区域显示添加专题界面
	$('#content-wrapper').load("/subject/toUpdateSubject?sid="+sid);
}
</script>
</html>
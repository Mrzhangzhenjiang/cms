<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<title>文章审核界面</title>
</head>
<body>
<div class="container-fluid">
		<div>
		     文章状态
			  <select class="form-control-sm" >
			    <option value="">全部</option>
			    <option value="0">待审核</option>
			    <option value="1">已审核</option>
			    <option value="-1">审核未通过</option>
			  </select>
		</div>
		<table class="table table-sm  table-hover table-bordered ">
			<thead class="thead-light">
				<tr align="center">
					<td>序号</td>
					<td>作者</td>
					<td>标题</td>
					<td>当前状态</td>
					<td>发布时间</td>
					<td>栏目</td>
					<td>分类</td>
					<td>操作</td>
				</tr>
			</thead>
			<c:forEach items="${list }" var="a" varStatus="index">
				<tr align="center">
					<td>${index.index+1 }</td>
					<td>${a.username}</td>
					<td>${a.title}</td>
					<td>${a.status==0?"待审核":a.status==1?"已审核":"未通过" }</td>
					<td>${a.created}</td>
					<td>${a.name}</td>
					<td>${a.catname}</td>
					<td><button type="button" class="btn btn-info" onclick="toDetail(${a.id})">详情</button> </td>
				</tr>
			</c:forEach>
		</table>
		<div>
		  ${pageInfo}
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$(".form-control-sm").change(function(){
		$("#content-wrapper").load("/article/checkList?status="+$(this).val())
	})
	//下拉框回显
	$(".form-control-sm").val('${status}')
})
//查看文章详情
function toDetail(id){
	$("#content-wrapper").load("/article/get?id="+id)
}
</script>
</html>
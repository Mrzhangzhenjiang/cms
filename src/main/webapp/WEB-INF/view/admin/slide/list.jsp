<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="/resource/js/cms.js"></script>
<title>用户列表</title>
</head>
<body>
	<div class="container-fluid">
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">广告管理</a></li>
			<li class="breadcrumb-item active">广告</li>
		</ol>
		<div class="row">
			<div>
				<a href="/addLeave">添加</a> <a href="javascript:void(0)"
					class="btn btn-danger" onclick="deleteObject()">批量删除</a> <a
					href="javascript:void(0)" class="btn btn-danger"
					onclick="addIndexSlides()">设置首页广口</a>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>序号</th>
						<th>广告图片</th>
						<th>广告标题</th>
						<th>广口链接</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sl" items="${slidelist}">
						<tr>
							<td><input type="checkbox" class="chbx" value="${sl.id}"/></td>
							<td>${sl.id}</td>
							<td><img width="100px" height="100px"
								src="/pic/${sl.picture}" alt="没有图" /></td>
							<td>${sl.title}</td>
							<td>${sl.url}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>${pageInfo}</div>
		</div>
		<!-- /.container-fluid -->
		<!-- Sticky Footer -->
		<footer class="sticky-footer">
			<div class="container my-auto">
				<div class="copyright text-center my-auto">
					<span>Copyright Â© Your Website 2019</span>
				</div>
			</div>
		</footer>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
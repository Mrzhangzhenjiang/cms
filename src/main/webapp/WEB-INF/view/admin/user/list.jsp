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
		<div class="form-inline">
			<label for="username">用户名:</label> <input id="username" type="text"
				name="username" value="${username}" class="form-control" placeholder="请输入姓名查询用户">
			<button type="button" class="btn btn-info" onclick="query()">查询</button>
		</div>
	
		<table class="table table-sm  table-hover table-bordered">
			<tr align="center" valign="middle">
				<td>序号</td>
				<td>姓名</td>
				<td>当前状态</td>
				<td>昵称</td>
				<td>性别</td>
				<td>出生日期</td>
				<td>注册日期</td>
				<td>更新日期</td>
			</tr>
			<c:forEach items="${list}" var="u" varStatus="index">
				<tr align="center" valign="middle">
					<td>${index.index+1 }</td>
					<td>${u.username}</td>
					<td><button type="button" class="btn btn-success" onclick="moption('${u.id}',this)">${u.locked=='1'?'点击禁用':'恢复正常'}</button></td>
					<td>${u.nickname}</td>
					<td>${u.gender==0?"女":"男"}</td>
					<td>${u.birthday}</td>
					<td>${u.create_time}</td>
					<td>${u.update_time}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
		  ${pageInfo}
		</div>
	</div>
</body>
<script type="text/javascript">
function query(){
	//在中间区域显示地址的内容
   $('#content-wrapper').load("/user/list?username="+$("[name='username']").val());
}
function moption(userid,obj){
	
	$.ajax({
		type:"post",
		data:{id:userid,locked:$(obj).text()=="恢复正常"?1:0},
		url:"/user/update",
		success:function(flag){
		  	if(flag){
			 $(obj).text($(obj).text()=="恢复正常"?"点击禁用":"恢复正常");
		  	}
		}
	}) 
}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>修改专题界面</title>
</head>
<form id="form">
	<div class="container-fluid">
		<input type="hidden" value="${subject.id}" name="id">
		<table class="table table-sm  table-hover table-bordered ">
			<tr>
				<td>专题名称：<input type="text" name="name" value="${subject.name}" id="name" onblur="checkName(this)">
					<span id="maid" style="color: red"></span></td>
			</tr>
			<tr>
				<td>专题摘要：<textarea rows="3" cols="50" name="sabstract">${subject.sabstract}</textarea></td>
			</tr>
			<tr>
				<td><button onclick="updateSubject()">修改专题</button></td>
			</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
	function updateSubject(){
		if(flag!=1){
			alert("请完成验证后添加文章！");
			return ;
		}
		$.ajax({
			url:"/subject/updateSubject",
			type:"post",
			data:$("#form").serialize(),
			success:function(result){
				if(result){
					alert("修改专题成功！");
				}else{
					alert("修改专题失败！");
				}
			}
		})
	}
	var flag=0;
	function checkName(obj){
		$.post("/user/validateNickname",{src:$(obj).val()},function(result){
			if(result){
				flag=1;
				$("#maid").text("");
			}else{
				flag=0;
				$("#maid").text("专题名称不能为空")
			}
		})
	}
</script>
</html>
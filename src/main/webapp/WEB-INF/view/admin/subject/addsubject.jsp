<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>添加专题页面</title>
</head>
<body>
<form id="form">
	<div class="container-fluid">
		<table class="table table-sm  table-hover table-bordered ">
			<tr>
				<td>专题名称：<input type="text" name="name" onblur="checkName(this)">
					<span id="maid" style="color: red"></span></td></td>
			</tr>
			<tr>
				<td>专题摘要：<textarea rows="5" cols="10" name="sabstract"></textarea></td>
			</tr>
			<tr>
				<td><button onclick="addSubject()">创建专题</button></td>
			</tr>
		</table>
	</div>
</form>
</body>
<script type="text/javascript">
	var flag=0;
	function addSubject(){
		if(flag!=1){
			alert("请完成验证后添加文章！");
		}else{
			$.ajax({
				url:"/subject/addSubject",
				type:"post",
				data:$("#form").serialize(),
				success:function(result){
					if(result){
						alert("创建专题成功！");
					}else{
						alert("创建专题失败！");
					}
				}
			})
		}
	}
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
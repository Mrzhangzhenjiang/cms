<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<title>添加专题文章界面</title>
</head>
<body>
	<div class="container-fluid">
		<div>
			<button class="btn btn-info" type="button" onclick="add()">添加文章</button>
		</div>
		<div>
			<span><font color="red">专题名称：${subject.name}</font></span><br/>
			<span><font color="red">专题摘要：${subject.sabstract}</font></span><br/>
			<span><font color="red">专题文章：</font></span><br/>
		</div>
		<div style="float: right">
		<form class="form-inline" >
			<div class="form-group">
				<label for="channel">文章栏目</label> <select
					class="form-control" id="channel"
					name="channelId">
					<option></option>
				</select> <label for="category">文章分类</label> <select
					class="form-control" id="category"
					name="categoryId">
				</select>
				<button class="btn btn-info"  type="button" onclick="query()">查询</button>
			</div>
			</form>
		</div>
		<table class="table table-sm  table-hover table-bordered ">
			<thead class="thead-light">
				<tr align="center">
					<td></td>
					<td>序号</td>
					<td>文章编号</td>
					<td>作者</td>
					<td>标题</td>
					<td>发布时间</td>
					<td>栏目</td>
					<td>分类</td>
					<td>操作</td>
				</tr>
			</thead>
			<c:forEach items="${list }" var="a" varStatus="index">
				<tr align="center">
					<td><input type="checkbox" class="ids" value="${a.id }">
					</td>
					<td>${index.index+1 }</td>
					<td>${a.id}</td>
					<td>${a.username}</td>
					<td>${a.title}</td>
					<td>${a.created}</td>
					<td>${a.name}</td>
					<td>${a.catname}</td>
					<td><button type="button" class="btn btn-info"
							onclick="toDetail(${a.id})">详情</button></td>
				</tr>
			</c:forEach>
			<tr>	
				<td colspan="11">
					<input type="text" id="aid" placeholder="输入文章编号单个添加" onblur="checkAid(this)">
					<span id="maid" style="color: red"></span>
					<input type="button" value="添加文章" class="btn btn-info" type="button" onclick="addArticle()">
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
function toDetail(id){
	$("#content-wrapper").load("/article/get?id="+id)
}
function query(){
	var cid=$("#channel").val();
	var catid=$("#category").val();
	$("#content-wrapper").load("/subject/toAddArticle?cid="+cid+"&catid="+catid)
}
//添加文章
function add(){
	var ids = new Array();
	 $(".ids:checked").each(function(i){
		 ids[i] =$(this).val();
	 })
	 if(ids.length==0){
		 alert("请至少选中一个文章")
		 return ;
	 }
	 //向专题中添加文章,需要传递两个参数,  ids:文章ID 的数组    .sid:专题的主键
	 $.post("/subject/add",{ids:ids,sid:'${sid}'},function(obj){
		 if(obj){
			 alert("添加成功!")
		 }else{
			 alert("添加失败!")
		 }
	 })

}
//如果文章已经属于某个专题,则选中
$(function(){
	$.get("/subject/selectArtilceListBySid",{sid:'${sid}'},function(list){
		for(var i in list){
			$(".ids").each(function(){
					if($(this).val()==list[i].id)
					$(this).prop("checked",true);
			})
		}
	})
})
$(function(){
	//自动加载文章的栏目
	$.ajax({
		type:"get",
		url:"/channel/listChannle",
		success:function(list){
			$("#channel").empty();
			for(var i in list){
				$("#channel").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
			}
			$("#channel").val('${cid}');
		}
	})
	//为栏目添加绑定事件
	$("#channel").change(function(){
		//先清空原有的栏目下的分类
		$("#category").empty();
		var cid =$(this).val();//获取当前的下拉框的id
		//根据ID 获取栏目下的分类
	 $.get("/channel/listCategory",{cid:cid},function(list){
		 for(var i in list){
		  $("#category").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
		 }
	   })
	})
})
var flag=0;
function checkAid(obj){
	$.post("/user/validateNickname",{src:$(obj).val()},function(result){
		if(result){
			flag=1;
			$("#maid").text("");
		}else{
			flag=0;
			$("#maid").text("文章编号不能为空")
		}
	})
}
function addArticle(){
	if(flag!=1){
		alert("请完成验证后添加文章！");
		return ;
	}
	$.post("/subject/addArticle",{aid:$("#aid").val(),sid:'${sid}'},function(result){
		if(result){
			alert("添加文章成功！");
		}else{
			alert("添加文章失败！可能不存在该文章！");
		}
	})
}
</script>
</html>
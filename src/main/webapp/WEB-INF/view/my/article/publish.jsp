<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>文章发布</title>
<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : '/resource/kindeditor/plugins/code/prettify.css',
				uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		function query(){
		alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		}
	</script>
</head>
<body>
	<form action="" id="form">
		<div class="form-group row ">
			<label for="title">文章标题</label> <input type="text"
				class="form-control" id="title" name="title" placeholder="请输入标题">
		</div>

		<div class="form-group row ">
			<textarea name="content1" cols="100" rows="8"
				style="width: 860px; height: 250px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
			<br />
		</div>
		<div class="form-group row ">
			<label for="title">文章标题图片</label> <input type="file"
				class="form-control" id="file" name="file">
		</div>
		<div class="form-group row ">
		  	<label for="channel">文章栏目</label> 
			<select class="custom-select custom-select-sm mb-3" id="channel" name="channelId">
			</select>
			<label for="category">文章分类</label> 
			<select class="custom-select custom-select-sm mb-3" id="category" name="categoryId">
			</select>
		</div>
		 <!-- 专题分类 -->
		<div>
		   专题:<br>
		  <c:forEach  items="${subList}" var="sub">
		   <input type="checkbox" name="sid" value="${sub.id }"> ${sub.name }
		  </c:forEach>
		</div>
		<div>
		   关键词&nbsp;&nbsp;:&nbsp;
		   <input type="text" name="keywords" placeholder="请输入关键字"><br/>
		文章来源: <input type="text" name="original" placeholder="请输入文章来源">
		</div>
		<div class="form-group row" >
		<button type="button" class="btn btn-success" onclick="publish()">发布</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	function publish() {
		//序列化表单数据带文件
		var formData = new FormData($("#form")[0]);
		//改变formData的值
		//editor1.html() 是富文本的内容
		formData.set("content", editor1.html());

		$.ajax({
			type : "post",
			data : formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url : "/article/publish",
			success : function(obj) {
				if (obj) {
					alert("发布成功!")
				} else {
					alert("发布失败")
				}
			}
		})
	}
	$(function() {
		$.ajax({
			type : "post",
			url : "/channel/listChannle",
			success : function(list) {
				for ( var i in list) {
					$("#channel").append(
							"<option value='"+list[i].id+"'>" + list[i].name
									+ "</option>")
				}
			}
		})
		$("#channel").change(
				function() {
					$("#category").empty();
					$.ajax({
						url : "/channel/listCategory",
						type : "post",
						data : {
							"cid" : $(this).val()
						},
						success : function(list) {
							for ( var i in list) {
								$("#category").append(
										"<option value='"+list[i].id+"'>"
												+ list[i].name + "</option>")
							}
						}
					})
				})
			
		})
</script>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
</html>
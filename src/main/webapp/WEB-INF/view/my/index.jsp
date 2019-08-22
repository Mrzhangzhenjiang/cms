<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>我的个人空间</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<br />
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12 my_banner"></div>
		</div>
	</div>
	<br />
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/view/my/left.jsp"></jsp:include>
				<br />
			</div>
			<div class="col-md-9">
				<div id="center">
				  <!-- 内容区域 -->
				  <div id="kindEditor" style="display: none">
				   <!-- 引入kindEditor的样式 -->
				  <jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
              </div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/common/footer.jsp" />

	<script type="text/javascript">
	//为左侧频道绑定点击事件
	 $(function(){
		 $('ul li').click(function () {
	    	   //获取当前默认高亮的属性
	         var li = $('ul li.list-group-item-warning');
	    	   //移除目前高亮的样式
	            li.removeClass('list-group-item-warning');
	    	   //为当前点击行添加高亮的样式
	            $(this).addClass('list-group-item-warning');
	    	   
	        });  
	       <!--当点击左侧菜单时-->
	        $('.channel').click(function (e) {
	        	  //获取点击的的url
	            var url = $(this).attr('data');
	           // console.log(url);
	           //在中间区域显示地址的内容
	            $('#center').load(url);
	        });
	 })	
	</script>
</body>
</html>
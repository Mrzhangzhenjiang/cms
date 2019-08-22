<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎注册</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.form-inline label {
	display: inline-block;
}

</style>
<script type="text/javascript">
function validator(){
	var username =$.trim($("[name='username']").val());
	var password =$.trim($("[name='password']").val());
	var repassword =$.trim($("[name='repassword']").val());
	if(username =="" ||password ==""){
		$("#umsg").text("用户或密码不能为空");
		return;
	}
	//校验用户是否唯一
	$.post("/user/checkUser",{username:username},function(obj){
		if(obj){
			$("#umsg").text("该用户名已经存在");
			return ;
		}else{
			$("#umsg").text("");
			$.post("/user/reg",$("form").serialize(),function(obj){
				if(obj){
					alert("注册成功")
					location.href="/user/toLogin";
				}else{
					alert("111");
				}
			})
		}
	})
	if(password!=repassword){
		$("#pmsg").text("两次密码输入不一致");
		return;	
	}
}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<!-- 登录注册页面 -->
	<div style="height: 50px;"></div>

	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
				<div class="passport_panel">
					<div class="card">
						<div class="card-header">欢迎注册</div>
						<div class="card-body">
							<form action="/reg" method="post">
							<p class="w-100" align="center" style="color: red"> ${msg }</p>
								<div class="form-group">
									<label for="username">用户名:</label> <input type="text"
										class="form-control"  name ="username"id="username" placeholder="请输入用户名" >
								  <span id="umsg" style="color: red"></span>
								</div>
								<div class="form-group">
									<label for="password">密码:</label> <input type="password"
										class="form-control" name="password" path="password" id="password" placeholder="请输入密码">
								<span id="errormsg" style="color: red"></span>
								</div>
								<div class="form-group">
									<label for="repassword">确认密码:</label> <input type="password"
										class="form-control" name="repassword" id="repassword" placeholder="再次输入密码">
								 <span id="pmsg" style="color: red"></span>
								</div>
								<div class="form-group">
									<label for="gender">性别:</label> <input class="radio" type="radio"
										class="form-control" name="gender" id="gender" value="1" checked="checked">男 <input
										type="radio"  class="radio" name="gender" class="form-control" id="gender" value="2">女
								</div>

								<div class="form-group">
									<button type="button" onclick="validator()"  class="btn btn-info">注册</button>
								<p class="w-100" align="right">
									如果已有帐号，请<a href="/user/toLogin">点这里登录</a>
								</p>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
	<script type="text/javascript">
	</script>
</body>
</html>
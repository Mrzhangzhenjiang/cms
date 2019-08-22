<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>欢迎回来</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css"/>
 	<link rel="stylesheet" type="text/css" href="/resource/css/cms.css"/>
 
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	
    	
    </style>
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
					  <div class="card-header">欢迎回来</div>
					  <div class="card-body">
					    <form action="/login" method="post">
							<p  align="center" style="color: red"> ${msg } ${error }</p>
								<div class="form-group">
									<label for="username">用户名:</label> <input type="text"
										class="form-control"  name ="username"id="username" placeholder="请输入用户名">
								 <span id="umsg" style="color: red"></span>
								
								</div>
								<div class="form-group">
									<label for="password">密码:</label> <input type="password"
										class="form-control" name="password" id="password" placeholder="请输入密码">
								</div>
								
								<div class="form-group">
									验证码：
								</div>
								
								<div class="form-group row">
									<div class="col-sm-6">
										<input type="text"
										class="form-control" name="code" id="code" placeholder="请输入验证码" maxlength="4" style="width: 150px; height: 35px">
									</div>
									<div class="col-sm-6" style="padding-left: 0px;">
										<img alt="出错啦"  src="/getcode" width="80px" height="35px" onclick="changeCode(this)">
									</div>
								</div>
								
								<div class="form-group">
									<button type="button"   class="btn btn-info" onclick="login()">登录</button>
								<p class="w-100" align="right">
									如果没有有帐号，请<a href="/user/toReg">点这里注册</a>
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
					<img src="/resource/images/guest.jpg" alt="..." class="rounded-circle img-thumbnail"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="/resource/images/guest1.jpg" alt="..." class="rounded-circle img-thumbnail"/>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div><br/><br/></div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	
	<script type="text/javascript">
		//更换验证码
		function changeCode(obj){
			$(obj).attr("src","/getcode?time="+new Date().getTime());
		}
		function login(){
			var username =$.trim($("[name='username']").val());
			var password =$.trim($("[name='password']").val());
			if(username =="" ||password ==""){
				$("#umsg").text("用户或密码不能为空");
				return;
			}
			$.post("/user/login",{username:username,password:password,code:$("#code").val()},function(data){
				if(data.code==10001){
					alert("登陆成功！");
					if(username=="admin"){
						location.href="/admin/";//进入个人页面
					}else{
						location.href="/my/";//进入个人页面
					}
				}else{
					alert(data.msg);
					window.history.go(0);
				}
			})
			
		}
	</script>
  </body>
</html>
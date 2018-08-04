<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎使用氢博客,请先登录~! |</title>

<!-- Bootstrap -->

<link href="vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- Animate.css -->
<link href="vendors/animate.css/animate.min.css" rel="stylesheet">
<!-- Custom Theme Style -->
<link href="build/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a> <a class="hiddenanchor"
			id="signin"></a>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form>
						<h1>
							H<span style="font-size: 16px">2</span>Blog后台登录
						</h1>
						<div>
							<span id="errorMessage" class="red">&nbsp;</span>
						</div>
						<div>
							<input id="userName" name="userName" type="text"
								class="form-control" placeholder="用户名" required="" />
						</div>
						<div>
							<input id="password" name="password" type="password"
								class="form-control" placeholder="密   码" required="" />
						</div>
						<div>
							<input id="loginBtn" class="btn btn-default submit" type="button"
								value="登录" /> <a class="reset_pass" href="#">忘记密码？</a>
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<p class="change_link">
								没有账号？ <a href="#signup" class="to_register"> 创建新用户</a>
							</p>
							<div class="clearfix"></div>
							<br />
						</div>
					</form>
				</section>
			</div>
			<div id="register" class="animate form registration_form">
				<section class="login_content">
					<form id="form1" method="post" name="regForm"
						enctype="multipart/from-data">
						<h1>新用户注册</h1>
						<div>
							<p id="userNameMessage" style="float: left;">&nbsp;</p>
							<input id="regUserName" type="text" name="userName"
								class="form-control" placeholder="用户名" required="" />
								
						</div>
						<div>
							<p id="emailMessage" style="float: left;"></p>
							<input id="regEmail" type="email" name="userEmail"
								class="form-control" placeholder="邮   箱" required="" />
						</div>
						<div>
							<p id="passwordMessage" style="float: left;"></p>
							<input id="regPassword" type="password" name="userPassword"
								class="form-control" placeholder="密   码" required="" />
						</div>
						<div>
							<p id="nickNameMessage" style="float: left;"></p>
							<input id="regNickName" type="text" name="userNickName"
								class="form-control" placeholder="昵   称" required="" />
						</div>
						<div style="margin-bottom: 20px">
							<p id="avatarMessage" style="float: left;"></p>
							<img height="120px" width="100px" src="" alt="请选择头像" id="avatar">
							<input id="regAvatar" class="btn btn-default submit"
								name="avatar" type="file" onchange="imgPreview(this)"
								title="选择头像" style="display:none;" /> <input
								class="btn btn-default submit" type="button" value="上传头像"
								onclick="document.regForm.avatar.click()">
						</div> 
						<div>
							<input id="registerBtn" type="button"
								class="btn btn-default submit" value="注册">
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<p class="change_link">
								已经拥有账号 ? <a href="#signin" class="to_register"> 前往登录 </a>
							</p>
							<div class="clearfix"></div>
							<br />
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
	<script src="statics/js/jquery-3.3.1.js"></script>
	<script src="statics/js/login.js"></script>
</body>

</html>
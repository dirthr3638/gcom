<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GuardCom</title>
		
		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
		<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
	
		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/darkblue.css" rel="stylesheet" type="text/css" id="color_scheme" />
		
		<style type="text/css">
			.blur-bg {
				position:relative;
				display: flex;
				align-items: center;
				flex-direction: column;
				justify-content: center;
				width: 100%;
				height: 100%;
				padding: 20px;
				/*background: url(assets/images/guard/guard-bg.jpg) no-repeat center center fixed;
				background-size: cover;*/ 
				background-color:#35373d;
			}

			.login-bg-color {
				background-color: rgb(242, 242, 242);
			}
		
			.radio_login {
				padding:0px 5px;
				display:inline-block;
			}

		</style>
	</head>
	<body class="smoothscroll enable-animation ">
		<!-- wrapper -->
		<div id="wrapper" class="blur-bg">
			<!--
			<section class="page-header page-header-xs">
				<div class="container">
				</div>
			</section> -->
		
			<!-- login section -->
			<section style="border:0;">
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-md-offset-3" >
							<div class="box-static box-border-top padding-30 text-center login-bg-color">
								<div class="box-title margin-bottom-30">
									<div style="width:300px; margin:0 auto;"><img src="/assets/images/guardcom_login_logo.png" width="100%"></div>
								</div>
		
								<form class="nomargin" method="post" action="#" autocomplete="off">
									<div class="clearfix">
										<div class="form-group">
											<div class="radio_login"><label><input type="radio" name="loginType" value="U" checked/>User</label></div>
											<div class="radio_login"><label><input type="radio" name="loginType" value="C" />Console</label></div>
											<div class="radio_login"><label><input type="radio" name="loginType" value="R" />Report</label></div>
										</div>
										<!-- ID -->
										<div class="form-group">
											<input type="text" name="email" class="form-control" placeholder="ID" required="">
										</div>
										
										<!-- Password -->
										<div class="form-group">
											<input type="password" name="password" class="form-control" placeholder="Password" required="">
										</div>
									</div>
									<div class="row">
										<div class="col-md-12 col-sm-12 col-xs-12 text-center">
											<button class="btn btn-primary" style="width:100%;">로그인</button>
										</div>
									</div>
		
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-6">
											<!-- Inform Tip 
											<div class="form-tip pt-20 text-left">
												<a class="no-text-decoration size-13" href="#">비밀번호 찾기</a>
												<a class="no-text-decoration size-13 margin-left-10" href="#">회원가입</a>
											</div>
											-->                                        
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<!--<div class="row">
						<div class="margin-top-30 text-center">
							<a href="page-register-1.html"><strong>Create Account</strong></a>
						</div>
					</div>-->
				</div>
			</section>
			<!-- /login section -->
		
		</div>
		<!-- /wrapper -->
		
		<!-- PRELOADER -->
		<div id="preloader">
			<div class="inner">
				<span class="loader"></span>
			</div>
		</div>
		<!-- /PRELOADER -->
		
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>

	</body>
</html>
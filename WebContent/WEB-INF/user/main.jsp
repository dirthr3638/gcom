<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom User</title>
	
		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
	
		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/user_essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/user_layout.css" rel="stylesheet" type="text/css" />
		
		<!-- PAGE LEVEL STYLE -->
		<link href="/assets/css/user_header.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/user_green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		
	</head>
	<body class="smoothscroll enable-animation">
		<jsp:include page="/WEB-INF/common/user_header.jsp" flush="false" />
		
		<!-- HOME -->
		<!-- SubHeader -->
		<section class="callout-dark heading-title heading-arrow-bottom" style="padding:60px 0;">
			<div class="container">
				<div class="container text-center">
					<h1 class="weight-300 size-40">GUARD COM USER</h1>
					<h2 class="weight-300 letter-spacing-1 size-13"><span>Welcome to Guard Com Customer Center</span></h2>
					<div class="margin-top-20">
						<a href="#" class="btn btn-3d btn-lg btn-teal"><i class="fa fa-download"></i>DOWNLOAD</a>
					</div>
				</div>

			</div>
		</section>
		<!-- /SubHeader -->

		<!-- User Policy -->
		<section style="padding:40px 0;">
			<div class="container">
				<h4><i class="fa fa-get-pocket"></i>사용자 정책</h4>
				<form action="#" method="post">
					<div class="sky-form">
						<div id="member_policy_div"></div>
					</div>
					<!--
					<div class="margin-top-10">
						<a href="#" class="btn btn-primary"><i class="fa fa-check"></i> 정책요청 </a>
						<a href="#" class="btn btn-default">취소</a>
					</div>
					-->
				</form>
			</div>
		</section>
		<!-- /User Policy -->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
		
		<script type="text/javascript">
			$(document).ready(function(){
				fn_member_policy_info();
			});
			
			function fn_member_policy_info() {
				
				$.ajax({      
			        type:"POST",  
			        url:'/ax/main/policy',
			        async: false,
			        data:{
			        	_ : $.now()
			        },
			        success:function(args){
			            $("#member_policy_div").html(args);
			        },   
			        //beforeSend:showRequest,  
			        error:function(e){  
			            console.log(e.responseText);  
			        }  
			    });
			}
			
		</script>
		
	</body>
</html>
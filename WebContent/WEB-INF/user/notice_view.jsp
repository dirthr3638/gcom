<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.user.model.UserNoticeModel"%>
<% 
	UserNoticeModel data = (UserNoticeModel)request.getAttribute("UserNoticeDetail");
%>
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
		
		<!-- Notice View -->
		<!-- -->
			<section style="padding:50px 0;">
				<div class="container">
					<h1 class="blog-post-title"><%= data.getBbsTitle() %></h1>
					<ul class="blog-post-info list-inline">
						<li>
							<a href="#">
								<i class="fa fa-clock-o"></i> 
								<span class="font-lato">작성일 : <%= data.getBbsRegDate() %></span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-eye" aria-hidden="true"></i> 
								<span class="font-lato">조회 : <%= data.getBbsClickCnt() %></span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-user"></i> 
								<span class="font-lato"><%= data.getBbsRegStaf() %></span>
							</a>
						</li>
					</ul>
					<!-- article content -->
					<div class="row" style="border:1px solid #f1f1f1; min-height:700px; padding:10px 20px;">
						<%= data.getBbsBody() %>
					</div>
					<div class="row">
						<a href="/notice" class="btn btn-primary pull-right"><i class="fa fa-list"></i>목록</a>
					</div>
					<!-- /article content -->
				</div>
				
			</section>
			<!-- / -->
		<!-- /Notice View -->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
	</body>
</html>
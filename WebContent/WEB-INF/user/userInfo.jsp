<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="gcom.user.model.UserInfoModel"%>
<% 
	UserInfoModel data = (UserInfoModel)request.getAttribute("userInfo");
	String name = data.getName();
	String phone = data.getPhone();
	String deptName = data.getDeptName();
	String duty = data.getDuty();
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
		
		<section>
			<div class="container">
				<!-- RIGHT -->
				<div class="col-lg-9 col-md-9 col-sm-8 col-lg-push-3 col-md-push-3 col-sm-push-4 margin-bottom-80">
					<!-- PERSONAL INFO TAB -->
					<div class="tab-pane fade in active" id="info">
						<form role="form" action="#" method="post">
							<div class="form-group">
								<label class="control-label">이름</label>
								<input type="text" class="form-control" value="<%= name %>" />
							</div>
							<div class="form-group">
								<label class="control-label">핸드폰</label>
								<input type="text" class="form-control" placeholder="010-1111-1111" value="<%= phone %>" />
							</div>
							<div class="form-group">
								<label class="control-label">변경 할 비밀번호</label>
								<input type="password" class="form-control" />
							</div>
							<div class="form-group">
								<label class="control-label">변경 할 비밀번호 확인</label>
								<input type="password" class="form-control" />
							</div>
							
							<div class="form-group">
								<label class="control-label">소속</label>
								<input type="text" class="form-control" value="<%= deptName %>" />
							</div>
							<div class="form-group">
								<label class="control-label">직책</label>
								<input type="text" class="form-control" value="<%= duty %>" />
							</div>
							<div class="form-group">
								<label class="control-label">비고</label>
								<textarea class="form-control" rows="3" placeholder="About Me..."></textarea>
							</div>
							<div class="margiv-top10">
								<a href="#" class="btn btn-primary"><i class="fa fa-check"></i> 정보 수정 </a>
								<a href="#" class="btn btn-default">취소</a>
							</div>
						</form>
					</div>
				</div>


				<!-- LEFT -->
				<div class="col-lg-3 col-md-3 col-sm-4 col-lg-pull-9 col-md-pull-9 col-sm-pull-8">
				
					<div class="thumbnail text-center">
						<img src="/assets/images/460x427.png" alt="" />
						<h2 class="size-18 margin-top-10 margin-bottom-0"><%= name %></h2>
						<h3 class="size-11 margin-top-0 margin-bottom-10 text-muted">DEVELOPER</h3>
					</div>

					<!-- completed -->
					<div class="margin-bottom-30">
						<label>88% completed profile</label>
						<div class="progress progress-xxs">
							<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="88" aria-valuemin="0" aria-valuemax="100" style="width: 88%; min-width: 2em;"></div>
						</div>
					</div>
					<!-- /completed -->

					<!-- SIDE NAV -->
					<ul class="side-nav list-group margin-bottom-60" id="sidebar-nav">
						<li class="list-group-item"><a href="page-profile.html"><i class="fa fa-eye"></i> PROFILE</a></li>
						<li class="list-group-item"><a href="page-profile-projects.html"><i class="fa fa-tasks"></i> PROJECTS</a></li>
						<li class="list-group-item"><a href="page-profile-comments.html"><i class="fa fa-comments-o"></i> COMMENTS</a></li>
						<li class="list-group-item"><a href="page-profile-history.html"><i class="fa fa-history"></i> HISTORY</a></li>
						<li class="list-group-item active"><a href="page-profile-settings.html"><i class="fa fa-gears"></i> SETTINGS</a></li>
					</ul>
					<!-- /SIDE NAV -->

				</div>
			</div>
		</section>
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
	</body>
</html>
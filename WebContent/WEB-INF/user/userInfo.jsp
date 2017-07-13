<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="gcom.user.model.UserInfoModel"%>
<% 
	UserInfoModel data = (UserInfoModel)request.getAttribute("userInfo");
	int userNo = data.getUserNo();
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
		
		<!-- Alert -->
		<link href="/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
		<link href="/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />
		
		<script type="text/javascript" src="/assets/plugins/vex/js/vex.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/vex/js/vex.combined.min.js"></script>

	</head>
	<body class="smoothscroll enable-animation">
		<jsp:include page="/WEB-INF/common/user_header.jsp" flush="false" />
		
		<section>
			<div class="container">
				<!-- RIGHT -->
				<div class="col-lg-9 col-md-9 col-sm-8 col-lg-push-3 col-md-push-3 col-sm-push-4 margin-bottom-80">
					<!-- PERSONAL INFO TAB -->
					<div class="tab-pane fade in active" id="info">
						<form id="frmUserInfo" role="form" action="/user/info/save" method="post">
							<input type="hidden" value="<%= userNo %>" name="user_no" id="user_no" />
							<div class="form-group">
								<label class="control-label">이름</label>
								<input type="text" class="form-control" name="mem_name" id="mem_name" value="<%= name %>" disabled />
							</div>
							<div class="form-group">
								<label class="control-label">핸드폰</label>
								<input type="text" class="form-control" name="mem_phone" id="mem_phone" placeholder="010-1111-1111" value="<%= phone %>" disabled/>
							</div>
							<div class="form-group">
								<label class="control-label">변경 할 비밀번호</label>
								<input type="password" name="change_password_input" id="change_password_input" class="form-control" maxlength="20" />
							</div>
							<div class="form-group">
								<label class="control-label">변경 할 비밀번호 확인</label>
								<input type="password" name="change_password_check" id="change_password_check" class="form-control" maxlength="20" />
							</div>
							
							<div class="form-group">
								<label class="control-label">소속</label>
								<input type="text" class="form-control" name="dept_name" id="dept_name" value="<%= deptName %>" disabled />
							</div>
							<div class="form-group">
								<label class="control-label">직책</label>
								<input type="text" class="form-control" name="mem_duty" id="mem_duty" value="<%= duty %>" disabled />
							</div>
							<div class="form-group">
								<label class="control-label">비고</label>
								<textarea class="form-control" rows="3" name="mem_notice" id="mem_notice" disabled ></textarea>
							</div>
							<div class="margiv-top10">
								<button id="btnUserInfoSave" class="btn btn-primary"><i class="fa fa-check"></i> 정보 수정 </button>
								<a href="/main" class="btn btn-default">취소</a>
							</div>
						</form>
					</div>
				</div>


				<!-- LEFT -->
				<div class="col-lg-3 col-md-3 col-sm-4 col-lg-pull-9 col-md-pull-9 col-sm-pull-8">
				
					<div class="thumbnail text-center">
						<img src="/assets/images/460x427.png" alt="" width="460px" height="427px"/>
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
						<!-- <li class="list-group-item"><a href="page-profile.html"><i class="fa fa-eye"></i> PROFILE</a></li>
						<li class="list-group-item"><a href="page-profile-projects.html"><i class="fa fa-tasks"></i> PROJECTS</a></li>
						<li class="list-group-item"><a href="page-profile-comments.html"><i class="fa fa-comments-o"></i> COMMENTS</a></li>
						<li class="list-group-item"><a href="page-profile-history.html"><i class="fa fa-history"></i> HISTORY</a></li> -->
						<li class="list-group-item active"><a href="page-profile-settings.html"><i class="fa fa-gears"></i> SETTINGS</a></li>
					</ul>
					<!-- /SIDE NAV -->

				</div>
			</div>
		</section>
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
		
		<script type="text/javascript" src="/assets/plugins/jquery/jquery.form.js" ></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$("#btnUserInfoSave").on("click" , function(e){
					e.preventDefault();
					fn_info_save_proc();
				});
			});
			
			function fn_info_save_proc() {
				var pw = $('#change_password_input').val();
				var pwChk = $('#change_password_check').val();
				
				if(pw != pwChk){
					vex.defaultOptions.className = 'vex-theme-os'
		    			
	    			vex.dialog.open({
	    				message: '비밀번호가 일치 하지 않습니다. 확인해주세요.',
	    				  buttons: [
	    				    $.extend({}, vex.dialog.buttons.YES, {
	    				      text: '확인'
	    				  })]
	    			})
					return false;
				}
				
				var option = {
				        url:       		"/user/info/save",
				    	type:      		"post",       
				    	success:     	fn_save_callback,
				    	fail:			callbackFail,
				    	cache: 			false,
				        resetForm: 		false 
				};
				
				$("#frmUserInfo").ajaxSubmit(option);
			}
			
			function callbackFail(){
				vex.defaultOptions.className = 'vex-theme-os'
	    			
    			vex.dialog.open({
    				message: '서버와의 통신에 실패하였습니다.',
    				  buttons: [
    				    $.extend({}, vex.dialog.buttons.YES, {
    				      text: '확인'
    				  })]
    			})
			}

			function fn_save_callback(data){
				vex.defaultOptions.className = 'vex-theme-os'
				
				if(data.returnCode == "S"){
					vex.dialog.open({
	    				message: '비밀번호가 변경 되었습니다.',
	    				  buttons: [
	    				    $.extend({}, vex.dialog.buttons.YES, {
	    				      text: '확인'
	    				  })],
	    				  callback: function(data) {
    				 	  	if (data) {
    				 	  		location.href="/main";
    				 	    }
    				 	  }
	    			})
	    			
				}else{
					switch (data.returnCode){
					  case "E":
						vex.dialog.open({
		    				message: data.message,
		    				  buttons: [
		    				    $.extend({}, vex.dialog.buttons.YES, {
		    				      text: '확인'
		    				  })]
		    			})
					    break;
					  case "EFV":
						  vex.dialog.open({
			    				message: '비밀번호가 일치 하지 않습니다. 확인해주세요.',
			    				  buttons: [
			    				    $.extend({}, vex.dialog.buttons.YES, {
			    				      text: '확인'
			    				  })]
			    			})
					    break;
					  default:
						  alert("서버와의 통신에 실패하였습니다.")
						  break;
					}
				}
				
			}
		
		</script>
		
	</body>
</html>
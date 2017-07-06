<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="gcom.user.model.UserContactModel"%>
<%@ page import="gcom.Model.FileInfoModel"%>
<% 
	UserContactModel data = (UserContactModel)request.getAttribute("UserContactDetail");
%>
<!doctype html>
<html lang="utf-8">
	<head>
	
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom Console</title>

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />

		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2600); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>문의 사항 관리</h1>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						<div class="col-md-12">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>문의사항 상세보기</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12" style="padding:10px 40px;">
											<h1 class="blog-post-title"><%= data.getContactTitle() %></h1>
											<ul class="blog-post-info list-inline">
												<li>
													<i class="fa fa-clock-o"></i> 
													<span class="font-lato">작성일 : <%= data.getRegDt() %></span>
												</li>
												<li>
													<span class="font-lato">답변여부 : <% if( "Y".equals(data.getCommentYN())) {%> <i class="fa fa-pencil"></i> 답변됨. <% } else { %> 답변없음. <% } %> </span>
												</li>
												<li>
													<i class="fa fa-user"></i> 
													<span class="font-lato">문의등록자 : <%= data.getRegUserName() %></span>
												</li>
											</ul>
												
											<!-- article content -->
											<div class="row" style="border:1px solid #f1f1f1; min-height:700px; padding:10px 20px;">
												<%= data.getContactBody() %>
											</div>
											<div class="row">
												<a href="/admin/user/contact" class="btn btn-primary pull-right"><i class="fa fa-list"></i>목록</a>
												<button id="btnRegComment" class="btn btn-green pull-right" style="color:#fff; font-weight:bold;" onClick="javascript:fn_open_comment('<%= data.getContactId() %>');"><i class="fa fa-check"></i>답변등록</button>
											</div>
											<!-- /article content -->
										</div>
									</div>
									
									
									<div class="ld_modal hidden" >
									    <div class="ld_center" >
									        <img alt="" src="/assets/images/loaders/loading.gif" />
									    </div>
									</div>
									
								</div>
								<!-- /panel content -->
							</div>
						</div>
					</div>
				</div>
			</section>
			
			<div id="comment_write_popup"></div>
		</div>
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>
		
		<script type="text/javascript">
		$(document).ready(function(){
			jQuery('#preloader').hide();
		});
		
		function fn_open_comment(conId) {
	 	
	 		$.ajax({      
			    type:"POST",  
			    url:'/admin/user/contact/write',
			    async: false,
			    data:{
			    	contact_id : conId,
			    	_ : $.now() 
			    },
			    success:function(data){
			    	$("#comment_write_popup").html(data);
		            $('#modalCommentWrite').modal('show');
			    },   
			    error:function(e){  
			        console.log(e.responseText);  
			    }  
			});
	 	}
		
		</script>
	</body>
</html>
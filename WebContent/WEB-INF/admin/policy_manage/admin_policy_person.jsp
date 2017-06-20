<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom Report</title>

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />

		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="/assets/plugins/datatables/extensions/Buttons/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"  />
		<link href="/assets/plugins/datatables/extensions/Buttons/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 3000); %> 
			<% request.setAttribute("menu_sub_first", 3200); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>개인정보정책 관리</h1>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						
						<div class="col-md-12">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>개인정보정책 정보</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="col-md-12" style="overflow: hidden;">
										<div class="aside-tabs">
											<ul class="nav nav-tabs nav-top-border">
												<li class="active"><a href="#" onClick="javascript:fn_policy_person_list('msg');" data-toggle="tab">메신저 감사</a></li>
												<li><a href="#" onClick="javascript:fn_policy_person_list('process');" data-toggle="tab">프로세스 차단</a></li>
												<li><a href="#" onClick="javascript:fn_policy_person_list('pattern');" data-toggle="tab">민감정보</a></li>
												<li><a href="#" onClick="javascript:fn_policy_person_list('mail');" data-toggle="tab">메일</a></li>
											</ul>
											
											<div id="policy_person_div" class="tab-content"></div>
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
		</div>
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>

		<script type="text/javascript">
			$(document).ready(function(){
				fn_policy_person_list('msg')
			});
			
			function fn_policy_person_list(tabCode){
				
				$.ajax({      
			        type:"POST",  
			        url:'/ax/admin/policy/person',
			        async: false,
			        data:{
			        	tabCode : tabCode,
			        	_ : $.now()
			        },
			        success:function(args){
			            $("#policy_person_div").html(args);
			           	
			            if (tabCode == 'msg') {
			            	fn_get_messenger_policy_data();
			            } else if (tabCode == 'process') {
			            	fn_get_process_policy_data();
			            } else if (tabCode == 'pattern') {
			            	fn_get_pattern_policy_data();
			            } else if (tabCode == 'mail') {
			            	fn_get_mail_policy_data();
			            }
			        },   
			        error:function(e){  
			            console.log(e.responseText);  
			        }  
			    });
			}
			
		</script>
	</body>
</html>
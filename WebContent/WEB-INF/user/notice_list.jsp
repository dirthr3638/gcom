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
		
		<!-- Notice Table -->
		<!-- -->
		<section style="min-height:700px;">
			<div class="container">

				<h4>공지사항</h4>
				<div class="table-responsive">
					<div>
						<a href="javascript:fn_notice_list();" class="btn btn-primary pull-right"  style="margin-top: 0px;"><i class="fa fa-check"></i> 검색</a>
						<input type="text" class="form-control pull-right" id="att_search_text" name="att_search_text" placeholder="검색어를 입력해주세요." style="width:200px;" value="" />
						<select class="form-control pull-right" id="sel_search_type" name="sel_search_type" style="width:100px;">
							<option value="1">제목</option>
							<option value="2">등록자</option>
						</select> 
					</div>
					<div id="notice_table_div"></div>
					<div class="text-center margin-top-20">
						<ul class="pagination">
							<li class="disabled"><a href="#">Previous</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">Next</a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<!-- /Notice Table -->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
		
		<script type="text/javascript">
			$(document).ready(function(){
				fn_notice_list();
				
			});
			
			function fn_notice_list() {
				
				var search_type = $('#sel_search_type option:selected').val();
				var search_text = $('#att_search_text').val();
				
				$.ajax({      
			        type:"POST",  
			        url:'/ax/user/notice/list',
			        async: false,
			        data:{
			        	search_type : search_type,
			        	search_text : search_text,
			        	_ : $.now()
			        },
			        success:function(args){
			        	console.log(args);
			            $("#notice_table_div").html(args);
			        },   
			        //beforeSend:showRequest,  
			        error:function(e){  
			            console.log(e.responseText);  
			        }  
			    });
			}
			
			function fn_bbs_detail(bbs_id) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
			 	form.setAttribute("action", "/notice/view");
			 	document.body.appendChild(form);
			 	
				var element = document.createElement("input");
				element.setAttribute("type"	,"hidden");
				element.setAttribute("name"	,"att_bbs_id");
				element.setAttribute("value", bbs_id);
				form.appendChild(element);
				form.submit();
			}
			
		</script>
	</body>
</html>
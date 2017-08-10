<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom Console</title>

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />

		<!-- CORE CSS -->
		<link href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="${context}/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"  />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css"  />
           <link href="${context}/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
           <link href="${context}/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />

	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2500); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>사용자정보</h1>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						<div class="col-md-2">
							<div id="panel-2" class="panel panel-default">
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>조직도</strong> <!-- panel title -->
									</span>
								</div>

								<!-- panel content -->
								<div id="dept_tree" class="panel-body" style="padding-left: 0px; padding-right: 0px;">

								</div>
								<!-- /panel content -->

							</div>
							<!-- /PANEL -->
					
						</div>

						<div class="col-md-10">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>사용자리스트</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
			
											<!-- Standard button -->
											<button type="button" class="btn btn-default" onclick="jQuery('#pre-1').slideToggle();"><i class="fa fa-filter" aria-hidden="true">&nbsp;검색필터</i></button>
		
											<!-- Info -->
											<button type="button" class="btn btn-info" onclick="searchUserLog()"><i class="fa fa-repeat" aria-hidden="true">&nbsp;재검색</i></button>
<!-- 											<button type="button" class="btn btn-danger" onclick="onClickRemoveUser()"><i class="fa fa-remove" aria-hidden="true">&nbsp;삭제</i></button>
 -->											<button type="button" class="btn btn-success" onclick="onClickCreateUser()"><i class="fa fa-plus" aria-hidden="true">&nbsp;추가</i></button>
											
											<!-- Primary -->
											<button type="button" class="btn btn-primary pull-right" onclick="onClickExcelButton()">내보내기</button>
											<!-- Success -->
											<button type="button" class="btn btn-success pull-right" onclick="onClickPrintButton()"><i class="fa fa-print" aria-hidden="true">&nbsp;인쇄</i></button>
											<div id="pre-1" class="margin-top-10 margin-bottom-10 text-left noradius text-danger softhide" style="width:400px;">
												<table id="user" class="table table-bordered">
													<tbody> 
														<tr>         
															<td width="35%">아이디</td>
															<td>
																<input type="text" name="filterUserId" id="filterUserId" value="" class="form-control required">
															</td>
														</tr>
														<tr>         
															<td width="35%">이름</td>
															<td>
																<input type="text" name="filterUserName" id="filterUserName" value="" class="form-control required">
															</td>
														</tr>
														<tr>         
															<td width="35%">연락처</td>
															<td>
																<input type="text" name="filterUserPhone" id="filterUserPhone" value="" class="form-control required">
															</td>
														</tr>																															
														
													</tbody>
												</table>	
												
												<button type="button" class="btn btn-success" onclick="jQuery('#pre-1').slideToggle();">접기</button>
																					
											</div>
<!-- 										
											<button type="button" class="btn btn-warning">Warning</button>
		
											
											<button type="button" class="btn btn-danger">Danger</button> -->
										</div>
									</div>
									<div class="row">
										<div class="col-md-12" style="overflow: auto;">
											<table class="table table-striped table-bordered table-hover x-scroll-table" id="table_userinfo" style="width:100%; min-width: 600px;">
												<thead>
													<tr>
														<th style="width:20px"><input type="checkbox" id="all_check_info" name="all_check_info" /></th>
														<th>부서</th>
														<th>아이디</th>
														<th>이름</th>
														<th >직책</th>
														<th >계급</th>
														<th >연락처</th>
														<th ></th>
													</tr>
												</thead>
				
												<tbody>
												</tbody>
											</table>
										
										
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
		<div id="user_input_popup">
		</div>
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '${context}/assets/plugins/';</script>
		<script type="text/javascript" src="${context}/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${context}/assets/js/app.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="${context}/assets/js/admin_function.js"></script>
           <script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.min.js"></script>
           <script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.combined.min.js"></script>

		<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.jqueryui.min.js"></script>

		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.print.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.html5.min.js"></script>


<script>

	
	var getFilterInfo = function(){
		var param = new Object();
		param.user_id = $('#filterUserId').val();
		param.user_name = $('#filterUserName').val();
		param.user_phone = $('#filterUserPhone').val();
		param.dept = getCheckedDept();

		return param;
	};
	
 	function setTree(){
		$.ajax({      
	        type:"POST",  
	        url:'${context}/common/tree/dept',
	        async: false,
	        //data:{},
	        success:function(args){   
	            $("#dept_tree").html(args);      
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}
 	
 	function searchUserLog(){
 		var datatable = $('#table_userinfo').dataTable().api();
		datatable.ajax.reload();   	
 	
 	}

 	function onClickPrintButton(){
 		var $buttons = $('.export-print');
 		$buttons.click();
 	}
 	
 	function onClickExcelButton(){
 		var $buttons = $('.export-csv');
 		$buttons.click();
 		
 	}
 	//user_input_popup
 	function onClickModifyUser(user_no){
		$.ajax({      
	        type:"GET",  
	        url:'${context}/ax/admin/userinput/modify',
	        async: false,
	        data:{
	        	user_no : user_no,
	        	_:$.now()
	        },
	        success:function(args){   
		        console.log(args);   

	        	$("#user_input_popup").html(args);      
	            $("#modalUserInfo").modal('show');
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 

 	}
 	
	
 	
	function onClickCreateUser(){
		console.log('user create') 				
		$.ajax({      
	        type:"GET",  
	        url:'${context}/ax/admin/userinput/create',
	        async: false,
	        data:{
	        	
	        	_:$.now()
	        },
	        success:function(args){   
	            $("#user_input_popup").html(args);      
	            $("#modalUserInfo").modal('show');
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}
	

	function getInputData(){
		var data = new Object();

		data.user_no = $('#user_no').val();
		data.user_dept = $('#user_dept option:selected').val();
		data.user_name = $('#user_name').val();
		data.user_duty = $('#user_duty').val();
		data.user_rank = $('#user_rank').val();
		data.user_id = $('#user_id').val();
		data.user_phone = $('#user_phone').val();
		data.user_password = $('#user_password').val();
		data.user_password2 = $('#user_password2').val();
		data.user_number = $('#user_number').val();
		
		var result = validCheck(data);
		if(result == true){
			return data;			
		}else{
			return false;
		}
	}
	
	function validCheck(data){
		
		var result = true;
		if(parseInt(data.user_dept) < 1){
			infoAlert('부서c선택 값이 유효하지 않습니다.')
			result = false;
		}else if(data.user_name == ''){
			infoAlert('이름을 입력하여주세요.')
			result = false;
		}else if(data.user_duty == ''){
			infoAlert('직책을 입력하여주세요.')
			result = false;
		}else if(data.user_rank == ''){
			infoAlert('계급을 입력하여주세요.')
			result = false;
		}else if(data.user_id == ''){
			infoAlert('아이디를 입력하여주세요.')
			result = false;
		}else if(data.user_phone == ''){
			infoAlert('핸드폰번호를 입력하여주세요.')
			result = false;
		}else if(data.user_number == ''){
			infoAlert('사번을 입력하여주세요.')
			result = false;
		}else if(data.user_password == '' && '${popup_type}' == 'create'){
			infoAlert('패스워드를 입력하여주세요.')
			result = false;
		}else if(data.user_password2 == '' && '${popup_type}' == 'create'){
			infoAlert('확인 패스워드를 입력하여주세요.')
			result = false;
		}else if(data.user_password != data.user_password2 ){
			infoAlert('패스워드가 일치하지 않습니다.')
			result = false;
		}
		
		return result;		
	}

	function onClickRemoveUser(user_no){
		vex.dialog.open({
			message: '해당 사용자 가 삭제됩니다. 계속하시겠습니까?',
		  buttons: [
		    $.extend({}, vex.dialog.buttons.YES, {
		      text: '확인'
		    }), $.extend({}, vex.dialog.buttons.NO, {
		      text: '취소'
		    })
		  ],
	 	    callback: function(data) {
	 	      if (data) {
	 	    	 DoRemoveUser(user_no);
	 	      }
	 	    }
 		});

 	}
	
	function DoRemoveUser(user_no){
		$.ajax({      
	        type:"POST",  
	        url:'${context}/admin/user/manage/do/remove',
	        async: false,
	        data:{
	        	user_no : user_no,
	        },
	        success:function(args){ 
	        	if(args.returnCode == 'S'){
	        		reloadTablePreventPage();
	    			infoAlert('사용자 삭제가 완료되었습니다.')
	        	}else{
	    			infoAlert('서버와의 통신에 실패하였습니다.')
	        	}
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}
	
	function fn_user_modify(){
		var data = getInputData();
		
		if(data != false){
			console.log('유저수정')
			$.ajax({      
		        type:"POST",  
		        url:'${context}/admin/user/manage/do/update',
		        async: false,
		        data: data,
		        dataType: "json",
		        success:function(args){
		        	if(args.returnCode == 'S'){
		        		reloadTablePreventPage();
		    			infoAlert('수정이 완료되었습니다.')
		    			$('#modalUserInfo').modal('hide');
		        	}else if(args.returnCode == 'EUN'){
		    			infoAlert('사번이 이미 존재합니다.')
		        	}else if(args.returnCode == 'DUI'){
		    			infoAlert('아이디가 이미 존재합니다.')
		        	}else{
		    			infoAlert('서버와의 통신에 실패하였습니다.')
		        	}
		        },
		        //beforeSend:showRequest,
		        error:function(e){
		            console.log(e.responseText);
		        }  
		    }); 
		}
	}

	function fn_user_create(){	
		var data = getInputData();
		console.log(JSON.stringify(data))
		if(data != false){
			console.log('유저생성')
			$.ajax({      
		        type:"POST",  
		        url:'${context}/admin/user/manage/do/create',
		        async: false,
		        data: data,
		        dataType: "json",
		        success:function(args){ 
		        	if(args.returnCode == 'S'){
		        		reloadTablePreventPage();
		    			infoAlert('사용자추가가 완료되었습니다.')
		        	}else if(args.returnCode == 'EUN'){
		    			infoAlert('사번이 이미 존재합니다.')
		        	}else if(args.returnCode == 'DUI'){
		    			infoAlert('아이디가 이미 존재합니다.')
		        	}else{
		    			infoAlert('서버와의 통신에 실패하였습니다.')
		        	}
		        },   
		        //beforeSend:showRequest,  
		        error:function(e){  
		            console.log(e.responseText);  
		        }  
		    }); 
		}
	}
	
 	function reloadTablePreventPage(){
 		var datatable = $('#table_userinfo').dataTable().api();
		datatable.ajax.reload(null, false);   	
 		
 	}

 	function setDataTable(){
 		if (jQuery().dataTable) {

			var export_filename = 'Filename';
			
			var table = jQuery('#table_userinfo');
			table.dataTable({
				"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right" l><"clearfix">>>t<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
				//dom: 'Bfrtip',
				"ajax" : {
					"url":'${context}/ax/userinfo/list',
				   	"type":'POST',
				   	"dataSrc" : "data",
				   	"data" :  function(param) {
						param.user_id = $('#filterUserId').val();
						param.user_name = $('#filterUserName').val();
						param.user_phone = $('#filterUserPhone').val();
						param.dept = getCheckedDept();
			        },
				        "beforeSend" : function(){
						jQuery('#preloader').show();
				        },
			        "dataSrc": function ( json ) {
						jQuery('#preloader').hide();
		                return json.data;
		            }
				},
				lengthMenu: [[20, 100, 99999], [20, 100, "전체"]],
				tableTools: {
			          "sSwfPath": plugin_path + "datatables/extensions/Buttons/js/swf/flashExport.swf"
			        },
			    "buttons": [
					              {
				                  text: '<i class="fa fa-lg fa-clipboard">csv</i>',
				                  extend: 'csvHtml5',
				                  className: 'btn btn-xs btn-primary p-5 m-0 width-35 assets-csv-btn export-csv ttip hidden',
				                  bom: true,
				                  exportOptions: {
				                      modifier: {
				                          search: 'applied',
				                          order: 'applied'
				                      }
				                  }
				              },  					              {
			                  text: '<i class="fa fa-lg fa-clipboard">프린트</i>',
			                  extend: 'print',
			                  className: 'btn btn-xs btn-primary p-5 m-0 width-35 assets-export-btn export-print ttip hidden',
			                  exportOptions: {
			                      modifier: {
			                          search: 'applied',
			                          order: 'applied'
			                      }
			                  }
			              }, 

			     ],
				
		 		"serverSide" : true,
		 		"processing": true,
		 	    "ordering": true,
				"columns": [{
					data: "userNo",							
					"orderable": false		
				}, {
					data: "deptName",
					"orderable": false	//부서
				}, {
					data: "userId",
					"orderable": false	//아이디
				}, {
					data: "userName",
					"orderable": false	//이름
				},
				{
					data: "duty",
					"orderable": false	//직책
				}, {
					data: "rank",
					"orderable": false	//계급
				}, {
					data: "phone",
					"orderable": false	//연락
				}, {
					data: "userNo",
					"orderable": false	//연락
				}],
				// set the initial value
				"pageLength": 20,
				"iDisplayLength": 20,
				"pagingType": "bootstrap_full_number",
				"language": {
					"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 사용자",
					"infoEmpty": "검색된 데이터가 없습니다.",
					"zeroRecords" :"검색된 데이터가 없습니다.",
					"lengthMenu": "  _MENU_ 개",
					"paginate": {
						"previous":"Prev",
						"next": "Next",
						"last": "Last",
						"first": "First"
					},
					
				},
				"columnDefs": [
				{	
					"targets": [0],	//추가정보
					"class":"center-cell",
					"render":function(data,type,row){
						return '<input type="checkbox" name="user_app_check" class="user_app_check" value="' + data + '" onClick="javascript:check_info()"/>';
					},
					"visible":false
				},         
				{  // set default column settings
					'targets': [1]	//부서
					,"class":"center-cell"
				}, {	
					"targets": [2]	//아이디
					,"class":"center-cell"
				}, {	
					"targets": [3]	//이름
					,"class":"center-cell"
				}, {	
					"targets": [4]	//직책
					,"class" : "center-cell"
				}, {	
					"targets": [5]	//계급
					,"class" : "center-cell"
				}, {	
					"targets": [6]	//연락처
					,"class" : "center-cell"
				}, {	
					"targets": [7]	//
					,"class" : "center-cell",
					"render":function(data,type,row){
							var ret = '<button type="button" class="btn btn-info btn-xs" onclick="javascript:onClickModifyUser(' +row.userNo+ ')"><i class="fa fa-gear" aria-hidden="true">&nbsp;수정</i></button>';
							ret += '<button type="button" class="btn btn-danger btn-xs" onclick="javascript:onClickRemoveUser(' +row.userNo+ ')"><i class="fa fa-remove" aria-hidden="true">&nbsp;삭제</i></button>';

							return ret;
					}
				}],						
				"initComplete": function( settings, json ) {
					$('.export-print').hide();
				}
			});
			
		}
 	}

 	
	$(document).ready(function(){
     	setTree();
    	vex.defaultOptions.className = 'vex-theme-os';
     	
		//전체 체크 박스 선택 시
		$("#all_check_info").click(function(){
			
		      if($(this).is(":checked")) {
		    	  $(".user_app_check").prop("checked", true);
		      } else {
		    	  $(".user_app_check").prop("checked", false);
		      }
		});

		$('#org_tree')
		.bind('ready.jstree', function(e, data) {
			setDataTable();
		})
     	

		jQuery('#preloader').hide();
       
    });
</script>
		
		
	</body>
</html>
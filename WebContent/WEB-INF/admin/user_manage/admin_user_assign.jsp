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
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="/assets/plugins/datatables/extensions/Buttons/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"  />
		<link href="/assets/plugins/datatables/extensions/Buttons/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css"  />
		
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>
		<script type="text/javascript" src="/assets/js/admin_function.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
	
		<script type="text/javascript" src="/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.jqueryui.min.js"></script>

		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.print.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.html5.min.js"></script>

		
	
		
		<!-- Alert -->
		<link href="/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
        <link href="/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />

        <script type="text/javascript" src="/assets/plugins/vex/js/vex.min.js"></script>
        <script type="text/javascript" src="/assets/plugins/vex/js/vex.combined.min.js"></script>
        
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2100); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>정책할당</h1>
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
								<div id="dept_tree" class="panel-body">

								</div>
								<!-- /panel content -->

							</div>
							<!-- /PANEL -->
					
						</div>

						<div class="col-md-10">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>정책할당 정보</strong> <!-- panel title -->
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
											
											<!-- Primary -->
											<button type="button" class="btn btn-primary pull-right" onclick="onClickExcelButton()">내보내기</button>
											<!-- Success -->
											<button type="button" class="btn btn-success pull-right" onclick="onClickPrintButton()"><i class="fa fa-print" aria-hidden="true">&nbsp;인쇄</i></button>
											<button type="button" class="btn btn-blue pull-right" onclick="fn_apply_policy()"><i class="fa fa-check" aria-hidden="true">&nbsp;정책할당</i></button>
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
														<!-- 
														<tr>         
															<td width="35%">작업시작일</td>
															<td>
							<input type="text" class="form-control datepicker" id="filterStartDate" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false">
															</td>
														</tr>																													
														<tr >         
															<td width="35%">작업종료일</td>
															<td>
							<input type="text" class="form-control datepicker" id="filterEndDate" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false">
															</td>
														</tr>																															
														 -->
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
										<div class="col-md-12" style="overflow: hidden;">
											<table class="table table-bordered x-scroll-table" id="table_apply_policy" style="width:100%; min-width: 600px;">
												<thead>
													<tr>
														<th style="width:30px">상세</th>
														<th style="width:20px"><input type="checkbox" id="all_check_info" name="all_check_info" /></th>
														<th>부서</th>
														<th>아이디</th>
														<th>이름</th>
														<th>번호</th>
														<th>직책</th>
														<th>계급</th>														
														<th>IP</th>
														<th>MAC</th>
														<th>PC이름</th>
														<th>정책요약</th>
                                                      
                                                        <!-- 
                                                       	<th>정책NO</th>
                                                        
                                                        <th>에이전트삭제가능</th>   
                                                        <th>파일실시간암호화</th>
                                                        <th>CD실시간암호화</th>

                                                        <th>프린트사용여부</th>

                                                        <th>CD사용가능여부</th>
                                                        <th>CD반출여부</th>
                                                        <th>무선랜사용가능여부</th>
                                                        <th>공유폴더사용여부</th>
                                                        <th>메일반출여부</th>

                                                        <th>USB포트사용여부</th>
                                                        <th>USB차단코드</th>

                                                        <th>시리얼포트사용여부</th>
                                                        <th>시리얼포트차단코드</th>

                                                        <th>네트워크포트사용여부</th>
                                                        <th>네트워크포트차단코드</th>

                                                        <th>프로그램차단여부</th>
                                                        <th>프로그램차단코드</th>

                                                        <th>민감패턴차단여부</th>
                                                        <th>민감패턴차단코드</th>
                                                        <th>민감패턴파일처리코드</th>

                                                        <th>사이트차단여부</th>
                                                        <th>사이트차단코드</th>

                                                        <th>메신저차단여부</th>
                                                        <th>메신저차단코드</th>

                                                        <th>워터마크</th>
                                                        <th>워터적용코드</th>
                                                        <th>워터마크적용일시</th>
                                                     
                                                        <th>프린터인쇄로그설정</th>
													 	-->
													</tr>
												</thead>				
												<tbody>
												</tbody>
											</table>									
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
		
		<!-- 정책 할당 Ajax Div -->
		<div id="policy_apply_div"></div>
		
		<!-- USER 할당된 정책 정보 Ajax PopUp Div -->
		<div id="pop_policy_setting_info"></div>
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>

		<script type="text/javascript">
			var table;
			
			// 조직도 
		 	function setTree(){
				$.ajax({      
			        type:"POST",  
			        url:'/common/tree/dept',
			        async: false,
			        //data:{},
			        success:function(args){   
			            $("#dept_tree").html(args);     
//			            setDataTable();
			        },   
			        //beforeSend:showRequest,  
			        error:function(e){  
			            console.log(e.responseText);  
			        }  
			    }); 
			}
		 	
		 	// 검색 버튼 클릭 시 
		 	function searchUserLog(){
		 		var datatable = $('#table_apply_policy').dataTable().api();
				datatable.ajax.reload();   	
		 	
		 	}
		
		 	// 인쇄 버튼 클릭 시 
		 	function onClickPrintButton(){
		 		var $buttons = $('.export-print');
		 		$buttons.click();
		 	}
		 	
		 	// 내보내기 버튼 클릭 시 
		 	function onClickExcelButton(){
				console.log('excel')
		 		var $buttons = $('.export-csv');
		 		$buttons.click();
		 		
		 	}
		 	
		 	// 정책할당 버튼 클릭 시 
		 	function fn_apply_policy() {
		 		var checkedLen = $("input:checkbox[name='policy_app_check']:checked").length;
		 		if (checkedLen < 1) {
		 			infoAlert("정책 할당을 위해서 하나 이상의 회원을 클릭해 주세요.");
		 			return false;
		 		}
		 		
		 		var apply_arr = new Array();
		 		gdTable = table.api();
				
				$(":checkbox[name='policy_app_check']:checked").each(function(pi,po){
					var check_row = $(this).parents('tr').get(0);
					var check_item = gdTable.row(check_row).data();
					apply_arr.push(check_item);
				});
				
				$.ajax({      
				    type:"POST",  
				    url:'/admin/user/assign/apply',
				    async: false,
				    data:{
				    	apply_list : JSON.stringify(apply_arr),
				    	_ : $.now()
				    },
				    success:function(data){
				    	$("#policy_apply_div").html(data);
			            $('#modalApplyPolicy').modal('show');
				    },   
				    error:function(e){  
				        console.log(e.responseText);  
				    }  
				});
		 		
		 	}
		 	
		 	// 체크 박스 클릭 시 전체 체크 여부 확인
		 	var check_Info = function() {
				
		 		var checkboxLen =  $("input:checkbox[name='policy_app_check']").length;
		 		var checkedLen = $("input:checkbox[name='policy_app_check']:checked").length;
		
		 		if(checkboxLen == checkedLen){
		 			$("#all_check_info").prop("checked", true);
		 		} else {
		 			$("#all_check_info").prop("checked", false);
		 		}
		 		
		 	}
		 	
		 	function fn_sel_policy_detailOpen(type, code){
		 		
				if(type == 'isWaterMark') {
					alert("준비중입니다.");
					return false;
				}
		 		
		 		$.ajax({      
				    type:"POST",  
				    url:'/admin/user/assign/setting/info',
				    async: false,
				    data:{
				    	type : type,
				    	code : code,
				    	_ : $.now()
				    },
				    success:function(data){
				    	$("#pop_policy_setting_info").html(data);
				    	
				    	if (type == 'isUsbBlock') {
				    		 $('#modalUsbSettingInfo').modal('show');
				    		 	usb_info_table();
				    	} else if (type == 'isComPortBlock') {
				    		$('#modalComPortSettingInfo').modal('show');
				    			 com_port_info_table();
				    	} else if (type == 'isNetPortBlock') {
				    		$('#modalNetPortSettingInfo').modal('show');
				    			net_port_info_table();
				    	} else if (type == 'isProcessList') {
				    		$('#modalProcessSettingInfo').modal('show');
				    			process_info_table();
				    	} else if (type == 'isFilePattern') {
				    		$('#modalPatternSettingInfo').modal('show');
				    			pattern_info_table();
				    	} else if (type == 'isWebAddr') {
				    		$('#modalWebAddrSettingInfo').modal('show');
				    			web_site_info_table();
				    	} else if (type == 'isMsgBlock') {
				    		$('#modalMsgBlockSettingInfo').modal('show');
				    			msg_block_info_table();
				    	} 
			           
				    },   
				    error:function(e){  
				        console.log(e.responseText);  
				    }  
				});
		 	}
		 	
		 	function setDataTable(){

				if (jQuery().dataTable) {

					var export_filename = 'Filename';
					
					apTable = jQuery('#table_apply_policy');
					table = apTable.dataTable({
						"dom": '<"row view-filter"<"col-sm-12"<"pull-left" i><"pull-right" ><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
						//dom: 'Bfrtip',
						"ajax" : {
							"url":'/ax/policy/assign/list',
						   	"type":'POST',
						   	"dataSrc" : "data",
						   	"data" :  function(param) {
								param.user_id 	= $('#filterUserId').val();
								param.user_name = $('#filterUserName').val();
								param.start_date = $('#filterStartDate').val();
								param.end_date 	= $('#filterEndDate').val();
								
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
				 		"serverSide" : true,
				 	    "ordering": true,
						"columns": [{
							data: "userNo",							
							"orderable": false	// 추가정보
						}, {
							data: "agentNo",
							"orderable": false	// select box
							,"render":function(data,type,row){
								return '<input type="checkbox" name="policy_app_check" class="policy_app_check" value="' + data + '" onClick="javascript:check_Info()"/>';
							}
						}, {
							data: "deptName",
							"orderable": false	//부서
						}, {
							data: "userId",
							"orderable": false	//아이디
						}, {
							data: "userName",
							"orderable": false	//이름
						}, {
							data: "userNo",
							"orderable": false	//번호
						}, {
							data: "duty",
							"orderable": false	//직책
						}, {
							data: "rank",
							"orderable": false	//계급
						}, {
							data: "ipAddr",
							"orderable": false	//IP
						}, {
							data: "macAddr",
							"orderable": false	//MAC
						}, {
							data: "pcName",
							"orderable": false	//PC이름
						}, {
							data: "policyNo",
							"orderable": false	//정책요약
							,"render":function(data,type,row){
								return getPolicyIcon(row); 
							}
						}, {
							data: "policyNo",
							"orderable": false	//정책NO
						},{
							data: "isUninstall",
							"orderable": false	//에이전트삭제가능
						},{
							data: "isFileEncryption",
							"orderable": false	//파일실시간암호화
						},{
							data: "isCdEncryption",
							"orderable": false	//CD실시간암호화
						},{
							data: "isPrint",
							"orderable": false	//프린트사용여부
						},{
							data: "isCdEnabled",
							"orderable": false	//CD사용가능여부
						},{
							data: "isCdExport",
							"orderable": false	//CD반출여부
						},{
							data: "isWlan",
							"orderable": false	//무선랜사용가능여부
						},{
							data: "isNetShare",
							"orderable": false	//공유폴더사용여부
						},{
							data: "isWebExport",
							"orderable": false	//메일반출여부
						},{
							data: "isUsbBlock",
							"orderable": false	//USB포트사용여부
						},{
							data: "usbBlockCode",
							"orderable": false	//USB차단코드
						},{
							data: "isComPortBlock",
							"orderable": false	//시리얼포트사용여부
						},{
							data: "comPortBlockCode",
							"orderable": false	//시리얼포트차단코드
						},{
							data: "isNetPortBlock",
							"orderable": false	//네트워크포트사용여부
						},{
							data: "netPortBlockCode",
							"orderable": false	//네트워크포트차단코드
						},{
							data: "isProcessList",
							"orderable": false	//프로세스차단여부
						},{
							data: "processListCode",
							"orderable": false	//프로세스차단코드
						},{
							data: "isFilePattern",
							"orderable": false	//민감패턴차단여부
						},{
							data: "filePatternCode",
							"orderable": false	//민감패턴차단코드
						},{
							data: "patternFileControl",
							"orderable": false	//민감패턴파일처리코드
						},{
							data: "isWebAddr",
							"orderable": false	//사이트차단여부
						},{
							data: "webAddrCode",
							"orderable": false	//사이트차단코드
						},{
							data: "isMsgBlock",
							"orderable": false	//메신저차단여부
						},{
							data: "msgBlockCode",
							"orderable": false	//메신저차단코드
						},{
							data: "isWaterMark",
							"orderable": false	//워터마크
						},{
							data: "waterMarkType",
							"orderable": false	//워터적용코드
						},{
							data: "waterMarkEndDate",
							"orderable": false	//워터마크적용일시
						},{
							data: "printLogDesc",
							"orderable": false	//프린터인쇄로그설정
						}],
						// set the initial value
						"pageLength": 20,
						"iDisplayLength": 20,
						"pagingType": "bootstrap_full_number",
						"language": {
							"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 개",
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
							"targets": [0],	// 추가정보
							"class":"center-cell add_detail_info",
							"render":function(data,type,row){
								return '<span class="datables-td-detail datatables-close"></span>';
							}
						}, {
							'targets': [1]	// select box
							,"class":"center-cell"
						}, {	
							"targets": [2]	//부서
							,"class":"center-cell"
						}, {	
							"targets": [3]	//아이디
							,"class":"center-cell"
						}, {	
							"targets": [4],	//이름
							"class":"center-cell"
						}, {	
							"targets": [5]	//번호
							,"class" : "center-cell"
						}, {	
							"targets": [6]	//직책
							,"class" : "center-cell"
						}, 
						{	
							"targets": [7]	//계급
							,"class" : "center-cell"
						}, {	
							"targets": [8]	//IP
							,"class" : "center-cell"
							,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}							
						}, {	
							"targets": [9]	//MAC
							,"class" : "center-cell"
	 						,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}		
	 													
						}, {	
							"targets": [10]	//PC이름
							,"class" : "center-cell"
							,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}	
						}, {	
							"targets": [11]	//정책요약
							,"class" : "center-cell"
						}, {	
							"targets": [12]	//정책NO
							,"visible" : false
						}, {	
							"targets": [13]	// 에이전트삭제가능
							,"visible" : false
						}, {	
							"targets": [14]	// 파일실시간암호화
							,"visible" : false
						}, {	
							"targets": [15]	// CD실시간암호화
							,"visible" : false
						}, {	
							"targets": [16]	// 프린트사용여부
							,"visible" : false
						}, {	
							"targets": [17]	// CD사용가능여부
							,"visible" : false
						}, {	
							"targets": [18]	// CD반출여부
							,"visible" : false
						}, {	
							"targets": [19]	// 무선랜사용가능여부
							,"visible" : false
						}, {	
							"targets": [20]	// 공유폴더사용여부
							,"visible" : false
						}, {	
							"targets": [21]	// 메일반출여부
							,"visible" : false
						}, {	
							"targets": [22]	// USB포트사용여부
							,"visible" : false
						}, {	
							"targets": [23]	// USB차단코드
							,"visible" : false
						}, {	
							"targets": [24]	// 시리얼포트사용여부
							,"visible" : false
						}, {	
							"targets": [25]	// 시리얼포트차단코드
							,"visible" : false
						}, {	
							"targets": [26]	// 네트워크포트사용여부
							,"visible" : false
						}, {	
							"targets": [27]	// 네트워크포트차단코드
							,"visible" : false
						}, {	
							"targets": [28]	// 프로그램차단여부
							,"visible" : false
						}, {	
							"targets": [29]	// 프로그램차단코드
							,"visible" : false
						}, {	
							"targets": [30]	// 민감패턴차단여부
							,"visible" : false
						}, {	
							"targets": [31]	// 민감패턴차단코드
							,"visible" : false
						}, {	
							"targets": [32]	// 민감패턴파일처리코드
							,"visible" : false
						}, {	
							"targets": [33]	// 사이트차단여부
							,"visible" : false
						}, {	
							"targets": [34]	// 사이트차단코드
							,"visible" : false
						}, {	
							"targets": [35]	// 메신저차단여부
							,"visible" : false
						}, {	
							"targets": [36]	// 메신저차단코드
							,"visible" : false
						}, {	
							"targets": [37]	// 워터마크
							,"visible" : false
						}, {	
							"targets": [38]	// 워터적용코드
							,"visible" : false
						}, {	
							"targets": [39]	// 워터마크적용일시
							,"visible" : false
						}, {	
							"targets": [40]	// 프린터인쇄로그설정
							,"visible" : false
						}],						
						"initComplete": function( settings, json ) {
							$('.export-print').hide();
						}
					});
					
					
					function fnFormatDetails(oTable, nTr) {
						var aData = oTable.fnGetData(nTr);
						var sOut = getApplyPolicyDetailItem(aData);

						return sOut;
					}
					
					var jTable = jQuery('#table_apply_policy');
					jTable.on('click', ' tbody td .datables-td-detail', function () {
						var nTr = jQuery(this).parents('tr')[0];
						if (table.fnIsOpen(nTr)) {
							/* This row is already open - close it */
							jQuery(this).addClass("datatables-close").removeClass("datatables-open");
							table.fnClose(nTr);
						} else {
							/* Open this row */
							jQuery(this).addClass("datatables-open").removeClass("datatables-close");
							table.fnOpen(nTr, fnFormatDetails(table, nTr), 'details');
						}
					});
				}
					
		 	}
		 	
			$(document).ready(function(){
				

				$(".select2theme").select2({
		   			  minimumResultsForSearch: -1,
		   			  dropdownAutoWidth : true,
		   			  width: 'auto'
		   		});
				
				//전체 체크 박스 선택 시
				$("#all_check_info").click(function(){
					
				      if($(this).is(":checked")) {
				    	  $(".policy_app_check").prop("checked", true);
				      } else {
				    	  $(".policy_app_check").prop("checked", false);
				      }
				});
				
		     	setTree();
		     	
				$('#org_tree')
				.bind('ready.jstree', function(e, data) {
					setDataTable();
				})
		 
			jQuery('#preloader').hide();
		
		    });
			
		</script>
	</body>
</html>
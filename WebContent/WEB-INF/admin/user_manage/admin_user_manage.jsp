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

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2500); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>에이전트감사로그</h1>
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
										<strong>에이전트감사로그</strong> <!-- panel title -->
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
											<table class="table table-striped table-bordered table-hover x-scroll-table" id="table_userinfo" style="width:100%; min-width: 600px;">
												<thead>
													<tr>
														<th style="width:20px"></th>
														<th>부서</th>
														<th>아이디</th>
														<th>이름</th>
														<th>번호</th>
														<th >직책</th>
														<th >계급</th>														
														<th >IP</th>
														<th >MAC</th>
														<th >PC이름</th>
														<th >작업모듈</th>
														<th >작업내역</th>
														<th >작업시간(서버)</th>
														<th >작업시간</th>
														<th >상태</th>

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
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>

<script>

	//라디오타입에 따라 컬럼 hide/show
	var setColumnType = function(cType){
		
		var datatable = $('#table_userinfo').dataTable().api();
		var aColumn = datatable.columns('.agentinfo' );
		var uColumn = datatable.columns('.userinfo' );
		if(cType == 1){
			uColumn.visible(true);
			aColumn.visible(false);			

 			var jTable = $('#table_userinfo').dataTable();;

//			var nsTr = $('tbody > td > .datables-td-detail').parents('tr')[0];
			var nsTr = $('#table_userinfo tr');
			for(var i = 0; i < nsTr.length; i++){
				var nTr = nsTr[i];
				jTable.fnClose(nTr);
			}
		}else if(cType == 2){
			uColumn.visible(false);
			aColumn.visible(true);	

			var nsTr = $('#table_userinfo tr td').find('span.datables-td-detail');
			nsTr.addClass("datatables-close").removeClass("datatables-open");
		}		
	}

 	function setTree(){
		$.ajax({      
	        type:"POST",  
	        url:'/common/tree/dept',
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
		console.log('excel')
 		var $buttons = $('.export-csv');
 		$buttons.click();
 		
 	}
 	
	$(document).ready(function(){
		
		$(".select2theme").select2({
   			  minimumResultsForSearch: -1,
   			  dropdownAutoWidth : true,
   			  width: 'auto'
   		});

		
     	setTree();

loadScript(plugin_path + "datatables/media/js/jquery.dataTables.min.js", function(){
loadScript(plugin_path + "datatables/media/js/dataTables.bootstrap.min.js", function(){
loadScript(plugin_path + "datatables/extensions/Buttons/js/dataTables.buttons.min.js", function(){
loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.print.min.js", function(){
loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.html5.min.js", function(){
loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.jqueryui.min.js", function(){
 
				if (jQuery().dataTable) {

					var export_filename = 'Filename';
					
					var table = jQuery('#table_userinfo');
					table.dataTable({
						"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right" l><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
						//dom: 'Bfrtip',
						"ajax" : {
							"url":'/ax/audit/client/list',
						   	"type":'POST',
						   	"dataSrc" : "data",
						   	"data" :  function(param) {
								param.user_id = $('#filterUserId').val();
								param.user_name = $('#filterUserName').val();
								param.start_date = $('#filterStartDate').val();
								param.end_date = $('#filterEndDate').val();
								
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
						lengthMenu: [[20, 100, 1000], [20, 100, 1000]],
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
				 	    "ordering": true,
						"columns": [{
							data: "exportNo",							
							"orderable": false	//추가정보
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
							data: "moduleName",
							"orderable": false	//작업모듈
						}, {
							data: "description",
							"orderable": false	//작업내역
						}, {
							data: "serverTime",
							"orderable": false	//작업시간(서버)
						}, {
							data: "clientTime",
							"orderable": false	//작업시간(PC)
						}, {
							data: "status",
							"orderable": false	//상태
						}],
						// set the initial value
						"pageLength": 20,
						"iDisplayLength": 20,
						"pagingType": "bootstrap_full_number",
						"language": {
							"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 개 로그",
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
							"class":"center-cell add_detail_info",
							"render":function(data,type,row){
								return '<span class="datables-td-detail datatables-close"></span>';
							}
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
							"targets": [4],	//번호
							"class":"center-cell"
						}, {	
							"targets": [5]	//직책
							,"class" : "center-cell"
						}, {	
							"targets": [6]	//계급
							,"class" : "center-cell"
						}, 
						{	
							"targets": [7]	//IP
							,"class" : "center-cell"
							,"visible" : false
							,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}								
						}, {	
							"targets": [8]	//MAC
							,"class" : "center-cell"
							,"visible" : false
	 						,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}								
						}, {	
							"targets": [9]	//PC이름
							,"class" : "center-cell"
							,"visible" : false
	 						,"render":function(data,type,row){
	 							if(data == ''){
	 								return '-'
	 							}else{
	 								return data;
	 							}
	 						}								
						}, {	
							"targets": [10]	//작업모듈
							,"class" : "center-cell"
						}, {	
							"targets": [11]	//작업내역
						}, {	
							"targets": [12]	//시간(서버)
							,"class" : "center-cell"
							,"visible" : false
						}, {	
							"targets": [13]	//시간(PC)
							,"class" : "center-cell"
						}, {	
							"targets": [14]	//상태
							,"class" : "center-cell"
						}],						
						"initComplete": function( settings, json ) {
							$('.export-print').hide();
						}
					});
					
					function fnFormatDetails(oTable, nTr) {
						var aData = oTable.fnGetData(nTr);
						var sOut = '<table class="table fixed"  style="width:100%;overflow:auto">';
						sOut += '<tr><td class="center-cell">MAC:</td><td>' + aData.macAddr + '</td>';
						sOut += '<td class="center-cell">PC명:</td><td>' + aData.pcName + '</td></tr>';
						sOut += '<tr><td class="center-cell">PC명:</td><td>' + aData.serverTime + '</td><td></td><td></td></tr>';
						
						sOut += '<td class="center-cell"></td><td></td></tr>';
												
						sOut += '</table>';

						return sOut;
					}
					
					var jTable = jQuery('#table_userinfo');
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
			});
			});
			});
			});
			}); 
		});
jQuery('#preloader').hide();

    });
</script>
	</body>
</html>
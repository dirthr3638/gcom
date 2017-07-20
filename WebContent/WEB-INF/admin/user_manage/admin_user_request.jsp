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
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2200); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>정책변경요청</h1>
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
										<strong>사용자요청리스트</strong> <!-- panel title -->
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
										<div class="col-md-12" style="overflow: hidden;">
											<table class="table table-striped table-bordered table-hover x-scroll-table" id="table_userinfo" style="width:100%; min-width: 600px;">
												<thead>
													<tr>
														<th></th>
														<th>부서</th>
														<th>아이디</th>
														<th>이름</th>
														<th >직책</th>
														<th >계급</th>
														<th >연락처</th>
														<th></th>
														<th></th>
														<th></th>

														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th ></th>
														<th ></th>
														<th ></th>
														<th></th>
														<th></th>
														<th></th>

														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th ></th>
														<th ></th>
														<th ></th>
														<th></th>
														<th></th>
														<th></th>

														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th ></th>
														<th ></th>
														<th ></th>
														<th></th>
														<th></th>
														<th></th>

														<th></th>
														<th></th>
														<th></th>
														<th></th>
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
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/js/admin_function.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>

		<script type="text/javascript" src="/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.jqueryui.min.js"></script>

		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.print.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/datatables/extensions/Buttons/js/buttons.html5.min.js"></script>

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
 	
 	function setDataTable(){
 		if (jQuery().dataTable) {

			var export_filename = 'Filename';
			
			var table = jQuery('#table_userinfo');
			table.dataTable({
				"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right" l><"clearfix">>>t<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
				//dom: 'Bfrtip',
				"ajax" : {
					"url":'/ax/admin/policy/requested/list',
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
					"orderable": false		//추가정보
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
					data: "isUninstall",
					"orderable": false	//연락
				}, {
					data: "isFileEncryption",
					"orderable": false	//연락
				}, {
					data: "isCdEncryption",
					"orderable": false	//연락
				}, {
					data: "isPrint",
					"orderable": false	//연락
				}, {
					data: "isCdEnabled",
					"orderable": false	//연락
				}, {
					data: "isCdExport",
					"orderable": false	//연락
				}, {
					data: "isWlan",
					"orderable": false	//연락
				}, {
					data: "isNetShare",
					"orderable": false	//연락
				}, {
					data: "isWebExport",
					"orderable": false	//연락
				}, {
					data: "isStorageExport",
					"orderable": false	//연락
				}, {
					data: "isStorageAdmin",
					"orderable": false	//연락
				}, {
					data: "isUsbBlock",
					"orderable": false	//연락
				}, {
					data: "isComPortBlock",
					"orderable": false	//연락
				}, {
					data: "isNetPortBlock",
					"orderable": false	//연락
				}, {
					data: "isProcessList",
					"orderable": false	//연락
				}, {
					data: "isFilePattern",
					"orderable": false	//연락
				}, {
					data: "isWebAddr",
					"orderable": false	//연락
				},  {
					data: "oldPolicy.isWaterMark",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.patternFileControl",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isUninstall",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isFileEncryption",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isCdEncryption",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isPrint",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isCdEnabled",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isCdExport",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isWlan",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isNetShare",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isWebExport",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isStorageExport",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isStorageAdmin",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isUsbBlock",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isComPortBlock",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isNetPortBlock",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isProcessList",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isFilePattern",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.isWebAddr",
					"orderable": false	//연락
				},  {
					data: "oldPolicy.isWaterMark",
					"orderable": false	//연락
				}, {
					data: "oldPolicy.patternFileControl",
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
					"targets": [4]	//직책
					,"class" : "center-cell"
				}, {	
					"targets": [5]	//계급
					,"class" : "center-cell"
				}, {	
					"targets": [6]	//연락처
					,"class" : "center-cell"
				}, {
					"targets": [7]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				
				}, {
					"targets": [8]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [9]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [10]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [11]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [12]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [13]	//연락처
				,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [14]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [15]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [16]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [17]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [18]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [19]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [20]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [21]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [22]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [23]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [24]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [25]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [26]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [27]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [28]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [29]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [30]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [31]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [32]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [33]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [34]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [35]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [36]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [37]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [38]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [39]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [40]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [41]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [42]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [43]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}, {
					"targets": [44]	//연락처
					,"class" : "center-cell"
					,"visible" : false
				}						],						
				"initComplete": function( settings, json ) {
					$('.export-print').hide();
				}
			});
			
			function fnFormatDetails(oTable, nTr) {
				var aData = oTable.fnGetData(nTr);
				var sOut = '<table class="table fixed"  style="width:100%;overflow:auto">';
				sOut += getRequestPolicyDetailTable(aData);
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
 	}
 	
	$(document).ready(function(){
		$(".select2theme").select2({
   			  minimumResultsForSearch: -1,
   			  dropdownAutoWidth : true,
   			  width: 'auto'
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
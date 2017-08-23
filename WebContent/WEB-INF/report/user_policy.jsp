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
		<link href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="${context}/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/color_scheme/black.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"  />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css"  />
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<!-- 
				ASIDE 
				Keep it outside of #wrapper (responsive purpose)
			-->
			<% request.setAttribute("menu_parent", 3000); %> 
			<% request.setAttribute("menu_sub_first", 3100); %> 
			<jsp:include page="/WEB-INF/common/report_left_menu.jsp" flush="false" />
			
			<!-- /ASIDE -->
			<!-- HEADER -->
			<jsp:include page="/WEB-INF/common/report_top_navi.jsp" flush="false" />			
			<!-- /HEADER -->

			<!-- 
				MIDDLE 
			-->
			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>정책변경로그</h1>
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
										<strong>정책변경로그</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
			
											<!-- Standard button -->
											<button type="button" class="btn btn-default" onclick="jQuery('#pre-1').slideToggle();"><i class="fa fa-filter" aria-hidden="true">&nbsp;검색필터</i></button>
		
											<!-- Info -->
											<button type="button" class="btn btn-info" onclick="reloadTable()"><i class="fa fa-repeat" aria-hidden="true">&nbsp;재검색</i></button>
											
											
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
                                                        <th>정책정보</th>   
                                                        <th>에이전트삭제가능</th>   
                                                        <th>프린트사용가능</th>
                                                        <th>워터마크</th>
                                                        <th>파일실시간암호화</th>
                                                        <th>USB포트사용가능</th>
                                                        <th>시리얼포트사용가능</th>
                                                        <th>무선랜사용가능</th>
                                                        <th>메일반출가능</th>
                                                        <th>민감파일접근시삭제</th>
                                                        <th>보호폴더접근가능</th>
                                                        <th>공유폴더사용여부</th>
                                                        <th>CD사용여부</th>
                                                        <th>적용시간</th>
                                                        <th>요청시간(서버)</th>
                                                        <th>요청시간(PC)</th>
													</tr>
												</thead>				
												<tbody>
												</tbody>
											</table>									
										</div>
									</div>
									
									
									<div class="ld_modal hidden" >
									    <div class="ld_center" >
									        <img alt="" src="${context}/assets/images/loaders/loading.gif" />
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
		<script type="text/javascript">var plugin_path = '${context}/assets/plugins/';</script>
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
 	
 	function reloadTable(){
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
				"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right" l><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
				//dom: 'Bfrtip',
				"ajax" : {
					"url":'${context}/ax/userpolicylog/list',
				   	"type":'POST',
				   	"dataSrc" : "data",
				   	"data" :  function(param) {
						param.user_id = $('#filterUserId').val();
						param.user_name = $('#filterUserName').val();
						
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
				                          order: 'applied',
				                      },
			                      columns: [ 1, 2, 3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25 ]
				                  }
				              },  					              
				              {
				                  text: '<i class="fa fa-lg fa-clipboard">프린트</i>',
				                  extend: 'print',
				                  className: 'btn btn-xs btn-primary p-5 m-0 width-35 assets-export-btn export-print ttip hidden',
				                  exportOptions: {
				                      modifier: {
				                          search: 'applied',
				                          order: 'applied',
			                     	 },
			                          columns: [ 1, 2, 3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,20,21,22,23 ]

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
				},{
					data: "userNo",
					"orderable": false	//요약아이콘
				},{
					data: "isUninstall",
					"orderable": false	//에이전트삭제가능
				}, {
					data: "isPrint",
					"orderable": false	//프린트사용가능
				}, {
					data: "isWaterMark",
					"orderable": false	//워터마크
				}, {
					data: "isFileEncryption",
					"orderable": false	//파일실시간암호화
				}, {
					data: "isUsbBlock",
					"orderable": false	//USB포트사용
				}, {
					data: "isComPortBlock",
					"orderable": false	//시리얼포트
				}, {
					data: "isWlan",
					"orderable": false	//무선랜사용가능
				}, {
					data: "isFilePattern",
					"orderable": false	//메일반출가능
				}, {
					data: "isFilePattern",
					"orderable": false	//민감파일
				}, {
					data: "isFilePattern",
					"orderable": false	//보호폴더접근가능
				}, {
					data: "isNetShare",
					"orderable": false	//공유폴더사용여부
				}, {
					data: "isCdEnabled",
					"orderable": false	//CD사용여부
				}, {
					data: "applyTime",
					"orderable": false	//적용시간
				}, {
					data: "requestServerTime",
					"orderable": false	//요청시간
				}, {
					data: "requestClientTime",
					"orderable": false	//요청시간
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
					"targets": [10]	//요약
					,"class" : "center-cell"
					,"render" : function(data,type,row){
						return getPolicyIcon(row);
					}
				}, {	
					"targets": [11]	//에이전트삭제가능
					,"class" : "center-cell"
					,"visible" : false
					,"render":function(data,type,row){
						if(data == true){
							return '허용';
						}else{
							return '불허';
						}
					}
				}, {	
					"targets": [12]	//프린트사용가능
					,"visible" : false
					,"render":function(data,type,row){
						if(data == true){
							return '허용';
						}else{
							return '불허';
						}
					}
				}, {	
					"targets": [13]	//워터마크
					,"class" : "center-cell"
					,"render":function(data,type,row){
						if(data == true){
							return '출력';
						}else{
							return '미출력';
						}
					}
				,"visible" : false
				}, {	
					"targets": [14]	//파일실시간암호화
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '암호화';
							}else{
								return '비암호화';
							}
						}
						,"visible" : false
				}, {	
					"targets": [15]	//USB포트사용가능
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [16]	//시리얼포트사용가능
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [17]	//무선랜사용가능
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [18]	//메일반출가능
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [19]	//민감파일접근시삭제
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '삭제';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [20]	//보호폴더접근가능
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [21]	//공유폴더사용여부
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [22]	//CD사용여부
					,"class" : "center-cell"
						,"render":function(data,type,row){
							if(data == true){
								return '허용';
							}else{
								return '불허';
							}
						}
						,"visible" : false
				}, {	
					"targets": [23]	//적용시간
					,"class" : "center-cell"
				}, {	
					"targets": [24]	//요청시간
					,"class" : "center-cell"
						,"visible" : false
				}, {	
					"targets": [25]	//요청시간(PC)
					,"class" : "center-cell"
						,"visible" : false
				}],						
				"initComplete": function( settings, json ) {
					$('.export-print').hide();
//					$('#table_userinfo').colResizable({liveDrag:true});

				}
			});
			
			function fnFormatDetails(oTable, nTr) {
				var aData = oTable.fnGetData(nTr);
				var sOut = getPolicyDetailTable(aData);

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
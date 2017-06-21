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
			<% request.setAttribute("menu_sub_first", 2600); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>공지사항 관리</h1>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						<div class="col-md-12">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>공시사항</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12" style="overflow: hidden;">
											<table class="table table-striped table-bordered table-hover x-scroll-table" id="table_notice" style="width:100%; min-width: 600px;">
												<col width="80px">
												<col>
												<col width="120px">
												<col width="150px">
												<col width="50px">
												<thead>
													<tr>
														<th>ID</th>
														<th>No</th>
														<th>제목</th>
														<th>작성자</th>
														<th>작성일</th>
														<th>조회</th>
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
				
				var datatable = $('#table_notice').dataTable().api();
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
		 	
		 	function searchUserLog(){
		 		var datatable = $('#table_notice').dataTable().api();
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
		
				loadScript(plugin_path + "datatables/media/js/jquery.dataTables.min.js", function(){
				loadScript(plugin_path + "datatables/media/js/dataTables.bootstrap.min.js", function(){
				loadScript(plugin_path + "datatables/extensions/Buttons/js/dataTables.buttons.min.js", function(){
				loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.print.min.js", function(){
				loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.html5.min.js", function(){
				loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.jqueryui.min.js", function(){
		 
						if (jQuery().dataTable) {
		
							var table = jQuery('#table_notice');
							table.dataTable({
								"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right" ><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
								"ajax" : {
									"url":'/ax/admin/notice/list',
								   	"type":'POST',
								   	"dataSrc" : "data",
								   	"data" :  function(param) {
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
									data: "bbsId",							
									"orderable": false	//ID
								}, {
									data: "bbsId",
									"orderable": false	//No
									,render : function(data, type, row, a){
										var paging = a.settings._iDisplayStart;
										return paging + a.row + 1;
										
									}
								}, {
									data: "bbsTitle",
									"orderable": false	//제목
								}, {
									data: "bbsRegStaf",
									"orderable": false	//작성자
								}, {
									data: "bbsRegDate",
									"orderable": false	//작성일
								}, {
									data: "bbsClickCnt",
									"orderable": false	//조회수
								}],
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
									"targets": [0],	//ID
									"class":"center-cell"
									,"visible" : false
								}, {  
									'targets': [1]	//No
									,"class":"center-cell"
								}, {	
									"targets": [2]	//제목
									,"class":"center-cell"
								}, {	
									"targets": [3]	//작성자
									,"class":"center-cell"
								}, {	
									"targets": [4],	//작성일
									"class":"center-cell"
								}, {	
									"targets": [5]	//조회수
									,"class" : "center-cell"
								}],						
								"initComplete": function( settings, json ) {
									
								}
							});
							
							var notice = $('#table_notice').DataTable();
							notice.on( 'click', 'td', function () {
								var data = notice.row( $(this).parent() ).data();
								
								if($(this).index() == 1){	// 제목 클릭
									location.href  = '/admin/user/notice/view?bbsId=' +  data.bbsId;
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
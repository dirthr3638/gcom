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
		<link href="/assets/css/color_scheme/black.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<!-- <link href="/assets/css/layout-datatables.css" rel="stylesheet" type="text/css" /> -->
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<!-- 
				ASIDE 
				Keep it outside of #wrapper (responsive purpose)
			-->
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
					<h1>사용자로그</h1>
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
								<div class="panel-body">

									<div id="org_tree" style="overflow: hidden;">
				                        <ul>
				                            <li class="jstree-open" data-jstree='{"type":"root"}'>Company
				                                <ul>
				                                    <li>사업부
				                                        <ul>
				                                            <li>사업 1팀</li>
				                                            <li>사입 2팀</li>
				                                            <li>디자인팀</li>
				                                        </ul>
				                                    </li>

				                                    <li>IT지원</li>
				                                </ul>
				                            </li>
				                        </ul>
				                    </div>
								</div>
								<!-- /panel content -->

							</div>
							<!-- /PANEL -->
					
						</div>

						<div class="col-md-10">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>사용자로그</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
			
											<!-- Standard button -->
											<button type="button" class="btn btn-default" onclick="jQuery('#pre-1').slideToggle();">검색필터</button>
		
											<!-- Info -->
											<button type="button" class="btn btn-info">새로고침</button>
											
											<label class="radio" style="margin-left: 10px">
												<input type="radio" name="table-type" value="1" checked="checked" onclick="onTypeCheck(this)">
												<i></i> 사용자정보
											</label>
											<label class="radio">
												<input type="radio" name="table-type" value="2" onclick="onTypeCheck(this)">
												<i></i> 에이전트정보
											</label>
											
											
											<!-- Primary -->
											<button type="button" class="btn btn-primary pull-right">내보내기</button>
											<!-- Success -->
											<button type="button" class="btn btn-success pull-right">인쇄</button>
											<div id="pre-1" class="margin-top-10 text-left noradius text-danger softhide" style="width:400px;">
												<table id="user" class="table table-bordered">
													<tbody> 
														<tr>         
															<td width="35%">접속여부</td>
															<td>
																<select class="select2theme">
																  <option value="0">전체</option>
																  <option value="1">접속</option>
																  <option value="2">미접속</option>
																</select>
															</td>
														</tr>	
														<tr>         
															<td width="35%">버전</td>
															<td>
																<select class="select2theme">
																  <option value="0">전체</option>
																  <option value="1">1.0.0</option>
																  <option value="2">1.0.1</option>
																</select>
															</td>
														</tr>		
														<tr>         
															<td width="35%">아이디</td>
															<td>
																<input type="text" name="userID" value="" class="form-control required">
															</td>
														</tr>
														<tr>         
															<td width="35%">이름</td>
															<td>
																<input type="text" name="userID" value="" class="form-control required">
															</td>
														</tr>
														<tr>         
															<td width="35%">연락처</td>
															<td>
																<input type="text" name="userID" value="" class="form-control required">
															</td>
														</tr>																															
														
													</tbody>
												</table>	
												
												<button type="button" class="btn btn-success" onclick="jQuery('#pre-1').slideToggle();">필터적용</button>
																					
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
														<th class="userinfo">직책</th>
														<th class="userinfo">계급</th>
														<th class="userinfo">연락처</th>
														<th>설치</th>
														
														<th class="agentinfo">IP</th>
														<th class="agentinfo">MAC</th>
														<th class="agentinfo">PC이름</th>
														<th class="agentinfo">버전</th>
														<th class="agentinfo">접속</th>
														<th class="agentinfo">설치시간</th>
														<th class="agentinfo">접속시간(서버)</th>
														<th class="agentinfo">접속시간(PC)</th>
													</tr>
												</thead>
				
												<tbody>
													<tr class="odd gradeX">
														<td><span class="datables-td-detail datatables-close"></span>
														</td>
														<td>인사과
														</td>
														<td>user_id
														</td>
														<td>오무진
														</td>
														<td>10000
														</td>
														<td>팀장
														</td>
														<td>과장
														</td>
														<td>010-333-5545
														</td>
														<td>설치
														</td>
														<td>192.0.0.1
														</td>
														<td>11:11:11:11:11:11
														</td>
														<td>windows-PC-client001
														</td>
														<td>1.0.0.1
														</td>
														<td>접속
														</td>
														<td>17-02-01 15:44:12
														</td>
														<td>17-02-01 15:44:12
														</td>
														<td>17-02-01 15:44:12
														</td>
													</tr>
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
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>
		
		
<script>

	//사용자정보/에이전트정보 뷰타입변경
	var currentType=1;
	function onTypeCheck(radioType){
		if(currentType != radioType.value){
			currentType = radioType.value;
			setColumnType(currentType);
		}
	}
	
	//라디오타입에 따라 컬럼 hide/show
	function setColumnType(cType){
		
		var datatable = $('#table_userinfo').dataTable().api();
		var aColumn = datatable.columns('.agentinfo' );
		var uColumn = datatable.columns('.userinfo' );
		if(cType == 1){
			uColumn.visible(true);
			aColumn.visible(false);			
		}else if(cType == 2){
			uColumn.visible(false);
			aColumn.visible(true);			
		}		
	}
	
	var getPageinfoHash = function(){
		//페이지번호
		//라디오타입
		//검색필터정보
	};
	
	var getFilterInfo = function(){
		
	};

	$(document).ready(function(){
     	$(document).ready(function() {
    		  $(".select2theme").select2({
    			  minimumResultsForSearch: -1,
    			  dropdownAutoWidth : true,
    			  width: 'auto'
    		  });
    	}); 
    	
        $('#org_tree').jstree({
            'core' : {
                'check_callback' : true
            },
            "contextmenu":{         
                "items": function($node) {
                    var tree = $("#org_tree").jstree(true);
                    return {
                        "Create": {
                            "separator_before": false,
                            "separator_after": false,
                            "label": "Create",
                            "action": function (obj) { 
                                $node = tree.create_node($node);
                                tree.edit($node);
                            }
                        },
                        "Rename": {
                            "separator_before": false,
                            "separator_after": false,
                            "label": "Rename",
                            "action": function (obj) { 
                                tree.edit($node);
                            }
                        },                         
                        "Remove": {
                            "separator_before": false,
                            "separator_after": false,
                            "label": "Remove",
                            "action": function (obj) { 
                                tree.delete_node($node);
                            }
                        }
                    };
                }
            },
            'plugins' : [ 'types', 'dnd', 'checkbox', 'contextmenu' ],
            'types' : {
                'default' : {
                    'icon' : 'fa fa-user-circle-o'
                },
                'root' : {
                    'icon' : 'fa fa-building'
                }

            }
        });

        
        loadScript(plugin_path + "datatables/js/jquery.dataTables.min.js", function(){
			loadScript(plugin_path + "datatables/dataTables.bootstrap.js", function(){
				loadScript(plugin_path + "datatables/js/dataTables.colResize.js", function(){
					loadScript(plugin_path + "datatables/js/dataTables.colVis.js", function(){

				if (jQuery().dataTable) {

					var table = jQuery('#table_userinfo');
					table.dataTable({
						//"autoWidth": true,
						//"dom": '<"row view-filter"<"col-sm-12"<"pull-left" i ><"pull-right"><"clearfix">>>t<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
						//dom: 'C<"clear">RZlfrtp',
						"dom": '<"row view-filter"<"col-sm-12"<"pull-left" i ><"pull-right"><"clearfix">>>t<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
						//l이 갯수
						"columns": [{
							"orderable": false		//추가정보
						}, {
							"orderable": false	//부서
						}, {
							"orderable": false	//아이디
						}, {
							"orderable": false	//이름
						}, {
							"orderable": false	//번호
						}, {
							"orderable": false	//직책
						}, {
							"orderable": false	//계급
						}, {
							"orderable": false	//연락
						}, {
							"orderable": false	//설치유무
						}, {
							"orderable": false	//IP
						}, {
							"orderable": false	//MAC
						}, {
							"orderable": false	//PC이름
						}, {
							"orderable": false	//버전
						}, {
							"orderable": false	//접속여부
						}, {
							"orderable": false	//설치시간
						}, {
							"orderable": false	//서버접속시간
						}, {
							"orderable": false	//PC접속시간
						}],
						// set the initial value
						"pageLength": 20,
						"iDisplayLength": 20,
						"pagingType": "bootstrap_full_number",
						"language": {
							"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 사용자",
							"infoEmpty":      "검색된 데이터가 없습니다.",
							"lengthMenu": "  _MENU_ 개",
							"paginate": {
								"previous":"Prev",
								"next": "Next",
								"last": "Last",
								"first": "First"
							}
						},
						"columnDefs": [
						{	
							"targets": [0],	//추가정보
							"class":"center-cell",
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
							,"class" : "userinfo center-cell"
						}, {	
							"targets": [6]	//계급
							,"class" : "userinfo center-cell"
						}, {	
							"targets": [7]	//연락처
							,"class" : "userinfo center-cell"
						}, {	
							"targets": [8]	//설치유무
							,"class":"center-cell"
						}, {	
							"targets": [9]	//IP
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [10]	//MAC
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [11]	//PC이름
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [12]	//버전
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [13]	//접속여부
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [14]	//설치시간
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [15]	//서버접속시간
							,"class" : "agentinfo center-cell"
						}, {	
							"targets": [16]	//PC접속시간
							,"class" : "agentinfo center-cell"
						}],						
						"initComplete": function( settings, json ) {
							setColumnType(1);
						}
					});
					
					function fnFormatDetails(oTable, nTr) {
						var aData = oTable.fnGetData(nTr);
						var sOut = '<table>';
						sOut += '<tr><td>Platform(s):</td><td>' + aData[2] + '</td></tr>';
						sOut += '<tr><td>Engine version:</td><td>' + aData[3] + '</td></tr>';
						sOut += '<tr><td>CSS grade:</td><td>' + aData[4] + '</td></tr>';
						sOut += '<tr><td>Others:</td><td>Could provide a link here</td></tr>';
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
</script>
		
		
	</body>
</html>
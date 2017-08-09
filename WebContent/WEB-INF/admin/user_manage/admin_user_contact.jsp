<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	String staf_id = request.getAttribute("user_id").toString();
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
		<link href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="${context}/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="${context}/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"  />
		<link href="${context}/assets/plugins/datatables/extensions/Buttons/css/buttons.jqueryui.min.css" rel="stylesheet" type="text/css"  />
		
		<!-- Alert -->
		<link href="${context}/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
		<link href="${context}/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />
		
		<script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.combined.min.js"></script>
		
	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<% request.setAttribute("menu_parent", 2000); %> 
			<% request.setAttribute("menu_sub_first", 2300); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			
<!-- 
				MIDDLE 
			-->
			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>문의사항 관리</h1>
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
										<strong>문의사항</strong> <!-- panel title -->
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
										<div class="col-md-12" style="overflow: auto;">
											<table class="table table-bordered table-hover x-scroll-table" id="table_contact" style="width:100%; min-width: 600px;">
												<col width="20px;">
												<col width="80px;">
												<col width="120px;">
												<col>
												<col width="150px;">
												<col width="100px;">
												<col width="80px;">			
												<thead>
													<tr>
														<th></th>
														<th>문의코드</th>
														<th>문의구분</th>
														<th>제목</th>
														<th>등록일</th>
														<th>등록자</th>
														<th>답변여부</th>
														<th>답변ID</th>
														<th>답변등록자</th>
														<th>답변등록일</th>
														<th>답변내용</th>
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
		
		<div id="contact_detail_view_div"></div>
		
		<div id="comment_write_popup"></div>
		
		<div id="recomment_write_popup"></div>
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '${context}/assets/plugins/';</script>
		<script type="text/javascript" src="${context}/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${context}/assets/js/app.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/select2/js/select2.full.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.jqueryui.min.js"></script>

		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.print.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.html5.min.js"></script>

<script>

	//조직도
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
 		var datatable = $('#table_contact').dataTable().api();
		datatable.ajax.reload();   	
 	
 	}
 	
 	function fn_open_comment(conId) {
	 	
 		$.ajax({      
		    type:"POST",  
		    url:'${context}/admin/user/contact/write',
		    async: false,
		    data:{
		    	contact_id : conId,
		    	_ : $.now() 
		    },
		    success:function(data){
		    	$("#modalContactDetailView").modal('hide');
		    	$("#comment_write_popup").html(data);
	            $('#modalCommentWrite').modal('show');
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
 	}
 	
 	function fn_open_recomment(stafId, commentId) {
 		
 		var reRegStafId = '<%= staf_id%>';
 		
 		if( reRegStafId != stafId) {
			vex.defaultOptions.className = 'vex-theme-os';
	    	
    		vex.dialog.open({
    			message: '문의 답변 수정은 답변 등록자만 가능합니다.',
    			  buttons: [
    			    $.extend({}, vex.dialog.buttons.YES, {
    			      text: '확인'
    			  })],
   			});
    		
			return false;
 		}
 		
 		$.ajax({      
		    type:"POST",  
		    url:'${context}/admin/user/contact/modify',
		    async: false,
		    data:{
		    	comment_id : commentId,
		    	_ : $.now()
		    },
		    success:function(data){
		    	$("#recomment_write_popup").html(data);
	            $('#modalCommentModify').modal('show');
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
 	}
 	
 	function fn_contact_detail_view(contact_id) {
 		
		$.ajax({      
	        type:"POST",  
	        url:'${context}/admin/user/contact/view',
	        async: false,
	        data:{
	        	contactId : contact_id,
	        	_ : $.now()
	        },
	        success:function(args){
	        	
	            $("#contact_detail_view_div").html(args);
	            $("#modalContactDetailView").modal('show');
	        },   
	        error:function(e){
	            console.log(e.responseText);  
	        }  
	    });
	}
 	
 	function setDataTable(){
 		if (jQuery().dataTable) {

			var export_filename = 'Filename';
			
			var table = jQuery('#table_contact');
			table.dataTable({
				"dom": '<"row view-filter"<"col-sm-12"<"pull-left" i><"pull-right" ><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
				//dom: 'Bfrtip',
				"ajax" : {
					"url":'${context}/ax/admin/contact/list',
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
		 		"serverSide" : true,
		 		"columns": [{
						data: "contactId"			//추가정보
					}, {
						data: "contactId"			//문의코드
					}, {
						data: "contactTypeName"		//문의구분
					}, {
						data: "contactTitle"		//제목
					}, {
						data: "regDt"				//등록일
					}, {
						data: "regUserName"			//등록자
					}, {
						data: "commentYN"			//답변여부
					}, {
						data: "commentId"			//답변ID
					}, {
						data: "commnetRegStafId"	//답변등록자
					}, {
						data: "commentRegDt"		//답변등록일
					}, {
						data: "replyContent"		//답변내용
				}],
				"pageLength": 20,
				"iDisplayLength": 20,
				"language": {
					"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 개 문의",
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
						'targets': [1]	//문의코드
						,"class":"center-cell"
					}, {	
						"targets": [2]	//문의구분
						,"class":"center-cell"
					}, {	
						"targets": [3],	//제목
						"render":function(data,type,row){
							return '<b style=\"cursor:pointer;\" >'+ data + '</b>';
						}	
					
					}, {	
						"targets": [4],	//등록일
						"class":"center-cell"
					}, {	
						"targets": [5],	//등록자
						"class":"center-cell"
					}, {	
						"targets": [6]	//답변여부
						,"class" : "center-cell"
						,"render":function(data,type,row){
							return data=="Y"?'<i class="fa fa-pencil">':'' ;
						}
					}, {	
						"targets": [7]	//답변ID
						,"visible":false
					}, {	
						"targets": [8]	//답변등록자
						,"visible":false
					}, {	
						"targets": [9]	//답변등록일
						,"visible":false
					}, {	
						"targets": [10]	//답변내용
						,"visible":false
				}],						
				"initComplete": function( settings, json ) {
				}
			});
			
			function fnFormatDetails(oTable, nTr) {
				var aData = oTable.fnGetData(nTr);

				var reFlag = aData.commentYN;
				var sOut = '<table class="table fixed" style="width:100%; overflow:auto; margin:0;">';

				if( reFlag == 'Y' ) {
					sOut += '<tr><td class="comment-cell" style="vertical-align: middle;">답변 : ';
					sOut += '<i class="fa fa-clock-o"></i> '+ aData.commentRegDt + '&nbsp;&nbsp;&nbsp;&nbsp;';
					sOut += '<i class="fa fa-user"></i> '+ aData.commnetRegStafId ;
					sOut += '<button id="btnModifyComment" class="btn btn-xs pull-right" style="background-color:#f3768b; color:#fff !important; font-weight:bold;" onClick="javascript:fn_open_recomment(\'' + aData.commnetRegStafId + '\' , \''+ aData.commentId + '\')">답변수정</button>';
					
					sOut += '</td></tr>';
					sOut += '<tr><td class="comment-cell" style="padding:20px 10px;">'+ aData.replyContent +'</td></tr>';
				} else {
					sOut += '<tr><td class="comment-cell" style="padding:20px 10px; vertical-align: middle;">등록된 답변이 없습니다.</td></tr>';
				}
				
				sOut += '</table>';
				
				return sOut;
			}
			
			var jTable = jQuery('#table_contact');
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
			
			var con = $('#table_contact').DataTable();
			con.on( 'click', 'td', function () {
				var data = con.row( $(this).parent() ).data();
				
				if($(this).index() == 3) {	// 제목 클릭
					fn_contact_detail_view(data.contactId);
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
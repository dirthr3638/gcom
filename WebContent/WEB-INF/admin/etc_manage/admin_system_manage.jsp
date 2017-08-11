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

			<% request.setAttribute("menu_parent", 5000); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			

			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>시스템관리</h1>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						
						<div class="col-md-12">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>시스템설정목록</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12" style="overflow: auto;">
											<table class="table table-striped table-bordered table-hover x-scroll-table" id="table_systeminfo" style="width:100%; min-width: 600px;">
												<thead>
													<tr>
														<th>번호</th>
														<th>시스템정보</th>
														<th>적용값</th>
														<th>수정</th>
														<th>name</th>
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
		<script type="text/javascript" src="${context}/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="${context}/assets/js/app.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/jstree/jstree.min.js"></script>
		<script type="text/javascript" src="${context}/assets/plugins/select2/js/select2.full.min.js"></script>
        <script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.min.js"></script>
        <script type="text/javascript" src="${context}/assets/plugins/vex/js/vex.combined.min.js"></script>
		<script type="text/javascript" src="${context}/assets/js/admin_function.js"></script>

<script> 

	function onClickModify(description, id, value, name){

		console.log(name)
		if (name == 'local_file_access_descriptor' || name == 'removable_file_access_descriptor' || name == 'network_file_access_descriptor') {
			logSystemPopup(description, id, value, name);
		}
		else{
			commonSystemPopup(description, id, value, name);
		}

	}
	
	function logSystemPopup(description, id, value, name){

		var read_value = value & 1;
		var write_value = value & 2;
		var read_log_flag = value & 4026531840;
		var read_block_log_flag = value & 251658240;
		var write_log_flag = value & 15728640;
		var write_block_log_flag = value & 983040;

		var table =
		
		'<table id="user" class="table table-bordered"><tbody> ' +
		'<tr><td width="35%">시스템정보</td>' +
		'<td><input type="text" name="filterDeviceName" id="filterDeviceName" value="'+ description +'" class="form-control" readonly></td></tr>' +
		'<tr><td width="35%">적용값</td>' +
		'<td><input type="text" name="filterValue" id="filterValue" value="'+ value +'" class="form-control" readonly></td></tr>' +
		'<tr><td width="35%">쓰기</td>';
		
		if(read_value == 1)	
 			table += '<td><input type="checkbox" name="isWrite" id="isWrite" value="1" checked></td></tr>';
		else
			table += '<td><input type="checkbox" name="isWrite" id="isWrite" value="1" ></td></tr>';
		
		table += '<tr><td width="35%">읽기</td>';

		if(write_value == 2)	
			table += '<td><input type="checkbox" name="isRead" value="2" id="isRead" checked></td></tr>';
		else
			table += '<td><input type="checkbox" name="isRead" value="2" id="isRead" ></td></tr>';
		
		table += '<tr><td width="35%">쓰기로그설정</td>';
		
		if(write_log_flag == 0){
			table += '<td><input type="radio" name="writeConfig" id="writeConfigDisable" value="0" checked>사용안함';
		}else{
			table += '<td><input type="radio" name="writeConfig" id="writeConfigDisable" value="0" >사용안함';			
		}
		
		if(write_log_flag == 1048576){
			table += '<input type="radio" name="writeConfig" id="writeConfigEventLog" value="1048576" checked>이벤트로그';		
		}else{
			table += '<input type="radio" name="writeConfig" id="writeConfigEventLog" value="1048576" >이벤트로그';			
		}

		if(write_log_flag == 9437184){
			table += '<input type="radio" name="writeConfig" id="writeConfigFileLog" value="9437184" checked>파일로그</td></tr>';
		}else{
			table += '<input type="radio" name="writeConfig" id="writeConfigFileLog" value="9437184" >파일로그</td></tr>';			
		}

		table += '<tr><td width="35%">쓰기차단로그설정</td>';

		if(write_block_log_flag == 0){
			table += '<td><input type="radio" name="writeDeniedConfig" id="writeDeniedConfigDisable" value="0" checked>사용안함';
		}else{
			table += '<td><input type="radio" name="writeDeniedConfig" id="writeDeniedConfigDisable" value="0" >사용안함';
		}
		if(write_block_log_flag == 65536){
			table += '<input type="radio" name="writeDeniedConfig" id="writeDeniedConfigEventLog" value="65536" checked>이벤트로그</td></tr>';
		}else{
			table += '<input type="radio" name="writeDeniedConfig" id="writeDeniedConfigEventLog" value="65536" >이벤트로그</td></tr>';
		}
		
		table +='</tbody>' +
		'</table>';

		vex.dialog.open({
            input: [
					table
                ].join(''),
     			buttons: [
				    $.extend({}, vex.dialog.buttons.YES, {
				      text: '확인'
				    }),
				    $.extend({}, vex.dialog.buttons.NO, {
				      text: '취소'
				    })
				    ],
 		    callback: function (data) {
 		        if (!data) {
 		        	return;
 		        }else{
 		        	updateSystemLogInfo(id, data);
 		        }
 		    }
       });
	}
	
	function vexAlert(text){
 		vex.dialog.open({
 			message: text,
 			  buttons: [
 			    $.extend({}, vex.dialog.buttons.YES, {
 			      text: '확인'
 			  })] 		    				
 		})
	}
	
	function commonSystemPopup(description, id, value, name){
		vex.dialog.open({
            input: [
                     '<label>시스템정보</label>',                   
                     '<input name="description" type="text" readonly value=" '+ description +' " />',
                     '<label>적용값</label>',                     
                     '<input name="value" type="text" id="system_value" value="'+ value +'" />'
                 ].join(''),
     			buttons: [
  					    $.extend({}, vex.dialog.buttons.YES, {
  					      text: '확인'
  					    }),
  					    $.extend({}, vex.dialog.buttons.NO, {
  					      text: '취소'
  					    })
  					],
  			onSubmit : function(e){
	  			e.preventDefault()
				value = $('#system_value').val();
	  			if ((name == 'console_time_out') || (name == 'session_time_out')){
				    var time = parseInt( value, 10 );
				    
				    value = time.toString();
				    
					if (value > 600) {
						vexAlert('타임아웃 시간은 10분이하로 설정하시기바랍니다.')
						return false; 
					}
						
					if ((value <= 0) && (value != 1)) {
						vexAlert('타임아웃 시간은 0이상 입력하시기 바랍니다.')
						return false; 
					}											
						
					for(i=0;i<value.length;i++){
					
						if (value[i] >"9" || value[i] < "0") {
							vexAlert('숫자만 입력 가능합니다.')
							return false; 
						
						}							
					}
				}else if (name == 'export_file_limit_size') {
					var time = parseInt( value, 10 );
				    
				    value = time.toString();
				    						
					for(i=0;i<value.length;i++){
					
						if (value[i] >"9" || value[i] < "0") {
							
							vexAlert('숫자만 입력 가능합니다.')
							return false; 
						
						}
					}
				
					if ((value <= 0) && (value != 1)) {
						vexAlert('반출 파일의 크기는 0MB이상으로 입력바랍니다.')
						return false; 
					}
					
					if (value > 4000) {
						vexAlert('반출 파일의 최대 크기는 4000MB이하으로 입력바랍니다.')
						return false; 
					}
				}else if (name == 'sensitive_info_found') {
					if ((value != 0) && (value != 1)) {
						vexAlert('0혹은 1를 입력해주시기 바랍니다')
						return false; 
					}
				}else if ((name == 'fsflt_enable') ||
						(name == 'wmlib_enable') ||
						(name == 'diskflt_enable') ||
						(name == 'usbflt_enable') ||
						(name == 'scrsvr_enable') ||
						(name == 'netflt_enable') ||
						(name == 'portflt_enable') ||
						(name == 'filescan_enable')
						) {
					if ((value != 0) && (value != 1)) {
						vexAlert('0혹은 1를 입력해주시기 바랍니다')
						return false; 
					}
					
				}else if (name == 'usb_descriptor' || name == 'port_descriptor') {
					if (value.length > 150) {
						vexAlert('150자 이내로 입력해주시기 바랍니다')
						return false; 
					}					
				} else {
					
					if (value.length > 30) {
						vexAlert('30자 이내로 입력해주시기 바랍니다')
						return false; 
					}
					
				}
             	updateSystemInfo(id, value);
		        	
  		     	return this.close()
 		      
  			},
  			callback : function(data){
  			}
		});		
	}
	
	function updateSystemInfo(system_no, value){
		$.ajax({      
		    type:"POST",  
		    url:'${context}/admin/system/update',
		    async: false,
		    data:{
		    	value : value,
		    	system_no : system_no
		    },
		    success:function(data){
		    	if(data.returnCode == "S") {
		    		reloadTablePreventPage();
		    		infoAlert("시스템설정이 변경이 적용되었습니다.");
		    	} else {
		    		infoAlert("서버와의 통신에 실패하였습니다.");
		    	}
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
	}

	function updateSystemLogInfo(system_no, value){

		var data = 0;
		data += parseInt(value.hasOwnProperty('isWrite') ? value.isWrite : 0); 
		data += parseInt(value.hasOwnProperty('isRead') ? value.isRead : 0);
		data += parseInt(value.hasOwnProperty('writeDeniedConfig') ? value.writeDeniedConfig : 0);
		data += parseInt(value.hasOwnProperty('writeConfig') ? value.writeConfig : 0);
		
 		$.ajax({      
		    type:"POST",  
		    url:'${context}/admin/system/update',
		    async: false,
		    data:{
		    	value : data,
		    	system_no : system_no
		    },
		    success:function(data){
		    	if(data.returnCode == "S") {
		    		reloadTablePreventPage();
		    		infoAlert("시스템설정이 변경이 적용되었습니다.");
		    	} else {
		    		infoAlert("서버와의 통신에 실패하였습니다.");
		    	}
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		}); 
	}

 	
 	function reloadTablePreventPage(){
 		var datatable = $('#table_systeminfo').dataTable().api();
		datatable.ajax.reload(null, false);   	
 		
 	}

	$(document).ready(function(){
	     vex.defaultOptions.className = 'vex-theme-os';
	
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

					var export_filename = 'Filename';
					
					var table = jQuery('#table_systeminfo');
					table.dataTable({
						"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
						//dom: 'Bfrtip',
						"ajax" : {
							"url":'${context}/ax/system/list',
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
							data: "sysNo",							
							"orderable": false	//
						}, {
							data: "description",
							"orderable": false	//
						}, {
							data: "value",
							"orderable": false	//
						}, {
							data: "sysNo",
							"orderable": false	//
						},{
							data:"name"
							
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
							"targets": [0],	//추가정보
							"class":"center-cell add_detail_info",
						},         
						{  // set default column settings
							'targets': [1]	//부서
							,"class":"center-cell"
						}, {	
							"targets": [2]	//아이디
							,"class":"center-cell"
						}, {	
							"targets": [3]	//수정
							,"class":"center-cell"
							,"render":function(data,type,row){
									return '<i class="fa fa-wrench" onclick="onClickModify(\''+ row.description +'\', ' + row.sysNo + ', ' + row.value + ', \''+ row.name +'\'  )"></i>';
								}
						}, {	
							"targets": [4]	//name
						,"class":"center-cell"
						,"visible":false
					}],						
						"initComplete": function( settings, json ) {
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
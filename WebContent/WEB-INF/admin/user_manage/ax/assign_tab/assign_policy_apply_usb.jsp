<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray" width="25%">USB 차단 선택</td>
			<td width="25%">
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_usb_block" value="Y" /><i></i> 허용
				</label>
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_usb_block" value="N" /><i></i> 차단
				</label>
			</td>
			<td class="th-cell-gray" width="25%">적용상태</td>
			<td width="25%">
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered table-striped" id="usb_policy_table">
	<thead>
		<tr>
			<td>장치명</td>
			<td>벤더식별자(VID)</td>
			<td>제품식별자(PID)</td>
			<td>일련번호(SerialNumber)</td>
			<td>설명</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
											
<script type="text/javascript">
	function usb_policy_table(){
		loadScript(plugin_path + "datatables/media/js/jquery.dataTables.min.js", function(){
		loadScript(plugin_path + "datatables/media/js/dataTables.bootstrap.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/dataTables.buttons.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.print.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.html5.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.jqueryui.min.js", function(){
			 
			if (jQuery().dataTable) {
		
				var table = jQuery('#usb_policy_table');
				table.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
					"url":'/ax/admin/policy/usbblock/list',
				   	"type":'POST',
				   	"dataSrc" : "data",
				   	"data" :  {
				   		menu_code : 'apply',
				   		_ : $.now()
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
		              }
			     ],
		 		"serverSide" : true,
		 		"columns": [{
					data: "msgNo"			//ID
				}, {
					data: "msgName"			//메신저명
				}, {
					data: "processName"		//프로세스명
				}, {
					data: "txtLog"			//Message 로
				}, {                                   
					data: "txtBlock"		//Message 차
				}, {                                   
					data: "fileLog"			//File 로깅  
				}, {                                   
					data: "fileBlock"		//File 차단  
				}],  
				"pageLength": 20,
				"iDisplayLength": 20,
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
					"targets": [0],	//ID
					"class":"center-cell"
					,"visible":false
				}, {  
					'targets': [1]	//메신저명
					,"class":"center-cell"
				}, {	
					"targets": [2]	//프로세스명
					,"class":"center-cell"
				}, {	
					"targets": [3]	//Message 로깅
					,"class":"center-cell"
				}, {	
					"targets": [4],	//Message 차단
					"class":"center-cell"
				}, {	
					"targets": [5]	//File 로깅
					,"class" : "center-cell"
				}, {	
					"targets": [6]	//File 차단
					,"class" : "center-cell"
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
	}
</script>

































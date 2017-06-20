<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<table id="table-network-policy" class="table table-bordered table-striped">
	<thead>
		<tr>
			<td>ID</td>
			<td>포트이름</td>
			<td>포트번호</td>
			<td>설명</td>
			<td>사용여부</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script type="text/javascript">

	function fn_get_network_policy_data() {
		console.log("dataTable");
		loadScript(plugin_path + "datatables/media/js/jquery.dataTables.min.js", function(){
		loadScript(plugin_path + "datatables/media/js/dataTables.bootstrap.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/dataTables.buttons.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.print.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.html5.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.jqueryui.min.js", function(){
			 
			if (jQuery().dataTable) {
		
				var table = jQuery('#table-network-policy');
				table.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
					"url":'/ax/admin/policy/network/list',
				   	"type":'POST',
				   	"dataSrc" : "data",
				   	"data" :  {},
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
					data: "netNo"			//ID
				}, {
					data: "netName"			//포트 이름
				}, {
					data: "port"			//포트 번호
				}, {
					data: "descriptor"		//설명
				}, {                                   
					data: "allow"			//사용여부
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
					'targets': [1]	//포트 이름
					,"class":"center-cell"
				}, {	
					"targets": [2]	//포트 번호
					,"class":"center-cell"
				}, {	
					"targets": [3]	//설명
					,"class":"center-cell"
				}, {	
					"targets": [4],	//사용여부
					"class":"center-cell"
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
		
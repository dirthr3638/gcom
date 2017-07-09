<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<button type="button" id="btnRegWebsite" class="btn btn-sm btn-green pull-right" onclick="javascript:fn_open_reg_website_popup(0);"><i class="fa fa-check"></i>정책 등록</button>
<table id="table-website-policy" class="table table-bordered table-hover">
	<thead>
		<tr>
			<td>선택</td>
			<td>사이트ID</td>
			<td>사이트주소</td>
			<td>설명</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div id="reg_website_popup_div"></div>

<script type="text/javascript">

	function fn_open_reg_website_popup(code){
		
		$.ajax({      
		    type:"POST",  
		    url:'/admin/policy/website/register',
		    async: false,
		    data:{ 
		    	code : code,
		    	_ : $.now()
		    },
		    success:function(data){
		    	$("#reg_website_popup_div").html(data);
	            $('#modalPolicyRegWebsite').modal('show');
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
	}

	function fn_get_website_policy_data() {
	
		loadScript(plugin_path + "datatables/media/js/jquery.dataTables.min.js", function(){
		loadScript(plugin_path + "datatables/media/js/dataTables.bootstrap.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/dataTables.buttons.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.print.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.html5.min.js", function(){
		loadScript(plugin_path + "datatables/extensions/Buttons/js/buttons.jqueryui.min.js", function(){
			 
			if (jQuery().dataTable) {
		
				var table = jQuery('#table-website-policy');
				table.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left" iB ><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
					"url":'/ax/admin/policy/website/list',
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
					data: "siteId"			//ID
				}, {
					data: "siteId"			//포트 이름
				}, {
					data: "address"			//설명
				}, {                        	           
					data: "description"		//사용여부
				}],  
				"pageLength": 20,
				"iDisplayLength": 20,
		 		"language": {               
					"info": " _PAGES_ 페이지 중  _PAGE_ 페이지 / 총 _TOTAL_ 개",
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
					"targets": [2]	//설명
					,"class":"center-cell"
				}, {	
					"targets": [3],	//사용여부
					"class":"center-cell"
				}],
				"initComplete": function( settings, json ) {
				}
			});
				
			var ctbl = $('#table-website-policy').DataTable();
			ctbl.on( 'click', 'td', function () {
				var data = ctbl.row( $(this).parent() ).data();
				fn_open_reg_website_popup(data.siteId);
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
		
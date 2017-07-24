<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean isNetPortBlock = Boolean.parseBoolean(request.getParameter("isNetPortBlock"));
	String netPortBlockCode = request.getParameter("netPortBlockCode").toString();
	String applyCode = netPortBlockCode.length() > 0 ? "," + netPortBlockCode : "";
	
	if (!isNetPortBlock){ 
		applyCode = "Y" + applyCode;
	} else {
		applyCode = "N" + applyCode;
	}
%>
<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="150px" style="vertical-align: middle;">네트워크 포트 차단 선택</td>
			<td class="center-cell" style="vertical-align: middle;">
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_net_port_block" value="Y" <% if (!isNetPortBlock){ %> checked <%}%>/><i></i> 허용
				</label>
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_net_port_block" value="N" <% if (isNetPortBlock){ %> checked <%}%>/><i></i> 차단
				</label>
			</td>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">적용상태</td>
			<td class="center-cell" style="vertical-align: middle;">
				<input type="text" id="att_net_port_type" name="att_net_port_type" class="form-control" value="<%= applyCode%>" disabled />
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered" id="net_port_table" style="width: 100%">
	<thead>
		<tr>
			<th>선택</th>
			<th>포트ID</th>
			<th>포트이름</th>
			<th>포트</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>											


<script type="text/javascript">
	$(document).ready(function(){
		$("input[name=radio_net_port_block]").change(function() {
			var chk_value = $(':radio[name="radio_net_port_block"]:checked').val();
			var type = $('#att_net_port_type').val();		
			if (chk_value == 'Y') {
				$('#att_net_port_type').val(type.replace('N','Y'));	
			} else {
				$('#att_net_port_type').val(type.replace('Y','N'));
			}
		});
	});
	
	function setNetPortSelectInfo(check_box) {
		
		var netCode = $(check_box).val();
		var type = $('#att_net_port_type').val();
		
		if ($(check_box).is(':checked')) {
			
			type += "," + netCode;
			$('#att_net_port_type').val(type);
			
		} else {
	
			var temp = type.split(',');
			var result = temp[0];
			
			if (temp.length > 1) {
				for (var i = 1 ; i < temp.length ; i++) {
					if (temp[i] != netCode) {
						result += "," + temp[i];
					}
				}
				
				$('#att_net_port_type').val(result);
			}
			
		}
		
	}
	
	function net_port_table() {
		
			if (jQuery().dataTable) {
		
				var npTable = jQuery('#net_port_table');
				npTable.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left"><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
					 	async: false,
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
			 		"serverSide" : true,
			 		"columns": [{
						data: "netNo",			// check_box (ID)
						render : function(data,type,row) {
							var check = '<%= applyCode %>';
							var strData = data.toString();
							
							if (check.indexOf(strData) != -1 && check != '') {
								return '<input type="checkbox" name="net_port_check" class="net_port_check" value="' + data + '" onClick="setNetPortSelectInfo( this )" checked />';
							} else {
								return '<input type="checkbox" name="net_port_check" class="net_port_check" value="' + data + '" onClick="setNetPortSelectInfo( this )" />';							
							}
						}
					}, {
						data: "netNo"			// ID
					}, {
						data: "netName"			// 포트이름
					}, {
						data: "port"			// 포트
					}, {
						data: "descriptor"		// 설명
					}],  
					"pageLength": 10,
					"iDisplayLength": 10,
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
						"targets": [0],	// check_box
						"class":"center-cell"
					}, {  
						'targets': [1]	// ID
						,"class":"center-cell"
					}, {  
						'targets': [2]	// 포트이름
						,"class":"center-cell"
					}, {	
						"targets": [3]	// 포트
						,"class":"center-cell"
					}, {	
						"targets": [4]	// 설명
						,"class":"center-cell"
					}],
					"initComplete": function( settings, json ) {
					}
				});
			
			}

	}
</script>

































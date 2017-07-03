<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean onlyFlag = Boolean.parseBoolean(request.getParameter("onlyFlag"));
	boolean isUsbBlock = Boolean.parseBoolean(request.getParameter("isUsbBlock"));
	String usbBlockCode = request.getParameter("usbBlockCode").toString();
	String applyCode = usbBlockCode.length() > 0 ? "," + usbBlockCode : "";
	String scriptCode = onlyFlag? usbBlockCode : "";
	
	if (!isUsbBlock){ 
		applyCode = "Y" + applyCode;
	} else {
		applyCode = "N" + applyCode;
	}
%>
<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">USB 차단 선택</td>
			<td class="center-cell" style="vertical-align: middle;">
				<% if (onlyFlag) { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_usb_block" value="Y" <% if (!isUsbBlock){ %> checked <%}%> /><i></i> 허용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_usb_block" value="N" <% if (isUsbBlock){ %> checked <%}%>/><i></i> 차단
					</label>
				<% } else { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_usb_block" value="Y" checked/><i></i> 허용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_usb_block" value="N" /><i></i> 차단
					</label>
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">적용상태</td>
			<td class="center-cell" style="vertical-align: middle;">
				<% if (onlyFlag) { %>
					<input type="text" id="att_usb_block_type" name="att_usb_block_type" class="form-control" value="<%= applyCode%>" disabled />
				<% } else { %>
					<input type="text" id="att_usb_block_type" name="att_usb_block_type" class="form-control" value="Y" disabled />
				<% } %>
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered" id="usb_policy_table" style="width:100%;">
	<thead>
		<tr>
			<th>선택</th>
			<th>장치ID</th>
			<th>장치명</th>
			<th>벤더식별자(VID)</th>
			<th>제품식별자(PID)</th>
			<th>일련번호(SerialNumber)</th>
			<th>설명</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
											
<script type="text/javascript">

	$(document).ready(function(){
		$("input[name=radio_usb_block]").change(function() {
			var chk_value = $(':radio[name="radio_usb_block"]:checked').val();
			var type = $('#att_usb_block_type').val();		
			if (chk_value == 'Y') {
				$('#att_usb_block_type').val(type.replace('N','Y'));	
			} else {
				$('#att_usb_block_type').val(type.replace('Y','N'));
			}
		});
	});

	function setUsbSelectInfo(check_box) {
		
		var usbCode = $(check_box).val();
		var type = $('#att_usb_block_type').val();
		
		if ($(check_box).is(':checked')) {
			
			type += "," + usbCode;
			$('#att_usb_block_type').val(type);
			
		} else {

			var temp = type.split(',');
			var result = temp[0];
			
			if (temp.length > 1) {
				for (var i = 1 ; i < temp.length ; i++) {
					if (temp[i] != usbCode) {
						result += "," + temp[i];
					}
				}
				
				$('#att_usb_block_type').val(result);
			}
			
		}
		
	}

	function usb_policy_table() {
			 
			if (jQuery().dataTable) {
		
				var upTable = jQuery('#usb_policy_table');
				upTable.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left"><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
						async: false,
						"url":'/ax/admin/policy/usbblock/list',
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
						data: "usbId",			// check_box (ID)
						render : function(data,type,row) {
							var check = '<%= scriptCode %>';
							var strData = data.toString();
		
							if (check.indexOf(strData) != -1 && check != '') {
								return '<input type="checkbox" name="usb_app_check" class="usb_app_check" value="' + data + '" onClick="setUsbSelectInfo( this )" checked />';
							} else {
								return '<input type="checkbox" name="usb_app_check" class="usb_app_check" value="' + data + '" onClick="setUsbSelectInfo( this )" />';							
							}
						}
					}, {
						data: "usbId"			// ID
					}, {
						data: "name"			// 장치명
					}, {
						data: "vid"			// 벤더식별자
					}, {
						data: "pid"			// 제품식별자
					}, {                                   
						data: "serialNumber"	// 시리얼 넘버
					}, {                                   
						data: "description"		// 설명  
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
						'targets': [2]	// 장치명
						,"class":"center-cell"
					}, {	
						"targets": [3]	// 벤더식별자
						,"class":"center-cell"
					}, {	
						"targets": [4]	// 제품식별자
						,"class":"center-cell"
					}, {	
						"targets": [5],	// 시리얼 넘버
						"class":"center-cell"
					}, {	
						"targets": [6]	// 설명
						,"class" : "center-cell"
					}],
					"initComplete": function( settings, json ) {
					}
				});
			
			}
	}
</script>

































<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean onlyFlag = Boolean.parseBoolean(request.getParameter("onlyFlag"));
	boolean isMsgBlock = Boolean.parseBoolean(request.getParameter("isMsgBlock"));
	String msgBlockCode = request.getParameter("msgBlockCode").toString();
	String applyCode = msgBlockCode.length() > 0 ? "," + msgBlockCode : "";
	String scriptCode = onlyFlag? msgBlockCode : "";
	
	if (!isMsgBlock){ 
		applyCode = "Y" + applyCode;
	} else {
		applyCode = "N" + applyCode;
	}
%>

<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">메신저 차단 선택</td>
			<td class="center-cell" style="vertical-align: middle;">
				<% if (onlyFlag) { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_msg_block" value="Y" <% if (!isMsgBlock){ %> checked <%}%> /><i></i> 허용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_msg_block" value="N" <% if (isMsgBlock){ %> checked <%}%>/><i></i> 차단
					</label>
				<% } else { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_msg_block" value="Y" checked/><i></i> 허용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_msg_block" value="N" /><i></i> 차단
					</label>
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">적용상태</td>
			<td class="center-cell" style="vertical-align: middle;">
				<% if (onlyFlag) { %>
					<input type="text" id="att_msg_block_type" name="att_msg_block_type" class="form-control" value="<%= applyCode%>" disabled />
				<% } else { %>
					<input type="text" id="att_msg_block_type" name="att_msg_block_type" class="form-control" value="Y" disabled />
				<% } %>
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered" id="msg_block_table" style="width:100%;">
	<thead>
		<tr>
			<td>선택</td>
			<td>메신저ID</td>
			<td>메신저명</td>
			<td>파일명</td>
			<td>Message로깅</td>
			<td>Message차단</td>
			<td>File전송로깅</td>
			<td>File전송차단</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
													

<script type="text/javascript">

	$(document).ready(function(){
		$("input[name=radio_msg_block]").change(function() {
			var chk_value = $(':radio[name="radio_msg_block"]:checked').val();
			var type = $('#att_msg_block_type').val();		
			if (chk_value == 'Y') {
				$('#att_msg_block_type').val(type.replace('N','Y'));	
			} else {
				$('#att_msg_block_type').val(type.replace('Y','N'));
			}
		});
	});

	function setMsgBlockSelectInfo(check_box) {
		
		var msgCode = $(check_box).val();
		var type = $('#att_msg_block_type').val();
		console.log(type);
		if ($(check_box).is(':checked')) {
			
			type += "," + msgCode;
			$('#att_msg_block_type').val(type);
			
		} else {

			var temp = type.split(',');
			var result = temp[0];
			
			if (temp.length > 1) {
				for (var i = 1 ; i < temp.length ; i++) {
					if (temp[i] != msgCode) {
						result += "," + temp[i];
					}
				}
				
				$('#att_msg_block_type').val(result);
			}
			
		}
		
	}

	function msg_block_table() {
			 
			if (jQuery().dataTable) {
		
				var mbTable = jQuery('#msg_block_table');
				mbTable.dataTable({
					"dom": '<"row view-filter"<"col-sm-12"<"pull-left"><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
					"ajax" : {
						async: false,
						"url":'/ax/admin/policy/messenger/list',
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
						data: "msgNo",			// check_box (ID)
						render : function(data,type,row) {
							var check = '<%= scriptCode %>';
							var strData = data.toString();
		
							if (check.indexOf(strData) != -1 && check != '') {
								return '<input type="checkbox" name="msg_block_check" class="msg_block_check" value="' + data + '" onClick="setMsgBlockSelectInfo( this )" checked />';
							} else {
								return '<input type="checkbox" name="msg_block_check" class="msg_block_check" value="' + data + '" onClick="setMsgBlockSelectInfo( this )" />';							
							}
						}
					}, {
						data: "msgNo"			// ID
					}, {
						data: "msgName"			// 메신저명
					}, {
						data: "processName"		// 메신저파일명
					}, {
						data: "txtLog"			// 텍스트 로그
					}, {
						data: "txtBlock"		// 텍스트 차단
					}, {
						data: "fileLog"			// 파일 로그
					}, {
						data: "fileBlock"		// 파일 차단
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
						'targets': [2]	// 메신저명
						,"class":"center-cell"
					}, {	
						"targets": [3]	// 메신저파일명
						,"class":"center-cell"
					}, {	
						"targets": [4]	// 텍스트 로그
						,"class":"center-cell"
					}, {	
						"targets": [5]	// 텍스트 차단
						,"class":"center-cell"
					}, {	
						"targets": [6]	// 파일 로그
						,"class":"center-cell"
					}, {	
						"targets": [7]	// 파일 차단
						,"class":"center-cell"
					}],
					"initComplete": function( settings, json ) {
					}
				});
			
			}
	}
</script>
































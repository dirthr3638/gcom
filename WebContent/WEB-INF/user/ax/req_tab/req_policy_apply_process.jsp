<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean isProcessList = Boolean.parseBoolean(request.getParameter("isProcessList"));
	String processListCode = request.getParameter("processListCode").toString();
	String applyCode = processListCode.length() > 0 ? processListCode : "";
		
%>
<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="150px" style="vertical-align: middle;">프로세스 차단 선택</td>
			<td class="center-cell" style="vertical-align: middle;">
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_process_block" value="Y" <% if (!isProcessList){ %> checked <%}%> /><i></i> 허용
				</label>
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_process_block" value="N"<% if (isProcessList){ %> checked <%}%>  /><i></i> 차단
				</label>
			</td>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">적용상태</td>
			<td class="center-cell" style="vertical-align: middle;">
				<input type="text" id="att_process_type" name="att_process_type" class="form-control" value="<%= applyCode%>" disabled/>
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered" id="process_table" style="width: 100%">
	<thead>
		<tr>
			<td>선택</td>
			<td>프로세스ID</td>
			<td>프로세스이름</td>
			<td>프로세스경로</td>
			<td>해시데이터</td>
			<td>설명</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
													

<script type="text/javascript">
$(document).ready(function(){
	$("input[name=radio_process_block]").change(function() {
		var chk_value = $(':radio[name="radio_process_block"]:checked').val();
		var type = $('#att_process_type').val();		
		if (chk_value == 'Y') {
			$('#att_process_type').val('');
			$('.porcess_check').prop("checked",false);
		} else {
			alert('차단 프로세스를 선택하면 차단 됩니다.');
			$('input:radio[name=radio_process_block]:input[value="Y"]').prop("checked", true);
		} 
	});
});

function setPorcessSelectInfo(check_box) {
	
	var prsCode = $(check_box).val();
	var type = $('#att_process_type').val();
	
	if ($(check_box).is(':checked')) {
		
		if (type.length < 1) {
			type += prsCode;
			$('input:radio[name=radio_process_block]:input[value="N"]').prop("checked", true);
		} else {
			type += "," + prsCode;
		}
		
		$('#att_process_type').val(type);
		
	} else {

		var temp = type.split(',');
		var result = [];

		for (var i = 0 ; i < temp.length ; i++) {
			if (temp[i] != prsCode) {
				result.push(temp[i]);
			}
		}
		
		$('#att_process_type').val(result.toString());
		
	}
	
}

function process_table() {
	
		if (jQuery().dataTable) {
	
			var nprTable = jQuery('#process_table');
			nprTable.dataTable({
				"dom": '<"row view-filter"<"col-sm-12"<"pull-left"><"pull-right"><"clearfix">>>tr<"row view-pager"<"col-sm-12"<"pull-left"<"toolbar">><"pull-right"p>>>',
				"ajax" : {
				 	async: false,
					"url":'/ax/admin/policy/process/list',
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
					data: "proNo",			// check_box (ID)
					render : function(data,type,row) {
						var check = '<%= applyCode %>';
						var strData = data.toString();
						
						if (check.indexOf(strData) != -1 && check != '') {
							return '<input type="checkbox" name="porcess_check" class="porcess_check" value="' + data + '" onClick="setPorcessSelectInfo( this )" checked />';
						} else {
							return '<input type="checkbox" name="porcess_check" class="porcess_check" value="' + data + '" onClick="setPorcessSelectInfo( this )" />';							
						}
					}
				}, {
					data: "proNo"			// ID
				}, {
					data: "processName"		// 프로세스 이름
				}, {
					data: "processPath"		// 프로세스 경로
				}, {
					data: "hash"			// 포르세스 해시 데이터
				}, {
					data: "notice"			// 설명
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
					'targets': [2]	// 프로세스 이름
					,"class":"center-cell"
				}, {	
					"targets": [3]	// 프로세스 경로
					,"class":"center-cell"
				}, {	
					"targets": [4]	// 포르세스 해시 데이터
					,"class":"center-cell"
				}, {	
					"targets": [5]	// 설명
				,"class":"center-cell"
			}],
				"initComplete": function( settings, json ) {
				}
			});
		
		}

}
</script>

































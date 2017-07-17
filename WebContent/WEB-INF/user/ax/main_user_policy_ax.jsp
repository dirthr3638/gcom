<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.common.util.CommonUtil "%>
<%@ page import="gcom.user.model.MemberPolicyModel"%>
<% 
	List<MemberPolicyModel> list = (List<MemberPolicyModel>)request.getAttribute("userPolicyInfo");
	
	boolean isEmpty = true;
	
	for(MemberPolicyModel mp : list) {
		if(mp.getPolicyNo() != 0) {
			isEmpty = false;
		}
	}

%>

<script type="text/javascript" src="/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>


	<% if (isEmpty) { %>
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td>사용자 정책이 존재 하지 않습니다.</td>
			</tr>
		</tbody>
	</table>
		
	<% 
	} else {
		
		for(MemberPolicyModel data : list) {
			String policyIcon = CommonUtil.getPolicyIcon(data);
	%>
	<button id="btnReqPolicy" class="btn btn-sm btn-green pull-right" style="color:#fff; font-weight:bold; margin-bottom: 5px;" onClick="javascript:fn_req_change_policy('<%= data.getPolicyNo() %>');"  ><i class="fa fa-cog" aria-hidden="true"></i>정책변경요청</button>
	<table class="table table-bordered">
		<tbody>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">에이전트 정보</td>
				<td class="center-cell" colspan="3">
					<ul class="inline-list">
						<li>PC 이름 : <%= data.getPcName() %></li>
						<li>MAC : <%= data.getMacAddr() %></li>
						<li>IP : <%= data.getIpAddr() %></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">정책요약 정보</td>
				<td class="center-cell" colspan="3"><%= policyIcon %></td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">에이전트삭제가능</td>
				<td class="center-cell" width="25%"> <% if (data.getIsUninstall()) { %> 허용 <% } else { %> 불가 <% } %></td>
				<td class="center-cell th-cell-gray" width="25%">파일실시간암호화</td>
				<td class="center-cell" width="25%"> <% if (data.getIsFileEncryption()) { %> 암호화 <% } else { %> 비암호화 <% } %></td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">CD실시간암호화</td>
				<td class="center-cell" width="25%"> <% if (data.getIsCdEncryption()) { %> 암호화 <% } else { %> 비암호화 <% } %></td>
				<td class="center-cell th-cell-gray" width="25%">프린트사용여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsPrint()) { %> 허용 <% } else { %> 불가 <% } %></td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">CD사용가능여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsCdEnabled()) { %> 허용 <% } else { %> 불가 <% } %></td>
				<td class="center-cell th-cell-gray" width="25%">CD반출여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsCdExport()) { %> 허용 <% } else { %> 불가 <% } %></td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">무선랜사용가능여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsWlan()) { %> 허용 <% } else { %> 차단 <% } %></td>
				<td class="center-cell th-cell-gray" width="25%">공유폴더사용여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsNetShare()) { %> 사용 <% } else { %> 미사용 <% } %></td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">메일반출여부</td>
				<td class="center-cell" width="25%"> <% if (data.getIsWebExport()) { %> 허용 <% } else { %> 차단 <% } %></td>
				<td class="center-cell th-cell-gray" width="25%">USB포트사용여부</td>
				<td class="center-cell" width="25%">
					<% if (data.getIsUsbBlock()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isUsbBlock', '<%= data.getUsbBlockCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">시리얼포트사용여부</td>
				<td class="center-cell" width="25%"> 
					<% if (data.getIsComPortBlock()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isComPortBlock', '<%= data.getComPortBlockCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
				<td class="center-cell th-cell-gray" width="25%">네트워크포트사용여부</td>
				<td class="center-cell" width="25%">
					<% if (data.getIsNetPortBlock()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isNetPortBlock', '<%= data.getNetPortBlockCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">프로세스차단여부</td>
				<td class="center-cell" width="25%"> 
					<% if (data.getIsProcessList()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isProcessList', '<%= data.getProcessListCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
				<td class="center-cell th-cell-gray" width="25%">민감패턴차단여부</td>
				<td class="center-cell" width="25%"> 
					<% if (data.getIsFilePattern()) { %> 차단 [검충패턴처리 : <%= data.getPatternFileControl() == 0? "격리" : "삭제" %>]  <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isFilePattern', '<%= data.getFilePatternCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">사이트차단여부</td>
				<td class="center-cell" width="25%"> 
					<% if (data.getIsWebAddr()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isWebAddr', '<%= data.getWebAddrCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
				<td class="center-cell th-cell-gray" width="25%">메신저차단여부</td>
				<td class="center-cell" width="25%"> 
					<% if (data.getIsMsgBlock()) { %> 차단 <% } else { %> 허용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isMsgBlock', '<%= data.getMsgBlockCode() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
			</tr>
			<tr>
				<td class="center-cell th-cell-gray" width="25%">워터마크사용여부</td>
				<td class="center-cell" width="25%">
					<% if (data.getIsWaterMark()) { %> 사용 [<%= data.getWaterMarkEndDate() %> 까지] <% } else { %> 미사용 <% } %>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onClick="javascript:fn_popup_detail('isWaterMark', '<%= data.getWaterMarkType() %>');" style="color:#1b74a7"><i class="fa fa-search"></i> 상세</a>
				</td>
				<td class="center-cell th-cell-gray" width="25%">프린터 인쇄 로그</td>
				<td class="center-cell" width="25%"> 
					<% 
						int printLog = data.getPrintLogDesc();
						if (printLog == 0) { %> 로그전송안함 <% } else if (printLog == 1) { %> 이벤트로그 <% } else if (printLog == 2) { %> 파일원본로그 <% } %>
				</td>
			</tr>
		</tbody>
	</table>
	<br />
	<% }
	} %>

<!-- 상세 팝업 -->
<div id="policy_detail_div"></div>



<script type="text/javascript">
	function fn_popup_detail(type, code){
			
		if(type == 'isWaterMark') {
			alert("준비중입니다.");
			return false;
		}
			
			$.ajax({      
		    type:"POST",  
		    url:'/ax/main/policy/modal',
		    async: false,
		    data:{
		    	type : type,
		    	code : code,
		    	_ : $.now()
		    },
		    success:function(data){
		    	$("#policy_detail_div").html(data);
		    	
		    	if (type == 'isUsbBlock') {
		    		 $('#modalUsbSettingInfo').modal('show');
		    		 	usb_info_table();
		    	} else if (type == 'isComPortBlock') {
		    		$('#modalComPortSettingInfo').modal('show');
		    			 com_port_info_table();
		    	} else if (type == 'isNetPortBlock') {
		    		$('#modalNetPortSettingInfo').modal('show');
		    			net_port_info_table();
		    	} else if (type == 'isProcessList') {
		    		$('#modalProcessSettingInfo').modal('show');
		    			process_info_table();
		    	} else if (type == 'isFilePattern') {
		    		$('#modalPatternSettingInfo').modal('show');
		    			pattern_info_table();
		    	} else if (type == 'isWebAddr') {
		    		$('#modalWebAddrSettingInfo').modal('show');
		    			web_site_info_table();
		    	} else if (type == 'isMsgBlock') {
		    		$('#modalMsgBlockSettingInfo').modal('show');
		    			msg_block_info_table();
		    	} 
	           
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
	}
</script>

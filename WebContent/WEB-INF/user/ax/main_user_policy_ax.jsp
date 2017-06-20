<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.user.model.MemberPolicyModel"%>
<% 
	MemberPolicyModel data = (MemberPolicyModel)request.getAttribute("userPolicyInfo");
%>
<table class="table table-bordered table-striped">
	<% if ("".equals(data.getUserId())) { %>
	<tbody>
		<tr>
			<td>사용자 정책이 존재 하지 않습니다.</td>
		</tr>
	<% 
	} else {
	%>
		<tr>
			<td>에이전트 삭제 가능 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsUninstall()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUninstall" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUninstall" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUninstall" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUninstall" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>파일 실시간 암복호화 사용 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsFileEncryption()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isFileEncryption" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isFileEncryption" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isFileEncryption" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isFileEncryption" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>CD 암복호화 사용 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsCdEncryption()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEncryption" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEncryption" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEncryption" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEncryption" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>프린터 사용 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsPrint()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isPrint" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isPrint" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isPrint" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isPrint" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>CD 사용 가능 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsCdEnabled()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEnabled" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEnabled" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEnabled" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdEnabled" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>CD 복사/이동 가능 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsCdExport()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdExport" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdExport" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdExport" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isCdExport" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>무선랜 사용 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsWlan()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWlan" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWlan" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWlan" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWlan" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>공유 폴더 사용 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsNetShare()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetShare" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetShare" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetShare" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetShare" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>WEB 외부 반출 가능 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsWebExport()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWebExport" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWebExport" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWebExport" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isWebExport" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>저장 정보 외부 반출 시 삭제 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsStorageExport()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageExport" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageExport" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageExport" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageExport" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>관리자 모드에서 저장정보 삭제 가능 여부</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsStorageAdmin()) { %>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageAdmin" checked="checked" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageAdmin" disabled="disabled"><i></i> 불가
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageAdmin" disabled="disabled"><i></i> 허용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isStorageAdmin" checked="checked" disabled="disabled"><i></i> 불가
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				USB포트 제어 정보
			</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsUsbBlock()) {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUsbBlock" checked="checked" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUsbBlock" disabled="disabled"><i></i> 미적용 
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isUsbBlock', '<%= data.getUsbBlockCode() %>');" ><i class="fa fa-search"></i> 상세정책보기</a> 
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUsbBlock" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isUsbBlock" checked="checked" disabled="disabled"><i></i> 미적용
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				시리얼 포트 제어 정보
			</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsComPortBlock()) {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isComPortBlock" checked="checked" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isComPortBlock" disabled="disabled"><i></i> 미적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isComPortBlock', '<%= data.getComPortBlockCode() %>');" ><i class="fa fa-search"></i> 상세정책보기</a> 
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isComPortBlock" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isComPortBlock" checked="checked" disabled="disabled"><i></i> 미적용
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				네트워크 포트 제어 정보
			</td>
			<td>
				<div class="inline-group">
					<% if (data.getIsNetPortBlock()) {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetPortBlock" checked="checked" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetPortBlock" disabled="disabled"><i></i> 미적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isNetPortBlock', '<%= data.getNetPortBlockCode() %>');" ><i class="fa fa-search"></i> 상세정책보기</a> 
						</label>
					<% } else {%>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetPortBlock" disabled="disabled"><i></i> 적용
						</label>
						<label class="radio nomargin-top nomargin-bottom">
							<input type="radio" name="isNetPortBlock" checked="checked" disabled="disabled"><i></i> 미적용
						</label>
					<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				프로세스 차단 정보
			</td>
			<td>
				<div class="inline-group">
				<% if (data.getIsProcessList()) {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isProcessList" checked="checked" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isProcessList" disabled="disabled"><i></i> 미적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
							<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isProcessList', '<%= data.getProcessListCode() %>');" ><i class="fa fa-search"></i> 상세정책보기</a> 
						</label>
				<% } else {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isProcessList" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isProcessList" checked="checked" disabled="disabled"><i></i> 미적용
					</label>
				<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				민감패턴 제어 정보
			</td>
			<td>
				<div class="inline-group">
				 <% if (data.getIsFilePattern()) {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isFilePattern" checked="checked" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isFilePattern" disabled="disabled"><i></i> 미적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isFilePattern', '<%= data.getFilePatternCode() %>');" ><i class="fa fa-search"></i> 상세정책보기</a> 
					</label>
				<% } else {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isFilePattern" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isFilePattern" checked="checked" disabled="disabled"><i></i> 미적용
					</label>
				<% } %>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				사이트 차단 정보
			</td>
			<td>
				<div class="inline-group">
				<% if (data.getIsWebAddr()) {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isWebAddr" checked="checked" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isWebAddr" disabled="disabled"><i></i> 미적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<a href="#" onClick="javascript:fn_sel_policy_detailOpen('isWebAddr', '<%= data.getWebAddrCode() %>');"><i class="fa fa-search"></i> 상세정책보기</a> 
					</label>
				<% } else {%>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isWebAddr" disabled="disabled"><i></i> 적용
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="isWebAddr" checked="checked" disabled="disabled"><i></i> 미적용
					</label>
				<% } %>
				</div>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<div id="policy_detail_div"></div>

<script type="text/javascript">
	function fn_sel_policy_detailOpen(group, key_code) {
		$.ajax({      
	        type:"POST",  
	        url:'/ax/main/policy/modal',
	        async: false,
	        data:{
	        	group : group,
	        	key_code : key_code,
	        	_ : $.now()
	        },
	        success:function(args){
	            $("#policy_detail_div").html(args);
	            $('#modalPolicyDetail').modal('show');
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}
</script>


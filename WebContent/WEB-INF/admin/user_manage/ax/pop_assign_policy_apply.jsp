<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	List<HashMap<String, Object>> apply_list = (List<HashMap<String, Object>>)request.getAttribute("apply_list");
	boolean onlyFlag = Boolean.parseBoolean(request.getAttribute("only_flag").toString());
	HashMap<String, Object> data = (HashMap<String, Object>)request.getAttribute("current_policy");
%>
<script type="text/javascript" src="${context}/assets/js/admin_function.js"></script>
<script type="text/javascript" src="${context}/assets/js/date.js"></script>
<div id="modalApplyPolicy" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 5%;">
	<div class="modal-dialog" style="width:940px;">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="myModalLabel">정책 항목 수정</h4>
				</div>
				<!-- /Modal Header -->
				
				<!-- Modal body -->
				<div class="modal-body">
					<div id="content" class="dashboard padding-20">
						<div class="row">
							
							<div class="col-md-12">
								<div id="panel-2" class="panel panel-default">
							
									<div class="panel-heading">
										<span class="title elipsis">
											<strong>개인정보정책 정보</strong> <!-- panel title -->
										</span>
									</div>
		
									<!-- panel content -->
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12" style="overflow: hidden;">
												<div class="aside-tabs">
													<ul class="nav nav-tabs nav-top-border">
														<li class="active"><a href="#basics" data-toggle="tab"><i class="fa fa-lock" title="기본정책"></i>기본</a></li>
														<li><a href="#usb" data-toggle="tab"><i class="fa fa-usb" title="USB 차단정책"></i>USB</a></li>
														<li><a href="#serial" data-toggle="tab"><i class="fa fa-plug" title="시리얼 포트 차단정책"></i>시리얼</a></li>
														<li><a href="#network" data-toggle="tab"><i class="fa fa-sitemap" title="네트워크 포트 차단정책"></i>네트워크</a></li>
														<li><a href="#program" data-toggle="tab"><i class="fa fa-desktop" title="프로세스 차단정책"></i>프로세스</a></li>
														<li><a href="#pattern" data-toggle="tab"><i class="fa fa-clone" title="민감패턴 차단정책"></i>민감패턴</a></li>
														<li><a href="#website" data-toggle="tab"><i class="fa fa-internet-explorer" title="사이트 차단정책"></i>사이트</a></li>
														<li><a href="#msg" data-toggle="tab"><i class="fa fa-commenting" title="메신저 차단정책"></i>메신저</a></li>
														<li><a href="#water" data-toggle="tab"><i class="fa fa-tint" title="워터마크 정책"></i>워터마크</a></li>
													</ul>
													
													<div class="tab-content">
														<!-- 기본정책 -->
														<div id="basics" class="tab-pane fade in active">
															<table class="table table-bordered">
																<tbody>
																	<% if (onlyFlag){ %>
																		<tr>
																			<td class="th-cell-gray" width="300px;">에이전트 삭제 가능 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isUninstall_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isUninstall"))){ %> checked <%}%> /></td>
																		</tr>	
																		<tr>
																			<td class="th-cell-gray">파일 암호화 사용</td>
																			<td><input type="checkbox" value="1" id="chk_isFileEncryption_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isFileEncryption"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD 암호화 사용</td>
																			<td><input type="checkbox" value="1" id="chk_isCdEncryption_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdEncryption"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">프린터 사용 가능 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isPrint_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isPrint"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD 사용 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isCdEnabled_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdEnabled"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD 반출 가능 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isCdExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdExport"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">무선랜 사용 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isWlan_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isWlan"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">공유폴더 사용 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isNetShare_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isNetShare"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">메일반출 사용 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isWebExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isWebExport"))){ %> checked <%}%> /></td>
																		</tr>
																																				
																		<tr>
																			<td class="th-cell-gray">보호폴더 접근 가능 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isSensitiveDir_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isSensitiveDirEnabled"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">민감파일 접근시 삭제 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isSensitiveFileAccess_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isSensitiveFileAccess"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">디스크 반출 가능 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isStorageExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isStorageExport"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">디스크 관리자 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isStorageAdmin_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isStorageAdmin"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">USB통제 기능 사용 여부</td>
																			<td><input type="checkbox" value="1" id="chk_isUsbControl_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isUsbControlEnabled"))){ %> checked <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">검출된 패턴파일 삭제 여부</td>
																			<td><input type="checkbox" value="1" id="chk_patternFileControl_item" name="chk_policy_item" <% if (Integer.parseInt(data.get("patternFileControl").toString()) == 1){ %> checked <%}%> /></td>
																		</tr>
																																				
																		<tr>
																			<td class="th-cell-gray">프린터 인쇄 로그</td>
																			<td>
																				<% int printLogDesc = Integer.parseInt(data.get("printLogDesc").toString()); %>
																				<input type="radio" value="0" id="radio_printLogDesc_item" name="radio_printLogDesc_item" <% if (printLogDesc == 0){ %> checked <%}%> />로그전송안함
																				<input type="radio" value="1" id="radio_printLogDesc_item" name="radio_printLogDesc_item" <% if (printLogDesc == 1){ %> checked <%}%> />이벤트로그
																				<input type="radio" value="2" id="radio_printLogDesc_item" name="radio_printLogDesc_item" <% if (printLogDesc == 2){ %> checked <%}%> />파일원본로그
																			</td>
																		</tr>
																 	<%} else {%>
																 		<tr>
																			<td class="th-cell-gray" width="300px;">에이전트 삭제 가능 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isUninstall_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isUninstall"))){ %> indeterminate <%}%> /></td>
																		</tr>	
																		<tr>
																			<td class="th-cell-gray">파일실시간 암호화</td>
																			<td><input type="checkbox" class="tristate" id="chk_isFileEncryption_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isFileEncryption"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD실시간 암호화</td>
																			<td><input type="checkbox" class="tristate" id="chk_isCdEncryption_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdEncryption"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">프린터 사용 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isPrint_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isPrint"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD 사용 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isCdEnabled_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdEnabled"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">CD 반출 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isCdExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isCdExport"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">무선랜 사용 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isWlan_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isWlan"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">공유폴더 사용 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isNetShare_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isNetShare"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">메일 반출 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isWebExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isWebExport"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		
																		<tr>
																			<td class="th-cell-gray">보호폴더 접근 가능 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isSensitiveDir_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isSensitiveDirEnabled"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">민감파일 접근시 삭제 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isSensitiveFileAccess_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isSensitiveFileAccess"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">디스크 반출 가능 여부</td>
																			<td><input type="checkbox"class="tristate" id="chk_isStorageExport_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isStorageExport"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">디스크 관리자 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isStorageAdmin_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isStorageAdmin"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">USB통제 기능 사용 여부</td>
																			<td><input type="checkbox" class="tristate" id="chk_isUsbControl_item" name="chk_policy_item" <% if (Boolean.TRUE.equals(data.get("isUsbControlEnabled"))){ %> indeterminate <%}%> /></td>
																		</tr>
																		<tr>
																			<td class="th-cell-gray">검출된 패턴파일 삭제 여부</td>	
																			<td><input type="checkbox" class="tristate" id="chk_patternFileControl_item" name="chk_policy_item" <% if (Integer.parseInt(data.get("patternFileControl").toString()) == 1){ %> indeterminate <%}%> /></td>
																		</tr>
																		
																		<tr>
																			<td class="th-cell-gray">프린터 인쇄 로그</td>
																			<td>
																				<input type="radio" value="0" id="radio_printLogDesc_item" name="radio_printLogDesc_item" />로그전송안함
																				<input type="radio" value="1" id="radio_printLogDesc_item" name="radio_printLogDesc_item" />이벤트로그
																				<input type="radio" value="2" id="radio_printLogDesc_item" name="radio_printLogDesc_item" />파일원본로그
																			</td>
																		</tr>
																 	<%}%>	
																</tbody>
															</table>
														</div>
														
														<!-- USB 차단정책 -->
														<div id="usb" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_usb.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isUsbBlock" value="<%= data.get(\"isUsbBlock\")%>"/>
   																<jsp:param name="usbBlockCode" value="<%= data.get(\"usbBlockCode\").toString() %>"/>
															</jsp:include>
														</div>
														
														<!-- 시리얼 포트 차단정책 -->
														<div id="serial" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_serial.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isComPortBlock" value="<%= data.get(\"isComPortBlock\")%>"/>
   																<jsp:param name="comPortBlockCode" value="<%= data.get(\"comPortBlockCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 네트워크 포트 차단정책 -->
														<div id="network" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_network.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isNetPortBlock" value="<%= data.get(\"isNetPortBlock\")%>"/>
   																<jsp:param name="netPortBlockCode" value="<%= data.get(\"netPortBlockCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 프로그램 차단정책 -->
														<div id="program" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_process.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isProcessList" value="<%= data.get(\"isProcessList\")%>"/>
   																<jsp:param name="processListCode" value="<%= data.get(\"processListCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 민감 패턴 차단정책 -->
														<div id="pattern" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_pattern.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isFilePattern" value="<%= data.get(\"isFilePattern\")%>"/>
   																<jsp:param name="filePatternCode" value="<%= data.get(\"filePatternCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 사이트 차단정책 -->
														<div id="website" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_website.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isWebAddr" value="<%= data.get(\"isWebAddr\")%>"/>
   																<jsp:param name="webAddrCode" value="<%= data.get(\"webAddrCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 메신저 차단정책 -->
														<div id="msg" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_msg.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isMsgBlock" value="<%= data.get(\"isMsgBlock\")%>"/>
   																<jsp:param name="msgBlockCode" value="<%= data.get(\"msgBlockCode\").toString() %>"/>
   															</jsp:include>
														</div>
														
														<!-- 워터마크 정책 -->
														<div id="water" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_water.jsp" flush="false" >
																<jsp:param name="onlyFlag" value="<%= onlyFlag%>"/>
   																<jsp:param name="isWaterMark" value="<%= data.get(\"isWaterMark\")%>"/>
   																<jsp:param name="waterMarkType" value="<%= data.get(\"waterMarkType\").toString() %>"/>
   																<jsp:param name="waterMarkEndDate" value="<%= data.get(\"waterMarkEndDate\").toString() %>"/>
   															</jsp:include>
														</div>
														
													</div>
												</div>	
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
				<!-- /Modal body -->

				<!-- Modal Footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="fn_policy_apply_save();" ><i class="fa fa-check"></i> 정책적용</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- 	<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>
 --%>
<%-- 	<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.jqueryui.min.js"></script>
 --%>
<%-- 	<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.print.min.js"></script>
	<script type="text/javascript" src="${context}/assets/plugins/datatables/extensions/Buttons/js/buttons.html5.min.js"></script>

	<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${context}/assets/plugins/datatables/media/js/dataTables.bootstrap.min.js"></script>
 --%>	<script type="text/javascript" src="${context}/assets/plugins/jquery.tristate.js"></script>

<script type="text/javascript">
	var oriData = new Object();
	
	$(document).ready(function(){
		initData();
				
		usb_policy_table();
		com_port_table();
		net_port_table();
		process_table();
		pattern_table();
		web_site_table();
		msg_block_table();
		
		$('.tristate').tristate({
			checked: 1,
	        unchecked: 0,
	        indeterminate: -1,
		});
	});
	
	function initData() {
		var flag = '<%= onlyFlag %>';
		oriData = getPolicyApplyData(flag);
	}

	function fn_policy_apply_save() {
		var flag = '<%= onlyFlag %>';
		var policy_data = getPolicyApplyData(flag);
		var apply_list = getApplyPolicyUserData();
		policy_data['apply_list'] = apply_list;
		if(!isChangeValueCheck(policy_data)) {
			vex.defaultOptions.className = 'vex-theme-os'
				
			vex.dialog.open({
				message: '변경된 정책이 없습니다. 적용할 정책을 선택 후 등록해주세요.',
				  buttons: [
				    $.extend({}, vex.dialog.buttons.YES, {
				      text: '확인'
				  })]
			})
			return false;
		}
		
		if(!isValid(policy_data)){
			return false;
		}

		if(apply_list.length > 1) {
			vex.defaultOptions.className = 'vex-theme-os'
				
			vex.dialog.open({
				message: '주의! 한명 이상에게 정책 적용 시 변경 된 정책만 적용되오니 유의하시기 바랍니다. 선택 된 정책을 적용하시겠습니까?',
				  buttons: [
				    $.extend({}, vex.dialog.buttons.YES, {
				      text: '확인'
				  }),
				  $.extend({}, vex.dialog.buttons.NO, {
				      text: '취소'
				  })],
				  callback: function(data) {
			 	  	if (data) {
			 	  		saveData(policy_data);
			 	    } else {
			 	    	$('#modalApplyPolicy').modal('hide');
			 	  		return false;
			 	    }
			 	  }
			})
		} else {
			saveData(policy_data);
		}
	}
	
	function saveData (policy_data) {
		
		$.ajax({      
		    type:"POST",  
		    url:'${context}/admin/user/apply/save',
		    async: false,
		    data:{
		    	apply_policy : JSON.stringify(policy_data),
		    	_ : $.now()
		    },
		    success:function(data){
		    	if(data.returnCode == "S") {
		    		vex.defaultOptions.className = 'vex-theme-os'
		    			
	    			vex.dialog.open({
	    				message: '정책이 적용 되었습니다.',
	    				  buttons: [
	    				    $.extend({}, vex.dialog.buttons.YES, {
	    				      text: '확인'
	    				  })],
	    				  callback: function(data) {
    				 	  	if (data) {
    				 	  		top.location.reload();
    				 	  		$('#modalApplyPolicy').modal('hide');
    				 	    }
    				 	  }
	    				  
	    			})
		    		
		    	} else {
		    		vex.defaultOptions.className = 'vex-theme-os'
		    			
	    			vex.dialog.open({
	    				message: '정책이 적용에 실패했습니다.',
	    				  buttons: [
	    				    $.extend({}, vex.dialog.buttons.YES, {
	    				      text: '확인'
	    				  })],
	    				  callback: function(data) {
    				 	  	if (data) {
    				 	  		top.location.reload();
    				 	  		$('#modalApplyPolicy').modal('hide');
    				 	    }
    				 	  }
	    				  
	    			})
		    	}
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
		
	}
	
	function getApplyPolicyUserData(){
		var arr = new Array();
		
		<% for(int i = 0; i < apply_list.size(); i++) { %>
			var map = new Object();
			map['agent_no'] = <%= apply_list.get(i).get("agentNo") %>
			map['user_no'] = <%= apply_list.get(i).get("uno") %>
			map['policy_no'] = <%= apply_list.get(i).get("policyNo") %>
			map['user_id'] = '<%= apply_list.get(i).get("userId") %>'
			
			arr.push(map);
		<% } %>
		
		console.log(arr);
		
		return arr;
	}
	
	function isChangeValueCheck(change_policy_data) {
		var cnt = 0;
		
		if( oriData.isUninstall != change_policy_data.isUninstall ){ cnt++; }
		if( oriData.isFileEncryption != change_policy_data.isFileEncryption	){ cnt++; }
		if( oriData.isCdEncryption != change_policy_data.isCdEncryption ){ cnt++; }
		if( oriData.isPrint != change_policy_data.isPrint ){ cnt++; }
		if( oriData.isCdEnabled	!= change_policy_data.isCdEnabled ){ cnt++; }
		if( oriData.isCdExport != change_policy_data.isCdExport ){ cnt++; }
		if( oriData.isWlan != change_policy_data.isWlan ){ cnt++; }
		if( oriData.isNetShare != change_policy_data.isNetShare ){ cnt++; }
		if( oriData.isWebExport != change_policy_data.isWebExport ){ cnt++; }
		
		if( oriData.isSensitiveDirEnabled != change_policy_data.isSensitiveDirEnabled ){ cnt++; }
		if( oriData.isSensitiveFileAccess != change_policy_data.isSensitiveFileAccess ){ cnt++; }
		if( oriData.isStorageExport != change_policy_data.isStorageExport ){ cnt++; }
		if( oriData.isStorageAdmin != change_policy_data.isStorageAdmin ){ cnt++; }
		if( oriData.isUsbControlEnabled != change_policy_data.isUsbControlEnabled ){ cnt++; }
		if( oriData.patternFileControl != change_policy_data.patternFileControl ){ cnt++; }
		
		if( oriData.printLogDesc != change_policy_data.printLogDesc ){ cnt++; }
		if( oriData.isUsbBlock != change_policy_data.isUsbBlock ){ cnt++; }
		if( oriData.isComPortBlock != change_policy_data.isComPortBlock	){ cnt++; }
		if( oriData.isNetPortBlock != change_policy_data.isNetPortBlock ){ cnt++; }
		if( oriData.isProcessList != change_policy_data.isProcessList ){ cnt++; }
		if( oriData.isProcessCheck != change_policy_data.isProcessCheck ){ cnt++; }
		if( oriData.isFilePattern != change_policy_data.isFilePattern ){ cnt++; }
		if( oriData.isPatternCheck != change_policy_data.isPatternCheck ){ cnt++; }
		if( oriData.isWebAddr != change_policy_data.isWebAddr ){ cnt++; }
		if( oriData.isMsgBlock != change_policy_data.isMsgBlock ){ cnt++; }
		if( oriData.isWaterMarkPrint != change_policy_data.isWaterMarkPrint ){ cnt++; }
		if( oriData.waterMark != change_policy_data.waterMark ){ cnt++; }
			
		return cnt == 0 ? false : true;
	}
	
	function isValid(policy_data) {
		vex.defaultOptions.className = 'vex-theme-os'
		
		if(policy_data.isWaterMarkPrint != -1 && policy_data.waterMark != '' && policy_data.waterPolicyValue != 'N') {
			if(parseInt(policy_data.waterLimitCheck) == 0) {
				vex.dialog.open({
					message: '워터마크 정책 출력 허용 시 기한 제한 선택은 필수 입니다. 확인해주세요.',
					  buttons: [
					    $.extend({}, vex.dialog.buttons.YES, {
					      text: '확인'
					  	})
					  ]
				})
				return false;
			}
			
			if (policy_data.waterLimitValue == 'Y') {
				if(policy_data.waterMarkDate == '' || policy_data.waterMarkLimitTime == '') {
					vex.dialog.open({
						message: '워터마크 정책 기한 제한시 시간 및 제한 기간 입력은 필수 입니다. 확인해주세요.',
						  buttons: [
						    $.extend({}, vex.dialog.buttons.YES, {
						      text: '확인'
						  	})
						  ]
					})
					return false;
				}
				
				var today = new Date().format("yyyyMMdd");
				var waterDate = policy_data.waterMarkDate.replace(/-/g, '');
				if(waterDate < today) {
					vex.dialog.open({
						message: '워터마크 정책 기한 제한 기준 일자가 오늘 이전 기간 입니다. 확인해주세요.',
						  buttons: [
						    $.extend({}, vex.dialog.buttons.YES, {
						      text: '확인'
						  	})
						  ]
					})
					return false;
				}
			}
		}
		
		return true;
	}
	
	
</script>

































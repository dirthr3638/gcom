<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	List<HashMap<String, Object>> apply_list = (List<HashMap<String, Object>>)request.getAttribute("list");
	boolean onlyFlag = apply_list.size() == 1 ? true : false;
	HashMap<String, Object> data = apply_list.get(0);
%>
<div id="modalApplyPolicy" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="margin-top: 10%;">
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
														<li><a href="#program" data-toggle="tab"><i class="fa fa-desktop" title="프로그램 차단정책"></i>프로그램</a></li>
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
																	<tr>
																		<td class="th-cell-gray" width="300px;">에이전트 삭제 가능 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isUninstall_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isUninstall"))){ %> checked <%}%> /></td>
																	</tr>	
																	<tr>
																		<td class="th-cell-gray">파일실시간 암호화</td>
																		<td><input type="checkbox" value="Y" id="chk_isFileEncryption_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isFileEncryption"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">CD실시간 암호화</td>
																		<td><input type="checkbox" value="Y" id="chk_isCdEncryption_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isCdEncryption"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">프린터 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isPrint_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isPrint"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">CD 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isCdEnabled_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isCdEnabled"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">CD 반출 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isCdExport_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isCdExport"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">무선랜 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isWlan_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isWlan"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">공유폴더 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isNetShare_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isNetShare"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">메일 반출 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isWebExport_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isWebExport"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">USB 장치 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isUsbBlock_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isUsbBlock"))){ %> checked <%}%> /> <a href="#" ><i class="fa fa-list" aria-hidden="true"></i> 장치선택</a></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">시리얼 포트 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isComPortBlock_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isComPortBlock"))){ %> checked <%}%> /></td>
																	</tr>	
																	<tr>
																		<td class="th-cell-gray">네트워크 포트 사용 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isNetPortBlock_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isNetPortBlock"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">프로그램 차단 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isProcessList_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isProcessList"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">민감패턴 차단 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isFilePattern_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isFilePattern"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">민감파일 접근 시 삭제</td>
																		<td><input type="checkbox" value="1" id="chk_patternFileControl_item" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("patternFileControl"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">사이트차단</td>
																		<td><input type="checkbox" value="1" id="chk_isWebAddr_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isWebAddr"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">메신저차단</td>
																		<td><input type="checkbox" value="1" id="chk_isMsgBlock_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isMsgBlock"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">인쇄 워터마크 출력 여부</td>
																		<td><input type="checkbox" value="Y" id="chk_isWaterMark_list" name="chk_policy_item" <% if (onlyFlag && Boolean.TRUE.equals(data.get("isWaterMark"))){ %> checked <%}%> /></td>
																	</tr>
																	<tr>
																		<td class="th-cell-gray">프린터 인쇄 로그</td>
																		<td>
																			<% if (onlyFlag){ 
																				int printLogDesc = Integer.parseInt(data.get("printLogDesc").toString());
																			%>  
																				<input type="radio" value="0" id="radio_printLogDesc_item" name="radio_policy_item" <% if (printLogDesc == 0){ %> checked <%}%> />로그전송안함
																				<input type="radio" value="1" id="radio_printLogDesc_item" name="radio_policy_item" <% if (printLogDesc == 1){ %> checked <%}%> />이벤트로그
																				<input type="radio" value="2" id="radio_printLogDesc_item" name="radio_policy_item" <% if (printLogDesc == 2){ %> checked <%}%> />파일원본로그
																			<% } else { %>
																				<input type="radio" value="0" id="radio_printLogDesc_item" name="radio_policy_item" checked/>로그전송안함
																				<input type="radio" value="1" id="radio_printLogDesc_item" name="radio_policy_item" />이벤트로그
																				<input type="radio" value="2" id="radio_printLogDesc_item" name="radio_policy_item" />파일원본로그
																			<% } %>
																		</td>
																	</tr>
																	
																</tbody>
															</table>
														</div>
														
														<!-- USB 차단정책 -->
														<div id="usb" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_usb.jsp" flush="false" />
														</div>
														
														<!-- 시리얼 포트 차단정책 -->
														<div id="serial" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_serial.jsp" flush="false" />
														</div>
														
														<!-- 네트워크 포트 차단정책 -->
														<div id="network" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_network.jsp" flush="false" />
														</div>
														
														<!-- 프로그램 차단정책 -->
														<div id="program" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_program.jsp" flush="false" />
														</div>
														
														<!-- 민감 패턴 차단정책 -->
														<div id="pattern" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_pattern.jsp" flush="false" />
														</div>
														
														<!-- 사이트 차단정책 -->
														<div id="website" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_website.jsp" flush="false" />
														</div>
														
														<!-- 메신저 차단정책 -->
														<div id="msg" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_msg.jsp" flush="false" />
														</div>
														
														<!-- 워터마크 정책 -->
														<div id="water" class="tab-pane fade">
															<jsp:include page="/WEB-INF/admin/user_manage/ax/assign_tab/assign_policy_apply_water.jsp" flush="false" />
														</div>
														
													</div>
												</div>	
											</div>
										</div>
										
										
										<div class="ld_modal hidden" >
										    <div class="ld_center" >
										        <img alt="" src="/assets/images/loaders/loading.gif" />
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

<script type="text/javascript">

	function fn_policy_apply_save() {
		var selPolicyData = getSelectPolicy();
		
		console.log(selPolicyData);
		/* 
		$.ajax({      
		    type:"POST",  
		    url:'/admin/user/assign/apply',
		    async: false,
		    data:{
		    	apply_list : JSON.stringify(apply_arr),
		    	_ : $.now()
		    },
		    success:function(data){
		    	$("#policy_apply_div").html(data);
	            $('#modalApplyPolicy').modal('show');
		    },   
		    error:function(e){  
		        console.log(e.responseText);  
		    }  
		});
		 */
	}
	
	function getSelectPolicy() {
		var map = new Object();
		
		map['isUninstall'] = $('#chk_isUninstall_item').is(':checked') == true ? 1 : 0 ;
		map['isPrint'] = $('#chk_isPrint_item').is(':checked') == true ? 1 : 0 ;
		map['isWaterMark'] = $('#chk_isWaterMark_item').is(':checked') == true ? 1 : 0 ;
		map['printLogDesc'] = $(':radio[name="radio_policy_item"]:checked').val();
		map['isUsbBlock'] = $('#chk_isUsbBlock_item').is(':checked') == true ? 'Y' : 'N' ;
		map['isComPortBlock'] = $('#chk_isComPortBlock_item').is(':checked') == true ? 'Y' : 'N' ;
		map['isWlan'] = $('#chk_isWlan_item').is(':checked') == true ? 1 : 0 ;
		map['isMailExport'] = $('#chk_isMailExport_item').is(':checked') == true ? 1 : 0 ;
		map['isFilePattern'] = $('#chk_isUninstall_item').is(':checked') == true ? 1 : 0 ;
		map['isProtect'] = $('#chk_isProtect_item').is(':checked') == true ? 1 : 0 ;
		map['isNetShare'] = $('#chk_isNetShare_item').is(':checked') == true ? 1 : 0 ;
		map['isCdEnabled'] = $('#chk_isCdEnabled_item').is(':checked') == true ? 1 : 0 ;
		map['isFileEncryption'] = $('#chk_isFileEncryption_item').is(':checked') == true ? 1 : 0 ;
		
		return map;
	}
</script>

































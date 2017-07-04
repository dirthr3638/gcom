<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="modalUserInfo" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="margin-top: 10%;">
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
													<table class="table table-bordered">
														<tbody>
															<tr>
																<td class="th-cell-gray" width="300px;">에이전트 삭제 가능 여부</td>
																<td><input type="checkbox" value="Y" id="chk_isUninstall_item" name="chk_policy_item" <% //if (//onlyFlag && Boolean.TRUE.equals(data.get("isUninstall"))){ %> checked <%//}%> /></td>
															</tr>	
															
														</tbody>
													</table>

												</div>	
											</div>
										</div>
										
										
										<div class="ld_modal hidden" >
										    <div class="ld_center" >
										        <img alt="" src="/assets/images/loaders/loading.gif" />
										    </div>
										</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				<!-- /Modal body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="fn_policy_apply_save();" ><i class="fa fa-check"></i> 정책적용</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">

	
</script>

































<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.Model.PolicyNetworkModel"%>
<%
	
	int keyNo = Integer.parseInt(request.getAttribute("keyNo").toString());
	boolean modifyCheck = keyNo != 0? true : false ;
	String popTitle = "정책정보등록";
	
	String netName ="";
	String port = "";
	String descriptor = "";
	
	if(modifyCheck) {
		popTitle = "정책정보수정";
		PolicyNetworkModel data = (PolicyNetworkModel)request.getAttribute("data");
		netName = data.getNetName();
		port = data.getPort();
		descriptor = data.getDescriptor();
	}
%>

<!-- Alert -->
<link href="/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
<link href="/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="/assets/plugins/vex/js/vex.min.js"></script>
<script type="text/javascript" src="/assets/plugins/vex/js/vex.combined.min.js"></script>
        
<div id="modalPolicyRegNetwork" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 5%;">
	<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
					<h4 class="modal-title" id="myModalLabel"><%= popTitle %></h4>
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
											<strong>정책정보설정</strong> <!-- panel title -->
										</span>
									</div>
		
									<!-- panel content -->
									<div class="panel-body">
										<table class="table table-bordered">
											<tbody>
												<tr>
													<td class="th-cell-gray center-cell" width="200px" style="vertical-align: middle;">네트워크 포트 이름</td>
													<td>
														<input type="text" id="att_net_work_name" name="att_net_work_name" class="form-control" value="<%= netName %>" />
													</td>
												</tr>
												<tr>
													<td class="th-cell-gray center-cell" style="vertical-align: middle;" >포트</td>
													<td>
														<input type="text" id="att_net_work_port" name="att_net_work_port" class="form-control" value="<%= port %>" />
													</td>
												</tr>
												<tr>
													<td class="th-cell-gray center-cell" style="vertical-align: middle;" >설명</td>
													<td>
														<input type="text" id="att_net_work_descript" name="att_net_work_descript" class="form-control" value="<%= descriptor %>" />
													</td>
												</tr>
											</tbody>
										</table>
										
										<input type="hidden" id="key" name="key" value="<%= keyNo %>" />

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
					<% if (modifyCheck) { %>
						<button type="button" id="btnPolicyNetworkModify" class="btn btn-primary" onclick="return false;" ><i class="fa fa-check"></i> 수정</button>
					<% } else { %>
						<button type="button" id="btnPolicyNetworkSave" class="btn btn-primary" onclick="return false;" ><i class="fa fa-check"></i> 등록</button>
					<% } %>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
													

<script type="text/javascript">

function get_policy_network_setting_data(){
	
	var map = new Object();
	
	map['net_no'] = $('#key').val();
	map['net_name'] = $('#att_net_work_name').val();
	map['net_port'] = $('#att_net_work_port').val();
	map['descript'] = $('#att_net_work_descript').val();
	
	return map;
}

function fn_policy_network_save () {
	
	var data = get_policy_network_setting_data();
	
	$.ajax({      
	    type:"POST",  
	    url:'/admin/policy/network/save',
	    async: false,
	    data:{
	    	data : JSON.stringify(data),
	    	_ : $.now()
	    },
	    success:function(data){
	    	vex.defaultOptions.className = 'vex-theme-os';
	    	
	    	if (data.returnCode == 'S') {
	    		vex.dialog.open({
	    			message: '정책 등록이 완료되었습니다.',
	    			  buttons: [
	    			    $.extend({}, vex.dialog.buttons.YES, {
	    			      text: '확인'
	    			  })],
	    			  callback: function(data) {
  				 	  	if (data) {
  				 	  		$('#modalPolicyRegNetwork').modal('hide');
  				 	  		location.href = '/admin/policy/export';
  				 	    }
  				 	  }
	    				
	    		})
	    		
	    	} else {
	    		
    			vex.dialog.open({
    				message: '정책 등록중 예기치 못한 오류가 발생하여 등록에 실패하였습니다.',
    				  buttons: [
    				    $.extend({}, vex.dialog.buttons.YES, {
    				      text: '확인'
    				  })],
    				  callback: function(data) {
   				 	  	if (data) {
   				 	  		$('#modalPolicyRegNetwork').modal('hide');
   				 	    }
   				 	  }
    			});
	    	}
	    },   
	    error:function(e){
	    	alert("서버와의 연결이 되지 않습니다.");
	        console.log(e.responseText);  
	    }  
	});
}


function fn_policy_network_modify () {
	
	var data = get_policy_network_setting_data();
	
	$.ajax({      
	    type:"POST",  
	    url:'/admin/policy/network/modify',
	    async: false,
	    data:{
	    	data : JSON.stringify(data),
	    	_ : $.now()
	    },
	    success:function(data){
	    	vex.defaultOptions.className = 'vex-theme-os';
	    	
	    	if (data.returnCode == 'S') {
	    		vex.dialog.open({
	    			message: '정책 수정이 완료되었습니다.',
	    			  buttons: [
	    			    $.extend({}, vex.dialog.buttons.YES, {
	    			      text: '확인'
	    			  })],
	    			  callback: function(data) {
  				 	  	if (data) {
  				 	  		$('#modalPolicyRegNetwork').modal('hide');
  				 	  		location.href = '/admin/policy/export';
  				 	    }
  				 	  }
	    				
	    		})
	    		
	    	} else {
	    		
    			vex.dialog.open({
    				message: '정책 수정중 예기치 못한 오류가 발생하여 등록에 실패하였습니다.',
    				  buttons: [
    				    $.extend({}, vex.dialog.buttons.YES, {
    				      text: '확인'
    				  })],
    				  callback: function(data) {
   				 	  	if (data) {
   				 	  		$('#modalPolicyRegNetwork').modal('hide');
   				 	    }
   				 	  }
    			});
	    	}
	    },   
	    error:function(e){
	    	alert("서버와의 연결이 되지 않습니다.");
	        console.log(e.responseText);  
	    }  
	});
}

$(document).ready(function(){
	jQuery('#preloader').hide();
	
	$('#btnPolicyNetworkSave').click(function(){
		fn_policy_network_save();				
	});
	
	$('#btnPolicyNetworkModify').click(function(){
		fn_policy_network_modify();				
	});
});
	
</script>








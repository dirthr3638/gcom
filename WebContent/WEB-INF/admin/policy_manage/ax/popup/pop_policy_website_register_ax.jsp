<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.Model.PolicyWebSiteBlocklModel"%>
<%
	
	int keyNo = Integer.parseInt(request.getAttribute("keyNo").toString());
	boolean modifyCheck = keyNo != 0? true : false ;
	String popTitle = "정책정보등록";
	
	String address ="";
	String description = "";
	
	if(modifyCheck) {
		popTitle = "정책정보수정";
		PolicyWebSiteBlocklModel data = (PolicyWebSiteBlocklModel)request.getAttribute("data");
		address = data.getAddress();
		description = data.getDescription();
	}
%>

<!-- Alert -->
<link href="/assets/plugins/vex/css/vex.css" rel="stylesheet" type="text/css"  />
<link href="/assets/plugins/vex/css/vex-theme-os.css" rel="stylesheet" type="text/css"  />

<script type="text/javascript" src="/assets/plugins/vex/js/vex.min.js"></script>
<script type="text/javascript" src="/assets/plugins/vex/js/vex.combined.min.js"></script>
        
<div id="modalPolicyRegWebsite" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top: 5%;">
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
													<td class="th-cell-gray center-cell" width="200px" style="vertical-align: middle;">사이트 주소</td>
													<td>
														<input type="text" id="att_website_name" name="att_website_name" class="form-control" value="<%= address %>" />
													</td>
												</tr>
												<tr>
													<td class="th-cell-gray center-cell" style="vertical-align: middle;" >설명</td>
													<td>
														<input type="text" id="att_website_descript" name="att_website_descript" class="form-control" value="<%= description %>" />
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
						<button type="button" id="btnPolicyWebsiteModify" class="btn btn-primary" onclick="return false;" ><i class="fa fa-check"></i> 수정</button>
					<% } else { %>
						<button type="button" id="btnPolicyWebsiteSave" class="btn btn-primary" onclick="return false;" ><i class="fa fa-check"></i> 등록</button>
					<% } %>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i> 닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
													

<script type="text/javascript">

function get_policy_website_setting_data(){
	
	var map = new Object();
	
	map['site_no'] = $('#key').val();
	map['site_address'] = $('#att_website_name').val();
	map['descript'] = $('#att_website_descript').val();
	
	return map;
}

function fn_policy_website_save () {
	
	var data = get_policy_website_setting_data();
	
	$.ajax({      
	    type:"POST",  
	    url:'/admin/policy/website/save',
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
  				 	  		$('#modalPolicyRegWebsite').modal('hide');
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
   				 	  		$('#modalPolicyRegWebsite').modal('hide');
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


function fn_policy_website_modify () {
	
	var data = get_policy_website_setting_data();
	
	$.ajax({      
	    type:"POST",  
	    url:'/admin/policy/website/modify',
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
  				 	  		$('#modalPolicyRegWebsite').modal('hide');
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
   				 	  		$('#modalPolicyRegWebsite').modal('hide');
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
	
	$('#btnPolicyWebsiteSave').click(function(){
		fn_policy_website_save();				
	});
	
	$('#btnPolicyWebsiteModify').click(function(){
		fn_policy_website_modify();				
	});
});
	
</script>








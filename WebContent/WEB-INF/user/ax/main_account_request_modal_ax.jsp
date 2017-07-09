<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

		<!-- WEB FONTS -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&amp;subset=latin,latin-ext,cyrillic,cyrillic-ext" rel="stylesheet" type="text/css" />

		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />


<div id="modalRequestDetail" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" style="margin-top: 10%;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="width:400px">

<div class="padding-15">

			<div class="login-box">

				<!--
				<div class="alert alert-danger">Complete all fields!</div>
				-->

				<!-- registration form -->
				<form action="index.html" method="post" class="sky-form boxed" novalidate="novalidate">
					<header><i class="fa fa-users"></i> 회원가입요청 <small class="note bold">관리자승인후가입됩니다</small></header>
					
					<fieldset>					
						<label class="input">
							<i class="icon-append fa fa-user"></i>
							<input type="text" placeholder="아이디를 입력하여주세요." id="user_id" name="user_id">
							<b class="tooltip tooltip-bottom-right">아이디를 입력하여주세요.</b>
						</label>

<!-- 						<label class="input">
							<i class="icon-append fa fa-envelope"></i>
							<input type="text" placeholder="사내 이메일주소를 입력하여주세요." id="user_mail" name="user_mail">
							<b class="tooltip tooltip-bottom-right">사내 이메일주소를 입력하여주세요.</b>
						</label>
					
 -->						<label class="input">
							<i class="icon-append fa fa-lock"></i>
							<input type="password" placeholder="비밀번호를 입력해주세요." id="user_password1" name="user_password1">
							<b class="tooltip tooltip-bottom-right">비밀번호를 입력해주세요.</b>
						</label>
					
						<label class="input">
							<i class="icon-append fa fa-lock"></i>
							<input type="password" placeholder="비밀번호를 재입력해주세요" id="user_password2" name="user_password2">
							<b class="tooltip tooltip-bottom-right">비밀번호를 재입력해주세요.</b>
						</label>
					</fieldset>
						
					<fieldset>
						<div class="row">
							<div class="col-md-12">
								<label class="input">
									<input type="text" placeholder="사번을 입력해주세요" id="user_number" name="user_number">
								</label>
							</div>
							<div class="col-md-12">
								<label class="input">
									<input type="text" placeholder="이름을 입력해주세요" id="user_name" name="user_name">
								</label>
							</div>
							<div class="col-md-12">
								<label class="input">
									<input type="text" placeholder="연락처를 - 를 포함하여 입력하여주세요." id="user_phone" name="user_phone">
								</label>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<label class="input">
									<input type="text" placeholder="직책을 입력해주세요" id="user_duty" name="user_duty">
								</label>
							</div>
							<div class="col-md-12">
								<label class="input">
									<input type="text" placeholder="계급을 입력해주세요" id="user_rank" name="user_rank">
								</label>
							</div>

						</div>

						<label class="nomargin"><a href="#" >소속부서를선택해주세요</a></label>
							<select class="form-control" id="user_dept" name="user_dept">
				                              
				                              
<c:choose>
	<c:when test="${fn:length(dept) > 0}">
		<c:forEach items="${dept}" var="item">
			<option value="${item.deptNo }">${item.shortName }</option>				
		</c:forEach>
	</c:when>
	<c:otherwise>
       <option value="-1">선택가능한 부서가 없습니다</option>
	</c:otherwise>
</c:choose>				                              
				                              
				            </select>

					</fieldset>

					<footer>
						<button type="button" onclick="return false;" id="btnRequest" class="btn btn-primary pull-right"><i class="fa fa-check"></i>가입요청</button>
					</footer>

				</form>
				<!-- /registration form -->

				<hr />

			</div>

		</div>

		</div>
	</div>
</div> 

<script>


$(document).ready(function(){
	$("#btnRequest").on("click" , function(e){

		fn_request_proc();

	});
});

function fn_request_proc() {
	var data = get_input_data()
	if(fn_request_input_valid(data)){
		//가입요청 보내기
		//
			
		$.ajax({      
	        type:"POST",  
	        url:'/account/request/do',
	        async: false,
	        data:data,
	        success:function(args){   
	        	if(args.returnCode == 'S'){
					alert('신청이 완료되었습니다.')
					$('#modalRequestDetail').modal('hide');
	        	}else{
					alert('회원 가입신청에 실패하였습니다. 관리자에게 문의해주세요')
					$('#modalRequestDetail').modal('hide');	        		
	        	}
	        	
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}

}


function get_input_data(){
	
	var data = new Object();
	data.user_id = $('#user_id').val()
	data.user_password1 = $('#user_password1').val()
	data.user_password2 = $('#user_password2').val()

	data.user_name = $('#user_name').val()
	data.user_number = $('#user_number').val()
	data.user_phone = $('#user_phone').val()
	
	data.user_duty = $('#user_duty').val()
	data.user_rank = $('#user_rank').val()
	data.user_dept = $('#user_dept option:selected').val()
	
	return data;
	
}

function fn_request_input_valid(data) {
	console.log(data);
	if (data.user_id.length < 1 || data.user_id == '' ) {
		alert("ID 입력은 필수 입니다. 확인해주세요.");
		return false;
	}
	if (data.user_number.length < 1 || data.user_number == '' ) {
		alert("사번 입력은 필수 입니다. 확인해주세요.");
		return false;
	}
	if (data.user_password1.length < 1 || data.user_password1 == '' ) {
		alert("패스워드 입력은 필수 입니다. 확인해주세요.");
		return false;
	} 
	
	if (data.user_password1 != data.user_password2 ) {
		alert("입력한 패스워드가 일치하지 않습니다.");
		return false;
	} 

	if (data.user_name.length < 1 || data.user_name == '' ) {
		alert("이름 입력은 필수 입니다. 확인해주세요.");
		return false;
	} 

	if (data.user_duty.length < 1 || data.user_duty == '' ) {
		alert("직책 입력은 필수 입니다. 확인해주세요.");
		return false;
	} 

	if (data.user_phone.length < 1 || data.user_phone == '' ) {
		alert("연락처 입력은 필수 입니다. 확인해주세요.");
		return false;
	} 

	if (data.user_rank.length < 1 || data.user_rank == '' ) {
		alert("계급 입력은 필수 입니다. 확인해주세요.");
		return false;
	} 

	if (data.user_dept < 0 ) {
		alert("부서선택이 유효하지 않습니다. 확인해주세요.");
		return false;
	} 

	
	return true;
}


function fn_login_callback(data){
	
	if(data.returnCode == "S"){
		location.href= data.goUrl;
	}else{
		switch (data.returnCode){
		  case "E":
			alert(data.message);
		    break;
		  case "NI":
			  alert("등록되지 않은 계정입니다. 계정 신청 후 사용하시기 바랍니다.");
		    break;
		  case "DI":
			alert("아이디 또는 암호를 확인해 주세요.");
		    break;
		  default:
		}
	}
	
}
</script>


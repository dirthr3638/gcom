<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<% 
	String user_id = (String)session.getAttribute("user_id");
	String admin_mode =  session.getAttribute("admin_mode").toString();
%>

<script>

function moveReport(){
	// 0 관리자, 1 콘솔, 2 레포트 -1 사용자
	if(<%= admin_mode %> == '0' ||  <%= admin_mode %> == '1'){
		location.href = '/dashboard';
	}else{
		alert('권한이 없습니다.')
	}
}

</script>
 
<header id="header">

				<!-- Mobile Button -->
				<button id="mobileMenuBtn"></button>

				<!-- Logo -->
				<span class="logo pull-left">
					<img src="/assets/images/logo_light.png" alt="admin panel" height="35" />
				</span>

				<nav>

					<!-- OPTIONS LIST -->
					<ul class="nav pull-right">

						<!-- USER OPTIONS -->
						<li class="dropdown pull-left">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
								<img class="user-avatar" src='/assets/images/EditProfile-512.png' alt="" height="34" /> 
								<span class="user-name">
									<span class="hidden-xs">
										<%= user_id %> <i class="fa fa-angle-down"></i>
									</span>
								</span>
							</a>
							<ul class="dropdown-menu hold-on-click">
								<li><!-- settings -->
									<a onclick="moveReport()"><i class="fa fa-th-large"></i>관리콘솔</a>
								</li>

								<li class="divider"></li>
								<li><!-- logout -->
									<a href="/logout"><i class="fa fa-power-off"></i>로그아웃</a>
								</li>
							</ul>
						</li>
						<!-- /USER OPTIONS -->

					</ul>
					<!-- /OPTIONS LIST -->

				</nav>

			</header>


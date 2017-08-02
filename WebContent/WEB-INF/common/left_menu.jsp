<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <% String menu_parent = request.getParameter("menu_parent"); %>
 --%>
 
 
<aside id="aside">
	<nav id="sideNav">
		<!-- MAIN MENU -->
		<ul class="nav nav-list">
			<li ${menu_parent == 1000 ? 'class="active"' : ''}><a href="${context}/dashboard"> <i
					class="main-icon fa fa-bar-chart"></i> <span>대시보드 </span>
			</a>

			<li ${menu_parent == 2000 ? 'class="active"' : ''}><a href="#"> <i
					class="main-icon fa fa-users"></i> <span>사용자관리 </span>
			</a>
				<ul>
					<li ${menu_sub_first == 2100 ? 'class="active"' : ''}><a href="${context}/admin/user/assign">정책할당</a></li>
					<li ${menu_sub_first == 2200 ? 'class="active"' : ''}><a href="${context}/admin/user/request">정책변경요청</a></li>
					<li ${menu_sub_first == 2700 ? 'class="active"' : ''}><a href="${context}/admin/user/enroll">회원등록요청</a></li>
					<li ${menu_sub_first == 2300 ? 'class="active"' : ''}><a href="${context}/admin/user/contact">문의사항</a></li>
					<li ${menu_sub_first == 2400 ? 'class="active"' : ''}><a href="${context}/admin/user/dept">부서관리</a></li>
					<li ${menu_sub_first == 2500 ? 'class="active"' : ''}><a href="${context}/admin/user/manage">사용자관리</a></li>
					<li ${menu_sub_first == 2600 ? 'class="active"' : ''}><a href="${context}/admin/user/notice">공지사항관리</a></li>
					
				</ul>


			<li ${menu_parent == 3000 ? 'class="active"' : ''}><a href="#">
					<i class="main-icon fa fa-th-large"></i> <span>정책관리</span>
			</a>
				<ul>
					<li ${menu_sub_first == 3100 ? 'class="active"' : ''}><a href="${context}/admin/policy/messenger">메신저감사</a></li>
					<li ${menu_sub_first == 3200 ? 'class="active"' : ''}><a href="${context}/admin/policy/process">프로세스차단</a></li>
					<li ${menu_sub_first == 3300 ? 'class="active"' : ''}><a href="${context}/admin/policy/pattern">민감정보</a></li>
					<li ${menu_sub_first == 3400 ? 'class="active"' : ''}><a href="${context}/admin/policy/network">네트워크포트</a></li>
					<li ${menu_sub_first == 3500 ? 'class="active"' : ''}><a href="${context}/admin/policy/serial">시리얼포트</a></li>
					<li ${menu_sub_first == 3600 ? 'class="active"' : ''}><a href="${context}/admin/policy/website">웹사이트차단</a></li>
				</ul>

			</li>
			<li ${menu_parent == 4000 ? 'class="active"' : ''}><a href="#"> <i
					class="main-icon fa fa-usb"></i> <span>디바이스관리</span>
			</a>
				<ul>
					<li ${menu_sub_first == 4100 ? 'class="active"' : ''}><a href="${context}/admin/device">USB 관리</a></li>
				</ul>
			</li>
			<li ${menu_parent == 5000 ? 'class="active"' : ''}><a href="${context}/admin/system/manage"> <i
					class="main-icon fa fa-gear"></i> <span>시스템관리</span>
			</a>

			</li>
			<li ${menu_parent == 6000 ? 'class="active"' : ''}><a href="${context}/admin/subadmin"> <i
					class="main-icon fa fa-vcard"></i> <span>관리자</span>
			</a>

			</li>
		</ul>
	</nav>

	<span id="asidebg">
		<!-- aside fixed background -->
	</span>
</aside>

<div id="preloader" >
	<div class="inner">
		<span class="loader"></span>
	</div>
</div> 

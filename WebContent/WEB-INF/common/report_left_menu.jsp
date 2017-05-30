<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <% String menu_parent = request.getParameter("menu_parent"); %>
 --%>
<aside id="aside">
	<nav id="sideNav">
		<!-- MAIN MENU -->
		<ul class="nav nav-list">
			<li ${menu_parent == 1000 ? 'class="active"' : ''}><a href="#"> <i
					class="main-icon fa fa-users"></i> <span>사용자정보 </span>
			</a>
				<ul>
					<li ${menu_sub_first == 1100 ? 'class="active"' : ''}><a href="/report/users">사용자/에이전트정보</a></li>
					<li ${menu_sub_first == 1200 ? 'class="active"' : ''}><a href="/report/login">로그인로그</a></li>
				</ul>


			<li ${menu_parent == 2000 ? 'class="active"' : ''}><a href="#">
					<i class="main-icon fa fa-usb"></i> <span>장치로그</span>
			</a>
				<ul>
					<li ${menu_sub_first == 2100 ? 'class="active"' : ''}><a href="/report/usbunauth">비인가 USB목록</a></li>
					<li ${menu_sub_first == 2200 ? 'class="active"' : ''}><a href="/report/usbtran">이동식디스크 파일전송로그</a></li>
					<li ${menu_sub_first == 2300 ? 'class="active"' : ''}><a href="/report/usbblock">USB차단로그</a></li>
					<li ${menu_sub_first == 2400 ? 'class="active"' : ''}><a href="/report/print">프린트로그</a></li>
				</ul>

			</li>
			<li ${menu_parent == 3000 ? 'class="active"' : ''}><a href="#"> <i
					class="main-icon fa fa-th-large"></i> <span>정책로그</span>
			</a>
				<ul>
					<li ${menu_sub_first == 3100 ? 'class="active"' : ''}><a href="/report/policy">정책로그</a></li>
					<li ${menu_sub_first == 3200 ? 'class="active"' : ''}><a href="/report/drain">정보유출로그</a></li>
					<li ${menu_sub_first == 3300 ? 'class="active"' : ''}><a href="/report/inspection">감사로그</a></li>
				</ul>
			</li>
		</ul>
	</nav>

	<span id="asidebg">
		<!-- aside fixed background -->
	</span>
</aside>
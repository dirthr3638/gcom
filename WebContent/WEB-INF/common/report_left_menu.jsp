<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<aside id="aside">
	<nav id="sideNav">
		<!-- MAIN MENU -->
		<ul class="nav nav-list">
			<li class="active"><a href="#"> <i
					class="main-icon fa fa-users"></i> <span>사용자정보</span>
			</a>

				<ul>
					<li><a href="/report/users">사용자/에이전트정보</a></li>
					<li><a href="/report/userlogin">로그인로그</a></li>
				</ul>


			<li><a href="#">
					<i class="main-icon fa fa-usb"></i> <span>장치로그</span>
			</a>
				<ul>
					<li><a href="/report/users">비인가 USB목록</a></li>
					<li><a href="/report/userlogin">이동식디스크 파일전송로그</a></li>
					<li><a href="/report/userlogin">USB차단로그</a></li>
					<li><a href="/report/userlogin">프린트로그</a></li>
				</ul>

			</li>
			<li><a href="#"> <i
					class="main-icon fa fa-th-large"></i> <span>정책로그</span>
			</a>
				<ul>
					<li><a href="/report/policy">정책로그</a></li>
					<li><a href="/report/outflow">정보유출로그</a></li>
					<li><a href="/report/inspection">감사로그</a></li>
				</ul>
			</li>
		</ul>
	</nav>

	<span id="asidebg">
		<!-- aside fixed background -->
	</span>
</aside>
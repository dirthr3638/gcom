<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

			<aside id="aside">
				<nav id="sideNav"><!-- MAIN MENU -->
					<ul class="nav nav-list">
						<li class="active"><!-- dashboard -->
							<a class="dashboard" href="/dashboard"><!-- warning - url used by default by ajax (if eneabled) -->
								<i class="main-icon fa fa-bar-chart-o"></i> <span>대시보드</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-users"></i> <span>사용자관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="/usergroup">부서/사용자관리</a></li>
								<li><a href="graphs-morris.html">에이전트관리</a></li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-th-large"></i> <span>정책설정</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="graphs-flot.html">개인정책</a></li>
								<li><a href="graphs-morris.html">시스템정책</a></li>
								<li><a href="graphs-inline.html">디바이스정책</a></li>
								<li><a href="graphs-chartjs.html">정책부여</a></li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-pencil-square-o"></i> <span>로그관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="tables-bootstrap.html">장치관리</a></li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-server"></i> <span>서버관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="form-elements.html">시스템정보</a></li>
							</ul>
						</li>
						<li>
							<a href="#" href="index.html">
								<i class="main-icon fa fa-upload"></i> <span>업데이트관리</span>
							</a>							
							
						</li>
						<li>
							<a href="#" href="index.html"> 
								<i class="main-icon fa fa-user-circle"></i> <span>서브관리자설정</span>
							</a>

						</li>
					</ul>
				</nav>

				<span id="asidebg"><!-- aside fixed background --></span>
			</aside>
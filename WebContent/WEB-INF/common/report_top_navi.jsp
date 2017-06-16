<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


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
								<img class="user-avatar" alt="" src="/assets/images/noavatar.jpg" height="34" /> 
								<span class="user-name">
									<span class="hidden-xs">
										Admin <i class="fa fa-angle-down"></i>
									</span>
								</span>
							</a>
							<ul class="dropdown-menu hold-on-click">
								<li><!-- settings -->
									<a href="/report/adminprofile"><i class="fa fa-th-large"></i>관리콘솔</a>
								</li>

								<li><!-- settings -->
									<a href="/report/adminprofile"><i class="fa fa-cogs"></i>정보변경</a>
								</li>

								<li class="divider"></li>
								<li><!-- logout -->
									<a href="page-login.html"><i class="fa fa-power-off"></i>로그아웃</a>
								</li>
							</ul>
						</li>
						<!-- /USER OPTIONS -->

					</ul>
					<!-- /OPTIONS LIST -->

				</nav>

			</header>


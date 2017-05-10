<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom Admin</title>
		<meta name="description" content="" />
		<meta name="Author" content="Dorin Grigoras [www.stepofweb.com]" />

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />

		<!-- WEB FONTS -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&amp;subset=latin,latin-ext,cyrillic,cyrillic-ext" rel="stylesheet" type="text/css" />

		<!-- CORE CSS -->
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />

	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<!-- 
				ASIDE 
				Keep it outside of #wrapper (responsive purpose)
			-->
			<aside id="aside">
				<nav id="sideNav"><!-- MAIN MENU -->
					<ul class="nav nav-list">
						<li class="active"><!-- dashboard -->
							<a class="dashboard" href="index.html"><!-- warning - url used by default by ajax (if eneabled) -->
								<i class="main-icon fa fa-dashboard"></i> <span>대시보드</span>
							</a>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-bar-chart-o"></i> <span>정책 설정</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="graphs-flot.html">123</a></li>
								<li><a href="graphs-morris.html">ㅇㄹㄴ</a></li>
								<li><a href="graphs-inline.html">ㅇ</a></li>
								<li><a href="graphs-chartjs.html">55</a></li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-table"></i> <span>로그 관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="tables-bootstrap.html">Bootstrap Tables</a></li>
								<li><a href="tables-jqgrid.html">jQuery Grid</a></li>
								<li><a href="tables-footable.html">jQuery Footable</a></li>
								<li>
									<a href="#">
										<i class="fa fa-menu-arrow pull-right"></i>
										Datatables
									</a>
									<ul>
										<li><a href="tables-datatable-managed.html">Managed Datatables</a></li>
										<li><a href="tables-datatable-editable.html">Editable Datatables</a></li>
										<li><a href="tables-datatable-advanced.html">Advanced Datatables</a></li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-pencil-square-o"></i> <span>서버 관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="form-elements.html">Smarty Elements</a></li>
								<li><a href="form-masked-inputs.html">Masked Inputs</a></li>
								<li><a href="form-pickers.html">Pickers</a></li>
								<li><a href="form-ui-sliders.html">UI Sliders</a></li>
								<li><a href="form-validation.html">Validation Form</a></li>
								<li><a href="form-html-editors.html">Html Editors</a></li>
								<li><a href="form-autosuggest.html">Autosuggest</a></li>
								<li><a href="form-x-editable.html">Form X-Editable</a></li>
								<li><a href="form-dropzone.html">Dropzone File Upload</a></li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-gears"></i> <span>업데이트 관리</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="ui-portlets.html">Portlets</a></li>
								<li><a href="ui-buttons.html">Buttons</a></li>
								<li>
									<a href="#">
										<i class="fa fa-menu-arrow pull-right"></i>
										Icons
									</a>
									<ul>
										<li><a href="ui-icons-fontawsome.html">Fontawsome</a></li>
										<li><a href="ui-icons-etline.html">Et-Line Icons</a></li>
										<li><a href="ui-icons-glyphicons.html">Glyph Icons</a></li>
										<li><a href="ui-icons-flags.html">Flags</a></li>
									</ul>
								</li>
								<li><a href="ui-alerts-dialogs.html">Alerts &amp; Dialogs</a></li>
								<li><a href="ui-tabs-acordion-navs.html">Tabs, Acordion &amp; Navs</a></li>
								<li><a href="ui-knob.html">Knob Circles</a></li>
								<li><a href="ui-nestable.html">Nestable List</a></li>
								<li><a href="ui-toastr.html">Toastr Notifications</a></li>
								<li><a href="ui-modals.html">Modals</a></li>
								<li><a href="ui-grid.html">Grid</a></li>
								<li><a href="ui-google-maps.html">Google Maps</a></li>
								<li><a href="ui-vector-maps.html">Vector Maps</a></li>
								<li><a href="ui-essentials.html">Essentials</a></li>
								<li>
									<a href="#">
										<i class="fa fa-menu-arrow pull-right"></i>
										<i class="fa fa-folder-open"></i>
										Deep Navigation
									</a>
									<!-- 3rd Level -->
									<ul>
										<li>
											<a href="#">
												3rd Level
											</a>
										</li>
										<li>
											<a href="#">
												<i class="fa fa-menu-arrow pull-right"></i>
												<i class="fa fa-folder-open"></i>
												4rd Level
											</a>
											<!-- 4th Level -->
											<ul>
												<li>
													<a href="#">
														4th Level
													</a>
												</li>
												<li>
													<a href="#">
														<i class="fa fa-menu-arrow pull-right"></i>
														<i class="fa fa-folder-open"></i>
														5th Level
													</a>
													<!-- 5th Level -->
													<ul>
														<li>
															<a href="#">
																5th level
															</a>
														</li>
														<li>
															<a href="#">
																<i class="fa fa-menu-arrow pull-right"></i>
																<i class="fa fa-folder-open"></i>
																6th level
															</a>
															<!-- 6th Level -->
															<ul>
																<li>
																	<a href="#">
																		6th level
																	</a>
																</li>
																<li>
																	<a href="#">
																		6th level
																	</a>
																</li>
															</ul><!-- /6th Level -->
														</li>
													</ul><!-- /5th Level -->
												</li>
											</ul><!-- /4th Level -->
										</li>
									</ul><!-- /3rd Level -->
								</li>
							</ul>
						</li>
						<li>
							<a href="#">
								<i class="fa fa-menu-arrow pull-right"></i>
								<i class="main-icon fa fa-book"></i> <span>서브관리자 설정</span>
							</a>
							<ul><!-- submenus -->
								<li><a href="page-invoice.html">Invoice</a></li>
								<li><a href="page-login.html">Login</a></li>
								<li><a href="page-register.html">Register</a></li>
								<li><a href="page-lock.html">Lock Screen</a></li>
								<li><a href="page-forum.html">Forum</a></li>
								<li><a href="page-404.html">Error 404</a></li>
								<li><a href="page-500.html">Error 500</a></li>
								<li><a href="page-pricing.html">Pricing Table</a></li>
								<li><a href="page-search.html">Search Result</a></li>
								<li><a href="page-sidebar.html">Sidebar Page</a></li>
								<li><a href="page-user-profile.html">User Profile</a></li>
								<li><a href="page-blank-1.html">Blank Page</a></li>
							</ul>
						</li>
					</ul>
				</nav>

				<span id="asidebg"><!-- aside fixed background --></span>
			</aside>
			<!-- /ASIDE -->


			<!-- HEADER -->
			<header id="header">

				<!-- Mobile Button -->
				<button id="mobileMenuBtn"></button>

				<!-- Logo -->
				<span class="logo pull-left">
					<img src="assets/images/logo_light.png" alt="admin panel" height="35" />
				</span>

				<nav>

					<!-- OPTIONS LIST -->
					<ul class="nav pull-right">

						<!-- USER OPTIONS -->
						<li class="dropdown pull-left">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
								<img class="user-avatar" alt="" src="assets/images/noavatar.jpg" height="34" /> 
								<span class="user-name">
									<span class="hidden-xs">
										John Doe <i class="fa fa-angle-down"></i>
									</span>
								</span>
							</a>
							<ul class="dropdown-menu hold-on-click">
								<li><!-- my calendar -->
									<a href="calendar.html"><i class="fa fa-calendar"></i> Calendar</a>
								</li>
								<li><!-- my inbox -->
									<a href="#"><i class="fa fa-envelope"></i> Inbox
										<span class="pull-right label label-default">0</span>
									</a>
								</li>
								<li><!-- settings -->
									<a href="page-user-profile.html"><i class="fa fa-cogs"></i> Settings</a>
								</li>

								<li class="divider"></li>

								<li><!-- lockscreen -->
									<a href="page-lock.html"><i class="fa fa-lock"></i> Lock Screen</a>
								</li>
								<li><!-- logout -->
									<a href="page-login.html"><i class="fa fa-power-off"></i> Log Out</a>
								</li>
							</ul>
						</li>
						<!-- /USER OPTIONS -->

					</ul>
					<!-- /OPTIONS LIST -->

				</nav>

			</header>
			<!-- /HEADER -->
		</div>
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = 'assets/plugins/';</script>
		<script type="text/javascript" src="assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="assets/js/app.js"></script>
		
	</body>
</html>
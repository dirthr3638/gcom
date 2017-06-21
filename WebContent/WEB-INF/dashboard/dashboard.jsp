<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom Admin</title>

		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />

		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />

	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">


			<% request.setAttribute("menu_parent", 1000); %> 
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			
			<section id="middle">
				<div id="content" class="dashboard padding-20">

					<!-- 
						PANEL CLASSES:
							panel-default
							panel-danger
							panel-warning
							panel-info
							panel-success

						INFO: 	panel collapse - stored on user localStorage (handled by app.js _panels() function).
								All pannels should have an unique ID or the panel collapse status will not be stored!
					-->
					<div id="panel-1" class="panel panel-default">
						<div class="panel-heading">
							<span class="title elipsis">
								<strong>정책통계</strong> <!-- panel title -->
								<small class="size-12 weight-300 text-mutted hidden-xs">2017</small>
							</span>

							<!-- right options -->
							<ul class="options pull-right list-inline">
								<li><a href="#" class="opt panel_colapse" data-toggle="tooltip" title="Colapse" data-placement="bottom"></a></li>
								<li><a href="#" class="opt panel_fullscreen hidden-xs" data-toggle="tooltip" title="Fullscreen" data-placement="bottom"><i class="fa fa-expand"></i></a></li>
							</ul>
							<!-- /right options -->

						</div>

						<!-- panel content -->
						<div class="panel-body">

							<div id="flot-sales" class="fullwidth height-250"></div>

						</div>
						<!-- /panel content -->

						<!-- panel footer -->
						<div class="panel-footer">

							<!-- 
								.md-4 is used for a responsive purpose only on col-md-4 column.
								remove .md-4 if you use on a larger column
							-->
							<ul class="easypiecharts list-unstyled">
								<li class="clearfix">
									<span class="stat-number">18.125</span>
									<span class="stat-title">사용자</span>

									<span class="easyPieChart" data-percent="86" data-easing="easeOutBounce" data-barColor="#F8CB00" data-trackColor="#dddddd" data-scaleColor="#dddddd" data-size="60" data-lineWidth="4">
										<span class="percent"></span>
									</span> 
								</li>
								<li class="clearfix">
									<span class="stat-number">60%</span>
									<span class="stat-title">에이전트설치율</span>

									<span class="easyPieChart" data-percent="59.83" data-easing="easeOutBounce" data-barColor="#F86C6B" data-trackColor="#dddddd" data-scaleColor="#dddddd" data-size="60" data-lineWidth="4">
										<span class="percent"></span>
									</span> 
								</li>
								<li class="clearfix">
									<span class="stat-number">12%</span>
									<span class="stat-title">문의답변율</span>

									<span class="easyPieChart" data-percent="12" data-easing="easeOutBounce" data-barColor="#98AD4E" data-trackColor="#dddddd" data-scaleColor="#dddddd" data-size="60" data-lineWidth="4">
										<span class="percent"></span>
									</span> 
								</li>
							</ul>

						</div>
						<!-- /panel footer -->

					</div>
					<!-- /PANEL -->



					<!-- BOXES -->
					<div class="row">

						<!-- Feedback Box -->
						<div class="col-md-3 col-sm-6">

							<!-- BOX -->
							<div class="box danger"><!-- default, danger, warning, info, success -->

								<div class="box-title"><!-- add .noborder class if box-body is removed -->
									<h4><a href="#">총9866명 사용자</a></h4>
									<small class="block">Today 6 명</small>
									<i class="fa fa-comments"></i>
								</div>

								<div class="box-body text-center">
									<span class="sparkline" data-plugin-options='{"type":"bar","barColor":"#ffffff","height":"35px","width":"100%","zeroAxis":"false","barSpacing":"2"}'>
										331,265,456,411,367,319,402,312,300,312,283,384,372,269,402,319,416,355,416,371,423,259,361,312,269,402,327
									</span>
								</div>

							</div>
							<!-- /BOX -->

						</div>

						<!-- Profit Box -->
						<div class="col-md-3 col-sm-6">

							<!-- BOX -->
							<div class="box warning"><!-- default, danger, warning, info, success -->

								<div class="box-title"><!-- add .noborder class if box-body is removed -->
									<h4>7501개 에이전트</h4>
									<small class="block">Today 5PC 설치</small>
									<i class="fa fa-bar-chart-o"></i>
								</div>

								<div class="box-body text-center">
									<span class="sparkline" data-plugin-options='{"type":"bar","barColor":"#ffffff","height":"35px","width":"100%","zeroAxis":"false","barSpacing":"2"}'>
										331,265,456,411,367,319,402,312,300,312,283,384,372,269,402,319,416,355,416,371,423,259,361,312,269,402,327
									</span>
								</div>

							</div>
							<!-- /BOX -->

						</div>

						<!-- Orders Box -->
						<div class="col-md-3 col-sm-6">

							<!-- BOX -->
							<div class="box default"><!-- default, danger, warning, info, success -->

								<div class="box-title"><!-- add .noborder class if box-body is removed -->
									<h4>371 처리되지않은 문의</h4>
									<small class="block">Today 18개 문의</small>
									<i class="fa fa-shopping-cart"></i>
								</div>

								<div class="box-body text-center">
									<span class="sparkline" data-plugin-options='{"type":"bar","barColor":"#ffffff","height":"35px","width":"100%","zeroAxis":"false","barSpacing":"2"}'>
										331,265,456,411,367,319,402,312,300,312,283,384,372,269,402,319,416,355,416,371,423,259,361,312,269,402,327
									</span>
								</div>

							</div>
							<!-- /BOX -->

						</div>

						<!-- Online Box -->
						<div class="col-md-3 col-sm-6">

							<!-- BOX -->
							<div class="box success"><!-- default, danger, warning, info, success -->

								<div class="box-title"><!-- add .noborder class if box-body is removed -->
									<h4>3485 Online</h4>
									<small class="block">접속현황</small>
									<i class="fa fa-globe"></i>
								</div>

								<div class="box-body text-center">
									<span class="sparkline" data-plugin-options='{"type":"bar","barColor":"#ffffff","height":"35px","width":"100%","zeroAxis":"false","barSpacing":"2"}'>
										331,265,456,411,367,319,402,312,300,312,283,384,372,269,402,319,416,355,416,371,423,259,361,312,269,402,327
									</span>
								</div>

							</div>
							<!-- /BOX -->

						</div>

					</div>
					<!-- /BOXES -->



					<div class="row">

						<div class="col-md-6">

							<!-- 
								PANEL CLASSES:
									panel-default
									panel-danger
									panel-warning
									panel-info
									panel-success

								INFO: 	panel collapse - stored on user localStorage (handled by app.js _panels() function).
										All pannels should have an unique ID or the panel collapse status will not be stored!
							-->
							<div id="panel-2" class="panel panel-default">
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>Required</strong> <!-- panel title -->
									</span>

									<!-- tabs nav -->
									<ul class="nav nav-tabs pull-right">
										<li class="active"><!-- TAB 1 -->
											<a href="#ttab1_nobg" data-toggle="tab">요청내역</a>
										</li>
										<li class=""><!-- TAB 2 -->
											<a href="#ttab2_nobg" data-toggle="tab">문의사항</a>
										</li>
									</ul>
									<!-- /tabs nav -->


								</div>

								<!-- panel content -->
								<div class="panel-body">

									<!-- tabs content -->
									<div class="tab-content transparent">

										<div id="ttab1_nobg" class="tab-pane active"><!-- TAB 1 CONTENT -->

											<div class="table-responsive">
												<table class="table table-striped table-hover table-bordered">
													<thead>
														<tr>
															<th>Product Name</th>
															<th>Price</th>
															<th>Sold</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><a href="#">Apple iPhone 5 - 32GB</a></td>
															<td>$612.50</td>
															<td>789</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Allview Ax4 Nano - Cortex A7 Dual-Core 1.30GHz, 7"</a></td>
															<td>$215.50</td>
															<td>3411</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Motorola Droid 4 XT894 - 16GB - Black </a></td>
															<td>$878.50</td>
															<td>784</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Intel Core i5-4460, 3.2GHz</a></td>
															<td>$42.33</td>
															<td>3556</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Samsung Galaxy Note 3 </a></td>
															<td>$655.00</td>
															<td>3987</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">HyperX FURY Blue 8GB, DDR3, 1600MHz</a></td>
															<td>$19.50</td>
															<td>2334</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Gigabyte NVIDIA GeForce GT 730</a></td>
															<td>$122.00</td>
															<td>3499</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
													</tbody>
												</table>

												<a class="size-12" href="#">
													<i class="fa fa-arrow-right text-muted"></i> 
													요청내역 페이지 이동
												</a>

											</div>

										</div><!-- /TAB 1 CONTENT -->

										<div id="ttab2_nobg" class="tab-pane"><!-- TAB 2 CONTENT -->

											<div class="table-responsive">
												<table class="table table-striped table-hover table-bordered">
													<thead>
														<tr>
															<th>Product Name</th>
															<th>Price</th>
															<th>Sold</th>
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td><a href="#">Motorola Droid 4 XT894 - 16GB - Black </a></td>
															<td>$878.50</td>
															<td>784</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Gigabyte NVIDIA GeForce GT 730</a></td>
															<td>$122.00</td>
															<td>3499</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">HyperX FURY Blue 8GB, DDR3, 1600MHz</a></td>
															<td>$19.50</td>
															<td>2334</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Intel Core i5-4460, 3.2GHz</a></td>
															<td>$42.33</td>
															<td>3556</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Samsung Galaxy Note 3 </a></td>
															<td>$655.00</td>
															<td>3987</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Apple iPhone 5 - 32GB</a></td>
															<td>$612.50</td>
															<td>789</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
														<tr>
															<td><a href="#">Allview Ax4 Nano - Cortex A7 Dual-Core 1.30GHz, 7"</a></td>
															<td>$215.50</td>
															<td>3411</td>
															<td><a href="#" class="btn btn-default btn-xs btn-block">View</a></td>
														</tr>
													</tbody>
												</table>

												<a class="size-12" href="#">
													<i class="fa fa-arrow-right text-muted"></i> 
													문의사항 페이지이동
												</a>

											</div>

										</div><!-- /TAB 1 CONTENT -->

									</div>
									<!-- /tabs content -->

								</div>
								<!-- /panel content -->

							</div>
							<!-- /PANEL -->
					
						</div>

						<div class="col-md-6">

							<!-- 
								PANEL CLASSES:
									panel-default
									panel-danger
									panel-warning
									panel-info
									panel-success

								INFO: 	panel collapse - stored on user localStorage (handled by app.js _panels() function).
										All pannels should have an unique ID or the panel collapse status will not be stored!
							-->
							<div id="panel-3" class="panel panel-default">
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>클라이언트 감사</strong> <!-- panel title -->
									</span>
								</div>

								<!-- panel content -->
								<div class="panel-body">

									<ul class="list-unstyled list-hover slimscroll height-300" data-slimscroll-visible="true">
										
										<li>
											<span class="label label-danger"><i class="fa fa-bell-o size-15"></i></span>
											가드컴 에이전트 제거
										</li>

										<li>
											<span class="label label-success bg-black"><i class="fa fa-cogs size-15"></i></span>
											설정 변경:[공용 로그인모드 :사용안함]
										</li>

										<li>
											<span class="label label-warning"><i class="fa fa-user size-15"></i></span>
											로그아웃(화면 잠금)
										</li>
										<li>
											<span class="label label-warning"><i class="fa fa-user size-15"></i></span>
											로그인 성공
										</li>
									
										<li>
											<span class="label label-danger"><i class="fa fa-bell-o size-15"></i></span>
											가드컴 에이전트 제거
										</li>

										<li>
											<span class="label label-success bg-black"><i class="fa fa-cogs size-15"></i></span>
											설정 변경:[공용 로그인모드 :사용안함]
										</li>

										<li>
											<span class="label label-warning"><i class="fa fa-user size-15"></i></span>
											로그아웃(화면 잠금)
										</li>
										<li>
											<span class="label label-warning"><i class="fa fa-user size-15"></i></span>
											로그인 성공
										</li>

									</ul>

								</div>
								<!-- /panel content -->

								<!-- panel footer -->
								<div class="panel-footer">

									<a href="#"><i class="fa fa-arrow-right text-muted"></i>감사내역페이지 이동 </a>

								</div>
								<!-- /panel footer -->

							</div>
							<!-- /PANEL -->
						</div>
					</div>
				</div>
			</section>



		</div>
		
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<!-- PAGE LEVEL SCRIPT -->
		<script type="text/javascript">
			/* 
				Toastr Notification On Load 

				TYPE:
					primary
					info
					error
					success
					warning

				POSITION
					top-right
					top-left
					top-center
					
					
					top-full-width
					bottom-right
					bottom-left
					bottom-center
					bottom-full-width
					
				false = click link (example: "http://www.stepofweb.com")
			*/

			/** SALES CHART
			******************************************* **/
			loadScript(plugin_path + "chart.flot/jquery.flot.min.js", function(){
				loadScript(plugin_path + "chart.flot/jquery.flot.resize.min.js", function(){
					loadScript(plugin_path + "chart.flot/jquery.flot.time.min.js", function(){
						loadScript(plugin_path + "chart.flot/jquery.flot.fillbetween.min.js", function(){
							loadScript(plugin_path + "chart.flot/jquery.flot.orderBars.min.js", function(){
								loadScript(plugin_path + "chart.flot/jquery.flot.pie.min.js", function(){
									loadScript(plugin_path + "chart.flot/jquery.flot.tooltip.min.js", function(){

										if (jQuery("#flot-sales").length > 0) {

											/* DEFAULTS FLOT COLORS */
										var $color_border_color = "#eaeaea";		/* light gray 	*/
											$color_grid_color 	= "#dddddd"			/* silver	 	*/
											$color_main 		= "#E24913";		/* red       	*/
											$color_second 		= "#6595b4";		/* blue      	*/
											$color_third 		= "#FF9F01";		/* orange   	*/
											$color_fourth 		= "#7e9d3a";		/* green     	*/
											$color_fifth 		= "#BD362F";		/* dark red  	*/
											$color_mono 		= "#000000";		/* black 	 	*/

											var datasets = {
													"USB": {
														label: "USB차단",
														data: [[1988, 483994], [1989, 479060], [1990, 457648], [1991, 401949], [1992, 424705], [1993, 402375], [1994, 377867], [1995, 357382], [1996, 337946], [1997, 336185], [1998, 328611], [1999, 329421], [2000, 342172], [2001, 344932], [2002, 387303], [2003, 440813], [2004, 480451], [2005, 504638], [2006, 528692]]
													},        
													"FILE": {
														label: "파일전송",
														data: [[1988, 218000], [1989, 203000], [1990, 171000], [1992, 42500], [1993, 37600], [1994, 36600], [1995, 21700], [1996, 19200], [1997, 21300], [1998, 13600], [1999, 14000], [2000, 19100], [2001, 21300], [2002, 23600], [2003, 25100], [2004, 26100], [2005, 31100], [2006, 34700]]
													},
													"PRINT": {
														label: "프린트",
														data: [[1988, 62982], [1989, 62027], [1990, 60696], [1991, 62348], [1992, 58560], [1993, 247303], [1994, 207303], [1995, 287303], [1996, 50554], [1997, 127303], [1998, 187303], [1999, 47529], [2000, 47778], [2001, 48760], [2002, 50949], [2003, 287303], [2004, 60234], [2005, 60076], [2006, 59213]]
													},
													"PATTERN": {
														label: "민감정보",
														data: [[1988, 55627], [1989, 55475], [1990, 58464], [1991, 55134], [1992, 52436], [1993, 47139], [1994, 43962], [1995, 43238], [1996, 42395], [1997, 40854], [1998, 40993], [1999, 41822], [2000, 41147], [2001, 40474], [2002, 40604], [2003, 40044], [2004, 38816], [2005, 38060], [2006, 36984]]
											}}
											
											var data = []; var i = 0;
											data.push(datasets['USB']);
											data.push(datasets['FILE']);
											data.push(datasets['PRINT']);
											data.push(datasets['PATTERN']);

											
											var options = {

												xaxis : {
//													mode : "time",
//													tickLength : 5
													tickDecimals: 0
												},

												series : {
													lines : {
														show : true,
														lineWidth : 1,
														fill : true,
														fillColor : {
															colors : [{
																opacity : 0.1
															}, {
																opacity : 0.15
															}]
														}
													},
												   //points: { show: true },
													shadowSize : 0
												},

												selection : {
													mode : "x"
												},

												grid : {
													hoverable : true,
													clickable : true,
													tickColor : $color_border_color,
													borderWidth : 0,
													borderColor : $color_border_color,
												},

												tooltip : true,

												tooltipOpts : {
													content : "일자: %x <span class='block'>%y개</span>",
													dateFormat : "%y-%0m-%0d",
													defaultTheme : false
												},

												colors : [$color_main, $color_second, $color_third, $color_fourth],
										
											};
										
											var plot = jQuery.plot(jQuery("#flot-sales"), data, options);
										}

									});
								});
							});
						});
					});
				});
			});
		
			jQuery('#preloader').hide();

			</script>
	</body>
</html>
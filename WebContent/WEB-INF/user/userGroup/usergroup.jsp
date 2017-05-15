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
		<link href="/assets/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css" id="color_scheme" />

	</head>
	<body>
		<!-- WRAPPER -->
		<div id="wrapper" class="clearfix">

			<!-- 
				ASIDE 
				Keep it outside of #wrapper (responsive purpose)
			-->
			<jsp:include page="/WEB-INF/common/left_menu.jsp" flush="false" />
			
			<!-- /ASIDE -->
			<!-- HEADER -->
			<jsp:include page="/WEB-INF/common/top_navi.jsp" flush="false" />			
			<!-- /HEADER -->

			<!-- 
				MIDDLE 
			-->
			<section id="middle">
			
				<!-- page title -->
				<header id="page-header">
					<h1>사용자관리</h1>
					<ol class="breadcrumb">
						<li>사용자관리</li>
						<li class="active">부서/사용자관리</li>
					</ol>
				</header>
				<!-- /page title -->
			
				<div id="content" class="dashboard padding-20">
					<div class="row">
						<div class="col-md-2">
							<div id="panel-2" class="panel panel-default">
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>조직도</strong> <!-- panel title -->
									</span>
								</div>

								<!-- panel content -->
								<div class="panel-body">

									<div id="jstree1" style="overflow: hidden;">
				                        <ul>
				                            <li class="jstree-open">Company
				                                <ul>
				                                    <li>css
				                                        <ul>
				                                            <li data-jstree='{"type":"css"}'>animate.css</li>
				                                            <li data-jstree='{"type":"css"}'>bootstrap.css</li>
				                                            <li data-jstree='{"type":"css"}'>style.css</li>
				                                        </ul>
				                                    </li>
				                                    <li>email-templates
				                                        <ul>
				                                            <li data-jstree='{"type":"html"}'>action.html</li>
				                                            <li data-jstree='{"type":"html"}'>alert.html</li>
				                                            <li data-jstree='{"type":"html"}'>billing.html</li>
				                                        </ul>
				                                    </li>
				                                    <li>fonts
				                                        <ul>
				                                            <li data-jstree='{"type":"svg"}'>glyphicons-halflings-regular.eot</li>
				                                            <li data-jstree='{"type":"svg"}'>glyphicons-halflings-regular.svg</li>
				                                            <li data-jstree='{"type":"svg"}'>glyphicons-halflings-regular.ttf</li>
				                                            <li data-jstree='{"type":"svg"}'>glyphicons-halflings-regular.woff</li>
				                                        </ul>
				                                    </li>
				                                    <li class="jstree-open">img
				                                        <ul>
				                                            <li data-jstree='{"type":"img"}'>profile_small.jpg</li>
				                                            <li data-jstree='{"type":"img"}'>angular_logo.png</li>
				                                            <li class="text-navy" data-jstree='{"type":"img"}'>html_logo.png</li>
				                                            <li class="text-navy" data-jstree='{"type":"img"}'>mvc_logo.png</li>
				                                        </ul>
				                                    </li>
				                                    <li class="jstree-open">js
				                                        <ul>
				                                            <li data-jstree='{"type":"js"}'>inspinia.js</li>
				                                            <li data-jstree='{"type":"js"}'>bootstrap.js</li>
				                                            <li data-jstree='{"type":"js"}'>jquery-2.1.1.js</li>
				                                            <li data-jstree='{"type":"js"}'>jquery-ui.custom.min.js</li>
				                                            <li  class="text-navy" data-jstree='{"type":"js"}'>jquery-ui-1.10.4.min.js</li>
				                                        </ul>
				                                    </li>
				                                    <li data-jstree='{"type":"html"}'> affix.html</li>
				                                    <li data-jstree='{"type":"html"}'> dashboard.html</li>
				                                    <li data-jstree='{"type":"html"}'> buttons.html</li>
				                                    <li data-jstree='{"type":"html"}'> calendar.html</li>
				                                    <li data-jstree='{"type":"html"}'> contacts.html</li>
				                                    <li data-jstree='{"type":"html"}'> css_animation.html</li>
				                                    <li  class="text-navy" data-jstree='{"type":"html"}'> flot_chart.html</li>
				                                    <li  class="text-navy" data-jstree='{"type":"html"}'> google_maps.html</li>
				                                    <li data-jstree='{"type":"html"}'> icons.html</li>
				                                    <li data-jstree='{"type":"html"}'> inboice.html</li>
				                                    <li data-jstree='{"type":"html"}'> login.html</li>
				                                    <li data-jstree='{"type":"html"}'> mailbox.html</li>
				                                    <li data-jstree='{"type":"html"}'> profile.html</li>
				                                    <li  class="text-navy" data-jstree='{"type":"html"}'> register.html</li>
				                                    <li data-jstree='{"type":"html"}'> timeline.html</li>
				                                    <li data-jstree='{"type":"html"}'> video.html</li>
				                                    <li data-jstree='{"type":"html"}'> widgets.html</li>
				                                </ul>
				                            </li>
				                        </ul>
				                    </div>




								</div>
								<!-- /panel content -->

							</div>
							<!-- /PANEL -->
					
						</div>

						<div class="col-md-10">
							<div id="panel-2" class="panel panel-default">
						
								<div class="panel-heading">
									<span class="title elipsis">
										<strong>사용자정보</strong> <!-- panel title -->
									</span>
								</div>
	
								<!-- panel content -->
								<div class="panel-body">
	
	
									
									
									
									<table class="table table-striped table-bordered table-hover" id="datatable_sample">
										<thead>
											<tr>
												<th class="table-checkbox">
													<input type="checkbox" class="group-checkable" data-set="#datatable_sample .checkboxes"/>
												</th>
												<th>Username</th>
												<th>Email</th>
												<th>Points</th>
												<th>Joined</th>
												<th>Status</th>
											</tr>
										</thead>
		
										<tbody>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 shuxer
												</td>
												<td>
													<a href="mailto:shuxer@gmail.com">
													shuxer@gmail.com </a>
												</td>
												<td>
													 120
												</td>
												<td class="center">
													 12 Jan 2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 looper
												</td>
												<td>
													<a href="mailto:looper90@gmail.com">
													looper90@gmail.com </a>
												</td>
												<td>
													 120
												</td>
												<td class="center">
													 12.12.2011
												</td>
												<td>
													<span class="label label-sm label-warning">
													Suspended </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 userwow
												</td>
												<td>
													<a href="mailto:userwow@yahoo.com">
													userwow@yahoo.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 user1wow
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													userwow@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-default">
													Blocked </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 restest
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													test@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 foopl
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 weep
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 coop
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 pppol
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 test
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 userwow
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													userwow@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-default">
													Blocked </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 test
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													test@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 goop
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 weep
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 15.11.2011
												</td>
												<td>
													<span class="label label-sm label-default">
													Blocked </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 toopl
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 16.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 userwow
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													userwow@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 9.12.2012
												</td>
												<td>
													<span class="label label-sm label-default">
													Blocked </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 tes21t
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													test@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 14.12.2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 fop
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 13.11.2010
												</td>
												<td>
													<span class="label label-sm label-warning">
													Suspended </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 kop
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 17.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 vopl
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.11.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 userwow
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													userwow@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-default">
													Blocked </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 wap
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													test@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 12.12.2012
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 test
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 19.12.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 toop
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 17.12.2010
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
											<tr class="odd gradeX">
												<td>
													<input type="checkbox" class="checkboxes" value="1"/>
												</td>
												<td>
													 weep
												</td>
												<td>
													<a href="mailto:userwow@gmail.com">
													good@gmail.com </a>
												</td>
												<td>
													 20
												</td>
												<td class="center">
													 15.11.2011
												</td>
												<td>
													<span class="label label-sm label-success">
													Approved </span>
												</td>
											</tr>
										</tbody>
									</table>
									
									
	
	
	
								</div>
								<!-- /panel content -->
							</div>
						</div>
					</div>
				</div>
			</section>



		</div>
		
	
		<!-- JAVASCRIPT FILES -->
		<script type="text/javascript">var plugin_path = '/assets/plugins/';</script>
		<script type="text/javascript" src="/assets/plugins/jquery/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/assets/js/app.js"></script>
		<script type="text/javascript" src="/assets/plugins/jstree/jstree.min.js"></script>
		
		
<script>
    $(document).ready(function(){

        $('#jstree1').jstree({
            'core' : {
                'check_callback' : true
            },
            'plugins' : [ 'types', 'dnd' ],
            'types' : {
                'default' : {
                    'icon' : 'fa fa-folder'
                },
                'html' : {
                    'icon' : 'fa fa-file-code-o'
                },
                'svg' : {
                    'icon' : 'fa fa-file-picture-o'
                },
                'css' : {
                    'icon' : 'fa fa-file-code-o'
                },
                'img' : {
                    'icon' : 'fa fa-file-image-o'
                },
                'js' : {
                    'icon' : 'fa fa-file-text-o'
                }

            }
        });
/* 
        $('#using_json').jstree({ 'core' : {
            'data' : [
                'Empty Folder',
                {
                    'text': 'Resources',
                    'state': {
                        'opened': true
                    },
                    'children': [
                        {
                            'text': 'css',
                            'children': [
                                {
                                    'text': 'animate.css', 'icon': 'none'
                                },
                                {
                                    'text': 'bootstrap.css', 'icon': 'none'
                                },
                                {
                                    'text': 'main.css', 'icon': 'none'
                                },
                                {
                                    'text': 'style.css', 'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'js',
                            'children': [
                                {
                                    'text': 'bootstrap.js', 'icon': 'none'
                                },
                                {
                                    'text': 'inspinia.min.js', 'icon': 'none'
                                },
                                {
                                    'text': 'jquery.min.js', 'icon': 'none'
                                },
                                {
                                    'text': 'jsTree.min.js', 'icon': 'none'
                                },
                                {
                                    'text': 'custom.min.js', 'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        },
                        {
                            'text': 'html',
                            'children': [
                                {
                                    'text': 'layout.html', 'icon': 'none'
                                },
                                {
                                    'text': 'navigation.html', 'icon': 'none'
                                },
                                {
                                    'text': 'navbar.html', 'icon': 'none'
                                },
                                {
                                    'text': 'footer.html', 'icon': 'none'
                                },
                                {
                                    'text': 'sidebar.html', 'icon': 'none'
                                }
                            ],
                            'state': {
                                'opened': true
                            }
                        }
                    ]
                },
                'Fonts',
                'Images',
                'Scripts',
                'Templates',
            ]
        } }); */
		
        loadScript(plugin_path + "datatables/js/jquery.dataTables.min.js", function(){
			loadScript(plugin_path + "datatables/dataTables.bootstrap.js", function(){

				if (jQuery().dataTable) {

					var table = jQuery('#datatable_sample');
					table.dataTable({
						"columns": [{
							"orderable": false
						}, {
							"orderable": true
						}, {
							"orderable": false
						}, {
							"orderable": false
						}, {
							"orderable": true
						}, {
							"orderable": false
						}],
						"lengthMenu": [
							[5, 15, 20, -1],
							[5, 15, 20, "All"] // change per page values here
						],
						// set the initial value
						"pageLength": 5,            
						"pagingType": "bootstrap_full_number",
						"language": {
							"lengthMenu": "  _MENU_ records",
							"paginate": {
								"previous":"Prev",
								"next": "Next",
								"last": "Last",
								"first": "First"
							}
						},
						"columnDefs": [{  // set default column settings
							'orderable': false,
							'targets': [0]
						}, {
							"searchable": false,
							"targets": [0]
						}],
						"order": [
							[1, "asc"]
						] // set first column as a default sort by asc
					});

					var tableWrapper = jQuery('#datatable_sample_wrapper');

					table.find('.group-checkable').change(function () {
						var set = jQuery(this).attr("data-set");
						var checked = jQuery(this).is(":checked");
						jQuery(set).each(function () {
							if (checked) {
								jQuery(this).attr("checked", true);
								jQuery(this).parents('tr').addClass("active");
							} else {
								jQuery(this).attr("checked", false);
								jQuery(this).parents('tr').removeClass("active");
							}
						});
						jQuery.uniform.update(set);
					});

					table.on('change', 'tbody tr .checkboxes', function () {
						jQuery(this).parents('tr').toggleClass("active");
					});

					tableWrapper.find('.dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown

				}

			});
		});

        
    });
</script>
		
		
	</body>
</html>
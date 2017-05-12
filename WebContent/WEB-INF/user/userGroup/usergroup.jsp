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

    });
</script>
		
		
	</body>
</html>
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

									<div id="deptTree">
									
									
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

	function setTree(){
		$.ajax({      
	        type:"POST",  
	        url:'/common/tree/dept',
	        //data:{},
	        success:function(args){   
	            $("#deptTree").html(args);      
	        },   
	        //beforeSend:showRequest,  
	        error:function(e){  
	            console.log(e.responseText);  
	        }  
	    }); 
	}

    $(document).ready(function(){
    	
    	setTree();
    	
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
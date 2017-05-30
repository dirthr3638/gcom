<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="utf-8">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
		<title>GuardCom User</title>
	
		<!-- mobile settings -->
		<meta name="viewport" content="width=device-width, maximum-scale=1, initial-scale=1, user-scalable=0" />
	
		<!-- CORE CSS -->
		<link href="/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		
		<!-- THEME CSS -->
		<link href="/assets/css/essentials.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/assets/css/color_scheme/green.css" rel="stylesheet" type="text/css" id="color_scheme" />

	</head>
	<body class="smoothscroll enable-animation">
		<jsp:include page="/WEB-INF/common/user_header.jsp" flush="false" />
		
		<!-- Contact Form -->
		<section style="padding:20px 0;">
			<div class="container">
			<!-- FORM -->
				<div class="col-md-12 col-sm-12">
					<div class="col-md-6 col-sm-6">
						<h3>문의 등록</h3>
					</div>
					<div class="col-md-6 col-sm-6" style="text-align:right;">
						<button type="button" class="btn btn-primary" onClick="location.href='#contact_list'"><i class="fa fa-list"></i>등록문의확인</button>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<!-- Alert Success -->
					<div id="alert_success" class="alert alert-success margin-bottom-30">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>Thank You!</strong> Your message successfully sent!
					</div>
					<!-- /Alert Success -->


					<!-- Alert Failed -->
					<div id="alert_failed" class="alert alert-danger margin-bottom-30">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>[SMTP] Error!</strong> Internal server error!
					</div>
					<!-- /Alert Failed -->


					<!-- Alert Mandatory -->
					<div id="alert_mandatory" class="alert alert-danger margin-bottom-30">
						<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						<strong>Sorry!</strong> You need to complete all mandatory (*) fields!
					</div>
					<!-- /Alert Mandatory -->


					<form action="php/contact.php" method="post" enctype="multipart/form-data">
						<fieldset>
							<input type="hidden" name="action" value="contact_send" />

							<div class="row">
								<div class="form-group">
									<div class="col-md-12">
										<label for="contact:name">성명 *</label>
										<input required type="text" value="" class="form-control" name="contact[name][required]" id="contact:name" value="가드컴" disabled>
									</div>
									<div class="col-md-12">
										<label for="contact:email">이메일 *</label>
										<input required type="email" value="" class="form-control" name="contact[email][required]" id="contact:email" placeholder="E-Mail">
									</div>
									<div class="col-md-12">
										<label for="contact:phone">핸드폰 *</label>
										<input type="text" value="" class="form-control" name="contact[phone]" id="contact:phone" placeholder="010XXXX0000">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-md-8">
										<label for="contact:subject">제목 *</label>
										<input required type="text" value="" class="form-control" name="contact[subject][required]" id="contact:subject" placeholder="Subject">
									</div>
									<div class="col-md-4">
										<label for="contact_department">문의구분</label>
										<select class="form-control pointer" name="contact[department]">
											<option value="">--- Select ---</option>
											<option value="Marketing">Marketing</option>
											<option value="Webdesign">Webdesign</option>
											<option value="Architecture">Architecture</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-md-12">
										<label for="contact:message">문의내용 *</label>
										<textarea required maxlength="10000" rows="8" class="form-control" name="contact[message]" id="contact:message"></textarea>
									</div>
								</div>
							</div>
							<!--
							<div class="row">
								<div class="form-group">
									<div class="col-md-12">
										<label for="contact:attachment">File Attachment</label>

										/* custom file upload */
										<input class="custom-file-upload" type="file" id="file" name="contact[attachment]" id="contact:attachment" data-btn-text="Select a File" />
										<small class="text-muted block">Max file size: 10Mb (zip/pdf/jpg/png)</small>

									</div>
								</div>
							</div> -->

						</fieldset>
						<div class="row">
							<div class="col-md-12">
								<button type="submit" class="btn btn-primary"><i class="fa fa-check"></i> 문의등록</button>
							</div>
						</div>
					</form>
				</div>
			<!-- /FORM -->
			</div>
		</section>
		<!-- /Contact Form -->

		<!-- Contact Table-->
		<section id="contact_list" style="padding:20px 0;">
			<div class="container">

				<h4>나의 등록 문의</h4>
				<table class="table table-striped table-bordered table-hover" id="sample_3">
					<thead>
						<tr>
							<th>문의코드</th>
							<th>문의구분</th>
							<th>제목</th>
							<th>등록일</th>
							<th>답변여부</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>43</td>
							<td>단순문의</td>
							<td>정책적용은 누가하나요?</td>
							<td>2017-05-23</td>
							<td></td>
						</tr>
						<tr>
							<td>42</td>
							<td>서비스문의</td>
							<td>다운 및 설치방법 문의드려요</td>
							<td>2017-05-17</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>41</td>
							<td>단순문의</td>
							<td>정책 해제 적용은 누가하나요?</td>
							<td>2017-05-15</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>40</td>
							<td>단순문의</td>
							<td>단순 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-13</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>39</td>
							<td>단순문의</td>
							<td>단순 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-11</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>38</td>
							<td>단순문의</td>
							<td>단순 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-10</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>37</td>
							<td>버그문의</td>
							<td>다운로드 되지 않아요.</td>
							<td>2017-05-10</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>36</td>
							<td>버그문의</td>
							<td>정책은 적용되었는데 표시되지 않아요</td>
							<td>2017-05-08</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>35</td>
							<td>단순문의</td>
							<td>단순 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-08</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>34</td>
							<td>단순문의</td>
							<td>단순 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-07</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>33</td>
							<td>서비스문의</td>
							<td>서비스 관련 문의 드립니다. 확인 부탁드려요.</td>
							<td>2017-05-06</td>
							<td><i class="fa fa-pencil"></i></td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Camino 1.5
							</td>
							<td>OSX.3+
							</td>
							<td>1.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Netscape 7.2
							</td>
							<td>Win 95+ / Mac OS 8.6-9.2
							</td>
							<td>1.7
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Netscape Browser 8
							</td>
							<td>Win 98SE+
							</td>
							<td>1.7
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Netscape Navigator 9
							</td>
							<td>Win 98+ / OSX.2+
							</td>
							<td>1.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.0
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.1
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.1
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.2
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.2
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.3
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.3
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.4
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.4
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.5
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.5
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.6
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>1.6
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.7
							</td>
							<td>Win 98+ / OSX.1+
							</td>
							<td>1.7
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Mozilla 1.8
							</td>
							<td>Win 98+ / OSX.1+
							</td>
							<td>1.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Seamonkey 1.1
							</td>
							<td>Win 98+ / OSX.2+
							</td>
							<td>1.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Gecko
							</td>
							<td>Epiphany 2.20
							</td>
							<td>Gnome
							</td>
							<td>1.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>Safari 1.2
							</td>
							<td>OSX.3
							</td>
							<td>125.5
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>Safari 1.3
							</td>
							<td>OSX.3
							</td>
							<td>312.8
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>Safari 2.0
							</td>
							<td>OSX.4+
							</td>
							<td>419.3
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>Safari 3.0
							</td>
							<td>OSX.4+
							</td>
							<td>522.1
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>OmniWeb 5.5
							</td>
							<td>OSX.4+
							</td>
							<td>420
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>iPod Touch / iPhone
							</td>
							<td>iPod
							</td>
							<td>420.1
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Webkit
							</td>
							<td>S60
							</td>
							<td>S60
							</td>
							<td>413
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 7.0
							</td>
							<td>Win 95+ / OSX.1+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 7.5
							</td>
							<td>Win 95+ / OSX.2+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 8.0
							</td>
							<td>Win 95+ / OSX.2+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 8.5
							</td>
							<td>Win 95+ / OSX.2+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 9.0
							</td>
							<td>Win 95+ / OSX.3+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 9.2
							</td>
							<td>Win 88+ / OSX.3+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera 9.5
							</td>
							<td>Win 88+ / OSX.3+
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Opera for Wii
							</td>
							<td>Wii
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Nokia N800
							</td>
							<td>N800
							</td>
							<td>-
							</td>
							<td>A
							</td>
						</tr>
						<tr>
							<td>Presto
							</td>
							<td>Nintendo DS browser
							</td>
							<td>Nintendo DS
							</td>
							<td>8.5
							</td>
							<td>C/A<sup>1</sup>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
		<!-- /Contact Table-->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
	</body>
</html>
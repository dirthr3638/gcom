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
		
		<!-- HOME -->
		<!-- SubHeader -->
		<section class="callout-dark heading-title heading-arrow-bottom" style="padding:60px 0;">
			<div class="container">
				<div class="container text-center">
					<h1 class="weight-300 size-40">GUARD COM USER</h1>
					<h2 class="weight-300 letter-spacing-1 size-13"><span>Welcome to Guard Com Customer Center</span></h2>
					<div class="margin-top-20">
						<a href="#" class="btn btn-3d btn-lg btn-teal"><i class="fa fa-download"></i>DOWNLOAD</a>
					</div>
				</div>

			</div>
		</section>
		<!-- /SubHeader -->

		<!-- System Policy -->
		<section style="padding:40px 0;">
			<div class="container">
				<h4><i class="fa fa-get-pocket"></i>시스템 정책</h4>
				<form action="#" method="post">
					<div class="sky-form">

						<table class="table table-bordered table-striped">
							<tbody>
								<tr>
									<td>서버 로그인 타임아웃</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_1" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_1"  disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>반출 파일 최대크기 제한</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_2" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_2" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>이동식 저장장치 파일 접근</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_3" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_3" checked="" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>민감 패턴 정보</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_4" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_4" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
					<!--
					<div class="margin-top-10">
						<a href="#" class="btn btn-primary"><i class="fa fa-check"></i> 정책요청 </a>
						<a href="#" class="btn btn-default">취소</a>
					</div>
					-->
				</form>
			</div>
		</section>
		<!-- /System Policy -->

		<!-- User Policy -->
		<section style="padding:40px 0;">
			<div class="container">
				<h4><i class="fa fa-get-pocket"></i>사용자 정책</h4>
				<form action="#" method="post">
					<div class="sky-form">

						<table class="table table-bordered table-striped">
							<tbody>
								<tr>
									<td>에이전트 삭제 가능 여부</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_1" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_1"  disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>프린터 사용가능 여부</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_2" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_2" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>인쇄 워터마크 출력 여부</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_3" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_3" checked="" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
								<tr>
									<td>인가된 USB 장치 사용여부</td>
									<td>
										<div class="inline-group">
											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_4" checked="" disabled="disabled"><i></i> Yes
											</label>

											<label class="radio nomargin-top nomargin-bottom">
												<input type="radio" name="radioOption_4" disabled="disabled"><i></i> No
											</label>
										</div>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
					<!--
					<div class="margin-top-10">
						<a href="#" class="btn btn-primary"><i class="fa fa-check"></i> 정책요청 </a>
						<a href="#" class="btn btn-default">취소</a>
					</div>
					-->
				</form>
			</div>
		</section>
		<!-- /User Policy -->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
	</body>
</html>
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
		
		<!-- Notice Table -->
		<!-- -->
		<section>
			<div class="container">

				<h4>공지사항</h4>
				<div class="table-responsive">
					<table class="table table-hover table-vertical-middle">
						<thead>
							<tr>
								<th class="width-50 text-center">No</th>
								<th class="text-center">제목</th>
								<th class="width-100 text-center">작성자</th>
								<th class="width-100 text-center">작성일</th>
								<th class="width-50 text-center">조회</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">6</td>
								<td class="text-center">공지- Guard Com User 3차 공지사항</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-23</td>
								<td class="text-center">5</td>
							</tr>
							<tr>
								<td class="text-center">5</td>
								<td class="text-center">공지- Guard Com User 2차 공지사항</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-20</td>
								<td class="text-center">17</td>
							</tr>
							<tr>
								<td class="text-center">4</td>
								<td class="text-center">공지- Guard Com User 1차 공지사항</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-15</td>
								<td class="text-center">20</td>
							</tr>
							<tr>
								<td class="text-center">3</td>
								<td class="text-center">Guard Com Report 오픈 예정 공지</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-14</td>
								<td class="text-center">15</td>
							</tr>
							<tr>
								<td class="text-center">2</td>
								<td class="text-center">Guard Com Console 오픈 예정 공지</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-13</td>
								<td class="text-center">11</td>
							</tr>
							<tr>
								<td class="text-center">1</td>
								<td class="text-center">Guard Com User 오픈 예정 공지</td>
								<td class="text-center">가드컴</td>
								<td class="text-center">2017-05-12</td>
								<td class="text-center">23</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</section>
		<!-- /Notice Table -->
		
		<jsp:include page="/WEB-INF/common/user_footer.jsp" flush="false" />
	</body>
</html>
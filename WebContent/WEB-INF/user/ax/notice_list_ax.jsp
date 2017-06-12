<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="gcom.user.model.UserNoticeModel"%>
<% 
	List<UserNoticeModel> list = (List<UserNoticeModel>)request.getAttribute("UserNoticeList");
	int list_cnt = Integer.parseInt(request.getAttribute("list_cnt").toString());
	
	//i+1
%>
<table id="user_notice_table" class="table table-hover table-vertical-middle">
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
		<% if (list_cnt < 1) { %>
			<tr>
				<td colspan="5">공지사항 게시물이 없습니다.</td>
			</tr>
		<% } else { %>
			<% for (int i = 0; i < list.size(); i ++ ) {  %>
				<tr>
					<td class="text-center"><%= list.get(i).getBbsId() %></td>
					<td class="text-center"><a href="javascript:fn_bbs_detail('<%=list.get(i).getBbsId() %>'); " ><%=list.get(i).getBbsTitle() %></a></td>
					<td class="text-center"><%=list.get(i).getBbsRegStaf() %></td>
					<td class="text-center"><%=list.get(i).getBbsRegDate() %></td>
					<td class="text-center"><%=list.get(i).getBbsClickCnt() %></td>
				</tr>
		<% } %>
	<%	} %>
	</tbody>
</table>

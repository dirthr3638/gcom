<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean onlyFlag = Boolean.parseBoolean(request.getParameter("onlyFlag"));
	boolean isWaterMark = Boolean.parseBoolean(request.getParameter("isWaterMark"));
	String waterMarkType = request.getParameter("waterMarkType").toString();
	String waterMarkEndDate = request.getParameter("waterMarkEndDate").toString();
	
%>
<script type="text/javascript" src="/assets/js/app.js"></script>
<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="120px" style="vertical-align: middle;">워터마크<br />출력선택</td>
			<td class="center-cell" width="180px" style="vertical-align: middle;">
				<% if (onlyFlag) { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_water_mark_print" value="Y" <% if (isWaterMark){ %> checked <%}%> /><i></i> 출력
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_water_mark_print" value="N" <% if (!isWaterMark){ %> checked <%}%> /><i></i> 미출력
					</label>
				<% } else { %>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_water_mark_print" value="Y" /><i></i> 출력
					</label>
					<label class="radio nomargin-top nomargin-bottom">
						<input type="radio" name="radio_water_mark_print" value="N" checked /><i></i> 미출력
					</label>
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" width="100px" style="vertical-align: middle;">적용<br />일자</td>
			<td class="center-cell">
				<% if (onlyFlag) { %>
					<input type="text" class="form-control datepicker" id="att_waterMark_end_date" name="att_waterMark_end_date" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false" value="<%= waterMarkEndDate %>" placeholder="워터마크 출력 종료일" style="width:200px;" maxlength="10" readonly="readonly" />
				<% } else { %>
					<input type="text" class="form-control datepicker" id="att_waterMark_end_date" name="att_waterMark_end_date" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false" placeholder="워터마크 출력 종료일" style="width:200px;" maxlength="10" readonly="readonly" />
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" width="100px" style="vertical-align: middle;">적용<br />타입</td>
			<td class="center-cell">
				<% if (onlyFlag) { %>
					<input type="number" id="att_waterMark_type" name="att_waterMark_type" class="form-control" value="0" style="width:60px;" value="<%= waterMarkType %>" disabled />
				<% } else { %>
					<input type="number" id="att_waterMark_type" name="att_waterMark_type" class="form-control" value="0" style="width:60px;" disabled />
				<% } %>
			</td>
		</tr>
	</table>
</div>

<table class="table table-bordered">
	<thead>
		<tr>
			<td>워터마크</td>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
													

<script type="text/javascript">
	
</script>

































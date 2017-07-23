<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean onlyFlag = Boolean.parseBoolean(request.getParameter("onlyFlag"));
	boolean isWaterMark = Boolean.parseBoolean(request.getParameter("isWaterMark"));
	String waterMarkType = request.getParameter("waterMarkType").toString();
	String waterMarkEndDate = request.getParameter("waterMarkEndDate").toString();
	
	String endDate ="";
	String endTime = "";
	if (!"".equals(waterMarkEndDate)) {
		String[] date = waterMarkEndDate.split(" ");
		endDate = date[0];
		endTime = date[1];
		
		String[] timeTemp = endTime.split(":");
		int hour = Integer.parseInt(timeTemp[0]);
		
		if (hour < 12) {
			endTime = hour + " : " + timeTemp[1] + " : AM";
		} else {
			endTime = (hour - 12) + " : " + timeTemp[1] + " : PM";
		}
		
	}
	
%>
<script type="text/javascript" src="/assets/js/app.js"></script>
<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="140px" style="vertical-align: middle;">워터마크 출력선택</td>
			<td class="center-cell" style="vertical-align: middle;">
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
						<input type="radio" name="radio_water_mark_print" value="N" /><i></i> 미출력
					</label>
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" width="100px" style="vertical-align: middle;">적용 타입</td>
			<td class="center-cell">
				<% if (onlyFlag) { %>
					<input type="number" id="att_waterMark_type" name="att_waterMark_type" class="form-control" value="0" style="width:80px; margin: 0;" value="<%= waterMarkType %>" disabled />
				<% } else { %>
					<input type="number" id="att_waterMark_type" name="att_waterMark_type" class="form-control" value="0" style="width:80px; margin: 0;" disabled />
				<% } %>
			</td>
		</tr>
		<tr>
			<td class="th-cell-gray center-cell" style="vertical-align: middle;">적용 일자</td>
			<td class="center-cell">
				<% if (onlyFlag) { %>
					<input type="text" class="form-control datepicker" id="att_waterMark_end_date" name="att_waterMark_end_date" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false" value="<%= endDate %>" placeholder="출력 종료일" style="width:200px; margin: 0;" maxlength="10" readonly="readonly" />
				<% } else { %>
					<input type="text" class="form-control datepicker" id="att_waterMark_end_date" name="att_waterMark_end_date" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false" placeholder="출력 종료일" style="width:200px; margin: 0;" maxlength="10" readonly="readonly" />
				<% } %>
			</td>
			<td class="th-cell-gray center-cell" style="vertical-align: middle;">적용 시간</td>
			<td class="center-cell">
				<% if (onlyFlag) { %>
					<input type="text" class="form-control timepicker" id="att_waterMark_end_time" name="att_waterMark_end_time" data-format="hh:mm" data-lang="en" data-RTL="false" value="<%= endTime %>" placeholder="출력 종료시간" style="width:200px; margin: 0;" maxlength="5" readonly="readonly" />
				<% } else { %>
					<input type="text" class="form-control timepicker" id="att_waterMark_end_time" name="att_waterMark_end_time" data-format="hh:mm" data-lang="en" data-RTL="false" placeholder="출력 종료시간" style="width:200px; margin: 0;" maxlength="5" readonly="readonly" />
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

































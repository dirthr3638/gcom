<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	boolean onlyFlag = Boolean.parseBoolean(request.getParameter("onlyFlag"));
	boolean isWaterMark = Boolean.parseBoolean(request.getParameter("isWaterMark"));
	String waterMarkType = request.getParameter("waterMarkType").toString();
	String waterMarkEndDate = request.getParameter("waterMarkEndDate").toString();
	
	boolean isLimitTime = true;
	String applyDate ="";
	String applyTime = "";
	String limitData = "";
	String limitType = "";
	String limitTime = "";
	if (!"".equals(waterMarkEndDate) && !"0".equals(waterMarkEndDate)) {
		String[] date = waterMarkEndDate.split(" ");
		applyDate = date[0];
		if(date.length > 1) {
			String[] temp = date[1].split(">"); 
			applyTime = temp[0];
			if(temp.length > 1) {
				limitData = temp[1];
				
				limitType = limitData.substring(0, 1);
				limitTime = limitData.substring(1, limitData.length());
			}
		}
	} else {
		isLimitTime = false;
	}
	
%>

<div>
	<table class="table table-bordered">
		<tr>
			<td class="th-cell-gray center-cell" width="140px" style="vertical-align: middle;">워터마크 출력선택</td>
			<td class="center-cell" style="vertical-align: middle;" width="370px">
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_water_mark_print" value="Y" <% if (isWaterMark){ %> checked <%}%> /><i></i> 출력
				</label>
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_water_mark_print" value="N" <% if (!isWaterMark){ %> checked <%}%> /><i></i> 미출력
				</label>
			</td>
			<td class="th-cell-gray center-cell" width="90px" style="vertical-align: middle;">적용 타입</td>
			<td class="center-cell">
				<input type="number" id="att_waterMark_type" name="att_waterMark_type" class="form-control" value="0" style="width:80px; margin: 0;" value="<%= waterMarkType %>" disabled />
			</td>
		</tr>
		<tr>
			<td class="th-cell-gray center-cell" width="140px" style="vertical-align: middle;">기한 제한</td>
			<td class="center-cell" style="vertical-align: middle;">
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_water_limit_use" value="Y" <% if (isWaterMark && isLimitTime){ %> checked <%}%> /><i></i> 제한
				</label>
				<label class="radio nomargin-top nomargin-bottom">
					<input type="radio" name="radio_water_limit_use" value="N" <% if (isWaterMark && !isLimitTime){ %> checked <%}%> /><i></i> 없음&nbsp;&nbsp;&nbsp;
				</label>
			</td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td class="th-cell-gray center-cell" style="vertical-align: middle;">기준 일시</td>
			<td class="center-cell">
				<input type="text" class="form-control datepicker" id="att_waterMark_apply_date" name="att_waterMark_apply_date" data-format="yyyy-mm-dd" data-lang="en" data-RTL="false" value="<%= applyDate %>" placeholder="날짜선택" style="width:110px; margin: 0; float: left;" maxlength="10" readonly="readonly" />
				<select class="form-control" id="att_waterMark_apply_hour" name="att_waterMark_apply_hour" style="width:80px; float: left;">
				</select>
				<select class="form-control" id="att_waterMark_apply_min" name="att_waterMark_apply_min" style="width:80px; float: left;">
				</select>
				<select class="form-control" id="att_waterMark_apply_sec" name="att_waterMark_apply_sec" style="width:80px; float: left;">
				</select>
			</td>
			<td class="th-cell-gray center-cell" style="vertical-align: middle;">적용 기한</td>
			<td class="center-cell">
				<select class="form-control" id="att_waterMark_limit_type" name="att_waterMark_limit_type" style="width:90px; float: left;">
					<option value="H" <% if("H".equals(limitType)){ %> selected <% }%> >시간</option>
					<option value="D" <% if("D".equals(limitType)){ %> selected <% }%> >일</option>
					<option value="M" <% if("M".equals(limitType)){ %> selected <% }%> >월</option>
				</select>
				<input class="form-control" id="att_waterMark_limit_time" name="att_waterMark_limit_time" style="width:90px; float: left;" maxlength="4" value="<%= limitTime %>" />
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

	function initWaterMarkTime(time){
		var getTime = time.split(":");
		var sel_hour = $('#att_waterMark_apply_hour');
		var sel_min = $('#att_waterMark_apply_min'); 
		var sel_sec = $('#att_waterMark_apply_sec'); 
		
		sel_hour.children('option').remove();
		sel_min.children('option').remove();
		sel_sec.children('option').remove();
		
		var max_hour = 24;
		var max_min = 60;
		var max_sec = 60;
		
		//시간 세팅
		for (var a = 0; a < max_hour; a++) {
			var hour_item = a;
			
			if (a < 10) {
				hour_item = "0" + a;
			}
			
			var hour_option = "";
			
			if (getTime[0] == hour_item) {
				hour_option = "<option value="+ hour_item + " selected>" + hour_item + "</option>";
			} else {
				hour_option = "<option value="+ hour_item + " >" + hour_item + "</option>";
			}
			 
			sel_hour.append(hour_option);
		}
		
		//분 세팅
		for (var b = 0; b < max_min; b++) {
			var min_item = b;
			if (b < 10) {
				min_item = "0" + b;
			}
			
			var min_option = ""
			
			if (getTime[1] == min_item) {
				min_option = "<option value="+ min_item + " selected>" + min_item + "</option>";
			} else {
				min_option = "<option value="+ min_item + ">" + min_item + "</option>";
			}
			
			sel_min.append(min_option);
		}
		
		//초 세팅
		for (var c = 0; c < max_sec; c++) {
			var sec_item = c;
			if (c < 10) {
				sec_item = "0" + c;
			}
			
			var sec_option = "";
			
			if (getTime[2] == sec_item) {
				sec_option = "<option value="+ sec_item + " selected>" + sec_item + "</option>";
			} else {
				sec_option = "<option value="+ sec_item + ">" + sec_item + "</option>";
			}
			
			sel_sec.append(sec_option);
		}
		
	}
	
	$(document).ready(function() {
		initWaterMarkTime('<%= applyTime %>');
	});

</script>

































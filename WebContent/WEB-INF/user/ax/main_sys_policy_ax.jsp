<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<% 
	String code = request.getAttribute("code").toString();
	List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)request.getAttribute("systemPolicyInfo");
%>

<% 
if(list.size() < 1 || list == null) { %>
	<table class="table table-bordered table-striped">
		<tbody>
			<tr><td>해당 정책은 적용되지 않았습니다.</td></tr>
		</tbody>
	</table>
<% 
} else { 
	if("system".equals(code)){ %>
	
		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>system</td></tr>
			</thead>
			<tbody>
				<tr><td>system</td></tr>
			</tbody>
		</table>
		
<%	} else if("pattern".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>pattern</td></tr>
			</thead>
			<tbody>
				<tr><td>pattern</td></tr>
			</tbody>
		</table>
		
<%	} else if("netport".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>netport</td></tr>
			</thead>
			<tbody>
				<tr><td>netport</td></tr>
			</tbody>
		</table>
		
<%	} else if("process".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>process</td></tr>
			</thead>
			<tbody>
				<tr><td>process</td></tr>
			</tbody>
		</table>
		
<%	} else if("usbport".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>usbport</td></tr>
			</thead>
			<tbody>
				<tr><td>usbport</td></tr>
			</tbody>
		</table>
		
<%	} else if("serialport".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>serialport</td></tr>
			</thead>
			<tbody>
				<tr><td>serialport</td></tr>
			</tbody>
		</table>
		
<%	} else if("messenger".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>messenger</td></tr>
			</thead>
			<tbody>
				<tr><td>messenger</td></tr>
			</tbody>
		</table>
		
<%	} else if("siteblock".equals(code)) { %>

		<table class="table table-bordered table-striped">
			<thead>
				<tr><td>siteblock</td></tr>
			</thead>
			<tbody>
				<tr><td>siteblock</td></tr>
			</tbody>
		</table>
		
<%	} else {  %>
	<table class="table table-bordered table-striped">
		<tbody>
			<tr><td>해당 정책은 적용되지 않았습니다.</td></tr>
		</tbody>
	</table>	
<% 	
	}
} 
%>

package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;
import gcom.common.util.CommonUtil;
import gcom.common.util.ConfigInfo;
import gcom.service.common.CommonServiceImpl;
import gcom.service.common.ICommonService;



public class PolicyDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public PolicyDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	

	public int getUserPolicyLogListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return result;
		}
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
	
		String sql= 
				"SELECT "
						+ "COUNT(*) AS cnt "
						+ "FROM policy_log AS policy "
						+ "INNER JOIN agent_info AS agent ON agent.policy_no = policy.no "
						+ "INNER JOIN user_info AS ur ON ur.no = agent.own_user_no "
						+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
		
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map){
		List<UserPolicyLogModel> data = new ArrayList<UserPolicyLogModel>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return data;
		}
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";

		
		whereSql += "ORDER BY policy.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "policy.no AS policy_no, "
+ "ifnull(policy.apply_time, '') AS apply_time, "
+ "ifnull(policy.request_server_time, '' ) AS request_server_time, "
+ "ifnull(policy.request_client_time, '' ) AS request_client_time, "
+ "policy.uninstall_enabled AS uninstall_enabled, "
+ "policy.file_encryption_enabled AS file_encryption_enabled, "
+ "policy.cd_encryption_enabled AS cd_encryption_enabled, "
+ "policy.printer_enabled AS printer_enabled, "
+ "policy.cd_enabled AS cd_enabled, "
+ "policy.cd_export_enabled AS cd_export_enabled, "
+ "policy.wlan_enabled AS wlan_enabled, "
+ "policy.net_share_enabled AS net_share_enabled, "
+ "policy.web_export_enabled AS web_export_enabled, "
+ "policy.removal_storage_export_enabled AS removal_storage_export_enabled, "
+ "policy.removal_storage_admin_mode AS removal_storage_admin_mode, "
+ "policy.usb_dev_list AS usb_dev_list, "
+ "policy.com_port_list AS com_port_list, "
+ "policy.net_port_list AS net_port_list, "
+ "policy.process_list AS process_list, "
+ "policy.file_pattern_list AS file_pattern_list, "
+ "policy.web_addr_list AS web_addr_list, "
+ "policy.watermark_descriptor AS watermark_descriptor, "
+ "policy.print_log_descriptor AS print_log_descriptor, "
+ "policy.quarantine_path_access_code AS quarantine_path_access_code, "
+ "policy.pattern_file_control AS pattern_file_control, "
+ "ur.no AS user_no, "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no, ur.duty, "
+ "ur.rank, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM policy_log AS policy "
+ "INNER JOIN agent_info AS agent ON agent.policy_no = policy.no "
+ "INNER JOIN user_info AS ur ON ur.no = agent.own_user_no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";


sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserPolicyLogModel model = new UserPolicyLogModel();
				model.setUserNo(rs.getInt("policy_no"));
				model.setUserNo(rs.getInt("user_no"));
				model.setRequestServerTime(rs.getString("request_server_time"));
				model.setRequestClientTime(rs.getString("request_client_time"));
				model.setApplyTime(rs.getString("apply_time"));				
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptId(rs.getInt("dept_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setPcName(rs.getString("pc_name"));
				model.setDeptName(rs.getString("dept_name"));
				model.setIsUninstall(rs.getInt("uninstall_enabled"));
				model.setIsFileEncryption(rs.getInt("file_encryption_enabled"));
				model.setIsCdEncryption(rs.getInt("cd_encryption_enabled"));
				model.setIsPrint(rs.getInt("printer_enabled"));
				model.setIsCdEnabled(rs.getInt("cd_enabled"));
				model.setIsCdExport(rs.getInt("cd_export_enabled"));
				model.setIsWlan(rs.getInt("wlan_enabled"));
				model.setIsNetShare(rs.getInt("net_share_enabled"));
				model.setIsWebExport(rs.getInt("web_export_enabled"));
				model.setIsStorageExport(rs.getInt("removal_storage_export_enabled"));
				model.setIsStorageAdmin(rs.getInt("removal_storage_admin_mode"));
				model.setIsUsbBlock(rs.getString("usb_dev_list"));
				model.setIsComPortBlock(rs.getString("com_port_list"));
				model.setIsNetPortBlock(rs.getString("net_port_list"));
				model.setIsProcessList(rs.getInt("process_list"));
				model.setIsFilePattern(rs.getInt("file_pattern_list"));
				model.setIsWebAddr(rs.getString("web_addr_list"));
				model.setWatermarkInfo(rs.getString("watermark_descriptor"));
				model.setPrintLogDesc(rs.getInt("print_log_descriptor"));
				model.setPatternFileControl(rs.getInt("pattern_file_control"));
				data.add(model);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public List<PolicyMessengerModel> getPolicyMessengerList(HashMap<String, Object> map) {
		List<PolicyMessengerModel> data = new ArrayList<PolicyMessengerModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT " 
					+ "no as msg_no, "
					+ "name as msg_name, "
					+ "process_name, "
					+ "txt_log, "
					+ "txt_block, "
					+ "file_log, "
					+ "file_block "
			    + "FROM msg_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyMessengerModel model = new PolicyMessengerModel();
				model.setMsgNo(rs.getInt("msg_no"));
				model.setMsgName(rs.getString("msg_name"));
				model.setProcessName(rs.getString("process_name"));
				model.setTxtLog(rs.getInt("txt_log"));
				model.setTxtBlock(rs.getInt("txt_block"));
				model.setFileLog(rs.getInt("file_log"));
				model.setFileBlock(rs.getInt("file_block"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}

	public int getPolicyMessengerListCount(HashMap<String, Object> map) {
		int result = 0;
			
		String sql= "SELECT COUNT(*) as cnt FROM msg_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<PolicyProcessModel> getPolicyProcessList(HashMap<String, Object> map) {
		List<PolicyProcessModel> data = new ArrayList<PolicyProcessModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as pro_no, "
					+ "process_name, "
					+ "IFNULL(process_path,'') as process_path, "
					+ "IFNULL(hash, '') as hash, "
					+ "notice, "
					+ "valid "
				+ "FROM process_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyProcessModel model = new PolicyProcessModel();
				model.setProNo(rs.getInt("pro_no"));
				model.setProcessName(rs.getString("process_name"));
				model.setProcessPath(rs.getString("process_path"));
				model.setHash(rs.getString("hash"));
				model.setNotice(rs.getString("notice"));
				model.setValid(rs.getInt("valid"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public int getPolicyProcessListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM process_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<HashMap<String, Object>> getPolicyPatternSimpleList() {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		
		String sql =
				"SELECT "
					+ "no as pattern_no, "
					+ "description as pattern_name "
				+ "FROM pattern_info "
			    + "WHERE valid = 1 ORDER BY no desc  ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HashMap<String, Object> model = new HashMap<String, Object>();
				model.put("pattern_no", rs.getInt("pattern_no"));
				model.put("pattern_name", rs.getString("pattern_name"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;

	}
	
	public List<PolicyPatternModel> getPolicyPatternList(HashMap<String, Object> map) {
		List<PolicyPatternModel> data = new ArrayList<PolicyPatternModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as pat_no, "
					+ "description as pat_name, "
					+ "IFNULL(data, '') as data, "
					+ "notice, "
					+ "valid "
				+ "FROM pattern_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyPatternModel model = new PolicyPatternModel();
				model.setPatNo(rs.getInt("pat_no"));
				model.setPatName(rs.getString("pat_name"));
				model.setData(rs.getString("data"));
				model.setNotice(rs.getString("notice"));
				model.setValid(rs.getInt("valid"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public int getPolicyPatternListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM pattern_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<PolicyNetworkModel> getPolicyNetworkList(HashMap<String, Object> map) {
		List<PolicyNetworkModel> data = new ArrayList<PolicyNetworkModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as net_no, "
					+ "name as net_name, "
					+ "port, "
					+ "descriptor, "
					+ "allow "
				+ "FROM net_port_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyNetworkModel model = new PolicyNetworkModel();
				model.setNetNo(rs.getInt("net_no"));
				model.setNetName(rs.getString("net_name"));
				model.setPort(rs.getString("port"));
				model.setDescriptor(rs.getString("descriptor"));
				model.setAllow(rs.getInt("allow"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public int getPolicyNetworkListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM net_port_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<PolicySerialModel> getPolicySerialList(HashMap<String, Object> map) {
		List<PolicySerialModel> data = new ArrayList<PolicySerialModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as serial_no, "
					+ "name as serial_name, "
					+ "allow, "
					+ "description "
				+ "FROM com_port_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicySerialModel model = new PolicySerialModel();
				model.setSerialNo(rs.getInt("serial_no"));
				model.setSerialName(rs.getString("serial_name"));
				model.setDescription(rs.getString("description"));
				model.setAllow(rs.getInt("allow"));

				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public int getPolicySerialListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM com_port_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public List<UserPolicyModel> getPolicyAssignMemberList(HashMap<String, Object> map) {
		List<UserPolicyModel> data = new ArrayList<UserPolicyModel>();
		
		String whereSql = "WHERE 1=1 AND ui.valid = 1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return data;
		}
		if(!user_id.equals("")) 	whereSql += "AND ui.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ui.name LIKE ? ";

		if(oDept != null)			whereSql += "AND ai.dept_no in ("+idList+") ";

		
		whereSql += "ORDER BY ui.no DESC LIMIT ?, ? ";	
		
		String sql= 
			"SELECT "
				+ "ai.no as agentNo, "
			    + "ui.no as userNo, "
			    + "ai.policy_no as policyNo, "
			    + "ai.dept_no as deptId, "
			    + "ui.id as userId, "
			    + "ui.name as userName, "
			    + "ui.duty as duty, "
			    + "ui.rank as rank, "
				+ "ai.ip_addr as ipAddr, "
			    + "ai.mac_addr as macAddr, "
			    + "ai.pc_name as pcName, "
			    + "di.short_name as deptName, "
			    
			    + "IFNULL(pi.uninstall_enabled, 0) as isUninstall, "
			    + "IFNULL(pi.file_encryption_enabled, 0) as isFileEncryption, "
			    + "IFNULL(pi.cd_encryption_enabled, 0) as isCdEncryption, "
			    + "IFNULL(pi.printer_enabled, 0) as isPrint, "
			    + "IFNULL(pi.cd_enabled, 0) as isCdEnabled, "
			    + "IFNULL(pi.cd_export_enabled, 0) as isCdExport, "
			    + "IFNULL(pi.wlan_enabled, 0) as isWlan, "
			    + "IFNULL(pi.net_share_enabled, 0) as isNetShare, "
			    + "IFNULL(pi.web_export_enabled, 0) as isWebExport, "
			    + "IFNULL(pi.removal_storage_export_enabled, 0) as isStorageExport, "
			    + "IFNULL(pi.removal_storage_admin_mode, 0) as isStorageAdmin, "
			    
			    + "IFNULL(pi.usb_dev_list, 'N') as isUsbBlock, "
			    + "IFNULL(pi.com_port_list, 'N') as isComPortBlock, "
			    + "IFNULL(pi.net_port_list, 'N') as isNetPortBlock, "
			    + "IFNULL(pi.process_list, '') as isProcessList, "
			    + "IFNULL(pi.file_pattern_list, '') as isFilePattern, "
			    + "IFNULL(pi.web_addr_list, 'N') as isWebAddr, "
			    + "IFNULL(pi.msg_block_list, 'N') as isMsgBlock, "
			    + "IFNULL(pi.watermark_descriptor, 'N') as isWaterMark, "
			    + "IFNULL(pi.print_log_descriptor, 0) as printLogDesc, "
			    + "IFNULL(pi.pattern_file_control, 0) as patternFileControl "
			+ "FROM agent_info AS ai "
			+ "INNER JOIN user_info AS ui ON ai.own_user_no = ui.no "
			+ "LEFT JOIN policy_info AS pi ON ai.policy_no = pi.no "
			+ "INNER JOIN dept_info as di ON ai.dept_no = di.no ";


		sql += whereSql;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
		
			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
		
			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserPolicyModel model = new UserPolicyModel();
				model.setAgentNo(rs.getInt("agentNo"));
				model.setUserNo(rs.getInt("userNo"));
				model.setPolicyNo(rs.getInt("policyNo"));
				model.setDeptId(rs.getInt("deptId"));
				model.setUserId(rs.getString("userId"));
				model.setUserName(rs.getString("userName"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ipAddr"));
				model.setMacAddr(rs.getString("macAddr"));
				model.setPcName(rs.getString("pcName"));
				model.setDeptName(rs.getString("deptName"));
				model.setIsUninstall(rs.getInt("isUninstall"));
				model.setIsFileEncryption(rs.getInt("isFileEncryption"));
				model.setIsCdEncryption(rs.getInt("isCdEncryption"));
				model.setIsPrint(rs.getInt("isPrint"));
				model.setIsCdEnabled(rs.getInt("isCdEnabled"));
				model.setIsCdExport(rs.getInt("isCdExport"));
				model.setIsWlan(rs.getInt("isWlan"));
				model.setIsNetShare(rs.getInt("isNetShare"));
				model.setIsWebExport(rs.getInt("isWebExport"));
				model.setIsStorageExport(rs.getInt("isStorageExport"));
				model.setIsStorageAdmin(rs.getInt("isStorageAdmin"));
				model.setIsUsbBlock(rs.getString("isUsbBlock"));
				model.setIsComPortBlock(rs.getString("isComPortBlock"));
				model.setIsNetPortBlock(rs.getString("isNetPortBlock"));
				model.setIsProcessList(rs.getString("isProcessList"));
				model.setIsFilePattern(rs.getString("isFilePattern"));
				model.setIsWebAddr(rs.getString("isWebAddr"));
				model.setIsMsgBlock(rs.getString("isMsgBlock"));
				model.setWatermarkInfo(rs.getString("isWaterMark"));
				model.setPrintLogDesc(rs.getInt("printLogDesc"));
				model.setPatternFileControl(rs.getInt("patternFileControl"));
				
				data.add(model);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}


	public int getPolicyAssignMemberListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return result;
		}
		if(oDept != null)			whereSql += "AND ai.dept_no in ("+idList+") ";

		if(!user_id.equals("")) 	whereSql += "AND ui.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ui.name LIKE ? ";
	
		String sql= 
				"SELECT "
						+ "COUNT(*) AS cnt "
						+ "FROM agent_info AS ai "
						+ "INNER JOIN user_info AS ui ON ai.own_user_no = ui.no "
						+ "LEFT JOIN policy_info AS pi ON ai.policy_no = pi.no ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}

			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	

	public int getRequestedPolicyListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
/*		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();
*/		

		if(!user_id.equals("")) 	whereSql += "AND ur.user_id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.user_name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND ur.phone LIKE ? ";

		/*		if(!start_date.equals("")) 	whereSql += "AND request.client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND (request.client_time < ? + interval 1 day) ";
*/



		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return result;
		}
		if(oDept != null)			whereSql += " AND ur.dept_no in ("+idList+") ";
		
		String sql= 
"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM policy_request_info AS request "
+ "INNER JOIN user_info AS ur ON ur.no = request.user_no "
+ "INNER JOIN agent_info AS agent ON agent.no = request.agent_no "
+ "INNER JOIN policy_info As policy ON agent.policy_no = policy.no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;

			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++, "%" + user_phone + "%");

			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
	
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<PolicyRequestInfo> getRequestedPolicyList(HashMap<String, Object> map){
		List<PolicyRequestInfo> data = new ArrayList<PolicyRequestInfo>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
/*		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();
*/		

		if(!user_id.equals("")) 	whereSql += "AND ur.user_id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.user_name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND ur.phone LIKE ? ";

		/*		if(!start_date.equals("")) 	whereSql += "AND request.client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND (request.client_time < ? + interval 1 day) ";
*/
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();
		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}else{
			return data;
		}
		if(oDept != null)			whereSql += " AND ur.dept_no in ("+idList+") ";
		
		

		
		whereSql += "ORDER BY request.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "request.no AS request_no, "
+ "request.new_policy_uninstall_enabled, "
+ "request.new_policy_file_encryption_enabled, "
+ "request.new_policy_cd_encryption_enabled, "
+ "request.new_policy_printer_enabled, "
+ "request.new_policy_cd_enabled, "
+ "request.new_policy_cd_export_enabled, "
+ "request.new_policy_wlan_enabled, "
+ "request.new_policy_net_share_enabled, "
+ "request.new_policy_web_export_enabled, "
+ "request.new_policy_removal_storage_export_enabled, "
+ "request.new_policy_removal_storage_admin_mode, "
+ "request.new_policy_usb_dev_list, "
+ "request.new_policy_com_port_list, "
+ "request.new_policy_net_port_list, "
+ "request.new_policy_process_list, "
+ "request.new_policy_file_pattern_list, "
+ "request.new_policy_web_addr_list, "
+ "request.new_policy_msg_block_list, "
+ "request.new_policy_watermark_descriptor, "
+ "request.new_policy_print_log_descriptor, "
+ "request.new_policy_quarantine_path_access_code, "
+ "request.new_policy_pattern_file_control, "
+ "request.notice, "
+ "request.request_server_time, "
+ "request.request_client_time, "
+ "request.permit, "
+ "IFNULL(request.permit_date, '') as permit_date, "
+ "IFNULL(request.permit_admin_id, '') as permit_admin_id, "
+ "policy.no as policy_no, "
+ "policy.uninstall_enabled AS uninstall_enabled, "
+ "policy.file_encryption_enabled AS file_encryption_enabled, "
+ "policy.cd_encryption_enabled, policy.printer_enabled, "
+ "policy.cd_enabled, "
+ "policy.cd_export_enabled, "
+ "policy.wlan_enabled, "
+ "policy.net_share_enabled, "
+ "policy.web_export_enabled, "
+ "policy.removal_storage_export_enabled, "
+ "policy.removal_storage_admin_mode, "
+ "policy.usb_dev_list, "
+ "policy.com_port_list, "
+ "policy.net_port_list, "
+ "policy.process_list, "
+ "policy.file_pattern_list, "
+ "policy.web_addr_list, "
+ "policy.msg_block_list, "
+ "policy.watermark_descriptor, "
+ "policy.print_log_descriptor, "
+ "policy.quarantine_path_access_code, "
+ "policy.pattern_file_control, "
+ "ur.no AS user_no, "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no, "
+ "ur.duty, "
+ "ur.rank, "
+ "ur.phone, "
+ "agent.no as agent_no, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM policy_request_info AS request "
+ "INNER JOIN user_info AS ur ON ur.no = request.user_no "
+ "INNER JOIN agent_info AS agent ON agent.no = request.agent_no "
+ "INNER JOIN policy_info As policy ON agent.policy_no = policy.no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++, "%" + user_phone + "%");
		 	if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyRequestInfo model = new PolicyRequestInfo();
				UserPolicyModel p_model = new UserPolicyModel();

				model.setRequestNo(rs.getInt("request_no"));
				model.setUserNo(rs.getInt("request_no"));
				
				model.setIpAddr(rs.getString("ip_addr"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setPcName(rs.getString("pc_name"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPhone(rs.getString("phone"));
				model.setReqNotice(rs.getString("notice"));
				model.setPermitState(rs.getString("permit"));
				model.setPermitDate(rs.getString("permit_date"));
				model.setPermitStaf(rs.getString("permit_admin_id"));
				
				model.setIsUninstall(rs.getInt("new_policy_uninstall_enabled"));
				model.setIsFileEncryption(rs.getInt("new_policy_file_encryption_enabled"));
				model.setIsCdEncryption(rs.getInt("new_policy_cd_encryption_enabled"));
				model.setIsPrint(rs.getInt("new_policy_printer_enabled"));
				model.setIsCdEnabled(rs.getInt("new_policy_cd_enabled"));
				model.setIsCdExport(rs.getInt("new_policy_cd_export_enabled"));
				model.setIsWlan(rs.getInt("new_policy_wlan_enabled"));
				model.setIsNetShare(rs.getInt("new_policy_net_share_enabled"));
				model.setIsWebExport(rs.getInt("new_policy_web_export_enabled"));
				model.setIsStorageExport(rs.getInt("new_policy_removal_storage_export_enabled"));
				model.setIsStorageAdmin(rs.getInt("new_policy_removal_storage_admin_mode"));
				
				model.setIsUsbBlock(rs.getString("new_policy_usb_dev_list"));
				model.setIsComPortBlock(rs.getString("new_policy_com_port_list"));
				model.setIsNetPortBlock(rs.getString("new_policy_net_port_list"));
				model.setIsProcessList(rs.getString("new_policy_process_list"));
				model.setIsFilePattern(rs.getString("new_policy_file_pattern_list"));
				model.setIsWebAddr(rs.getString("new_policy_web_addr_list"));
				model.setIsMsgBlock(rs.getString("new_policy_msg_block_list"));
				model.setWatermarkInfo(rs.getString("new_policy_watermark_descriptor"));
				model.setPrintLogDesc(rs.getInt("new_policy_print_log_descriptor"));
				model.setPatternFileControl(rs.getInt("new_policy_pattern_file_control"));

				p_model.setAgentNo(rs.getInt("agent_no"));
				p_model.setPolicyNo(rs.getInt("policy_no"));
				p_model.setIsUninstall(rs.getInt("uninstall_enabled"));
				p_model.setIsFileEncryption(rs.getInt("file_encryption_enabled"));
				p_model.setIsCdEncryption(rs.getInt("cd_encryption_enabled"));
				p_model.setIsPrint(rs.getInt("printer_enabled"));
				p_model.setIsCdEnabled(rs.getInt("cd_enabled"));
				p_model.setIsCdExport(rs.getInt("cd_export_enabled"));
				p_model.setIsWlan(rs.getInt("wlan_enabled"));
				p_model.setIsNetShare(rs.getInt("net_share_enabled"));
				p_model.setIsWebExport(rs.getInt("web_export_enabled"));
				p_model.setIsStorageExport(rs.getInt("removal_storage_export_enabled"));
				p_model.setIsStorageAdmin(rs.getInt("removal_storage_admin_mode"));
				p_model.setIsUsbBlock(rs.getString("usb_dev_list"));
				p_model.setIsComPortBlock(rs.getString("com_port_list"));
				p_model.setIsNetPortBlock(rs.getString("net_port_list"));
				p_model.setIsProcessList(rs.getString("process_list"));
				p_model.setIsFilePattern(rs.getString("file_pattern_list"));
				p_model.setIsWebAddr(rs.getString("web_addr_list"));
				p_model.setIsMsgBlock(rs.getString("msg_block_list"));
				p_model.setWatermarkInfo(rs.getString("watermark_descriptor"));
				p_model.setPrintLogDesc(rs.getInt("print_log_descriptor"));
				p_model.setPatternFileControl(rs.getInt("pattern_file_control"));

				model.setOldPolicy(p_model);

				data.add(model);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}


	public List<UsbDevInfoModel> getPolicyUsbBlockList(HashMap<String, Object> map) {
		List<UsbDevInfoModel> data = new ArrayList<UsbDevInfoModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as usb_id, "
					+ "name as usb_name, "
					+ "vid, "
					+ "pid, "
					+ "serial_number, "
					+ "allow, "
					+ "description "
				+ "FROM usb_dev_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UsbDevInfoModel model = new UsbDevInfoModel();
				model.setUsbId(rs.getInt("usb_id"));
				model.setName(rs.getString("usb_name"));
				model.setVid(rs.getString("vid"));
				model.setPid(rs.getString("pid"));
				model.setSerialNumber(rs.getString("serial_number"));
				model.setDescription(rs.getString("description"));
				
				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}


	public int getPolicyUsbBlockListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM usb_dev_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public List<PolicyWebSiteBlocklModel> getPolicyWebSiteBlockList(HashMap<String, Object> map) {
		List<PolicyWebSiteBlocklModel> data = new ArrayList<PolicyWebSiteBlocklModel>();
		int start_date = Integer.parseInt(map.get("startRow").toString());
		int end_date = Integer.parseInt(map.get("endRow").toString());
		
		String sql =
				"SELECT "
					+ "no as site_id, "
					+ "address, "
					+ "allow, "
					+ "description "
				+ "FROM web_addr_info "
			    + "ORDER BY no desc LIMIT ?, ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  start_date);
			pstmt.setInt(2,  end_date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyWebSiteBlocklModel model = new PolicyWebSiteBlocklModel();
				model.setSiteId(rs.getInt("site_id"));
				model.setAddress(rs.getString("address"));
				model.setAllow(rs.getInt("allow"));
				model.setDescription(rs.getString("description"));
				
				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}


	public int getPolicyWebSiteBlockListCount(HashMap<String, Object> map) {
		int result = 0;
		
		String sql= "SELECT COUNT(*) as cnt FROM web_addr_info ";
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt("cnt");				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicyMessengerModel getMsgInfo(int code) {
		PolicyMessengerModel model = new PolicyMessengerModel();
		int msgNo = code;
		
		String sql =
				"SELECT " 
					+ "no as msg_no, "
					+ "name as msg_name, "
					+ "process_name, "
					+ "txt_log, "
					+ "txt_block, "
					+ "file_log, "
					+ "file_block "
			    + "FROM msg_info "
			    + "WHERE no = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  msgNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setMsgNo(rs.getInt("msg_no"));
				model.setMsgName(rs.getString("msg_name"));
				model.setProcessName(rs.getString("process_name"));
				model.setTxtLog(rs.getInt("txt_log"));
				model.setTxtBlock(rs.getInt("txt_block"));
				model.setFileLog(rs.getInt("file_log"));
				model.setFileBlock(rs.getInt("file_block"));
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyMsgSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String msgName = map.get("msg_name").toString();
		String msgProName = map.get("msg_pro_name").toString();
		int txt_log = Integer.parseInt(map.get("txt_log").toString());
		int txt_block = Integer.parseInt(map.get("txt_block").toString());
		int file_log = Integer.parseInt(map.get("file_log").toString());
		int file_block = Integer.parseInt(map.get("file_block").toString());
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO msg_info (name, process_name, txt_log, txt_block, file_log, file_block) VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, msgName);
			pstmt.setString(2, msgProName);
			pstmt.setInt(3, txt_log);
			pstmt.setInt(4, txt_block);
			pstmt.setInt(5, file_log);
			pstmt.setInt(6, file_block);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyMsgUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int msgNo = Integer.parseInt(map.get("msg_no").toString());
		String msgName = map.get("msg_name").toString();
		String msgProName = map.get("msg_pro_name").toString();
		int txt_log = Integer.parseInt(map.get("txt_log").toString());
		int txt_block = Integer.parseInt(map.get("txt_block").toString());
		int file_log = Integer.parseInt(map.get("file_log").toString());
		int file_block = Integer.parseInt(map.get("file_block").toString());
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE msg_info  SET name = ?, process_name = ?, txt_log = ?, txt_block = ?, file_log = ?, file_block = ? WHERE no = ?";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, msgName);
			pstmt.setString(2, msgProName);
			pstmt.setInt(3, txt_log);
			pstmt.setInt(4, txt_block);
			pstmt.setInt(5, file_log);
			pstmt.setInt(6, file_block);
			pstmt.setInt(7, msgNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicyProcessModel getProcessInfo(int code) {
		PolicyProcessModel model = new PolicyProcessModel();
		int proNo = code;
		
		String sql =
				"SELECT "
					+ "no as pro_no, "
					+ "process_name, "
					+ "IFNULL(process_path,'') as process_path, "
					+ "IFNULL(hash, '') as hash, "
					+ "notice, "
					+ "valid "
				+ "FROM process_info "
				+ "WHERE no = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  proNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setProNo(rs.getInt("pro_no"));
				model.setProcessName(rs.getString("process_name"));
				model.setProcessPath(rs.getString("process_path"));
				model.setHash(rs.getString("hash"));
				model.setNotice(rs.getString("notice"));
				model.setValid(rs.getInt("valid"));

			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyProcessSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String proName = map.get("pro_name").toString();
		String proPath = map.get("pro_path").toString();
		String hash = map.get("hash").toString();
		String notice = map.get("notice").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO process_info (process_name, process_path, hash, notice, valid) VALUES (?, ?, ?, ?, ?)";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, proName);
			pstmt.setString(2, proPath);
			pstmt.setString(3, hash);
			pstmt.setString(4, notice);
			pstmt.setInt(5, valid);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyProcessUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int proNo = Integer.parseInt(map.get("pro_no").toString());
		String proName = map.get("pro_name").toString();
		String proPath = map.get("pro_path").toString();
		String hash = map.get("hash").toString();
		String notice = map.get("notice").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE process_info SET process_name = ?, process_path = ?, hash = ?, notice = ?, valid = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, proName);
			pstmt.setString(2, proPath);
			pstmt.setString(3, hash);
			pstmt.setString(4, notice);
			pstmt.setInt(5, valid);
			pstmt.setInt(6, proNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicyPatternModel getPatternInfo(int code) {
		PolicyPatternModel model = new PolicyPatternModel();
		int patId = code;
		
		String sql =
				"SELECT "
					+ "no as pat_no, "
					+ "description as pat_name, "
					+ "IFNULL(data, '') as data, "
					+ "notice, "
					+ "valid "
				+ "FROM pattern_info "
				+ "WHERE no = ?";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, patId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setPatNo(rs.getInt("pat_no"));
				model.setPatName(rs.getString("pat_name"));
				model.setData(rs.getString("data"));
				model.setNotice(rs.getString("notice"));
				model.setValid(rs.getInt("valid"));
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyPatternSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String patName = map.get("pat_name").toString();
		String patData = map.get("pat_data").toString();
		String notice = map.get("notice").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO pattern_info (description, data, notice, valid) VALUES (?, ?, ?, ?)";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, patName);
			pstmt.setString(2, patData);
			pstmt.setString(3, notice);
			pstmt.setInt(4, valid);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyPatternUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int patNo = Integer.parseInt(map.get("pat_no").toString());
		String patName = map.get("pat_name").toString();
		String patData = map.get("pat_data").toString();
		String notice = map.get("notice").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE pattern_info SET description = ?, data = ?, notice = ?, valid = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, patName);
			pstmt.setString(2, patData);
			pstmt.setString(3, notice);
			pstmt.setInt(4, valid);
			pstmt.setInt(5, patNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicyNetworkModel getNetworkInfo(int code) {
		PolicyNetworkModel model = new PolicyNetworkModel();
		int netId = code;
		
		String sql =
				"SELECT "
					+ "no as net_no, "
					+ "name as net_name, "
					+ "port, "
					+ "descriptor, "
					+ "allow "
				+ "FROM net_port_info "
				+ "WHERE no = ?";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  netId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				model.setNetNo(rs.getInt("net_no"));
				model.setNetName(rs.getString("net_name"));
				model.setPort(rs.getString("port"));
				model.setDescriptor(rs.getString("descriptor"));
				model.setAllow(rs.getInt("allow"));

			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyNetworkSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String netName = map.get("net_name").toString();
		String netPort = map.get("net_port").toString();
		String descript = map.get("descript").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO net_port_info (name, port, descriptor, allow) VALUES (?, ?, ?, ?) ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, netName);
			pstmt.setString(2, netPort);
			pstmt.setString(3, descript);
			pstmt.setInt(4, valid);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyNetworkUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int netNo = Integer.parseInt(map.get("net_no").toString());
		String netName = map.get("net_name").toString();
		String netPort = map.get("net_port").toString();
		String descript = map.get("descript").toString();
		int valid = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE net_port_info SET name = ?, port = ?, descriptor = ?, allow = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, netName);
			pstmt.setString(2, netPort);
			pstmt.setString(3, descript);
			pstmt.setInt(4, valid);
			pstmt.setInt(5, netNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicySerialModel getSerialInfo(int code) {
		PolicySerialModel model = new PolicySerialModel();
		int serialId = code;
		
		String sql =
				"SELECT "
					+ "no as serial_no, "
					+ "name as serial_name, "
					+ "allow, "
					+ "description "
				+ "FROM com_port_info "
				+ "WHERE no = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  serialId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setSerialNo(rs.getInt("serial_no"));
				model.setSerialName(rs.getString("serial_name"));
				model.setDescription(rs.getString("description"));
				model.setAllow(rs.getInt("allow"));

			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicySerialSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String serialName = map.get("serial_name").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO com_port_info (name, allow, description) VALUES (?, ?, ?) ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, serialName);
			pstmt.setInt(2, allow);
			pstmt.setString(3, descript);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicySerialUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int serialNo = Integer.parseInt(map.get("serial_no").toString());
		String serialName = map.get("serial_name").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE com_port_info SET name = ?, allow = ?, description = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, serialName);
			pstmt.setInt(2, allow);
			pstmt.setString(3, descript);
			pstmt.setInt(4, serialNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public PolicyWebSiteBlocklModel getWebsiteInfo(int code) {
		PolicyWebSiteBlocklModel model = new PolicyWebSiteBlocklModel();
		int siteId = code;
		
		String sql =
				"SELECT "
					+ "no as site_id, "
					+ "address, "
					+ "allow, "
					+ "description "
				+ "FROM web_addr_info "
				+ "WHERE no = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  siteId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setSiteId(rs.getInt("site_id"));
				model.setAddress(rs.getString("address"));
				model.setAllow(rs.getInt("allow"));
				model.setDescription(rs.getString("description"));
				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyWebsiteSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String siteAddress = map.get("site_address").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO web_addr_info (address, allow, description) VALUES (?, ?, ?) ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, siteAddress);
			pstmt.setInt(2, allow);
			pstmt.setString(3, descript);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyWebsiteUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int siteNo = Integer.parseInt(map.get("site_no").toString());
		String siteAddress = map.get("site_address").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE web_addr_info SET address = ?, allow = ?, description = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, siteAddress);
			pstmt.setInt(2, allow);
			pstmt.setString(3, descript);
			pstmt.setInt(4, siteNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public UsbDevInfoModel getUsbInfo(int code) {
		UsbDevInfoModel model = new UsbDevInfoModel();
		int usbId = code;
		
		String sql =
				"SELECT "
					+ "no as usb_id, "
					+ "name as usb_name, "
					+ "vid, "
					+ "pid, "
					+ "serial_number, "
					+ "allow, "
					+ "description "
				+ "FROM usb_dev_info "
				+ "WHERE no = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  usbId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				model.setUsbId(rs.getInt("usb_id"));
				model.setName(rs.getString("usb_name"));
				model.setVid(rs.getString("vid"));
				model.setPid(rs.getString("pid"));
				model.setSerialNumber(rs.getString("serial_number"));
				model.setDescription(rs.getString("description"));
				
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return model;
	}


	public HashMap<String, Object> insertPolicyUsbSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String usbName = map.get("usb_name").toString();
		String vid = map.get("vid").toString();
		String pid = map.get("pid").toString();
		String serial = map.get("serial").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO usb_dev_info (name, vid, pid, serial_number, allow, description) VALUES (?, ?, ?, ?, ?, ?) ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, usbName);
			pstmt.setString(2, vid);
			pstmt.setString(3, pid);
			pstmt.setString(4, serial);
			pstmt.setInt(5, allow);
			pstmt.setString(6, descript);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePolicyUsbUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int usbNo = Integer.parseInt(map.get("usb_no").toString());
		String usbName = map.get("usb_name").toString();
		String vid = map.get("vid").toString();
		String pid = map.get("pid").toString();
		String serial = map.get("serial").toString();
		String descript = map.get("descript").toString();
		int allow = 1;	// 추후 수정?? (DB : default 또는 front 정책 설정)
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE usb_dev_info SET name = ?, vid = ?, pid = ?, serial_number = ?, allow = ?, description = ? WHERE no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, usbName);
			pstmt.setString(2, vid);
			pstmt.setString(3, pid);
			pstmt.setString(4, serial);
			pstmt.setInt(5, allow);
			pstmt.setString(6, descript);
			pstmt.setInt(7, usbNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public HashMap<String, Object> insertBlockDeviceSaveWithGetAgentData(HashMap<String, Object> map) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		int deviceId = Integer.parseInt(map.get("deviceId").toString());
		int allow = 1; // 추후 필요시 파라미터 대입
		
		// 장치 로그 정보 검색
		String sql= "SELECT " 
					+ "no, "
					+ "user_no, "
					+ "device_name, "
					+ "vid, "
					+ "pid, "
					+ "serial_number "
					+ "FROM usb_connect_log " 
					+ "WHERE no = ? ";

			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, deviceId);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column = "";
			
			if (rs.next()) {
				HashMap<String, Object> device_info = new HashMap<String, Object>();
				
				// Column의 갯수만큼 회전
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column = metaData.getColumnName(indexOfcolumn + 1);
					// phone number 일 경우 복호화
					device_info.put(column, rs.getString(column));
				}
				
				data.put("user_no", device_info.get("user_no"));
				
				// USB 등록 확인
				sql = "SELECT no FROM usb_dev_info WHERE vid = ? AND pid = ? AND serial_number = ? ";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, device_info.get("vid").toString());
				pstmt.setString(2, device_info.get("pid").toString());
				pstmt.setString(3, device_info.get("serial_number").toString());
				rs = pstmt.executeQuery();
				
				int usb_no = 0;
				
				if (rs != null && rs.next()) {
					usb_no = rs.getInt("no");
					data.put("usb_no", usb_no);
				} else {
					
					con.setAutoCommit(false);
					
					sql= "INSERT INTO usb_dev_info (name, vid, pid, serial_number, allow, description) VALUES (?, ?, ?, ?, ?, ?) ";
					
					pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, device_info.get("device_name").toString());
					pstmt.setString(2, device_info.get("vid").toString());
					pstmt.setString(3, device_info.get("pid").toString());
					pstmt.setString(4, device_info.get("serial_number").toString());
					pstmt.setInt(5, allow);
					pstmt.setString(6, "차단 Device 허용");
					pstmt.executeUpdate();
					
					rs = pstmt.getGeneratedKeys();
					
					if (rs.next()) {
						usb_no = rs.getInt(1);
					}
					
					data.put("usb_no", usb_no);
					con.commit();
				}
								
				data.put("returnCode", ConfigInfo.RETURN_CODE_SUCCESS);
			} 
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return data;
	}


	public HashMap<String, Object> setUserPolicyDeviceData(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String usb_no = map.get("usb_no").toString();
		int user_no = Integer.parseInt(map.get("user_no").toString());
		int sCount = 0;
		int fCount = 0;
		
		String sql= "SELECT "
					+ "ai.no as agent_no, "
						+ "ai.policy_no, "
						+ "pi.usb_dev_list "
						+ "FROM agent_info as ai "
					+ "INNER JOIN user_info as ui ON ai.own_user_no = ui.no "
					+ "LEFT JOIN policy_info as pi ON ai.policy_no = pi.no "
					+ "WHERE ui.no = ? AND ui.valid = 1 ";

			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, user_no);
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				int policy_no = 0;
				
				while (rs.next()) {
					policy_no = rs.getInt("policy_no");
					
					if(policy_no == 0) {
						fCount ++;
					} else {
						String usbPolicy = rs.getString("usb_dev_list");
						String savePolicy = CommonUtil.getStrPolicyChangeOperation(usb_no, usbPolicy);
						
						if (!"".equals(savePolicy)) {
							
							con.setAutoCommit(false);
							
							sql = "UPDATE policy_info SET usb_dev_list = ? WHERE no = ? ";
							
							pstmt=con.prepareStatement(sql);
							pstmt.setString(1, savePolicy);
							pstmt.setInt(2, policy_no);
							pstmt.executeUpdate();
							
							con.commit();
							
							// 정책 변경 로그
							ICommonService commonService = new CommonServiceImpl();
							commonService.setPolicyUpdateToInsertLog(policy_no);
						}
						
						sCount ++;
					}
				}
								
				result.put("success_cnt", sCount);
				result.put("fail_cnt", fCount);
				result.put("returnCode", ConfigInfo.RETURN_CODE_SUCCESS);
			} else {
				// Agent 정보 없음.
				result.put("returnCode", ConfigInfo.EXIST_NOT_AGENT);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updatePermitRequestPolicy(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int policyNo = Integer.parseInt(map.get("policy_no").toString());
		int requestNo = Integer.parseInt(map.get("request_no").toString());
		String adminId = map.get("admin_id").toString();
		
		String sql= "UPDATE policy_info AS pi, (SELECT * FROM policy_request_info WHERE no = ?) AS pri "
				+ "SET "
					+ "pi.uninstall_enabled = pri.new_policy_uninstall_enabled"
					+ ",pi.file_encryption_enabled = pri.new_policy_file_encryption_enabled"
					+ ",pi.cd_encryption_enabled = pri.new_policy_cd_encryption_enabled"
					+ ",pi.printer_enabled = pri.new_policy_printer_enabled"
					+ ",pi.cd_enabled = pri.new_policy_cd_enabled"
					+ ",pi.cd_export_enabled = pri.new_policy_cd_export_enabled"
					+ ",pi.wlan_enabled = pri.new_policy_wlan_enabled" 
					+ ",pi.net_share_enabled = pri.new_policy_net_share_enabled"
					+ ",pi.web_export_enabled = pri.new_policy_web_export_enabled"
				    + ",pi.removal_storage_export_enabled = pri.new_policy_removal_storage_export_enabled"
				    + ",pi.removal_storage_admin_mode = pri.new_policy_removal_storage_admin_mode"
					+ ",pi.usb_dev_list = pri.new_policy_usb_dev_list"
					+ ",pi.com_port_list = pri.new_policy_com_port_list"
					+ ",pi.net_port_list = pri.new_policy_net_port_list"
					+ ",pi.process_list = pri.new_policy_process_list"
					+ ",pi.file_pattern_list = pri.new_policy_file_pattern_list"
					+ ",pi.web_addr_list = pri.new_policy_web_addr_list"
					+ ",pi.msg_block_list = pri.new_policy_msg_block_list"
					+ ",pi.watermark_descriptor = pri.new_policy_watermark_descriptor"
					+ ",pi.print_log_descriptor = pri.new_policy_print_log_descriptor"
				    + ",pi.quarantine_path_access_code = pri.new_policy_quarantine_path_access_code"
					+ ",pi.pattern_file_control = pri.new_policy_pattern_file_control "
				+ "WHERE pi.no = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, requestNo);
			pstmt.setInt(2, policyNo);
			pstmt.executeUpdate();
			
			sql = "UPDATE policy_request_info "
				+ "SET "
					+ "permit = 'P' "
					+ ",permit_date = NOW()"
					+ ",permit_admin_id = ? "
				+ "WHERE no = ? "; 
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setInt(2, requestNo);
			pstmt.executeUpdate();
			
			con.commit();
			
			// 정책 변경 로그
			ICommonService commonService = new CommonServiceImpl();
			commonService.setPolicyUpdateToInsertLog(policyNo);
			
			result.put("returnCode", ConfigInfo.RETURN_CODE_SUCCESS);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public HashMap<String, Object> updateRejectRequestPolicy(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		int requestNo = Integer.parseInt(map.get("request_no").toString());
		String adminId = map.get("admin_id").toString();
		
		String sql = "UPDATE policy_request_info "
				+ "SET "
				+ "permit = 'R' "
				+ ",permit_date = NOW()"
				+ ",permit_admin_id = ? "
			+ "WHERE no = ? "; 
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, adminId);
			pstmt.setInt(2, requestNo);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", ConfigInfo.RETURN_CODE_SUCCESS);
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
			ex.printStackTrace();
		}finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}

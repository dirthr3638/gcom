package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;


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
+ "policy.policy_uninstall_enabled AS uninstall_enabled, "
+ "policy.policy_file_encryption_enabled AS file_encryption_enabled, "
+ "policy.policy_cd_encryption_enabled AS cd_encryption_enabled, "
+ "policy.policy_printer_enabled AS printer_enabled, "
+ "policy.policy_cd_enabled AS cd_enabled, "
+ "policy.policy_cd_export_enabled AS cd_export_enabled, "
+ "policy.policy_wlan_enabled AS wlan_enabled, "
+ "policy.policy_net_share_enabled AS net_share_enabled, "
+ "policy.policy_web_export_enabled AS web_export_enabled, "
+ "policy.policy_removal_storage_export_enabled AS removal_storage_export_enabled, "
+ "policy.policy_removal_storage_admin_mode AS removal_storage_admin_mode, "
+ "policy.policy_usb_dev_list AS usb_dev_list, "
+ "policy.policy_com_port_list AS com_port_list, "
+ "policy.policy_net_port_list AS net_port_list, "
+ "policy.policy_process_list AS process_list, "
+ "policy.policy_file_pattern_list AS file_pattern_list, "
+ "policy.policy_web_addr_list AS web_addr_list, "
+ "policy.policy_watermark_descriptor AS watermark_descriptor, "
+ "policy.policy_print_log_descriptor AS print_log_descriptor, "
+ "policy.policy_quarantine_path_access_code AS quarantine_path_access_code, "
+ "policy.policy_pattern_file_control AS pattern_file_control, "
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
	
	public int getAuditClientLogListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();
		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";

		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND audit.audit_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND audit.audit_client_time < ? + interval 1 day ";

	
		String sql= 
				"SELECT "
						+ "COUNT(*) AS cnt "
						+ "FROM client_audit AS audit "
						+ "INNER JOIN user_info AS ur ON ur.no = audit.user_no "
						+ "INNER JOIN agent_info AS agent ON agent.own_user_no = ur.no "
						+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";

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
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);

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
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map){
		List<AuditClientModel> data = new ArrayList<AuditClientModel>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();

		
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND audit.audit_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND (audit.audit_client_time < ? + interval 1 day) ";


		
		whereSql += "ORDER BY audit.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "audit.no AS audit_no, "
+ "audit.module_name AS module_name, "
+ "audit.description AS description, "
+ "audit.audit_server_time, "
+ "audit.audit_client_time, "
+ "audit.status, "
+ "ur.no AS user_no,  "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no, "
+ "ur.duty,  "
+ "ur.rank, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM client_audit AS audit "
+ "INNER JOIN user_info AS ur ON ur.no = audit.user_no "
+ "INNER JOIN agent_info AS agent ON agent.own_user_no = ur.no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no ";


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
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
	
			System.out.println(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AuditClientModel model = new AuditClientModel();
				model.setAuditNo(rs.getInt("audit_no"));
				model.setModuleName(rs.getString("module_name"));
				model.setDescription(rs.getString("description"));
				model.setServerTime(rs.getString("audit_server_time"));
				model.setClientTime(rs.getString("audit_client_time"));
				model.setStatus(rs.getString("status"));
				model.setUserNo(rs.getInt("user_no"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptId(rs.getInt("dept_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setPcName(rs.getString("pc_name"));
				model.setDeptName(rs.getString("dept_name"));
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
	
	
	public int getAuditServerLogListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();


		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND audit.audit_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND (audit.audit_time < ? + interval 1 day) ";


	
		String sql= 
				"SELECT "
						+ "COUNT(*) AS cnt "
						+ "FROM server_audit AS audit ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;

			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);
		
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
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map){
		List<AuditServerModel> data = new ArrayList<AuditServerModel>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();

		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND audit.audit_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND (audit.audit_time < ? + interval 1 day) ";


		
		whereSql += "ORDER BY audit.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "audit.no AS audit_no, "
+ "audit.id AS admin_id, "
+ "audit.ip AS ip, "
+ "audit.parameter, "
+ "audit.description, "
+ "audit.audit_time, "
+ "audit.status "
+ "FROM server_audit AS audit ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);


			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AuditServerModel model = new AuditServerModel();
				model.setAuditNo(rs.getInt("audit_no"));
				model.setIpAddr(rs.getString("ip"));
				model.setAdminId(rs.getString("admin_id"));
				model.setParameter(rs.getString("parameter"));
				model.setDescription(rs.getString("description"));
				model.setAuditTime(rs.getString("audit_time"));
				model.setStatus(rs.getString("status"));

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
	
	
	public String getAuditServerWorkData(int key){
		String result = "";
		
		String whereSql = "WHERE no = ? ";
	
		String sql= 
				"SELECT "
						+ "parameter AS data "
						+ "FROM server_audit AS audit ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;

			pstmt.setInt(i++, key);
		
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getString("data");				
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
	
}

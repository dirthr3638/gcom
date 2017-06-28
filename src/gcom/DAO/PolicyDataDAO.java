package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyInfoModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PrintFileModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.UserPolicyModel;
import gcom.Model.statistic.AuditClientSimpleModel;


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
	
	public List<AuditClientSimpleModel> getAuditClientSimpleLogList(Map<String, Object> map){
		List<AuditClientSimpleModel> data = new ArrayList<AuditClientSimpleModel>();
		
		String whereSql = "WHERE 1=1 ";
		
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
		
		whereSql += "ORDER BY audit.no DESC LIMIT 9 ";	
		
		String sql= 
"SELECT "
+ "audit.no AS audit_no, "
+ "audit.description, "
+ "audit.level, "
+ "ur.name AS user_name, "
+ "dept.short_name "
+ "FROM client_audit AS audit "
+ "INNER JOIN user_info AS ur ON ur.no = audit.user_no "
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

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				AuditClientSimpleModel model = new AuditClientSimpleModel();
				model.setAuditNo(rs.getInt("audit_no"));
				model.setAction(rs.getString("description"));
				model.setDeptName(rs.getString("short_name"));
				model.setUserName(rs.getString("user_name"));
				model.setLevel(rs.getInt("level"));
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

		
		whereSql += "ORDER BY request.no DESC LIMIT ?, ? ";	
		
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

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
	
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
+ "request.new_policy_watermark_descriptor, "
+ "request.new_policy_print_log_descriptor, "
+ "request.new_policy_quarantine_path_access_code, "
+ "request.new_policy_pattern_file_control, "
+ "request.notice, "
+ "request.request_server_time, "
+ "request.request_client_time, "
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


			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PolicyRequestInfo model = new PolicyRequestInfo();
				PolicyInfoModel p_model = new PolicyInfoModel();

				model.setPolicyNo(rs.getInt("request_no"));
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
				model.setIsProcessList(rs.getInt("new_policy_process_list"));
				model.setIsFilePattern(rs.getInt("new_policy_file_pattern_list"));
				model.setIsWebAddr(rs.getString("new_policy_web_addr_list"));
				model.setWatermarkInfo(rs.getString("new_policy_watermark_descriptor"));
				model.setPrintLogDesc(rs.getInt("new_policy_print_log_descriptor"));
				model.setPatternFileControl(rs.getInt("new_policy_pattern_file_control"));

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
				p_model.setIsProcessList(rs.getInt("process_list"));
				p_model.setIsFilePattern(rs.getInt("file_pattern_list"));
				p_model.setIsWebAddr(rs.getString("web_addr_list"));
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
	
	
	
}

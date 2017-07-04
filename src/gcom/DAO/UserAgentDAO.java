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

import gcom.Model.UserAgentModel;
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;


public class UserAgentDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public UserAgentDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getUserAgentListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE userinfo.valid=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
		int user_installed = Integer.parseInt(map.get("user_installed").toString());
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
			
		
		if(!user_id.equals("")) 	whereSql += "AND userinfo.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND userinfo.name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND userinfo.phone LIKE ? ";
		if(user_installed == 1) 	whereSql += "AND agent.ip_addr is not null ";	//설치 선택
		else if(user_installed == 2) 	whereSql += "AND agent.ip_addr is null ";	//미설치 선택
		
		if(oDept != null)			whereSql += "AND userinfo.dept_no in ("+idList+") ";
		
		String sql= 
"SELECT "
+ "COUNT(*) AS cnt " 
+ "FROM user_info AS userinfo "
+ "LEFT JOIN agent_info AS agent ON agent.own_user_no=userinfo.no "
+ "INNER JOIN dept_info AS dept ON userinfo.dept_no = dept.no ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++,  "%" + user_phone + "%");
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
	
	
	public List<UserAgentModel> getUserAgentList(HashMap<String, Object> map){
		List<UserAgentModel> data = new ArrayList<UserAgentModel>();
		
		String whereSql = "WHERE userinfo.valid=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
		int user_installed = Integer.parseInt(map.get("user_installed").toString());
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

		if(!user_id.equals("")) 	whereSql += "AND userinfo.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND userinfo.name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND userinfo.phone LIKE ? ";
		if(user_installed == 1) 	whereSql += "AND agent.ip_addr is not null ";	//설치 선택
		else if(user_installed == 2) 	whereSql += "AND agent.ip_addr is null ";	//미설치 선택
		
		if(oDept != null)			whereSql += "AND userinfo.dept_no in ("+idList+") ";
		
		whereSql += "ORDER BY userinfo.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "userinfo.no AS uid, "
+ "userinfo.dept_no,"
+ "userinfo.duty,"
+ "userinfo.rank,"
+ "userinfo.name, "
+ "userinfo.phone, "
+ "userinfo.id,"
+ "userinfo.valid,"
+ "dept.short_name AS dept_name,"
+ "ifnull(agent.pc_name, '') AS pc_name,"
+ "ifnull(agent.ip_addr,'') AS ip_addr, "
+ "ifnull(agent.mac_addr,'') AS mac_addr, "
+ "ifnull(agent.connect_server_time,'') AS connect_server_time, "
+ "ifnull(agent.install_server_time,'') AS install_server_time, "
+ "ifnull(agent.connect_server_time,'') AS connect_client_time, "
+ "ifnull(agent.install_server_time,'') AS install_client_time, "
+ "ifnull(agent.version, '') AS version "
+ "FROM user_info AS userinfo "
+ "LEFT JOIN agent_info AS agent ON agent.own_user_no=userinfo.no "
+ "INNER JOIN dept_info AS dept ON userinfo.dept_no = dept.no ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++,  "%" + user_phone + "%");
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserAgentModel model = new UserAgentModel();
				model.setUid(rs.getInt("uid"));
				model.setDeptNo(rs.getInt("dept_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setName(rs.getString("name"));
				model.setPhone(rs.getString("phone"));
				model.setId(rs.getString("id"));
				model.setDeptName(rs.getString("dept_name"));
				model.setValid(rs.getInt("valid"));
				model.setVersion(rs.getString("version"));
				model.setPcName(rs.getString("pc_name"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setConnect_server_time(rs.getString("connect_server_time"));
				model.setConnect_client_time(rs.getString("connect_client_time"));
				model.setInstall_server_time(rs.getString("install_server_time"));
				model.setInstall_client_time(rs.getString("install_client_time"));

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
	
	


	public int getUserPolicyListCount(HashMap<String, Object> map){
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
+ "COUNT(*) cnt "
+ "FROM policy_info AS policy "
+ "INNER JOIN agent_info AS agent ON agent.no = policy.agent_no "
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
	
	
	public List<UserPolicyModel> getUserPolicyList(HashMap<String, Object> map){
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
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";

		
		whereSql += "ORDER BY policy.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "policy.no AS policy_no,"
+ "IFNULL(policy.update_server_time, '') AS print_server_time, "
+ "IFNULL(policy.update_client_time, '') AS print_client_time, "
+ "policy.uninstall_enabled AS uninstall_enabled, "
+ "policy.file_encryption_enabled AS file_encryption_enabled, "
+ "policy.cd_encryption_enabled,"
+ "policy.printer_enabled,"
+ "policy.cd_enabled,"
+ "policy.cd_export_enabled,"
+ "policy.wlan_enabled,"
+ "policy.net_share_enabled,"
+ "policy.web_export_enabled,"
+ "policy.removal_storage_export_enabled,"
+ "policy.removal_storage_admin_mode,"
+ "policy.usb_dev_list,"
+ "policy.com_port_list,"
+ "policy.net_port_list,"
+ "policy.process_list,"
+ "policy.file_pattern_list,"
+ "policy.web_addr_list,"
+ "policy.watermark_descriptor,"
+ "policy.print_log_descriptor,"
+ "policy.quarantine_path_access_code,"
+ "policy.pattern_file_control,"
+ "ur.no AS user_no, "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no, "
+ "ur.duty, "
+ "ur.rank, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM policy_info AS policy "
+ "INNER JOIN agent_info AS agent ON agent.no = policy.agent_no "
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
				UserPolicyModel model = new UserPolicyModel();
				model.setUserNo(rs.getInt("policy_no"));
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
				model.setIsProcessList(rs.getString("process_list"));
				model.setIsFilePattern(rs.getString("file_pattern_list"));
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
	

	public int getUserInfoListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE userinfo.valid=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
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
			
		
		if(!user_id.equals("")) 	whereSql += "AND userinfo.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND userinfo.name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND userinfo.phone LIKE ? ";
		
		if(oDept != null)			whereSql += "AND userinfo.dept_no in ("+idList+") ";
		
		String sql= 
"SELECT "
+ "COUNT(*) AS cnt " 
+ "FROM user_info AS userinfo "
+ "INNER JOIN dept_info AS dept ON userinfo.dept_no = dept.no ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++,  "%" + user_phone + "%");
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
	
	
	public List<UserInfoModel> getUserInfoList(HashMap<String, Object> map){
		List<UserInfoModel> data = new ArrayList<UserInfoModel>();
		
		String whereSql = "WHERE userinfo.valid=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_phone = map.get("user_phone").toString();
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

		if(!user_id.equals("")) 	whereSql += "AND userinfo.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND userinfo.name LIKE ? ";
		if(!user_phone.equals("")) 	whereSql += "AND userinfo.phone LIKE ? ";
		
		if(oDept != null)			whereSql += "AND userinfo.dept_no in ("+idList+") ";
		
		whereSql += "ORDER BY userinfo.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "userinfo.no AS user_no, "
+ "userinfo.dept_no,"
+ "userinfo.duty,"
+ "userinfo.rank,"
+ "userinfo.name, "
+ "userinfo.phone, "
+ "userinfo.id,"
+ "userinfo.valid,"
+ "dept.short_name AS dept_name "
+ "FROM user_info AS userinfo "
+ "INNER JOIN dept_info AS dept ON userinfo.dept_no = dept.no ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!user_id.equals("")) pstmt.setString(i++, "%" + user_id + "%");
			if(!user_name.equals("")) pstmt.setString(i++, "%" + user_name + "%");
			if(!user_phone.equals("")) pstmt.setString(i++,  "%" + user_phone + "%");
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserInfoModel model = new UserInfoModel();
				model.setUserNo(rs.getInt("user_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setUserName(rs.getString("name"));
				model.setPhone(rs.getString("phone"));
				model.setUserId(rs.getString("id"));
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
	
	public UserInfoModel getUserInfo(HashMap<String, Object> map){
		UserInfoModel data = new UserInfoModel();
		int user_no = Integer.parseInt(map.get("user_no").toString());
		
		String whereSql = "WHERE no = ? ";
		
		String sql= 
"SELECT "
+ "no, "
+ "dept_no, "
+ "duty, "
+ "rank, "
+ "number, "
+ "name, "
+ "phone, "
+ "id FROM "
+ "user_info ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			pstmt.setInt(i++, user_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				data.setUserNo(rs.getInt("no"));				
				data.setDeptId(rs.getInt("dept_no"));				
				data.setDuty(rs.getString("duty"));
				data.setRank(rs.getString("rank"));
				data.setNumber(rs.getString("number"));
				data.setUserName(rs.getString("name"));
				data.setPhone(rs.getString("phone"));
				data.setUserId(rs.getString("id"));

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

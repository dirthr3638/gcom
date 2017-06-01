package gcom.user.dao;

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

import gcom.Model.UserAgentModel;
import gcom.user.model.UserPolicyListModel;
import gcom.user.model.UserPolicyModel;


public class UserDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public UserDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map){
		List<UserPolicyListModel> result = new ArrayList<UserPolicyListModel>();
		String sql= 
				"SELECT agent_info.policy_uninstall_enabled, "
			    + "agent_info.policy_watermark_enabled, "
			    + "agent_info.policy_printer_enabled, "
			    + "agent_info.policy_fs_encryption_enabled, "
			    + "agent_info.policy_cd_enabled, "
			    + "agent_info.policy_cd_encryption_enabled, "
			    + "agent_info.policy_removal_storage_export_enabled,"
			    + "agent_info.policy_removal_storage_admin_mode, "
			    + "IFNULL(agent_info.policy_usb_descriptor, '') as policy_usb_descriptor, "
			    + "IFNULL(agent_info.policy_port_descriptor, '') as policy_port_descriptor, "
			    + "agent_info.policy_net_descriptor, "
			    + "agent_info.policy_wlan_enabled, "
			    + "agent_info.policy_web_export_enabled, "
			    + "agent_info.policy_sensitive_file_access, "
			    + "agent_info.policy_sensitive_dir_enabled, "
			    + "IFNULL(agent_info.policy_authentication_code, '') as policy_authentication_code, "
			    + "agent_info.policy_net_share_descriptor, "
			    + "IFNULL(agent_info.policy_print_log_descriptor, '') as policy_print_log_descriptor "
			    + "FROM user_info " 
				+ "INNER JOIN agent_info ON user_info.no = agent_info.own_user_no "
				+ "WHERE user_info.id = ?";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, map.get("user_id").toString());
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column_name = "";
			
			UserPolicyModel policy = new UserPolicyModel();
			
			if(rs.next()){
				
				UserPolicyListModel model = new UserPolicyListModel();
				
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column_name = metaData.getColumnName(indexOfcolumn + 1);
					
					model.setPolicyEngName(column_name);
					model.setPolicyKorName(policy.getPolicy().get(column_name));
					model.setPolicyStatus(rs.getString(column_name));
					
					result.add(model);
				}
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
+ "ifnull(agent.login_server_time,'') AS login_server_time, "
+ "ifnull(agent.connect_server_time,'') AS connect_server_time, "
+ "ifnull(agent.install_server_time,'') AS install_server_time, "
+ "ifnull(agent.version, '') AS version "
+ "FROM user_info AS userinfo "
+ "LEFT JOIN agent_info AS agent ON agent.own_user_no=userinfo.no "
+ "INNER JOIN dept_info AS dept ON userinfo.dept_no = dept.no "
+ "WHERE 1=1 "
+ "ORDER BY userinfo.no desc "
+ "LIMIT ?, ?";
//		LIMIT #{startRow}, #{endRow}

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			//pstmt.setInt(1,  adminNumber);
			pstmt.setInt(1,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(2,  Integer.parseInt(map.get("endRow").toString()));

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
				model.setLogin_server_time(rs.getString("login_server_time"));
				model.setConnect_server_time(rs.getString("connect_server_time"));
				model.setInstall_server_time(rs.getString("install_server_time"));
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

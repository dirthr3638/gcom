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
+ "ifnull(agent.login_server_time,'') AS login_server_time, "
+ "ifnull(agent.connect_server_time,'') AS connect_server_time, "
+ "ifnull(agent.install_server_time,'') AS install_server_time, "
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

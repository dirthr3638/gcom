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

import com.mysql.jdbc.Statement;

import gcom.Model.FileInfoModel;
import gcom.Model.MailExportModel;
import gcom.Model.MsnFileModel;
import gcom.Model.MsnTalkModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PrivacyLogModel;
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;
import gcom.common.services.ConfigInfo;
import gcom.user.model.UserContactModel;


public class PersonalDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public PersonalDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getMailExportListCount(HashMap<String, Object> map){
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
		if(!start_date.equals("")) 	whereSql += "AND mail.exported_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND mail.exported_client_time < ? + interval 1 day ";
		
		String sql= 
	"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM mail_export_info AS mail "
+ "INNER JOIN user_info AS ur ON ur.no = mail.user_no "
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
	
	
	public List<MailExportModel> getMailExportList(HashMap<String, Object> map){
		List<MailExportModel> data = new ArrayList<MailExportModel>();
		
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
		if(!start_date.equals("")) 	whereSql += "AND mail.exported_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND mail.exported_client_time < ? + interval 1 day ";
		
		whereSql += "ORDER BY mail.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "mail.no AS mail_no,"
+ "mail.email AS email,"
+ "mail.file_name,"
+ "mail.notice,"
+ "mail.file_key,"
+ "ifnull(mail.exported_server_time, '') AS exported_server_time, "
+ "ifnull(mail.exported_client_time, '') AS exported_client_time, "
+ "ur.no AS user_no, "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no,"
+ "ur.duty, "
+ "ur.rank, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM mail_export_info AS mail "
+ "INNER JOIN user_info AS ur ON ur.no = mail.user_no "
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
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MailExportModel model = new MailExportModel();
				model.setMailNo(rs.getInt("mail_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPcName(rs.getString("pc_name"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));

				model.setEmail(rs.getString("email"));
				model.setFileName(rs.getString("file_name"));
				model.setNotice(rs.getString("notice"));
				model.setFileKey(rs.getInt("file_key"));
				model.setExportServerTime(rs.getString("exported_server_time"));
				model.setExportClientTime(rs.getString("exported_client_time"));

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getMsnTalkListCount(HashMap<String, Object> map){
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
		if(!start_date.equals("")) 	whereSql += "AND msg.send_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND msg.send_client_time < ? + interval 1 day ";
		
		String sql= 
	"SELECT "
	+ "COUNT(*) AS cnt "
	+ "FROM msg_txt_log AS msg "
	+ "INNER JOIN user_info AS ur ON ur.no = msg.user_no "
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
	
	
	public List<MsnTalkModel> getMsnTalkList(HashMap<String, Object> map){
		List<MsnTalkModel> data = new ArrayList<MsnTalkModel>();
		
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
		if(!start_date.equals("")) 	whereSql += "AND msg.send_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND msg.send_client_time < ? + interval 1 day ";
		
		whereSql += "ORDER BY msg.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "msg.no AS msg_no,"
+ "msg.msg_type,"
+ "msg.send_server_time,"
+ "msg.send_client_time,"
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
+ "FROM msg_txt_log AS msg "
+ "INNER JOIN user_info AS ur ON ur.no = msg.user_no "
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
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MsnTalkModel model = new MsnTalkModel();
				model.setMsgNo(rs.getInt("msg_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPcName(rs.getString("pc_name"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));

				model.setMsgType(rs.getString("msg_type"));
				model.setSendServerTime(rs.getString("send_client_time"));
				model.setSendClientTime(rs.getString("send_server_time"));

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
	
	public int getMsnFileListCount(HashMap<String, Object> map){
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
		if(!start_date.equals("")) 	whereSql += "AND msg.send_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND msg.send_client_time < ? + interval 1 day ";

		String sql= 
"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM msg_file_log AS msg "
+ "INNER JOIN user_info AS ur ON ur.no = msg.user_no "
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
	
	
	public List<MsnFileModel> getMsnFileList(HashMap<String, Object> map){
		List<MsnFileModel> data = new ArrayList<MsnFileModel>();
		
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
		if(!start_date.equals("")) 	whereSql += "AND msg.send_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND msg.send_client_time < ? + interval 1 day ";
		
		whereSql += "ORDER BY msg.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "msg.no AS msg_no, "
+ "msg.msg_type AS msg_type, "
+ "msg.file_list AS file_list, "
+ "msg.send_server_time AS send_server_time, "
+ "msg.send_client_time AS send_client_time, "
+ "ur.no AS user_no,  "
+ "ur.id AS user_id,  "
+ "ur.name AS user_name,  "
+ "ur.dept_no,  "
+ "ur.duty,  "
+ "ur.rank,  "
+ "agent.ip_addr,  "
+ "agent.mac_addr,  "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM msg_file_log AS msg "
+ "INNER JOIN user_info AS ur ON ur.no = msg.user_no "
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
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MsnFileModel model = new MsnFileModel();
				model.setMsgNo(rs.getInt("msg_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPcName(rs.getString("pc_name"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setMsgType(rs.getString("msg_type"));
				model.setFileList(rs.getString("file_list"));
				model.setSendServerTime(rs.getString("send_client_time"));
				model.setSendClientTime(rs.getString("send_server_time"));

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
	
	
	
	public int getPrivacyFileListCount(HashMap<String, Object> map){
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
		if(!start_date.equals("")) 	whereSql += "AND mail.exported_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND mail.exported_client_time < ? + interval 1 day ";
			
		String sql= 
"SELECT "
	+ "COUNT(*) AS cnt"
	+ "FROM mail_export_info AS mail "
	+ "INNER JOIN user_info AS ur ON ur.no = mail.user_no "
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
	
	
	public List<PrivacyLogModel> getPrivacyFileList(HashMap<String, Object> map){
		List<PrivacyLogModel> data = new ArrayList<PrivacyLogModel>();
		
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
		if(!start_date.equals("")) 	whereSql += "AND ptn.found_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND ptn.found_client_time < ? + interval 1 day ";
		
		whereSql += "ORDER BY ptn.no desc LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "ptn.no AS ptn_no, "
+ "ptn.process_name AS process_name, "
+ "ptn_info.description AS pattern_name, "
+ "ptn.file_name AS file_name, "
+ "ptn.file_size AS file_size,"
+ "ptn.matched_data AS matched_data, "
+ "ifnull(ptn.found_server_time, '') AS found_server_time, "
+ "ifnull(ptn.found_client_time, '') AS found_client_time, "
+ "ur.no AS user_no, "
+ "ur.id AS user_id, "
+ "ur.name AS user_name, "
+ "ur.dept_no,  "
+ "ur.duty, "
+ "ur.rank, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM pattern_log AS ptn "
+ "INNER JOIN user_info AS ur ON ur.no = ptn.user_no "
+ "INNER JOIN agent_info AS agent ON agent.own_user_no = ur.no  "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no "
+ "INNER JOIN pattern_info AS ptn_info ON ptn.pattern_id = ptn_info.id  ";
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
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				PrivacyLogModel model = new PrivacyLogModel();
				model.setPtnNo(rs.getInt("ptn_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setUserName(rs.getString("user_name"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPcName(rs.getString("pc_name"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));

				model.setProcessName(rs.getString("process_name"));				
				model.setFileName(rs.getString("file_name"));
				model.setPatternName(rs.getString("pattern_name"));
				model.setFileSize(rs.getString("file_size"));
				model.setMatchedData(rs.getString("matched_data"));
				model.setFoundServerTime(rs.getString("found_server_time"));
				model.setFoundClientTime(rs.getString("found_client_time"));

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
	

	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String bbs_title = map.get("bbs_title").toString();
		String bbs_body = map.get("bbs_body").toString();
		String bbs_body_trim = map.get("bbs_body_trim").toString();
		String special_type = map.get("special_type").toString();
		String attfile_yn = map.get("attfile_yn").toString();
		int reg_staf_no = Integer.parseInt(map.get("reg_staf_no").toString());
		
		String save_file_nm =  map.get("save_file_nm").toString();
		String view_file_nm =  map.get("view_file_nm").toString();
		String att_file_path =  map.get("att_file_path").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		
		
		String sql= "INSERT INTO services_file_upload_info (att_file_path, view_file_nm, save_file_nm) "
					+ "VALUES (?, ?, ?) ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			if (attfile_yn.equals("Y")) {
				pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, att_file_path);
				pstmt.setString(2, view_file_nm);
				pstmt.setString(3, save_file_nm);
				pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
					int fileKey = rs.getInt(1);
					
					sql= "INSERT INTO user_notice_bbs (bbs_title, special_type, bbs_body, bbs_body_trim, reg_staf_no, reg_dt, attfile_yn, attfile_id) " 
							+ "VALUES (?, ?, ?, ?, ?, NOW(), ?, ? )";
					
					pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, bbs_title);
					pstmt.setString(2, special_type);
					pstmt.setString(3, bbs_body);
					pstmt.setString(4, bbs_body_trim);
					pstmt.setInt(5, reg_staf_no);
					pstmt.setString(6, attfile_yn);
					pstmt.setInt(7, fileKey);
					pstmt.executeUpdate();
					
					rs = pstmt.getGeneratedKeys();
					
					if (rs.next()) {
						int bbsKey = rs.getInt(1);
						sql = "INSERT INTO user_notice_bbs_hit (bbs_id) VALUES (?)";
						
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, bbsKey);
						pstmt.executeUpdate();
					}
					
				}
				
			} else {
				
				sql= "INSERT INTO user_notice_bbs (bbs_title, special_type, bbs_body, bbs_body_trim, reg_staf_no, reg_dt, attfile_yn) " 
						+ "VALUES (?, ?, ?, ?, ?, NOW(), ? )";
				
				pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, bbs_title);
				pstmt.setString(2, special_type);
				pstmt.setString(3, bbs_body);
				pstmt.setString(4, bbs_body_trim);
				pstmt.setInt(5, reg_staf_no);
				pstmt.setString(6, attfile_yn);
				pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
					int bbsKey = rs.getInt(1);
					sql = "INSERT INTO user_notice_bbs_hit (bbs_id) VALUES (?)";
					
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, bbsKey);
					pstmt.executeUpdate();
				}
				
			}
									
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

	public FileInfoModel getAttFileInfo(HashMap<String, Object> map) {
		FileInfoModel model = new FileInfoModel();
		int fileId = Integer.parseInt(map.get("file_id").toString());
		
		String sql =
				"SELECT "
					+ "attfile_id, "
					+ "att_file_path, "
					+ "view_file_nm, "
					+ "save_file_nm "
				+ "FROM services_file_upload_info "
			    + "WHERE attfile_id = ? ";
		
		try{
			
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  fileId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				model.setAttFileId(rs.getInt("attfile_id"));
				model.setAttFilePath(rs.getString("att_file_path"));
				model.setAttViewFileName(rs.getString("view_file_nm"));
				model.setAttSaveFileName(rs.getString("save_file_nm"));
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

	public String insertPolicyDataSave(HashMap<String, Object> map) {
		
		int agent_id = Integer.parseInt(map.get("agnet_no").toString());
		int user_id = Integer.parseInt(map.get("user_no").toString());
		int isUninstall = Integer.parseInt(map.get("isUninstall").toString());
		int isFileEncryption = Integer.parseInt(map.get("isFileEncryption").toString());
		int isCdEncryption = Integer.parseInt(map.get("isCdEncryption").toString());
		int isPrint = Integer.parseInt(map.get("isPrint").toString());
		int isCdEnabled = Integer.parseInt(map.get("isCdEnabled").toString());
		int isCdExport = Integer.parseInt(map.get("isCdExport").toString());
		int isWlan = Integer.parseInt(map.get("isWlan").toString());
		int isNetShare = Integer.parseInt(map.get("isNetShare").toString());
		int isWebExport = Integer.parseInt(map.get("isWebExport").toString());
		int patternFileControl = Integer.parseInt(map.get("patternFileControl").toString());
		String printLogDesc = map.get("printLogDesc").toString();
		String isUsbBlock = map.get("isUsbBlock").toString();
		String isComPortBlock = map.get("isComPortBlock").toString();
		String isNetPortBlock = map.get("isNetPortBlock").toString();
		String isProcessList = map.get("isProcessList").toString();
		String isFilePattern = map.get("isFilePattern").toString();
		String isWebAddr = map.get("isWebAddr").toString();
		String isMsgBlock = map.get("isMsgBlock").toString();
		String waterMark = map.get("waterMark").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO policy_info ("
					+ "agent_no, "
					+ "update_server_time, " 
					+ "uninstall_enabled, " 
					+ "file_encryption_enabled, " 
					+ "cd_encryption_enabled, " 
					+ "printer_enabled, " 
					+ "cd_enabled, " 
					+ "cd_export_enabled, " 
					+ "wlan_enabled, " 
					+ "net_share_enabled, " 
					+ "web_export_enabled, " 
					+ "usb_dev_list, " 
					+ "com_port_list, " 
					+ "net_port_list, "
					+ "process_list, " 
					+ "file_pattern_list, " 
					+ "web_addr_list, " 
					+ "msg_block_list, " 
					+ "watermark_descriptor, " 
					+ "print_log_descriptor, " 
					+ "pattern_file_control )" 
				+ "VALUES ( ?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, agent_id);
			pstmt.setInt(2, isUninstall);
			pstmt.setInt(3, isFileEncryption);
			pstmt.setInt(4, isCdEncryption);
			pstmt.setInt(5, isPrint);
			pstmt.setInt(6, isCdEnabled);
			pstmt.setInt(7, isCdExport);
			pstmt.setInt(8, isWlan);
			pstmt.setInt(9, isNetShare);
			pstmt.setInt(10, isWebExport);
			pstmt.setString(11, isUsbBlock);
			pstmt.setString(12, isComPortBlock);
			pstmt.setString(13, isNetPortBlock);
			pstmt.setString(14, isProcessList);
			pstmt.setString(15, isFilePattern);
			pstmt.setString(16, isWebAddr);
			pstmt.setString(17, isMsgBlock);
			pstmt.setString(18, waterMark);
			pstmt.setString(19, printLogDesc);
			pstmt.setInt(20, patternFileControl);
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				int policy_id = rs.getInt(1);
				sql = "UPDATE agent_info SET policy_no = ? WHERE no = ? AND own_user_no = ? ";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, policy_id);
				pstmt.setInt(2, agent_id);
				pstmt.setInt(3, user_id);
				pstmt.executeUpdate();
			}
									
			con.commit();
		
		}catch(SQLException ex){
			returnCode = ConfigInfo.RETURN_CODE_ERROR;
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
		
		return returnCode;
	}

	public String updatePolicyDataSave(HashMap<String, Object> map) {
		
		int policy_id = Integer.parseInt(map.get("policy_no").toString());
		int isUninstall = Integer.parseInt(map.get("isUninstall").toString());
		int isFileEncryption = Integer.parseInt(map.get("isFileEncryption").toString());
		int isCdEncryption = Integer.parseInt(map.get("isCdEncryption").toString());
		int isPrint = Integer.parseInt(map.get("isPrint").toString());
		int isCdEnabled = Integer.parseInt(map.get("isCdEnabled").toString());
		int isCdExport = Integer.parseInt(map.get("isCdExport").toString());
		int isWlan = Integer.parseInt(map.get("isWlan").toString());
		int isNetShare = Integer.parseInt(map.get("isNetShare").toString());
		int isWebExport = Integer.parseInt(map.get("isWebExport").toString());
		int patternFileControl = Integer.parseInt(map.get("patternFileControl").toString());
		String printLogDesc = map.get("printLogDesc").toString();
		String isUsbBlock = map.get("isUsbBlock").toString();
		String isComPortBlock = map.get("isComPortBlock").toString();
		String isNetPortBlock = map.get("isNetPortBlock").toString();
		String isProcessList = map.get("isProcessList").toString();
		String isFilePattern = map.get("isFilePattern").toString();
		String isWebAddr = map.get("isWebAddr").toString();
		String isMsgBlock = map.get("isMsgBlock").toString();
		String waterMark = map.get("waterMark").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE policy_info "
				+ "SET "
					+ "update_server_time= NOW(), " 
					+ "uninstall_enabled= ?, " 
					+ "file_encryption_enabled= ?, " 
					+ "cd_encryption_enabled= ?, " 
					+ "printer_enabled= ?, " 
					+ "cd_enabled= ?, " 
					+ "cd_export_enabled= ?, " 
					+ "wlan_enabled= ?, " 
					+ "net_share_enabled= ?, " 
					+ "web_export_enabled= ?, " 
					+ "usb_dev_list= ?, " 
					+ "com_port_list= ?, " 
					+ "net_port_list= ?, "
					+ "process_list= ?, " 
					+ "file_pattern_list= ?, " 
					+ "web_addr_list= ?, " 
					+ "msg_block_list= ?, " 
					+ "watermark_descriptor= ?, " 
					+ "print_log_descriptor= ?, " 
					+ "pattern_file_control= ? " 
				+ "WHERE no= ? ";
		
		try{
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, isUninstall);
			pstmt.setInt(2, isFileEncryption);
			pstmt.setInt(3, isCdEncryption);
			pstmt.setInt(4, isPrint);
			pstmt.setInt(5, isCdEnabled);
			pstmt.setInt(6, isCdExport);
			pstmt.setInt(7, isWlan);
			pstmt.setInt(8, isNetShare);
			pstmt.setInt(9, isWebExport);
			pstmt.setString(10, isUsbBlock);
			pstmt.setString(11, isComPortBlock);
			pstmt.setString(12, isNetPortBlock);
			pstmt.setString(13, isProcessList);
			pstmt.setString(14, isFilePattern);
			pstmt.setString(15, isWebAddr);
			pstmt.setString(16, isMsgBlock);
			pstmt.setString(17, waterMark);
			pstmt.setString(18, printLogDesc);
			pstmt.setInt(19, patternFileControl);
			pstmt.setInt(20, policy_id);
			pstmt.executeUpdate();
									
			con.commit();
		
		}catch(SQLException ex){
			returnCode = ConfigInfo.RETURN_CODE_ERROR;
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
		
		return returnCode;
	}

	public HashMap<String, Object> updateNoticeModifyUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int bbs_id = Integer.parseInt(map.get("bbs_id").toString());
		int file_id = Integer.parseInt(map.get("file_id").toString());
		String bbs_title = map.get("bbs_title").toString();
		String bbs_body = map.get("bbs_body").toString();
		String bbs_body_trim = map.get("bbs_body_trim").toString();
		String special_type = map.get("special_type").toString();
		String attfile_yn = map.get("attfile_yn").toString();
		int upd_staf_no = Integer.parseInt(map.get("upd_staf_no").toString());
		
		String save_file_nm =  map.get("save_file_nm").toString();
		String view_file_nm =  map.get("view_file_nm").toString();
		String att_file_path =  map.get("att_file_path").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		
		
		String sql= "UPDATE services_file_upload_info "
				+ "SET att_file_path = ? , "
				+ "view_file_nm = ? , "
				+ "save_file_nm = ? "
				+ "WHERE attfile_id = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			if (attfile_yn.equals("Y")) {
				
				if (file_id == 0) {
					
					sql= "INSERT INTO services_file_upload_info (att_file_path, view_file_nm, save_file_nm) "
							+ "VALUES (?, ?, ?) ";
					
					pstmt=con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, att_file_path);
					pstmt.setString(2, view_file_nm);
					pstmt.setString(3, save_file_nm);
					pstmt.executeUpdate();
					
					rs = pstmt.getGeneratedKeys();
					
					if (rs.next()) {
						file_id = rs.getInt(1);
					}
					
				} else {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, att_file_path);
					pstmt.setString(2, view_file_nm);
					pstmt.setString(3, save_file_nm);
					pstmt.setInt(4, file_id);
					pstmt.executeUpdate();
				}
				
				sql= "UPDATE user_notice_bbs "
						+ "SET bbs_title = ?, "
						+ "special_type = ?, "
						+ "bbs_body = ?, "
						+ "bbs_body_trim = ?, "
						+ "upd_staf_no = ?, "
						+ "upd_dt = NOW(), "
						+ "attfile_yn = ?, "
						+ "attfile_id = ? " 
						+ "WHERE bbs_id = ?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, bbs_title);
				pstmt.setString(2, special_type);
				pstmt.setString(3, bbs_body);
				pstmt.setString(4, bbs_body_trim);
				pstmt.setInt(5, upd_staf_no);
				pstmt.setString(6, attfile_yn);
				pstmt.setInt(7, file_id);
				pstmt.setInt(8, bbs_id);
				pstmt.executeUpdate();
				
			} else {
				
				sql= "UPDATE user_notice_bbs "
						+ "SET bbs_title = ?, "
						+ "special_type = ?, "
						+ "bbs_body = ?, "
						+ "bbs_body_trim = ?, "
						+ "upd_staf_no = ?, "
						+ "upd_dt = NOW(), "
						+ "attfile_yn = ?, "
						+ "attfile_id = ? " 
						+ "WHERE bbs_id = ?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, bbs_title);
				pstmt.setString(2, special_type);
				pstmt.setString(3, bbs_body);
				pstmt.setString(4, bbs_body_trim);
				pstmt.setInt(5, upd_staf_no);
				pstmt.setString(6, attfile_yn);
				pstmt.setInt(7, file_id);
				pstmt.setInt(8, bbs_id);
				pstmt.executeUpdate();
				
			}
									
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

	public List<UserContactModel> getAdminContactList(HashMap<String, Object> map) {
		List<UserContactModel> data = new ArrayList<UserContactModel>();
		
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

		if(oDept != null)			whereSql += "AND ui.dept_no in ("+idList+") ";

		
		whereSql += "ORDER BY con.contact_id DESC LIMIT ?, ? ";	
		
		String sql= 
				"SELECT con.contact_id, "
				+ "con.contact_type, "
				+ "con.contact_title, "
				+ "con.contact_body, "
				+ "ui.id, "
				+ "ui.name, "
				+ "DATE(con.reg_dt) as reg_dt, "
				+ "con.comment_yn, "
				+ "con_comm.comment_id, "
				+ "IFNULL(admin_info.id, '')as comment_reg_staf_id, "
				+ "IFNULL(con_comm.reply_content, '') as reply_content, "
				+ "IFNULL(con_comm.reg_dt, '') as comment_reg_dt "
				+ "FROM user_contact_info AS con "
				+ "LEFT JOIN user_info AS ui ON con.reg_user_staf_no = ui.no "
				+ "LEFT JOIN user_contact_comment AS con_comm ON con.contact_id = con_comm.contact_id "
				+ "LEFT JOIN admin_info AS admin_info ON con_comm.reg_admin_staf_no = admin_info.no ";

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
				UserContactModel model = new UserContactModel();
				model.setContactId(rs.getInt("contact_id"));
				model.setContactType(rs.getInt("contact_type"));
				model.setTypeName(rs.getInt("contact_type"));
				model.setContactTitle(rs.getString("contact_title"));
				model.setContactBody(rs.getString("contact_body"));
				model.setRegUserStafId(rs.getString("id"));
				model.setRegUserName(rs.getString("name"));
				model.setRegDt(rs.getString("reg_dt"));
				model.setCommentYN(rs.getString("comment_yn"));
				model.setCommentId(rs.getInt("comment_id"));
				model.setCommnetRegStafId(rs.getString("comment_reg_staf_id"));
				model.setReplyContent(rs.getString("reply_content"));
				model.setCommentRegDt(rs.getString("comment_reg_dt"));
				
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

	public int getAdminContactListCount(HashMap<String, Object> map) {
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
		if(!user_id.equals("")) 	whereSql += "AND ui.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ui.name LIKE ? ";

		if(oDept != null)			whereSql += "AND ui.dept_no in ("+idList+") ";
	
		String sql= 
				"SELECT "
						+ "COUNT(*) AS cnt "
						+ "FROM user_contact_info AS con "
						+ "LEFT JOIN user_info AS ui ON con.reg_user_staf_no = ui.no "
						+ "LEFT JOIN user_contact_comment AS con_comm ON con.contact_id = con_comm.contact_id "
						+ "LEFT JOIN admin_info AS admin_info ON con_comm.reg_admin_staf_no = admin_info.no ";

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

	public HashMap<String, Object> insertContactCommentSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int reg_admin_staf_no = Integer.parseInt(map.get("reg_admin_staf_no").toString());
		int contact_id = Integer.parseInt(map.get("contact_id").toString());
		String reply_content = map.get("reply_content").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "INSERT INTO user_contact_comment (contact_id, reg_admin_staf_no, reply_content, reg_dt) VALUES ( ?, ?, ?, NOW() )";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contact_id);
			pstmt.setInt(2, reg_admin_staf_no);
			pstmt.setString(3, reply_content);
			pstmt.executeUpdate();
			
			sql = "UPDATE user_contact_info set comment_yn = 'Y' WHERE contact_id = ? ";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contact_id);
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

	public HashMap<String, Object> getCommentInfo(HashMap<String, Object> map) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		int comment_id = Integer.parseInt(map.get("comment_id").toString());
		
		String sql= "SELECT "
				+ "comment_id, "
				+ "contact_id, "
				+ "reg_admin_staf_no, "
				+ "reply_content, "
				+ "reg_dt "
				+ "FROM user_contact_comment WHERE del_yn = 'N' AND comment_id = ?";
					
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, comment_id);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column = "";
			
			if(rs.next()){
				// Column의 갯수만큼 회전
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column = metaData.getColumnName(indexOfcolumn + 1);
					// phone number 일 경우 복호화
					data.put(column, rs.getString(column));
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
		
		return data;
	}

	public HashMap<String, Object> updateContactCommentUpdate(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int comment_id = Integer.parseInt(map.get("comment_id").toString());
		String reply_content = map.get("reply_content").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String sql= "UPDATE user_contact_comment SET reply_content = ?, upd_dt = NOW() WHERE comment_id = ?";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
					
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reply_content);
			pstmt.setInt(2, comment_id);
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
	
}

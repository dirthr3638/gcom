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
import gcom.common.services.ConfigInfo;


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
	
}

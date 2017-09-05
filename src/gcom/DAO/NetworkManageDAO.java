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

import gcom.Model.CDExportLogModel;
import gcom.Model.NetExportLogModel;
import gcom.Model.NetPortLogModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;
import gcom.common.util.ConfigInfo;
import gcom.common.util.encrypto.hashEncrypto;


public class NetworkManageDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public NetworkManageDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	

	public List<NetPortLogModel> getNetPortLogList(HashMap<String, Object> map){
		List<NetPortLogModel> data = new ArrayList<NetPortLogModel>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_number = map.get("user_number").toString();
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
		}else{
			return data;
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!user_number.equals("")) 	whereSql += "AND ur.number LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND port.control_server_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND port.control_server_time < ? + interval 1 day ";


		
		whereSql += "ORDER BY port.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT 	"
+ "port.no AS port_no,    "
+ "port.process_name,    "
+ "port.port,    "
+ "port.description,    "
+ "port.control,    "
+ "port.control_server_time,    "
+ "port.control_client_time,	"
+ "ur.id AS user_id, 	"
+ "ur.number AS user_no, 	"
+ "ur.dept_no,	"
+ "ur.name,  "
+ "ur.duty, "
+ "ur.rank, "
+ "agent.ip_addr, 	"
+ "agent.mac_addr,	"
+ "agent.pc_name, 	"
+ "dept.name AS dept_name "
+ "FROM net_port_log AS port "
+ "INNER JOIN user_info AS ur ON ur.no = port.user_no "
+ "INNER JOIN agent_log AS agent ON agent.no = port.agent_log_no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no  ";

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
			if(!user_number.equals("")) pstmt.setString(i++, "%" + user_number + "%");
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				NetPortLogModel model = new NetPortLogModel();
				model.setPortNo(rs.getInt("export_no"));

				model.setProcessName(rs.getString("file_id"));
				model.setPort(rs.getInt("port"));
				model.setDescription(rs.getString("notice"));
				model.setControl(rs.getString("control"));
				model.setServerTime(rs.getString("control_server_time"));
				model.setClientTime(rs.getString("control_client_time"));

				
				model.setUserNo(rs.getString("user_no"));
				model.setUserId(rs.getString("user_id"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setUserNumber(rs.getString("user_no"));
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

	public List<NetExportLogModel> getNetExportLogList(HashMap<String, Object> map){
		List<NetExportLogModel> data = new ArrayList<NetExportLogModel>();
		
		String whereSql = "WHERE 1=1 ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String user_number = map.get("user_number").toString();
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
		}else{
			return data;
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!user_number.equals("")) 	whereSql += "AND ur.number LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND exp.export_server_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND exp.export_server_time < ? + interval 1 day ";


		
		whereSql += "ORDER BY exp.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT 	"
+ "exp.no AS export_no,    "
+ "exp.process_name,    "
+ "exp.protocol_type,    "
+ "exp.dest_addr,    "
+ "exp.file_name,    "
+ "exp.file_key,    "
+ "exp.export_server_time,    "
+ "exp.export_client_time,    "
+ "exp.completed,	"
+ "ur.id AS user_id, 	"
+ "ur.dept_no, 	"
+ "ur.name,	"
+ "ur.duty,	"
+ "ur.rank,	"
+ "agent.ip_addr,	"
+ "agent.mac_addr,	"
+ "agent.pc_name, 	"
+ "dept.name AS dept_name "
+ "FROM net_export_log AS exp "
+ "INNER JOIN user_info AS ur ON ur.no = exp.user_no "
+ "INNER JOIN agent_log AS agent ON agent.no = exp.agent_log_no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no "
+ "WHERE exp.protocal_type = 'SMB'  ";

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
			if(!user_number.equals("")) pstmt.setString(i++, "%" + user_number + "%");
			if(!start_date.equals("")) 	pstmt.setString(i++, start_date);
			if(!end_date.equals("")) 	pstmt.setString(i++, end_date);

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				NetExportLogModel model = new NetExportLogModel();
				model.setPortNo(rs.getInt("export_no"));

				model.setProcessName(rs.getString("file_id"));
				model.setPort(rs.getInt("port"));
				model.setDescription(rs.getString("notice"));
				model.setControl(rs.getString("control"));
				model.setServerTime(rs.getString("control_server_time"));
				model.setClientTime(rs.getString("control_client_time"));

				
				model.setUserNo(rs.getString("user_no"));
				model.setUserId(rs.getString("user_id"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setUserNumber(rs.getString("user_no"));
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
	
	
}

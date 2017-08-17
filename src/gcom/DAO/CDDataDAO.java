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

import gcom.Model.DiskConnectLogModel;
import gcom.Model.DiskExportModel;
import gcom.Model.FileEventLogModel;
import gcom.Model.PartitionConnectLogModel;
import gcom.Model.UsbConnectModel;
import gcom.Model.UsbDevInfoModel;;

//디스크 파일전송로그
public class CDDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public CDDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getCDExportListCount(HashMap<String, Object> map){
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
		}else{
			return result;
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND de.export_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND de.export_client_time < ? + interval 1 day ";


		
		String sql= 
				"SELECT "
				+ "COUNT(*) AS cnt "
				+ "FROM disk_export_log AS de "
				+ "INNER JOIN user_info AS ur ON ur.no = de.user_no "
				+ "INNER JOIN agent_log AS agent ON agent.no = de.agent_log_no "
				+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no "
				+ "LEFT JOIN partition_log AS ptn ON de.partition_log_no = ptn.no ";

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
	
	
	public List<DiskExportModel> getCDExportList(HashMap<String, Object> map){
		List<DiskExportModel> data = new ArrayList<DiskExportModel>();
		
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
		}else{
			return data;
		}
		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
		if(!user_id.equals("")) 	whereSql += "AND ur.id LIKE ? ";
		if(!user_name.equals("")) 	whereSql += "AND ur.name LIKE ? ";
		if(!start_date.equals("")) 	whereSql += "AND de.export_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND de.export_client_time < ? + interval 1 day ";


		
		whereSql += "ORDER BY de.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "de.no AS export_no, "
+ "ur.number AS user_no, "
+ "ifnull(de.export_server_time, '') AS export_server_time, "
+ "ifnull(de.export_client_time, '') AS export_client_time, "
+ "de.grade, "
+ "de.file_list, "
+ "de.notice, "
+ "de.export_status, "
+ "de.file_id, "
+ "ur.id AS user_id, "
+ "ur.dept_no, "
+ "ur.name, "
+ "ur.duty,"
+ "ur.rank,"
+ "agent.ip_addr,"
+ "agent.mac_addr,"
+ "agent.pc_name,"
+ "dept.name AS dept_name, "
+ "ifnull(ptn.guid, '') AS partition_guid, "
+ "ifnull(ptn.label, '') AS partition_label "
+ "FROM disk_export_log AS de "
+ "INNER JOIN user_info AS ur ON ur.no = de.user_no "
+ "INNER JOIN agent_log AS agent ON agent.no = de.agent_log_no "
+ "INNER JOIN dept_info AS dept ON dept.no = ur.dept_no "
+ "LEFT JOIN partition_log AS ptn ON de.partition_log_no = ptn.no ";

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
				DiskExportModel model = new DiskExportModel();
				model.setExportNo(rs.getInt("export_no"));
				model.setUserNo(rs.getString("user_no"));
				model.setUserName(rs.getString("name"));
				model.setExportServerTime(rs.getString("export_server_time"));
				model.setExportClientTime(rs.getString("export_client_time"));
				model.setGrade(rs.getInt("grade"));
				model.setFileList(rs.getString("file_list"));
				model.setNotice(rs.getString("notice"));
				model.setExportStatus(rs.getString("export_status"));
				model.setFileId(rs.getString("file_id"));
				model.setUserId(rs.getString("user_id"));
				model.setDeptId(rs.getInt("dept_no"));
				model.setDuty(rs.getString("duty"));
				model.setRank(rs.getString("rank"));
				model.setIpAddr(rs.getString("ip_addr"));
				model.setMacAddr(rs.getString("mac_addr"));
				model.setPcName(rs.getString("pc_name"));
				model.setDeptName(rs.getString("dept_name"));
				model.setPartitionGuid(rs.getString("partition_guid"));
				model.setPartitionLabel(rs.getString("partition_label"));
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

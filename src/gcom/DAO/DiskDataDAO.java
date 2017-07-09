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

import gcom.Model.DiskExportModel;
import gcom.Model.UsbConnectModel;
import gcom.Model.UsbDevInfoModel;;

//이동식디스크 파일전송로그
public class DiskDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public DiskDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getDiskExportListCount(HashMap<String, Object> map){
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
		if(!start_date.equals("")) 	whereSql += "AND de.export_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND de.export_client_time < ? + interval 1 day ";


		
		String sql= 
				"SELECT "
				+ "COUNT(*) AS cnt "
				+ "FROM disk_export_log AS de "
				+ "INNER JOIN user_info AS ur ON ur.no = de.user_no "
				+ "INNER JOIN agent_info AS agent ON agent.own_user_no = ur.no "
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
	
	
	public List<DiskExportModel> getDiskExportList(HashMap<String, Object> map){
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
+ "de.user_no, "
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
+ "INNER JOIN agent_info AS agent ON agent.own_user_no = ur.no "
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
				model.setUserNo(rs.getInt("user_no"));
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
	
	
	public int getUsbConnectListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE usb.visible = true ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();
		String duty = map.get("duty").toString();
		String rank = map.get("rank").toString();
		String device_name = map.get("device_name").toString();
		String pc_name = map.get("pc_name").toString();
		String device_property = map.get("device_property").toString();

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
		if(!start_date.equals("")) 	whereSql += "AND usb.connect_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND usb.connect_client_time < ? + interval 1 day ";
		
		if(!duty.equals("")) 		whereSql += "AND ur.duty LIKE ? ";
		if(!rank.equals("")) 		whereSql += "AND ur.rank LIKE ? ";
		if(!device_name.equals("")) whereSql += "AND usb.device_name LIKE ? ";
		if(!pc_name.equals("")) 		whereSql += "AND agent.pc_name LIKE ? ";
		if(!device_property.equals("")) 	whereSql += "AND usb.device_property LIKE ? ";


		String sql= 
"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM usb_connect_log AS usb "
+ "INNER JOIN user_info AS ur ON ur.no = usb.user_no "
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
			if(!duty.equals("")) 		pstmt.setString(i++, "%" + duty + "%");
			if(!rank.equals("")) 		pstmt.setString(i++, "%" + rank + "%");
			if(!device_name.equals("")) pstmt.setString(i++, "%" + device_name + "%");
			if(!pc_name.equals("")) 	pstmt.setString(i++, "%" + pc_name + "%");
			if(!device_property.equals("")) pstmt.setString(i++, "%" + device_property + "%");
			
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
	
	
	public List<UsbConnectModel> getUsbConnectList(HashMap<String, Object> map){
		List<UsbConnectModel> data = new ArrayList<UsbConnectModel>();
		
		String whereSql = "WHERE usb.visible = true ";
		String user_id = map.get("user_id").toString();
		String user_name = map.get("user_name").toString();
		String start_date = map.get("start_date").toString();
		String end_date = map.get("end_date").toString();
		String duty = map.get("duty").toString();
		String rank = map.get("rank").toString();
		String device_name = map.get("device_name").toString();
		String pc_name = map.get("pc_name").toString();
		String device_property = map.get("device_property").toString();
		
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
		if(!start_date.equals("")) 	whereSql += "AND usb.connect_client_time >= ? ";
		if(!end_date.equals("")) 	whereSql += "AND usb.connect_client_time < ? + interval 1 day ";
		
		if(!duty.equals("")) 		whereSql += "AND ur.duty LIKE ? ";
		if(!rank.equals("")) 		whereSql += "AND ur.rank LIKE ? ";
		if(!device_name.equals("")) whereSql += "AND usb.device_name LIKE ? ";
		if(!pc_name.equals("")) 		whereSql += "AND agent.pc_name LIKE ? ";
		if(!device_property.equals("")) 	whereSql += "AND usb.device_property LIKE ? ";

		
		whereSql += "ORDER BY usb.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "usb.no AS usb_no, "
+ "usb.device_name, "
+ "usb.device_property, "
+ "ifnull(usb.connect_server_time, '') AS connect_server_time, "
+ "ifnull(usb.connect_client_time, '') AS connect_client_time, "
+ "usb.notice, "
+ "ur.id AS user_id, "
+ "ur.dept_no, ur.duty, "
+ "ur.rank, "
+ "ur.no AS user_no, "
+ "ur.name AS user_name, "
+ "agent.ip_addr, "
+ "agent.mac_addr, "
+ "agent.pc_name, "
+ "dept.name AS dept_name "
+ "FROM usb_connect_log AS usb "
+ "INNER JOIN user_info AS ur ON ur.no = usb.user_no "
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
			if(!duty.equals("")) 		pstmt.setString(i++, "%" + duty + "%");
			if(!rank.equals("")) 		pstmt.setString(i++, "%" + rank + "%");
			if(!device_name.equals("")) pstmt.setString(i++, "%" + device_name + "%");
			if(!pc_name.equals("")) 	pstmt.setString(i++, "%" + pc_name + "%");
			if(!device_property.equals("")) pstmt.setString(i++, "%" + device_property + "%");

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UsbConnectModel model = new UsbConnectModel();
				model.setUsbNo(rs.getInt("usb_no"));
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
				model.setConnectServerTime(rs.getString("connect_server_time"));
				model.setConnectClientTime(rs.getString("connect_client_time"));
				model.setDeviceProperty(rs.getString("device_property"));
				model.setDeviceName(rs.getString("device_name"));			
				model.setNotice(rs.getString("notice"));
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
	
	
	public int getUnAuthUsbListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE usb.allow = 0 ";
		String name = map.get("name").toString();
		String serial = map.get("serial").toString();
		String desc = map.get("desc").toString();
		String allow = map.get("allow").toString();

		if(!name.equals("")) 	whereSql += "AND usb.name LIKE ? ";
		if(!serial.equals("")) 	whereSql += "AND usb.serial_number LIKE ? ";
		if(!desc.equals("")) 	whereSql += "AND usb.description LIKE ? ";
		if(!allow.equals("")) 	whereSql += "AND usb.allow = ? ";

		
		String sql= 
"SELECT "
+ "COUNT(*) cnt "
+ "FROM usb_dev_info AS usb ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!name.equals("")) pstmt.setString(i++, "%" + name + "%");
			if(!serial.equals("")) pstmt.setString(i++, "%" + serial + "%");
			if(!desc.equals("")) 	pstmt.setString(i++, "%" + desc + "%");
			if(!allow.equals("")){
				int iAllow = Integer.parseInt(allow.toString());
				pstmt.setInt(i, iAllow);
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
	
	
	public List<UsbDevInfoModel> getUnAuthUsbList(HashMap<String, Object> map){
		List<UsbDevInfoModel> data = new ArrayList<UsbDevInfoModel>();
		
		String whereSql = "WHERE 1=1 ";
		String name = map.get("name").toString();
		String serial = map.get("serial").toString();
		String desc = map.get("desc").toString();
		String allow = map.get("allow").toString();

		if(!name.equals("")) 	whereSql += "AND usb.name LIKE ? ";
		if(!serial.equals("")) 	whereSql += "AND usb.serial_number LIKE ? ";
		if(!desc.equals("")) 	whereSql += "AND usb.description LIKE ? ";
		if(!allow.equals("")) 	whereSql += "AND usb.allow = ? ";
		
		whereSql += "ORDER BY usb.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "usb.no, "
+ "usb.name, "
+ "usb.vid, "
+ "usb.pid, "
+ "usb.allow, "
+ "usb.serial_number, "
+ "usb.description "
+ "FROM usb_dev_info AS usb ";
sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
			if(!name.equals("")) pstmt.setString(i++, "%" + name + "%");
			if(!serial.equals("")) pstmt.setString(i++, "%" + serial + "%");
			if(!desc.equals("")) 	pstmt.setString(i++, "%" + desc + "%");
			if(!allow.equals("")){
				int iAllow = Integer.parseInt(allow.toString());
				pstmt.setInt(i++, iAllow);
			}

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UsbDevInfoModel model = new UsbDevInfoModel();
				model.setUsbId(rs.getInt("no"));
				model.setName(rs.getString("name"));
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
}

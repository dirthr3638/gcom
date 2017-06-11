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

import gcom.Model.UsbDevInfoModel;;


public class DeviceDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public DeviceDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getUnAuthUsbListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE usb.allow = 0 ";
		String name = map.get("name").toString();
		String serial = map.get("serial").toString();
		String desc = map.get("desc").toString();

		if(!name.equals("")) 	whereSql += "AND usb.name LIKE ? ";
		if(!serial.equals("")) 	whereSql += "AND usb.serial_number LIKE ? ";
		if(!desc.equals("")) 	whereSql += "AND usb.description LIKE ? ";
		
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
		
		String whereSql = "WHERE usb.allow = 0 ";
		String name = map.get("name").toString();
		String serial = map.get("serial").toString();
		String desc = map.get("desc").toString();

		if(!name.equals("")) 	whereSql += "AND usb.name LIKE ? ";
		if(!serial.equals("")) 	whereSql += "AND usb.serial_number LIKE ? ";
		if(!desc.equals("")) 	whereSql += "AND usb.description LIKE ? ";
		
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

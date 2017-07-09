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

import gcom.Model.SubAdminModel;
import gcom.common.services.ConfigInfo;
import gcom.common.util.encrypto.hashEncrypto;
import gcom.user.model.UserInfoModel;


public class ManagementDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public ManagementDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getAdminListCount(HashMap<String, Object> map){
		int result = 0;
		
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
		if(oDept != null)	whereSql += "AND admin.dept_no in ("+idList+") ";

	
		String sql= 
"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM admin_info AS admin ";
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
	
	public List<SubAdminModel> getAdminList(HashMap<String, Object> map){
		List<SubAdminModel> data = new ArrayList<SubAdminModel>();
		
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
		if(oDept != null)			whereSql += "AND admin.dept_no in ("+idList+") ";
		
		whereSql += "ORDER BY admin.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "admin.no AS admin_no, "
+ "admin.id AS admin_id, "
+ "ifnull(admin.pw, '') AS admin_pw, "
+ "admin.ip_addr0 AS ip_addr, "
+ "admin.ip_addr1 AS ip_addr1, "
+ "admin.admin_mode AS admin_mode, "
+ "admin.login_failed_timestamp "
+ "FROM admin_info AS admin ";

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

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				SubAdminModel model = new SubAdminModel();
				model.setAdminNo(rs.getInt("admin_no"));
				model.setAdminId(rs.getString("admin_id"));
				model.setIsPassword(rs.getString("admin_pw").equals("") ? false : true);
				model.setIpAddr(rs.getString("ip_addr"));
				model.setIpAddr1(rs.getString("ip_addr1"));
				model.setAdminMode(rs.getInt("admin_mode"));
				model.setLoginFailTimeStamp(rs.getDouble("login_failed_timestamp"));

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

	public SubAdminModel getAdminUserInfo(HashMap<String, Object> map) {
		SubAdminModel model = new SubAdminModel();
		String adminId = map.get("no").toString();
		
		String sql= 
				"SELECT "
					+ "admin.no AS admin_no, "
					+ "admin.id AS admin_id, "
					+ "ifnull(admin.pw, '') AS admin_pw, "
					+ "admin.ip_addr0 AS ip_addr, "
					+ "admin.ip_addr1 AS ip_addr1, "
					+ "admin.dept_no AS dept_no "					
				+ "FROM admin_info AS admin "
				+ "WHERE admin.no = ? ";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, adminId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setAdminNo(rs.getInt("admin_no"));
				model.setAdminId(rs.getString("admin_id"));
				model.setIsPassword(rs.getString("admin_pw").equals("") ? false : true);
				model.setIpAddr(rs.getString("ip_addr"));
				model.setIpAddr1(rs.getString("ip_addr1"));
				model.setDept_no(rs.getInt("dept_no"));
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
	

	public HashMap<String, Object> insertAdminUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String id = map.get("id").toString();
		String pw = map.get("pw").toString();
		int dept_no = Integer.parseInt(map.get("dept").toString());
		String ip1 = map.get("ip1").toString();
		String ip2 = map.get("ip2").toString();

		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;

		String insertSql= 
				"INSERT INTO guardcom.admin_info(id, pw, dept_no, user_no, admin_mode, ip_addr0, ip_addr1) "
				+ "VALUES (?, ?, ?, 0, 3, ?, ?)";


		try{
			con = ds.getConnection();

			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setString(i++, id);
			pstmt.setString(i++, hashEncrypto.HashEncrypt(pw));
			pstmt.setInt(i++, dept_no);
			pstmt.setString(i++, ip1);
			pstmt.setString(i++, ip2);

			pstmt.executeUpdate();
			
			result.put("returnCode", returnCode);
			
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
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
	
	
	public HashMap<String, Object> updateAdminUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String id = map.get("id").toString();
		String pw = map.get("pw").toString();
		String ip1 = map.get("ip1").toString();
		String ip2 = map.get("ip2").toString();

		int no = Integer.parseInt(map.get("no").toString());

		int dept = Integer.parseInt(map.get("dept").toString());
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;

		String insertSql= 
				"UPDATE admin_info "
				+ "SET "
				+ "id=?, "
				+ "ip_addr0=?, "
				+ "ip_addr1=?, "
				+ "dept_no=? ";

				if(pw != null && pw != "")
					insertSql +=  ",pw = ? ";
				
		insertSql +=   "WHERE no=?";


		try{
			con = ds.getConnection();

			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setString(i++, id);

			pstmt.setString(i++, ip1);
			pstmt.setString(i++, ip2);

			pstmt.setInt(i++, dept);
			if(pw != null && pw != "")
				pstmt.setString(i++, hashEncrypto.HashEncrypt(pw));

			pstmt.setInt(i++, no);

			pstmt.executeUpdate();
			
			result.put("returnCode", returnCode);
			
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
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
	
	public HashMap<String, Object> deleteAdminUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		String no = map.get("no").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;

		String insertSql= 
				"DELETE FROM guardcom.admin_info WHERE no=?";


		try{
			con = ds.getConnection();

			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setString(i++, no);

			pstmt.execute();
			
			result.put("returnCode", returnCode);
			
			
		}catch(SQLException ex){
			result.put("returnCode", ConfigInfo.RETURN_CODE_ERROR);
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

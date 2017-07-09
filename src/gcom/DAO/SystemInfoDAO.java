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

import gcom.Model.SystemInfoModel;
import gcom.common.util.ConfigInfo;

//이동식디스크 파일전송로그
public class SystemInfoDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public SystemInfoDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public int getSystemInfoListCount(HashMap<String, Object> map){
		int result = 0;
		
		String whereSql = "WHERE visible = 1 ";
		
		String sql= 
"SELECT "
+ "COUNT(*) AS cnt "
+ "FROM system_info AS sys ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

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
	
	
	public List<SystemInfoModel> getSystemInfoList(HashMap<String, Object> map){
		List<SystemInfoModel> data = new ArrayList<SystemInfoModel>();
		
		String whereSql = "WHERE visible = 1 ";
		
		whereSql += "ORDER BY sys.no DESC LIMIT ?, ? ";	
		
		String sql= 
"SELECT "
+ "sys.no AS sys_no, "
+ "sys.name AS sys_name, "
+ "sys.value AS value, "
+ "sys.description AS description "
+ "FROM system_info AS sys ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;

			pstmt.setInt(i++,  Integer.parseInt(map.get("startRow").toString()));
			pstmt.setInt(i++,  Integer.parseInt(map.get("endRow").toString()));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				SystemInfoModel model = new SystemInfoModel();
				model.setSysNo(rs.getInt("sys_no"));
				model.setDescription(rs.getString("description"));
				model.setValue(rs.getString("value"));
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
	

	public HashMap<String, Object> updateSystemInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int system_no = Integer.parseInt(map.get("system_no").toString()); 
		String value = map.get("value").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;

		String insertSql= "UPDATE system_info SET  "
				+ "value = ? ";

				insertSql += "WHERE no = ? ";

		try{
			con = ds.getConnection();

			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setString(i++, value);
			pstmt.setInt(i++, system_no);
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
	
	
}

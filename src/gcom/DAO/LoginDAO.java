package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.common.services.ConfigInfo;
import gcom.common.util.EncProc;
import gcom.common.util.encrypto.hashEncrypto;


public class LoginDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public LoginDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}

	public HashMap<String, Object> selectUserLoginCheck(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String userId = map.get("userId").toString();
    	String userPw = "";
    	
		try {
			userPw = hashEncrypto.HashEncrypt(map.get("userPw").toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
    	String returnCode = "S";
    	
    	String sql= 
				"SELECT no, "
				+ "dept_no, "
				+ "name, "
				+ "id, "
				+ "password "
				+ "FROM user_info "
				+ "WHERE id = ? ";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result.put("userNo", rs.getString("no"));
				result.put("deptNo", rs.getString("dept_no"));
				result.put("userName", rs.getString("name"));
				result.put("userId", rs.getString("id"));
				
				if (!userPw.equals(rs.getString("password"))) {
					returnCode = ConfigInfo.NOT_CORRECT_PASSWORD;
					result.put("returnCode", returnCode);
				} else {
					result.put("returnCode", returnCode);
				}
				
			} else {
				returnCode = ConfigInfo.NOT_EXIST_USER;
				result.put("returnCode", returnCode);
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

	public HashMap<String, Object> selectConsoleLoginCheck(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String userId = map.get("userId").toString();
		String userIp = map.get("userIp").toString();
    	String userPw = "";
    	
		try {
			userPw = EncProc.encrypt(map.get("userPw").toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
    	String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
    	
    	String sql= 
				"SELECT no, "
				+ "dept_no, "
				+ "id, "
				+ "pw,"
				+ "ip_addr0,"
				+ "ip_addr1  "
				+ "FROM admin_info "
				+ "WHERE id = ? ";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result.put("userNo", rs.getString("no"));
				result.put("deptNo", rs.getString("dept_no"));
				result.put("userId", rs.getString("id"));
				
				if (!hashEncrypto.HashEncrypt(userPw).equals(rs.getString("pw"))) {
					returnCode = ConfigInfo.NOT_CORRECT_PASSWORD;
					result.put("returnCode", returnCode);
				} else if(!userIp.equals(rs.getString("ip_addr0")) &&  !userIp.equals(rs.getString("ip_addr1"))){
					//IP가 일치하지 않음
					returnCode = ConfigInfo.NOT_CORRECT_IP;
					result.put("returnCode", returnCode);					
				}
				else {
					result.put("returnCode", returnCode);
				}
				
			} else {
				returnCode = ConfigInfo.NOT_EXIST_USER;
				result.put("returnCode", returnCode);
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
	
}

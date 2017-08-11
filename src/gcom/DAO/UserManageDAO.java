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
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;
import gcom.common.util.ConfigInfo;
import gcom.common.util.encrypto.hashEncrypto;


public class UserManageDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public UserManageDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	

	public HashMap<String, Object> insertUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int dept_no = Integer.parseInt(map.get("dept_no").toString()); 
		String duty = map.get("duty").toString();
		String rank = map.get("rank").toString();
		String number = map.get("number").toString();
		String name = map.get("name").toString();
		String phone = map.get("phone").toString();
		String id = map.get("id").toString();
		String password = map.get("password").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;

		String numberCheck = "SELECT COUNT(*) AS cnt FROM user_info WHERE number = ? ";
		String idCheck = "SELECT COUNT(*) AS cnt FROM user_info WHERE id = ? ";
		
		
		String insertSql= "INSERT INTO user_info "
				+ "(dept_no, duty, rank, number, name, phone, id, valid, salt, pshash, ekek, session_id, public_key, private_key, password, notice) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, 1, null, null, null, null, null, null, ?, '') ";
		
		try{
			con = ds.getConnection();

			pstmt=con.prepareStatement(numberCheck);

			int i = 1;
			pstmt.setString(i++, number);

			rs = pstmt.executeQuery();
			int numberCount = 0;
			int idCount = 0;
			if(rs.next()){	
				numberCount = rs.getInt("cnt");
			}
			
			if(numberCount > 0){
				result.put("returnCode", ConfigInfo.EXIST_USER_NUMBER);
				return result;
			}

			pstmt=con.prepareStatement(idCheck);

			 i = 1;
			pstmt.setString(i++, id);

			if(idCount > 0){
				result.put("returnCode", ConfigInfo.DUP_USER_ID);
				return result;
			}

			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			i = 1;
			pstmt.setInt(i++, dept_no);
			pstmt.setString(i++, duty);
			pstmt.setString(i++, rank);
			pstmt.setString(i++, number);
			pstmt.setString(i++, name);
			pstmt.setString(i++, phone);
			pstmt.setString(i++, id);
			pstmt.setString(i++, hashEncrypto.HashEncrypt(password));

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
	
	public HashMap<String, Object> updateUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int user_no = Integer.parseInt(map.get("user_no").toString()); 
		int dept_no = Integer.parseInt(map.get("dept_no").toString()); 
		String duty = map.get("duty").toString();
		String rank = map.get("rank").toString();
		String number = map.get("number").toString();
		String name = map.get("name").toString();
		String phone = map.get("phone").toString();
		String id = map.get("id").toString();
		String password = map.get("password").toString();
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		String numberCheck = "SELECT COUNT(*) AS cnt FROM user_info WHERE number = ? AND no != ?";
		String idCheck = "SELECT COUNT(*) AS cnt FROM user_info WHERE id = ? AND no != ?";

		
		String insertSql= "UPDATE user_info SET  "
				+ "dept_no = ?, "
				+ "duty = ?, "
				+ "rank = ?, "
				+ "number = ?, "
				+ "name = ?, "
				+ "phone = ?, "
				+ "id = ? ";

				if(password != null && password != "")
					insertSql +=  ",password = ? ";

				insertSql += "WHERE no = ? ";

		try{
			con = ds.getConnection();

			pstmt=con.prepareStatement(numberCheck);

			int i = 1;
			pstmt.setString(i++, number);
			pstmt.setInt(i++, user_no);

			rs = pstmt.executeQuery();
			int numberCount = 0;
			int idCount = 0;
			if(rs.next()){	
				numberCount = rs.getInt("cnt");
			}
			
			if(numberCount > 0){
				result.put("returnCode", ConfigInfo.EXIST_USER_NUMBER);
				return result;
			}

			
			pstmt=con.prepareStatement(idCheck);

			 i = 1;
			pstmt.setString(i++, id);

			if(idCount > 0){
				result.put("returnCode", ConfigInfo.DUP_USER_ID);
				return result;
			}
			
			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			i = 1;
			pstmt.setInt(i++, dept_no);
			pstmt.setString(i++, duty);
			pstmt.setString(i++, rank);
			pstmt.setString(i++, number);
			pstmt.setString(i++, name);
			pstmt.setString(i++, phone);
			pstmt.setString(i++, id);
			if(password != null && password != "")
				pstmt.setString(i++, hashEncrypto.HashEncrypt(password));

			pstmt.setInt(i++, user_no);

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
	
	public HashMap<String, Object> removeUserInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		int user_no = Integer.parseInt(map.get("user_no").toString()); 
		
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		String insertSql= "UPDATE user_info SET  "
				+ "valid = 0 "
				+ "WHERE no = ?";
		
		try{
			con = ds.getConnection();

			pstmt=con.prepareStatement(insertSql, java.sql.Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pstmt.setInt(i++, user_no);

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

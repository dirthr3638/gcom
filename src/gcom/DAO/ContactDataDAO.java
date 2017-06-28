package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.Model.PrintFileModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;
import gcom.Model.statistic.ContactSimpleModel;


public class ContactDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public ContactDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public List<ContactSimpleModel> getSimpleContactList(Map<String, Object> map){
		List<ContactSimpleModel> data = new ArrayList<ContactSimpleModel>();
		
		String whereSql = "WHERE ur.valid=1 ";
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
		
		if(oDept != null)			whereSql += "AND dept.dept_no in ("+idList+") ";
		
		whereSql += "ORDER BY contact.contact_id desc LIMIT 7 ";	
		
		String sql= 
"SELECT "
+ "contact.contact_id, "
+ "contact.contact_title, "
+ "ur.name AS user_name,dept.short_name "
+ "FROM user_contact_info AS contact "
+ "INNER JOIN user_info AS ur ON ur.no = contact.reg_user_staf_no "
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

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ContactSimpleModel model = new ContactSimpleModel();
				model.setContactNo(rs.getInt("contact_id"));
				model.setContactSubject(rs.getString("contact_title"));
				model.setContactDept(rs.getString("short_name"));
				model.setContactWriter(rs.getString("user_name"));

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

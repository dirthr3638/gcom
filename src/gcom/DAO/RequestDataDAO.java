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
import gcom.Model.statistic.RequestSimpleModel;


public class RequestDataDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public RequestDataDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public List<RequestSimpleModel> getSimpleRequestList(Map<String, Object> map){
		List<RequestSimpleModel> data = new ArrayList<RequestSimpleModel>();
		
		String whereSql = " ";
		String[] oDept = null;
		StringBuilder idList = new StringBuilder();

/*		if(map.containsKey("dept") && map.get("dept") != null){
			oDept = (String[])map.get("dept");			
			for (String id : oDept){
				if(idList.length() > 0 )	
					idList.append(",");

				idList.append("?");
			}
		}
		
		if(oDept != null)			whereSql += "AND dept.dept_no in ("+idList+") ";
*/		
		
		String sql= 
"SELECT * "
+ "FROM (  "
	+ "(SELECT 2 AS req_type, "
	+ "req.req_id, "
	+ "dept.short_name AS dept_name, "
	+ "req.mem_name, "
	+ "req.req_date AS reg_date "
	+ "FROM user_account_request AS req   "
	+ "INNER JOIN dept_info AS dept "
	+ "ON dept.no = req.dept_no  "
	+ "ORDER BY req.req_date DESC LIMIT 7)  "
	+ " "
	+ "UNION"
	+ " "
	+ "(SELECT 1 AS req_type, "
	+ "req.no AS req_id, "
	+ "dept.short_name AS dept_name, "
	+ "ur.name AS mem_name, "
	+ "req.request_server_time AS reg_date "
	+ "FROM policy_request_info AS req  "
	+ "INNER JOIN user_info AS ur ON ur.no = req.user_no  "
	+ "INNER JOIN dept_info AS dept ON ur.dept_no = dept.no  "
	+ "ORDER BY req.request_server_time DESC LIMIT 7)"
+ ") AS T "
+ "ORDER BY reg_date DESC LIMIT 7 ";

		sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

			int i = 1;
/*			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				RequestSimpleModel model = new RequestSimpleModel();
				model.setRequestType(rs.getInt("req_type"));
				model.setRequestNo(rs.getInt("req_id"));
				model.setRequestDept(rs.getString("dept_name"));
				model.setRequestWriter(rs.getString("mem_name"));

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

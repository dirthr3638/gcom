package gcom.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.Model.DeptModel;

public class DeptDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public DeptDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public List<DeptModel> getDeptList(int adminNumber){
		List<DeptModel> data = new ArrayList<DeptModel>();
		
		String sql= 
"SELECT * from "
+ "(select * from dept_info order by parent, no) dept_info_sorted,"
+ "(select @pv := ?) initialisation where (find_in_set(parent, @pv) > 0 or no = @pv) and"
+ "@pv := concat(@pv, ',', no);";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  adminNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DeptModel model = new DeptModel();
				model.setDept_no(rs.getInt("no"));
				model.setParent(rs.getInt("no"));
				model.setLeap(rs.getInt("no"));
				model.setDept(rs.getInt("no"));
				model.setAdmin_no(rs.getInt("no"));
				model.setName(rs.getString("no"));
				model.setShortName(rs.getString("no"));
				model.setValid(rs.getInt("no"));
				model.setChildCount(rs.getInt("no"));
				
				data.add(model);
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		} 
		
		return data;
	}
}

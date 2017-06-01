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
import gcom.Model.DeptTreeModel;

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
		} finally {
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
	
	public List<DeptTreeModel> getDeptListForJSTree(int adminNumber){
		List<DeptTreeModel> data = new ArrayList<DeptTreeModel>();
		
		String sql= 
"SELECT if(no = ? , 0, parent) AS parent,"
+" no, leaf, admin_no, name, short_name, valid, sort_index,child_count, recent_no, min_child_no, max_child_no"
+" from "
+" (select * from dept_info order by parent, no) dept_info_sorted,"
+" (select @pv := ?) initialisation where (find_in_set(parent, @pv) > 0 or no = @pv) and"
+" @pv := concat(@pv, ',', no);";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,  adminNumber);
			pstmt.setInt(2,  adminNumber);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				DeptTreeModel model = new DeptTreeModel();
				model.setId(Integer.toString(rs.getInt("no")));
				model.setParent( Integer.toString(rs.getInt("parent")));
				model.setText(rs.getString("short_name"));
				data.add(model);

				if(rs.getInt("child_count") > 0 ){
					DeptTreeModel _model = new DeptTreeModel();
					_model.setId( "_" + Integer.toString(rs.getInt("no")));
					_model.setParent( Integer.toString(rs.getInt("no")));
					_model.setText(rs.getString("short_name") + " 소속인원");					
					data.add(_model);
				}
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

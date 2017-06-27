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

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.statistic.FlotChartDataModel;
import gcom.Model.PolicyInfoModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PrintFileModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;


public class PolicyStatistcDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public PolicyStatistcDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getExportChartDayData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
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

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
*/
		
		whereSql += "ORDER BY exp.export_client_time ";	
		
		String sql= 
"SELECT UNIX_TIMESTAMP(date_format(exp.export_client_time, '%Y/%m/%d'))*1000 AS reg_time, COUNT(*) AS cnt " 
+ "FROM disk_export_log AS exp "
+ "INNER JOIN user_info AS ur ON exp .user_no = ur.no "
+ "WHERE exp.export_client_time >= '2015-04-23' GROUP BY DATE(exp.export_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getExportChartMonthData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
		String whereSql = " ";
		
/*		String[] oDept = null;
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
*/
		
		whereSql += "ORDER BY exp.export_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(exp.export_client_time, '%Y/%m/1'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt " 
+ "FROM disk_export_log AS exp "
+ "INNER JOIN user_info AS ur ON exp .user_no = ur.no "
+ "WHERE exp.export_client_time >= '2015-04-23' "
+ "GROUP BY YEAR(exp.export_client_time), MONTH(exp.export_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	
	
	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getPrintChartDayData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
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

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
*/
		
		whereSql += "ORDER BY print.print_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(print.print_client_time, '%Y/%m/%d'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt " 
+ "FROM print_log AS print "
+ "INNER JOIN user_info AS ur ON print.user_no = ur.no "
+ "WHERE print.print_client_time >= '2015-04-23' "
+ "GROUP BY DATE(print.print_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getPrintChartMonthData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
		String whereSql = " ";
		
/*		String[] oDept = null;
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
*/
		
		whereSql += "ORDER BY print.print_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(print.print_client_time, '%Y/%m/1'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt " 
+ "FROM print_log AS print "
+ "INNER JOIN user_info AS ur ON print.user_no = ur.no "
+ "WHERE print.print_client_time >= '2015-04-23' "
+ "GROUP BY YEAR(print.print_client_time), MONTH(print.print_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getPatternChartDayData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
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

		if(oDept != null)			whereSql += "AND ur.dept_no in ("+idList+") ";
*/
		
		whereSql += "ORDER BY pattern.found_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(pattern.found_client_time, '%Y/%m/%d'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt " 
+ "FROM pattern_log AS pattern "
+ "INNER JOIN user_info AS ur ON pattern.user_no = ur.no "
+ "WHERE pattern.found_client_time >= '2015-04-23' "
+ "GROUP BY DATE(pattern.found_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getPatternChartMonthData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
		String whereSql = " ";
		
/*		String[] oDept = null;
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
*/
		
		whereSql += "ORDER BY pattern.found_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(pattern.found_client_time, '%Y/%m/1'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt " 
+ "FROM pattern_log AS pattern "
+ "INNER JOIN user_info AS ur ON pattern.user_no = ur.no "
+ "WHERE pattern.found_client_time >= '2015-04-23' "
+ "GROUP BY YEAR(pattern.found_client_time ), MONTH(pattern.found_client_time ) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getUSBChartDayData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
		String whereSql = " ";
		
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

		
		whereSql += "ORDER BY usb.connect_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(usb.connect_client_time, '%Y/%m/%d'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt "
+ "FROM usb_connect_log AS usb "
+ "INNER JOIN user_info AS ur ON usb.user_no = ur.no "
+ "WHERE usb.connect_client_time >= '2015-04-23' "
+ "GROUP BY DATE(usb.connect_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
	

	//기준일, 기준일로부터의 데이터, 권한
	public FlotChartDataModel getUSBChartMonthData(Map<String, Object> map){
		FlotChartDataModel data = new FlotChartDataModel();
		
		String whereSql = " ";
		
/*		String[] oDept = null;
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
*/
		
		whereSql += "ORDER BY usb.connect_client_time ";	
		
		String sql= 
"SELECT "
+ "UNIX_TIMESTAMP(date_format(usb.connect_client_time, '%Y/%m/1'))*1000 AS reg_time, "
+ "COUNT(*) AS cnt "
+ "FROM usb_connect_log AS usb "
+ "INNER JOIN user_info AS ur ON usb.user_no = ur.no "
+ "WHERE usb.connect_client_time >= '2015-01-23' "
+ "GROUP BY YEAR(usb.connect_client_time), MONTH(usb.connect_client_time) ";

sql += whereSql;			
			
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);

/*			int i = 1;
			if(oDept != null){
				for(int t = 0; t<oDept.length ; t++){
					pstmt.setInt(i++, Integer.parseInt(oDept[t]));
				}
			}
*/			
			rs = pstmt.executeQuery();

			List<List<Long>> c_data = new ArrayList<List<Long>>();

			while(rs.next()){
				List<Long> item = new ArrayList<Long>();
				item.add(rs.getLong("reg_time"));
				item.add(rs.getLong("cnt"));
				c_data.add(item);
			}
			
			data.setItem(c_data);
			
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
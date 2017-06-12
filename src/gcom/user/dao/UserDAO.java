package gcom.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;
import gcom.user.model.UserPolicyListModel;
import gcom.user.model.UserPolicyModel;


public class UserDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public UserDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}
	
	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map){
		List<UserPolicyListModel> list = new ArrayList<UserPolicyListModel>();
		String sql= 
				"SELECT agent_info.policy_uninstall_enabled, "
			    + "agent_info.policy_watermark_enabled, "
			    + "agent_info.policy_printer_enabled, "
			    + "agent_info.policy_fs_encryption_enabled, "
			    + "agent_info.policy_cd_enabled, "
			    + "agent_info.policy_cd_encryption_enabled, "
			    + "agent_info.policy_removal_storage_export_enabled,"
			    + "agent_info.policy_removal_storage_admin_mode, "
			    + "IFNULL(agent_info.policy_usb_descriptor, '') as policy_usb_descriptor, "
			    + "IFNULL(agent_info.policy_port_descriptor, '') as policy_port_descriptor, "
			    + "agent_info.policy_net_descriptor, "
			    + "agent_info.policy_wlan_enabled, "
			    + "agent_info.policy_web_export_enabled, "
			    + "agent_info.policy_sensitive_file_access, "
			    + "agent_info.policy_sensitive_dir_enabled, "
			    + "IFNULL(agent_info.policy_authentication_code, '') as policy_authentication_code, "
			    + "agent_info.policy_net_share_descriptor, "
			    + "IFNULL(agent_info.policy_print_log_descriptor, '') as policy_print_log_descriptor "
			    + "FROM user_info " 
				+ "INNER JOIN agent_info ON user_info.no = agent_info.own_user_no "
				+ "WHERE user_info.id = ?";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, map.get("user_id").toString());
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column_name = "";
			
			UserPolicyModel policy = new UserPolicyModel();
			
			if(rs.next()){
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column_name = metaData.getColumnName(indexOfcolumn + 1);
					UserPolicyListModel model = new UserPolicyListModel();
					
					model.setPolicyEngName(column_name);
					model.setPolicyKorName(policy.getPolicy().get(column_name));
					model.setPolicyStatus(rs.getString(column_name));
					
					list.add(model);
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
		
		return list;
	}
	

	public UserInfoModel getUserInfo(HashMap<String, Object> map) {
		UserInfoModel model = new UserInfoModel();
		
		String sql= 
				"SELECT user_info.no, "
				+ "user_info.name, "
				+ "user_info.phone, "
				+ "dept_info.short_name as dept_name, "
				+ "user_info.duty "
				+ "FROM user_info "
				+ "INNER JOIN dept_info ON user_info.dept_no = dept_info.no "
				+ "WHERE user_info.id = ?";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, map.get("user_id").toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setKeyNo(rs.getInt("no"));
				model.setName(rs.getString("name"));
				model.setPhone(rs.getString("phone"));
				model.setDeptName(rs.getString("dept_name"));
				model.setDuty(rs.getString("duty"));
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
	
	public int getUserNoticeListCount(HashMap<String, Object> map) {
		int cnt = 0;
		String search_text = map.get("search_text").toString();
		String search_type = map.get("search_type").toString();
		
		String sql= 
				"SELECT COUNT(*) AS cnt FROM user_notice_bbs AS bbs "
				+ "INNER JOIN user_info AS user_info ON bbs.reg_staf_id = user_info.id "
				+ "WHERE bbs.del_yn = 'N' ";
		
		if(!"".equals(search_text)){
			if("1".equals(search_type)) {
				sql += "AND bbs.bbs_title like ? ";
			} else if ("2".equals(search_type)) {
				sql += "AND user_info.name like ? ";
			}
		}
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			if(!"".equals(search_text)){
				pstmt.setString(1, "%" + search_text + "%");
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("cnt");	
				
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
		return cnt;
	}

	public List<UserNoticeModel> getUserNoticeList(HashMap<String, Object> map) {
		List<UserNoticeModel> list = new ArrayList<UserNoticeModel>();
		String search_text = map.get("search_text").toString();
		String search_type = map.get("search_type").toString();
		
		String sql= 
				"SELECT bbs.bbs_id, "
			    + "bbs.bbs_title, "
			    + "bbs.special_type, "
			    + "user_info.name, "
			    + "DATE(bbs.reg_dt) AS reg_dt, "
			    + "bbs_hit.hit_cnt, "
			    + "bbs.attfile_yn "
				+ "FROM user_notice_bbs AS bbs "
				+ "INNER JOIN user_info AS user_info ON bbs.reg_staf_id = user_info.id "
				+ "INNER JOIN user_notice_bbs_hit AS bbs_hit ON bbs.bbs_id = bbs_hit.bbs_id "
				+ "WHERE bbs.del_yn = 'N' ";
				
		
		if(!"".equals(search_text)){
			if("1".equals(search_type)) {
				sql += "AND bbs.bbs_title like ? ";
			} else if ("2".equals(search_type)) {
				sql += "AND user_info.name like ? ";
			}
		}
		
		sql += "ORDER BY bbs.bbs_id DESC, bbs.reg_dt DESC ";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			if(!"".equals(search_text)){
				pstmt.setString(1, "%" + search_text + "%");
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserNoticeModel model = new UserNoticeModel();
				model.setBbsId(rs.getInt("bbs_id"));
				model.setBbsTitle(rs.getString("bbs_title"));
				model.setBbsSpecialYN(rs.getString("special_type"));
				model.setBbsRegStaf(rs.getString("name"));
				model.setBbsRegDate(rs.getString("reg_dt"));
				model.setBbsClickCnt(rs.getInt("hit_cnt"));
				model.setBbsAttfileYN(rs.getString("attfile_yn"));
				
				list.add(model);
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
		
		return list;
	}
}

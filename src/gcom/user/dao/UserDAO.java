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

import gcom.user.model.MemberPolicyModel;
import gcom.user.model.UserContactModel;
import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;
import gcom.user.model.UserSystemPolicyQueryModel;

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
	
	public MemberPolicyModel getMemberPolicyInfo(HashMap<String, Object> map){
		MemberPolicyModel model = new MemberPolicyModel();
		String user_id = map.get("user_id").toString();
		
		String sql= 
				"SELECT ui.no AS userNo, "
				    + "ui.id AS userId, "
				    + "pi.no AS policyNo, "
				    + "pi.uninstall_enabled, "
				    + "pi.file_encryption_enabled, "
				    + "pi.cd_encryption_enabled, "
				    + "pi.printer_enabled, "
				    + "pi.cd_enabled, "
				    + "pi.cd_export_enabled, "
				    + "pi.wlan_enabled, "
				    + "pi.net_share_enabled, "
				    + "pi.web_export_enabled, "
				    + "pi.removal_storage_export_enabled, "
				    + "pi.removal_storage_admin_mode, "
				    + "pi.usb_dev_list, "
				    + "pi.com_port_list, "
				    + "pi.net_port_list, "
				    + "pi.process_list, "
				    + "pi.file_pattern_list, "
				    + "web_addr_list, "
				    + "watermark_descriptor, "
				    + "print_log_descriptor, "
				    + "quarantine_path_access_code, "
				    + "pattern_file_control "
				+ "FROM user_info as ui "
				+ "INNER JOIN agent_info as ai on ui.no = ai.own_user_no "
				+ "INNER JOIN policy_info as pi on ai.policy_no = pi.no "
				+ "WHERE ui.id = ? ";

		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				model.setUserNo(rs.getInt("userNo"));
				model.setUserId(rs.getString("userId"));
				model.setPolicyNo(rs.getInt("policyNo"));
				
				model.setIsUninstall(rs.getInt("uninstall_enabled"));
				model.setIsFileEncryption(rs.getInt("file_encryption_enabled"));
				model.setIsCdEncryption(rs.getInt("cd_encryption_enabled"));
				model.setIsPrint(rs.getInt("printer_enabled"));
				model.setIsCdEnabled(rs.getInt("cd_enabled"));
				model.setIsCdExport(rs.getInt("cd_export_enabled"));
				model.setIsWlan(rs.getInt("wlan_enabled"));
				model.setIsNetShare(rs.getInt("net_share_enabled"));
				model.setIsWebExport(rs.getInt("web_export_enabled"));
				model.setIsStorageExport(rs.getInt("removal_storage_export_enabled"));
				model.setIsStorageAdmin(rs.getInt("removal_storage_admin_mode"));
				
				model.setIsUsbBlock(rs.getString("usb_dev_list"));
				model.setIsComPortBlock(rs.getString("com_port_list"));
				model.setIsNetPortBlock(rs.getString("net_port_list"));
				model.setIsProcessList(rs.getString("process_list"));
				model.setIsFilePattern(rs.getString("file_pattern_list"));
				model.setIsWebAddr(rs.getString("web_addr_list"));
				
				model.setWatermarkInfo(rs.getString("watermark_descriptor"));
				model.setPrintLogDesc(Integer.parseInt(rs.getString("print_log_descriptor").toString()));
				model.setQuarantinePathAccessCode(rs.getString("quarantine_path_access_code"));
				model.setPatternFileControl(rs.getInt("pattern_file_control"));
				
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
				+ "LEFT JOIN admin_info AS admin ON bbs.reg_staf_no = admin.no "
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
		int startRow = Integer.parseInt(map.get("startRow").toString());
		int endRow = Integer.parseInt(map.get("endRow").toString());
		
		String sql= 
				"SELECT bbs.bbs_id, "
			    + "bbs.bbs_title, "
			    + "bbs.special_type, "
			    + "admin.id, "
			    + "DATE(bbs.reg_dt) AS reg_dt, "
			    + "bbs_hit.hit_cnt, "
			    + "bbs.attfile_yn "
				+ "FROM user_notice_bbs AS bbs "
				+ "LEFT JOIN admin_info AS admin ON bbs.reg_staf_no = admin.no "
				+ "INNER JOIN user_notice_bbs_hit AS bbs_hit ON bbs.bbs_id = bbs_hit.bbs_id "
				+ "WHERE bbs.del_yn = 'N' ";
				
		
		if(!"".equals(search_text)){
			if("1".equals(search_type)) {
				sql += "AND bbs.bbs_title like ? ";
			} else if ("2".equals(search_type)) {
				sql += "AND user_info.name like ? ";
			}
		}
		
		sql += "ORDER BY bbs.bbs_id DESC, bbs.reg_dt DESC LIMIT ?, ?";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			if(!"".equals(search_text)){
				pstmt.setString(1, "%" + search_text + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserNoticeModel model = new UserNoticeModel();
				model.setBbsId(rs.getInt("bbs_id"));
				model.setBbsTitle(rs.getString("bbs_title"));
				model.setBbsSpecialYN(rs.getString("special_type"));
				model.setBbsRegStaf(rs.getString("id"));
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

	public UserNoticeModel getUserNoticeDetail(HashMap<String, Object> map) {
		UserNoticeModel model = new UserNoticeModel();
		int bbs_id = Integer.parseInt(map.get("bbs_id").toString());
		
		String sql= 
				"SELECT bbs.bbs_id, "
			    + "bbs.bbs_title, "
			    + "bbs.special_type, "
			    + "admin.id, "
			    + "DATE(bbs.reg_dt) AS reg_dt, "
			    + "bbs_hit.hit_cnt, "
			    + "bbs.attfile_yn, "
			    + "bbs.bbs_body "
				+ "FROM user_notice_bbs AS bbs "
				+ "LEFT JOIN admin_info AS admin ON bbs.reg_staf_no = admin.no "
				+ "INNER JOIN user_notice_bbs_hit AS bbs_hit ON bbs.bbs_id = bbs_hit.bbs_id "
				+ "WHERE bbs.del_yn = 'N' "
				+ "AND bbs.bbs_id = ?";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bbs_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				model.setBbsId(rs.getInt("bbs_id"));
				model.setBbsTitle(rs.getString("bbs_title"));
				model.setBbsSpecialYN(rs.getString("special_type"));
				model.setBbsRegStaf(rs.getString("id"));
				model.setBbsRegDate(rs.getString("reg_dt"));
				model.setBbsClickCnt(rs.getInt("hit_cnt"));
				model.setBbsAttfileYN(rs.getString("attfile_yn"));
				model.setBbsBody(rs.getString("bbs_body"));
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
	
	public void updateNoticeViewCount(HashMap<String, Object> map) {
		int bbs_id = Integer.parseInt(map.get("bbs_id").toString());
		
		String sql= "UPDATE user_notice_bbs_hit SET hit_cnt = hit_cnt + 1 WHERE bbs_id = ? ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bbs_id);
			pstmt.executeUpdate();
			
			con.commit();
			
		}catch(SQLException ex){
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
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
	}

	public int getUserContactInfoCount(HashMap<String, Object> map) {
		int cnt = 0;
		
		String id = map.get("user_id").toString();
		
		String sql= 
				"SELECT COUNT(*) as cnt FROM (SELECT con.contact_id, "
				+ "con.contact_type, "
				+ "con.contact_title, "
				+ "user_info.id, "
				+ "DATE(con.reg_dt) as reg_dt, "
				+ "con.comment_yn, "
				+ "IFNULL(admin_info.id, '') as comment_reg_staf_id, "
				+ "IFNULL(con_comm.reply_content, '') as reply_content, "
				+ "IFNULL(con_comm.reg_dt, '') as comment_reg_dt "
				+ "FROM user_contact_info AS con "
				+ "LEFT JOIN user_info AS user_info ON con.reg_user_staf_no = user_info.no "
				+ "LEFT JOIN user_contact_comment AS con_comm ON con.contact_id = con_comm.contact_id "
				+ "LEFT JOIN admin_info AS admin_info ON con_comm.reg_admin_staf_no = admin_info.no "
				+ "WHERE user_info.id = ?  ) AS T ";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
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

	public List<UserContactModel> getUserContactlist(HashMap<String, Object> map) {
		List<UserContactModel> list = new ArrayList<UserContactModel>();
		String id = map.get("user_id").toString();
		int startRow = Integer.parseInt(map.get("startRow").toString());
		int endRow = Integer.parseInt(map.get("endRow").toString());
		
		String sql= 
				"SELECT con.contact_id, "
				+ "con.contact_type, "
				+ "con.contact_title, "
				+ "user_info.id, "
				+ "DATE(con.reg_dt) as reg_dt, "
				+ "con.comment_yn, "
				+ "IFNULL(admin_info.id, '')as comment_reg_staf_id, "
				+ "IFNULL(con_comm.reply_content, '') as reply_content, "
				+ "IFNULL(con_comm.reg_dt, '') as comment_reg_dt "
				+ "FROM user_contact_info AS con "
				+ "LEFT JOIN user_info AS user_info ON con.reg_user_staf_no = user_info.no "
				+ "LEFT JOIN user_contact_comment AS con_comm ON con.contact_id = con_comm.contact_id "
				+ "LEFT JOIN admin_info AS admin_info ON con_comm.reg_admin_staf_no = admin_info.no "
				+ "WHERE user_info.id = ? "
				+ "ORDER BY con.contact_id DESC, con.reg_dt DESC "
				+ "LIMIT ? ,? ";
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				UserContactModel model = new UserContactModel();
				model.setContactId(rs.getInt("contact_id"));
				model.setContactType(rs.getInt("contact_type"));
				model.setTypeName(rs.getInt("contact_type"));
				model.setContactTitle(rs.getString("contact_title"));
				model.setId(rs.getString("id"));
				model.setRegDt(rs.getString("reg_dt"));
				model.setCommentYN(rs.getString("comment_yn"));
				model.setCommnetRegStafId(rs.getString("comment_reg_staf_id"));
				model.setReplyContent(rs.getString("reply_content"));
				model.setCommentRegDt(rs.getString("comment_reg_dt"));
								
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

	public List<HashMap<String, Object>> getMemberPolicyDetail(HashMap<String, Object> map) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String group_id = map.get("group_id").toString();
		String keyCode = map.get("key").toString();
		UserSystemPolicyQueryModel getQuery = new UserSystemPolicyQueryModel(group_id, keyCode);
		String sql=  getQuery.getPolicySqlQuery();
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column = "";
			
			while(rs.next()){
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				// Column의 갯수만큼 회전
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column = metaData.getColumnName(indexOfcolumn + 1);
					// phone number 일 경우 복호화
					data.put(column, rs.getString(column));
				}
								
				list.add(data);
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

	public HashMap<String, Object> insertContactSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String titel = map.get("titel").toString();
		String body = map.get("body").toString();
		int userNo = Integer.parseInt(map.get("user_no").toString());
		String eMail = map.get("eMail").toString();
		int conType = Integer.parseInt(map.get("conType").toString());
		
		String returnCode = "S";
		
		String sql= "INSERT INTO user_contact_info (contact_title, contact_body, reg_user_staf_no, reg_dt, email, contact_type, comment_yn) " 
					+ " VALUES ( ?, ?, ?, NOW(), ?, ?, 'N') ";
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, titel);
			pstmt.setString(2, body);
			pstmt.setInt(3, userNo);
			pstmt.setString(4, eMail);
			pstmt.setInt(5, conType);
			pstmt.executeUpdate();
			
			con.commit();
			result.put("returnCode", returnCode);
			
		}catch(SQLException ex){
			result.put("returnCode", "E");
			if(con!=null) try{con.rollback();}catch(SQLException sqle){sqle.printStackTrace();}
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
	
	/*
	public List<HashMap<String, Object>> getUserSystemPolicyList(String code) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		UserSystemPolicyQueryModel getQuery = new UserSystemPolicyQueryModel(code);
		String sql=  getQuery.getPolicySqlQuery();
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int sizeOfColumn = metaData.getColumnCount();
			
			String column = "";
			
			while(rs.next()){
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				// Column의 갯수만큼 회전
				for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
					// Column의 갯수만큼 회전
					column = metaData.getColumnName(indexOfcolumn + 1);
					// phone number 일 경우 복호화
					data.put(column, rs.getString(column));
				}
								
				list.add(data);
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
	*/

}

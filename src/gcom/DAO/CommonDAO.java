package gcom.DAO;

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

import gcom.Model.PolicyInfoModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;
import gcom.common.util.CommonUtil;
import gcom.common.util.ConfigInfo;
import gcom.service.common.CommonServiceImpl;
import gcom.service.common.ICommonService;



public class CommonDAO {
	DataSource ds;
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public CommonDAO(){ 
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			ds=(DataSource)envCtx.lookup("jdbc/mysql");			
		}catch(Exception ex ){
			ex.printStackTrace();
		}
	}

	public void setPolicyUpdateToInsertLog(int key) {
		
		int policy_no = key;
		
		String sql= " INSERT INTO policy_log ( "
						+ "apply_time, "
						+ "uninstall_enabled, "
						+ "file_encryption_enabled, "
						+ "cd_encryption_enabled, "
						+ "printer_enabled, "
						+ "cd_enabled, "
						+ "cd_export_enabled, " 
						+ "wlan_enabled, "
						+ "net_share_enabled, "
						+ "web_export_enabled, "
						+ "removal_storage_export_enabled, "
						+ "removal_storage_admin_mode, "
						+ "usb_dev_list, "
						+ "com_port_list, "
						+ "net_port_list, "
						+ "process_list, "
						+ "file_pattern_list, "
						+ "web_addr_list, "
						+ "msg_block_list, "
						+ "watermark_descriptor, "
						+ "print_log_descriptor, "
						+ "quarantine_path_access_code, " 
						+ "pattern_file_control, "
						+ "request_server_time, "
						+ "request_client_time "
					+ ") SELECT "
						+ "NOW(), "
						+ "uninstall_enabled, "
						+ "file_encryption_enabled, "
						+ "cd_encryption_enabled, "
						+ "printer_enabled, "
						+ "cd_enabled, "
						+ "cd_export_enabled, " 
						+ "wlan_enabled, "
						+ "net_share_enabled, "
						+ "web_export_enabled, "
						+ "removal_storage_export_enabled, "
						+ "removal_storage_admin_mode, "
						+ "usb_dev_list, "
						+ "com_port_list, "
						+ "net_port_list, "
						+ "process_list, "
						+ "file_pattern_list, "
						+ "web_addr_list, "
						+ "msg_block_list, "
						+ "watermark_descriptor, " 
						+ "print_log_descriptor, "
						+ "quarantine_path_access_code, " 
						+ "pattern_file_control, "
						+ "update_server_time, "
						+ "update_client_time "
					+ "FROM policy_info "
					+ "WHERE no = ? ";
;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, policy_no);
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
	
}

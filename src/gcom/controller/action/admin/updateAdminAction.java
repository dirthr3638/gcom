package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;
import gcom.service.System.ISystemService;
import gcom.service.System.SystemServiceImpl;
import gcom.service.UserManage.IUserManageService;
import gcom.service.UserManage.UserManageServiceImpl;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;

public class updateAdminAction {
	public HashMap<String, Object> updateEnrollRequestReject(HashMap<String, Object> map){
		IRequestService as = new RequestServiceImpl();
		return as.updateEnrollRequestReject(map);	
	}
	
	public HashMap<String, Object> removeUserInfo(HashMap<String, Object> map){
		IUserManageService as = new UserManageServiceImpl();
		return as.removeUserInfo(map);	
	}
	
	public HashMap<String, Object> updateUserInfo(HashMap<String, Object> map){
		IUserManageService as = new UserManageServiceImpl();
		return as.updateUserInfo(map);	
	}
	
	public HashMap<String, Object> updateSystemInfo(HashMap<String, Object> map){
		ISystemService as = new SystemServiceImpl();
		return as.updateSystemInfo(map);	
	}
	
	public HashMap<String, Object> updateAdminUserInfo(HashMap<String, Object> map){
		IManagementService as = new ManagementServiceImpl();
		return as.updateAdminUserInfo(map);
	}

	public HashMap<String, Object> deleteAdminUserInfo(HashMap<String, Object> map){
		IManagementService as = new ManagementServiceImpl();
		return as.deleteAdminUserInfo(map);
	}
	
}

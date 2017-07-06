package gcom.controller.action.admin;

import java.util.HashMap;
import java.util.Map;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;
import gcom.service.UserManage.IUserManageService;
import gcom.service.UserManage.UserManageServiceImpl;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;

public class insertAdminAction {

	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.insertNoticeWriteSave(map);
	}
	
	public HashMap<String, Object> insertUserInfoFromRequest(HashMap<String, Object> map){
		IRequestService as = new RequestServiceImpl();
		return as.insertUserInfoFromRequest(map);
	}

	public HashMap<String, Object> applyPolicyDataSave(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.applyPolicyDataSave(map);
	}

	public HashMap<String, Object> insertUserInfo(HashMap<String, Object> map) {
		IUserManageService as = new UserManageServiceImpl();
		return as.insertUserInfo(map);
	}
	
	public HashMap<String, Object> insertAdminUserInfo(HashMap<String, Object> map){
		IManagementService as = new ManagementServiceImpl();
		return as.insertAdminUserInfo(map);
	}
	
	
	
	

}

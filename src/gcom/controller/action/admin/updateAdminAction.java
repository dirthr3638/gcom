package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;
import gcom.service.UserManage.IUserManageService;
import gcom.service.UserManage.UserManageServiceImpl;

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

	public HashMap<String, Object> updateNoticeModifyUpdate(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.updateNoticeModifyUpdate(map);
	}

	public HashMap<String, Object> updateContactCommentUpdate(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.updateContactCommentUpdate(map);
	}
}

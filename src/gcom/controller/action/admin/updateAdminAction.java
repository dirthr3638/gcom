package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
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
	

	public HashMap<String, Object> updateNoticeModifyUpdate(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.updateNoticeModifyUpdate(map);
	}

	public HashMap<String, Object> updateContactCommentUpdate(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.updateContactCommentUpdate(map);
	}

	public HashMap<String, Object> updatePolicyMsgUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyMsgUpdate(map);
	}

	public HashMap<String, Object> updatePolicyProcessUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyProcessUpdate(map);
	}

	public HashMap<String, Object> updatePolicyPatternUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyPatternUpdate(map);
	}

	public HashMap<String, Object> updatePolicyNetworkUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyNetworkUpdate(map);
	}

	public HashMap<String, Object> updatePolicySerialUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicySerialUpdate(map);
	}

	public HashMap<String, Object> updatePolicyWebsiteUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyWebsiteUpdate(map);
	}

	public HashMap<String, Object> updatePolicyUsbUpdate(HashMap<String, Object> map) {
		IPolicyService as = new PolicyServiceImpl();
		return as.updatePolicyUsbUpdate(map);
	}
}

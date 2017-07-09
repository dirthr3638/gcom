package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
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

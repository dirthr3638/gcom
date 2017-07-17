package gcom.controller.action;

import java.util.HashMap;

import gcom.Model.ServerAuditModel;
import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;
import gcom.service.UserManage.IUserManageService;
import gcom.service.UserManage.UserManageServiceImpl;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.service.UserService;
import gcom.user.service.UserServiceImpl;

public class updateAction {

	public HashMap<String, Object> updateUserInfoData(HashMap<String, Object> map) {
		UserService us = new UserServiceImpl();
		return us.updateUserInfoData(map);
	}

	

}

package gcom.controller.action.admin;

import java.util.HashMap;
import java.util.Map;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;

public class insertAdminAction {

	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.insertNoticeWriteSave(map);
	}
	
	public HashMap<String, Object> insertUserInfoFromRequest(HashMap<String, Object> map){
		IRequestService as = new RequestServiceImpl();
		return as.insertUserInfoFromRequest(map);
	}

}

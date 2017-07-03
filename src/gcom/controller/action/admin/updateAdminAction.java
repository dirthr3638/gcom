package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Request.IRequestService;
import gcom.service.Request.RequestServiceImpl;

public class updateAdminAction {
	public HashMap<String, Object> updateEnrollRequestReject(HashMap<String, Object> map){
		IRequestService as = new RequestServiceImpl();
		return as.updateEnrollRequestReject(map);	
	}
}

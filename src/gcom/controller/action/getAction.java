package gcom.controller.action;

import java.util.HashMap;
import java.util.List;

import gcom.Model.UserAgentModel;
import gcom.service.IUserAgentService;
import gcom.service.UserAgentServiceImpl;

public class getAction {

	public HashMap<String, Object> getUserAgentList(HashMap<String, Object> map){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		IUserAgentService as = new UserAgentServiceImpl();
		List<UserAgentModel> data = as.getUserAgentList(map);
		result.put("data", data);
		int total = as.getUserAgentListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	
}

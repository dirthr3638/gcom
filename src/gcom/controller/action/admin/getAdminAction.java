package gcom.controller.action.admin;

import java.util.HashMap;
import java.util.List;

import gcom.Model.*;
import gcom.service.System.ISystemService;
import gcom.service.System.SystemServiceImpl;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;

public class getAdminAction {

	public HashMap<String, Object> getSubAdminList(HashMap<String, Object> map){
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		IManagementService as = new ManagementServiceImpl();
		List<SubAdminModel> data = as.getAdminList(map);
		result.put("data", data);
		int total = as.getAdminListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	
	public HashMap<String, Object> getSystemInfoList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		ISystemService as = new SystemServiceImpl();
		List<SystemInfoModel> data = as.getSystemInfoList(map);
		result.put("data", data);
		int total = as.getSystemInfoListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;		
	}
	
}

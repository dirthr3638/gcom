package gcom.controller.action.admin;

import java.util.HashMap;
import java.util.List;

import gcom.Model.FileInfoModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.SubAdminModel;
import gcom.Model.SystemInfoModel;
import gcom.Model.UserInfoModel;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
import gcom.service.System.ISystemService;
import gcom.service.System.SystemServiceImpl;
import gcom.service.UserAgent.IUserAgentService;
import gcom.service.UserAgent.UserAgentServiceImpl;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.model.UserNoticeModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

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
	
	public HashMap<String, Object> getPolicyMessengerList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<PolicyMessengerModel> data = as.getPolicyMessengerList(map);
		result.put("data", data);
		int total = as.getPolicyMessengerListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}

	
	public HashMap<String, Object> getPolicyProcessList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<PolicyProcessModel> data = as.getPolicyProcessList(map);
		result.put("data", data);
		int total = as.getPolicyProcessListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}

	
	public HashMap<String, Object> getPolicyPatternList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<PolicyPatternModel> data = as.getPolicyPatternList(map);
		result.put("data", data);
		int total = as.getPolicyPatternListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}

	
	public HashMap<String, Object> getPolicyNetworkList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<PolicyNetworkModel> data = as.getPolicyNetworkList(map);
		result.put("data", data);
		int total = as.getPolicyNetworkListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	


	public HashMap<String, Object> getPolicySerialList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<PolicySerialModel> data = as.getPolicySerialList(map);
		result.put("data", data);
		int total = as.getPolicySerialListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}

	public HashMap<String, Object> getAdminNoticeList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		UserServiceInterface us = new UserServiceImpl();
		List<UserNoticeModel> data = us.getUserNoticeList(map);
		result.put("data", data);
		int total = us.getUserNoticeListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	
	public HashMap<String, Object> getUserInfoList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IUserAgentService as = new UserAgentServiceImpl();
		List<UserInfoModel> data = as.getUserInfoList(map);
		result.put("data", data);
		int total = as.getUserInfoListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;		
		
	}
	
}

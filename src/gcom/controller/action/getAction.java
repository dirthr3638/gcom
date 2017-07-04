package gcom.controller.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.DiskExportModel;
import gcom.Model.LoginLogModel;
import gcom.Model.MailExportModel;
import gcom.Model.MsnFileModel;
import gcom.Model.MsnTalkModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.PrintFileModel;
import gcom.Model.PrivacyLogModel;
import gcom.Model.UsbConnectModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;
import gcom.service.Device.DeviceInfoServiceImpl;
import gcom.service.Device.IDeviceInfoService;
import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
import gcom.service.UserAgent.IUserAgentService;
import gcom.service.UserAgent.UserAgentServiceImpl;
import gcom.user.service.UserService;
import gcom.user.service.UserServiceImpl;

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
	
	public HashMap<String, Object> getLoginLogList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IUserAgentService as = new UserAgentServiceImpl();
		List<LoginLogModel> data = as.getLoginlogList(map);
		result.put("data", data);
		int total = as.getLoginlogListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;				
	}
	
	public HashMap<String, Object> getUnAuthUsbList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getDiskTranList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<DiskExportModel> data = as.getDiskTranList(map);
		result.put("data", data);
		int total = as.getDiskTranListCount(map);
//		int total = 100;

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	public HashMap<String, Object> getUsbBlockList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbConnectModel> data = as.getUsbBlockList(map);
		result.put("data", data);
		int total = as.getUsbBlockListCount(map);

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getPrintList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<PrintFileModel> data = as.getPrintLogList(map);
		result.put("data", data);
		int total = as.getPrintLogCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getModifyPolicyList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getMailExportList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPersonalService as = new PersonalServiceImpl();
		List<MailExportModel> data = as.getMailExportList(map);
		result.put("data", data);
		int total = as.getMailExportListCount(map);

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getMsnTalkList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPersonalService as = new PersonalServiceImpl();
		List<MsnTalkModel> data = as.getMsnTalkList(map);
		result.put("data", data);
		int total = as.getMsnTalkListCount(map);

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getMsnFileList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPersonalService as = new PersonalServiceImpl();
		List<MsnFileModel> data = as.getMsnFileList(map);
		result.put("data", data);
		int total = as.getMsnFileListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getPrivacyFileList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPersonalService as = new PersonalServiceImpl();
		List<PrivacyLogModel> data = as.getPrivacyFileList(map);
		result.put("data", data);
		int total = as.getMsnFileListCount(map);
//		int total = 100;

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	
	
	public HashMap<String, Object> getUserPolicyList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IUserAgentService as = new UserAgentServiceImpl();
		List<UserPolicyModel> data = as.getUserPolicyList(map);
		result.put("data", data);
		int total = as.getUserPolicyCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getUserPolicyLogList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<UserPolicyLogModel> data = as.getUserPolicyLogList(map);
		result.put("data", data);
		int total = as.getUserPolicyLogCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getAuditClientLogList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<AuditClientModel> data = as.getAuditClientLogList(map);
		result.put("data", data);
		int total = as.getAuditClientLogListCount(map);

		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getAuditServerLogList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IPolicyService as = new PolicyServiceImpl();
		List<AuditServerModel> data = as.getAuditServerLogList(map);
		result.put("data", data);
		int total = as.getAuditServerLogListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getPolicyUsbBlockList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("usbId").toString())) {
			List<UsbDevInfoModel> empty = new ArrayList<UsbDevInfoModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<UsbDevInfoModel> data = us.getPolicyUsbBlockList(map);
			result.put("data", data);
			int total = us.getPolicyUsbBlockListCount(map);

			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicySerialList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("serialId").toString())) {
			List<PolicySerialModel> empty = new ArrayList<PolicySerialModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicySerialModel> data = us.getPolicySerialList(map);
			result.put("data", data);
			int total = us.getPolicySerialListCount(map);

			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicyNetworkList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("netId").toString())) {
			List<PolicyNetworkModel> empty = new ArrayList<PolicyNetworkModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicyNetworkModel> data = us.getPolicyNetworkList(map);
			result.put("data", data);
			int total = us.getPolicyNetworkListCount(map);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicyProcessList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("prsId").toString())) {
			List<PolicyProcessModel> empty = new ArrayList<PolicyProcessModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicyProcessModel> data = us.getPolicyProcessList(map);
			result.put("data", data);
			int total = us.getPolicyProcessListCount(map);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicyPatternList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("patId").toString())) {
			List<PolicyPatternModel> empty = new ArrayList<PolicyPatternModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicyPatternModel> data = us.getPolicyPatternList(map);
			result.put("data", data);
			int total = us.getPolicyPatternListCount(map);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicyWebSiteBlockList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("siteId").toString())) {
			List<PolicyWebSiteBlocklModel> empty = new ArrayList<PolicyWebSiteBlocklModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicyWebSiteBlocklModel> data = us.getPolicyWebSiteBlockList(map);
			result.put("data", data);
			int total = us.getPolicyWebSiteBlockListCount(map);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}

	public HashMap<String, Object> getPolicyMessengerList(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		if ("".equals(map.get("msgId").toString())) {
			List<PolicyMessengerModel> empty = new ArrayList<PolicyMessengerModel>();
			result.put("data", empty);
			result.put("recordsTotal", 0);
			result.put("recordsFiltered", 0);
		} else {
			UserService us = new UserServiceImpl();
			List<PolicyMessengerModel> data = us.getPolicyMessengerList(map);
			result.put("data", data);
			int total = us.getPolicyMessengerListCount(map);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
		}
		
		return result;
	}


}

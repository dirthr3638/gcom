package gcom.controller.action;

import java.util.HashMap;
import java.util.List;

import gcom.Model.*;
import gcom.service.Device.*;
import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;
import gcom.service.UserAgent.*;

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
	
	public HashMap<String, Object> getServerInspectionList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getAgentInspectionList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}

	public HashMap<String, Object> getMailList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getMsnTalkList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getMsnFileList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
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
}

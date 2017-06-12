package gcom.controller.action;

import java.util.HashMap;
import java.util.List;

import gcom.Model.LoginLogModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserAgentModel;
import gcom.service.Department.*;
import gcom.service.Device.*;
import gcom.service.Policy.*;
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
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	public HashMap<String, Object> getUsbBlockList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}
	
	public HashMap<String, Object> getPrintList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
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
	
	public HashMap<String, Object> getPolicyList(HashMap<String, Object> map){
		HashMap<String, Object> result = new HashMap<String, Object>();
		IDeviceInfoService as = new DeviceInfoServiceImpl();
		List<UsbDevInfoModel> data = as.getUnAuthUsbList(map);
		result.put("data", data);
		int total = as.getUnAuthUsbListCount(map);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;						
	}


}

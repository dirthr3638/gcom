package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.DAO.AuditDataDAO;
import gcom.DAO.PolicyDataDAO;
import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.ServerAuditModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;
import gcom.Model.statistic.AuditClientSimpleModel;
import gcom.common.util.CommonUtil;
import gcom.common.util.ConfigInfo;

public class PolicyServiceImpl implements IPolicyService {
	
	PolicyDataDAO poDao = new PolicyDataDAO();
	AuditDataDAO auDao = new AuditDataDAO();
	
	
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map){
		return poDao.getUserPolicyLogList(map);
	}
	public int getUserPolicyLogCount(HashMap<String, Object> map){
		return poDao.getUserPolicyLogListCount(map);
	}
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map){
		return auDao.getAuditClientLogList(map);
		
	}
	public int getAuditClientLogListCount(HashMap<String, Object> map){
		return auDao.getAuditClientLogListCount(map);
		
	}
	
	public List<AuditClientSimpleModel> getAuditClientSimpleLogList(Map<String, Object> map){
		return auDao.getAuditClientSimpleLogList(map); 
	}
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map){
		return auDao.getAuditServerLogList(map);
	}

	public int getAuditServerLogListCount(HashMap<String, Object> map){
		return auDao.getAuditServerLogListCount(map);
	}
	
	public HashMap<String, Object> insertServeriAudit(ServerAuditModel audit){
		return auDao.insertServeriAudit(audit);		
	}
	
	public HashMap<String, Object> getAuditMap(HashMap<String, Object> map){
		return auDao.getAuditMap(map);		
		
	}
	
	public List<PolicyRequestInfo> getRequestedPolicyList(HashMap<String, Object> map){
		List<PolicyRequestInfo> list = poDao.getRequestedPolicyList(map);
		
		for(PolicyRequestInfo model : list) {
			model.setWaterLimitDate(CommonUtil.getFomatLimitTime(model.getWaterMarkEndDate()));
			model.getOldPolicy().setWaterLimitDate(CommonUtil.getFomatLimitTime(model.getOldPolicy().getWaterMarkEndDate()));
		}
		
		return list;
		
	}
	public int getRequestedPolicyListCount(HashMap<String, Object> map){
		return poDao.getRequestedPolicyListCount(map);
		
	}
	
	public String getAuditServerWorkData(int key){
		return auDao.getAuditServerWorkData(key);
	}
	
	public List<PolicyMessengerModel> getPolicyMessengerList(HashMap<String, Object> map) {
		return poDao.getPolicyMessengerList(map);		
	}
	
	public int getPolicyMessengerListCount(HashMap<String, Object> map) {
		return poDao.getPolicyMessengerListCount(map);		
	}
	
	public List<PolicyProcessModel> getPolicyProcessList(HashMap<String, Object> map) {
		return poDao.getPolicyProcessList(map);	
	}
	
	public int getPolicyProcessListCount(HashMap<String, Object> map) {
		return poDao.getPolicyProcessListCount(map);		
	}
	
	public List<HashMap<String, Object>> getPolicyPatternSimpleList() {
		return poDao.getPolicyPatternSimpleList();	
	}
	
	public List<PolicyPatternModel> getPolicyPatternList(HashMap<String, Object> map) {
		return poDao.getPolicyPatternList(map);	
	}
	
	public int getPolicyPatternListCount(HashMap<String, Object> map) {
		return poDao.getPolicyPatternListCount(map);	
	}
	
	public List<PolicyNetworkModel> getPolicyNetworkList(HashMap<String, Object> map) {
		return poDao.getPolicyNetworkList(map);	
	}
	
	public int getPolicyNetworkListCount(HashMap<String, Object> map) {
		return poDao.getPolicyNetworkListCount(map);	
	}
	
	public List<PolicySerialModel> getPolicySerialList(HashMap<String, Object> map) {
		return poDao.getPolicySerialList(map);
	}
	
	public int getPolicySerialListCount(HashMap<String, Object> map) {
		return poDao.getPolicySerialListCount(map);	
	}
	
	public List<UserPolicyModel> getPolicyAssignMemberList(HashMap<String, Object> map) {
		List<UserPolicyModel> list = poDao.getPolicyAssignMemberList(map);
		
		for(UserPolicyModel model : list) {
			model.setWaterLimitDate(CommonUtil.getFomatLimitTime(model.getWaterMarkEndDate()));
		}
		
		return list;
	}
	
	public int getPolicyAssignMemberListCount(HashMap<String, Object> map) {
		return poDao.getPolicyAssignMemberListCount(map);	
	}
	
	public List<UsbDevInfoModel> getPolicyUsbBlockList(HashMap<String, Object> map) {
		return poDao.getPolicyUsbBlockList(map);	
	}
	
	public int getPolicyUsbBlockListCount(HashMap<String, Object> map) {
		return poDao.getPolicyUsbBlockListCount(map);	
	}
	
	public List<PolicyWebSiteBlocklModel> getPolicyWebSiteBlockList(HashMap<String, Object> map){
		return poDao.getPolicyWebSiteBlockList(map);	
	}
	
	public int getPolicyWebSiteBlockListCount(HashMap<String, Object> map){
		return poDao.getPolicyWebSiteBlockListCount(map);	
	}

	public PolicyMessengerModel getMsgInfo(int code) {
		return poDao.getMsgInfo(code);	
	}
	
	public HashMap<String, Object> insertPolicyMsgSave(HashMap<String, Object> map) {
		return poDao.insertPolicyMsgSave(map);
	}
	
	public HashMap<String, Object> updatePolicyMsgUpdate(HashMap<String, Object> map){
		return poDao.updatePolicyMsgUpdate(map);
	}
	
	public PolicyProcessModel getProcessInfo(int code) {
		return poDao.getProcessInfo(code);	
	}
	
	public HashMap<String, Object> insertPolicyProcessSave(HashMap<String, Object> map) {
		return poDao.insertPolicyProcessSave(map);
	}
	
	public HashMap<String, Object> updatePolicyProcessUpdate(HashMap<String, Object> map) {
		return poDao.updatePolicyProcessUpdate(map);
	}
	
	public PolicyPatternModel getPatternInfo(int code) {
		return poDao.getPatternInfo(code);	
	}
	
	public HashMap<String, Object> insertPolicyPatternSave(HashMap<String, Object> map) {
		return poDao.insertPolicyPatternSave(map);
	}
	
	public HashMap<String, Object> updatePolicyPatternUpdate(HashMap<String, Object> map){
		return poDao.updatePolicyPatternUpdate(map);
	}
	
	public PolicyNetworkModel getNetworkInfo(int code) {
		return poDao.getNetworkInfo(code);	
	}
	
	public HashMap<String, Object> insertPolicyNetworkSave(HashMap<String, Object> map) {
		return poDao.insertPolicyNetworkSave(map);
	}
	
	public HashMap<String, Object> updatePolicyNetworkUpdate(HashMap<String, Object> map) {
		return poDao.updatePolicyNetworkUpdate(map);
	}
	
	public PolicySerialModel getSerialInfo(int code) {
		return poDao.getSerialInfo(code);
	}
	
	public HashMap<String, Object> insertPolicySerialSave(HashMap<String, Object> map) {
		return poDao.insertPolicySerialSave(map);
	}
	
	public HashMap<String, Object> updatePolicySerialUpdate(HashMap<String, Object> map) {
		return poDao.updatePolicySerialUpdate(map);
	}
	
	public PolicyWebSiteBlocklModel getWebsiteInfo(int code) {
		return poDao.getWebsiteInfo(code);
	}
	
	public HashMap<String, Object> insertPolicyWebsiteSave(HashMap<String, Object> map) {
		return poDao.insertPolicyWebsiteSave(map);
	}
	
	public HashMap<String, Object> updatePolicyWebsiteUpdate(HashMap<String, Object> map) {
		return poDao.updatePolicyWebsiteUpdate(map);
	}
	
	public UsbDevInfoModel getUsbInfo(int code) {
		return poDao.getUsbInfo(code);
	}
	
	public HashMap<String, Object> insertPolicyUsbSave(HashMap<String, Object> map) {
		return poDao.insertPolicyUsbSave(map);
	}
	
	public HashMap<String, Object> updatePolicyUsbUpdate(HashMap<String, Object> map) {
		return poDao.updatePolicyUsbUpdate(map);
	}
	
	public HashMap<String, Object> insertPolicyDeviceSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// Block Device 정보 확인 및 Device 장치 추가
		HashMap<String, Object> data = poDao.insertBlockDeviceSaveWithGetAgentData(map);
		String returnCode = data.get("returnCode").toString();
		
		if(returnCode.equals(ConfigInfo.RETURN_CODE_SUCCESS)) {
			int usb_no = Integer.parseInt(data.get("usb_no").toString());
			
			if(usb_no != 0) {
				// 해당 장치 USER 정책 허용
				result = poDao.setUserPolicyDeviceData(data);
			} else {
				result.put("returnCode", ConfigInfo.EXIST_NOT_PARAM);
			}
		}
		
		return result;
	}
	
	public HashMap<String, Object> updatePermitRequestPolicy(HashMap<String, Object> map) {
		return poDao.updatePermitRequestPolicy(map);
	}
	
	public HashMap<String, Object> updateRejectRequestPolicy(HashMap<String, Object> map) {
		return poDao.updateRejectRequestPolicy(map);
	}
	
	public HashMap<String, Object> daletePolicyMsgData(HashMap<String, Object> map) {
		return poDao.daletePolicyMsgData(map);
	}
	
	public HashMap<String, Object> daletePolicyProcessData(HashMap<String, Object> map) {
		return poDao.daletePolicyProcessData(map);
	}
	
	public HashMap<String, Object> daletePolicyPatternData(HashMap<String, Object> map) {
		return poDao.daletePolicyPatternData(map);
	}
	
	public HashMap<String, Object> daletePolicyNetworkData(HashMap<String, Object> map) {
		return poDao.daletePolicyNetworkData(map);
	}
	
	public HashMap<String, Object> daletePolicySerialData(HashMap<String, Object> map) {
		return poDao.daletePolicySerialData(map);
	}
	
	public HashMap<String, Object> daletePolicyWebsiteData(HashMap<String, Object> map) {
		return poDao.daletePolicyWebsiteData(map);
	}
	
	public HashMap<String, Object> daletePolicyUsbData(HashMap<String, Object> map) {
		return poDao.daletePolicyUsbData(map);
	}
}

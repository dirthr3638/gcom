package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserPolicyLogModel;

import gcom.Model.UserPolicyModel;
import gcom.Model.statistic.AuditClientSimpleModel;

public interface IPolicyService {
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map);	
	public int getUserPolicyLogCount(HashMap<String, Object> map);
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map);	
	public int getAuditClientLogListCount(HashMap<String, Object> map);
	public List<AuditClientSimpleModel> getAuditClientSimpleLogList(Map<String, Object> map);
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map);	
	public int getAuditServerLogListCount(HashMap<String, Object> map);
	
	public List<PolicyRequestInfo> getRequestedPolicyList(HashMap<String, Object> map);	
	public int getRequestedPolicyListCount(HashMap<String, Object> map);
	
	
	public String getAuditServerWorkData(int key);
	public List<PolicyMessengerModel> getPolicyMessengerList(HashMap<String, Object> map);
	public int getPolicyMessengerListCount(HashMap<String, Object> map);
	public List<PolicyProcessModel> getPolicyProcessList(HashMap<String, Object> map);
	public int getPolicyProcessListCount(HashMap<String, Object> map);
	public List<PolicyPatternModel> getPolicyPatternList(HashMap<String, Object> map);
	public int getPolicyPatternListCount(HashMap<String, Object> map);
	public List<PolicyNetworkModel> getPolicyNetworkList(HashMap<String, Object> map);
	public int getPolicyNetworkListCount(HashMap<String, Object> map);
	public List<PolicySerialModel> getPolicySerialList(HashMap<String, Object> map);
	public int getPolicySerialListCount(HashMap<String, Object> map);
	public List<UserPolicyModel> getPolicyAssignMemberList(HashMap<String, Object> map);
	public int getPolicyAssignMemberListCount(HashMap<String, Object> map);
	public List<UsbDevInfoModel> getPolicyUsbBlockList(HashMap<String, Object> map);
	public int getPolicyUsbBlockListCount(HashMap<String, Object> map);
	public List<PolicyWebSiteBlocklModel> getPolicyWebSiteBlockList(HashMap<String, Object> map);
	public int getPolicyWebSiteBlockListCount(HashMap<String, Object> map);
}

package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.UserPolicyLogModel;

public interface IPolicyService {
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map);	
	public int getUserPolicyLogCount(HashMap<String, Object> map);
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map);	
	public int getAuditClientLogListCount(HashMap<String, Object> map);
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map);	
	public int getAuditServerLogListCount(HashMap<String, Object> map);
	
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
}

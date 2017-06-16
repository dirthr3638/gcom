package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;

import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.LoginLogModel;
import gcom.Model.UsbDevInfoModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.UserPolicyModel;

public interface IPolicyService {
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map);	
	public int getUserPolicyLogCount(HashMap<String, Object> map);
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map);	
	public int getAuditClientLogListCount(HashMap<String, Object> map);
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map);	
	public int getAuditServerLogListCount(HashMap<String, Object> map);
	
	public String getAuditServerWorkData(int key);
}

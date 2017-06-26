package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.PolicyDataDAO;
import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.UserPolicyLogModel;

public class PolicyServiceImpl implements IPolicyService {
	
	PolicyDataDAO poDao = new PolicyDataDAO();
	
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map){
		return poDao.getUserPolicyLogList(map);
	}
	public int getUserPolicyLogCount(HashMap<String, Object> map){
		return poDao.getUserPolicyLogListCount(map);
	}
	
	public List<AuditClientModel> getAuditClientLogList(HashMap<String, Object> map){
		return poDao.getAuditClientLogList(map);
		
	}
	public int getAuditClientLogListCount(HashMap<String, Object> map){
		return poDao.getAuditClientLogListCount(map);
		
	}
	
	public List<AuditServerModel> getAuditServerLogList(HashMap<String, Object> map){
		return poDao.getAuditServerLogList(map);
		
	}
	public int getAuditServerLogListCount(HashMap<String, Object> map){
		return poDao.getAuditServerLogListCount(map);
		
	}
	
	public List<PolicyRequestInfo> getRequestedPolicyList(HashMap<String, Object> map){
		return poDao.getRequestedPolicyList(map);
		
	}
	public int getRequestedPolicyListCount(HashMap<String, Object> map){
		return poDao.getRequestedPolicyListCount(map);
		
	}
	
	public String getAuditServerWorkData(int key){
		return poDao.getAuditServerWorkData(key);
	}


}

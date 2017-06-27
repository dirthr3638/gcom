package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.DAO.PolicyDataDAO;
import gcom.Model.AuditClientModel;
import gcom.Model.AuditServerModel;
import gcom.Model.PolicyRequestInfo;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.UserPolicyLogModel;
import gcom.Model.statistic.AuditClientSimpleModel;

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
	
	public List<AuditClientSimpleModel> getAuditClientSimpleLogList(Map<String, Object> map){
		return poDao.getAuditClientSimpleLogList(map); 
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

}

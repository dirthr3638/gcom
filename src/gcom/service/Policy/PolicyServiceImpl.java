package gcom.service.Policy;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.PolicyDataDAO;
import gcom.Model.UserPolicyLogModel;

public class PolicyServiceImpl implements IPolicyService {
	
	PolicyDataDAO poDao = new PolicyDataDAO();
	
	public List<UserPolicyLogModel> getUserPolicyLogList(HashMap<String, Object> map){
		return poDao.getUserPolicyLogList(map);
	}
	public int getUserPolicyLogCount(HashMap<String, Object> map){
		return 100;
	}

}

package gcom.service.UserAgent;

import java.util.HashMap;
import java.util.List;

import gcom.Model.LoginLogModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyModel;

public interface IUserAgentService {

	public List<UserAgentModel> getUserAgentList(HashMap<String, Object> map);
	public int getUserAgentListCount(HashMap<String, Object> map);	
	public List<LoginLogModel> getLoginlogList(HashMap<String, Object> map);	
	public int getLoginlogListCount(HashMap<String, Object> map);

	public List<UserPolicyModel> getUserPolicyList(HashMap<String, Object> map);	
	public int getUserPolicyCount(HashMap<String, Object> map);

}

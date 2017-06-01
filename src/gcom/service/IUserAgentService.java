package gcom.service;

import java.util.HashMap;
import java.util.List;

import gcom.Model.UserAgentModel;

public interface IUserAgentService {

	public List<UserAgentModel> getUserAgentList(HashMap<String, Object> map);
	public int getUserAgentListCount(HashMap<String, Object> map);	
}

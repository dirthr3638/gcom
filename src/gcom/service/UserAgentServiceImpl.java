package gcom.service;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.UserAgentDAO;
import gcom.Model.UserAgentModel;

public class UserAgentServiceImpl implements IUserAgentService {

	UserAgentDAO dao = new UserAgentDAO();
	
	public List<UserAgentModel> getUserAgentList(HashMap<String, Object> map){
		
		return dao.getUserAgentList(map);
	}
	
	public int getUserAgentListCount(HashMap<String, Object> map){
		
		return dao.getUserAgentListCount(map);		
	}


	
}

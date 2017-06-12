package gcom.service.UserAgent;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.LoginLogDAO;
import gcom.DAO.UserAgentDAO;
import gcom.Model.LoginLogModel;
import gcom.Model.UserAgentModel;

public class UserAgentServiceImpl implements IUserAgentService {

	UserAgentDAO uaDao = new UserAgentDAO();
	LoginLogDAO loginDao = new LoginLogDAO();
	
	//사용자에이전트 정보
	public List<UserAgentModel> getUserAgentList(HashMap<String, Object> map){		
		return uaDao.getUserAgentList(map);
	}	
	public int getUserAgentListCount(HashMap<String, Object> map){		
		return uaDao.getUserAgentListCount(map);		
	}
	
	//로그인로그
	public List<LoginLogModel> getLoginlogList(HashMap<String, Object> map){		
		return loginDao.getLoginlogList(map);		
	}
	public int getLoginlogListCount(HashMap<String, Object> map){
		
		return loginDao.getLoginlogListCount(map);		
	}


	
}

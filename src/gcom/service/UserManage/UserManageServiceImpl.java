package gcom.service.UserManage;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.LoginLogDAO;
import gcom.DAO.UserAgentDAO;
import gcom.DAO.UserManageDAO;
import gcom.Model.LoginLogModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserInfoModel;
import gcom.Model.UserPolicyModel;

public class UserManageServiceImpl implements IUserManageService {

	UserManageDAO uaDao = new UserManageDAO();
	
	public HashMap<String, Object> insertUserInfo(HashMap<String, Object> map){		
		return uaDao.insertUserInfo(map);
	}
	public HashMap<String, Object> removeUserInfo(HashMap<String, Object> map){		
		return uaDao.removeUserInfo(map);
	}
	public HashMap<String, Object> updateUserInfo(HashMap<String, Object> map){		
		return uaDao.updateUserInfo(map);
	}


}

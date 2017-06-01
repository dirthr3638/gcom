package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.user.dao.UserDAO;
import gcom.user.model.UserInfoModel;
import gcom.user.model.UserPolicyListModel;

public class UserServiceImpl implements UserServiceInterface {

	UserDAO userDAO = new UserDAO();
	
	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map) {
		return userDAO.getUserPolicySetInfo(map);
	}
	
	public UserInfoModel getUserInfo(HashMap<String, Object> map) {
		return userDAO.getUserInfo(map);
	}
	
}

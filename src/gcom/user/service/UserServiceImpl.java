package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.user.dao.UserDAO;
import gcom.user.model.UserContactModel;
import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;
import gcom.user.model.UserPolicyListModel;

public class UserServiceImpl implements UserServiceInterface {

	UserDAO userDAO = new UserDAO();
	
	public List<HashMap<String, Object>> getUserSystemPolicyList(String code) {
		return  userDAO.getUserSystemPolicyList(code);
	}
	
	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map) {
		return userDAO.getUserPolicySetInfo(map);
	}
	
	public UserInfoModel getUserInfo(HashMap<String, Object> map) {
		return userDAO.getUserInfo(map);
	}
	
	public int getUserNoticeListCount(HashMap<String, Object> map) {
		return userDAO.getUserNoticeListCount(map);
	}
	
	public List<UserNoticeModel> getUserNoticeList(HashMap<String, Object> map) {
		return userDAO.getUserNoticeList(map);
	}
	
	public UserNoticeModel getUserNoticeDetail(HashMap<String, Object> map) {
		return userDAO.getUserNoticeDetail(map);
	}
	
	public void updateNoticeViewCount(HashMap<String, Object> map) throws Exception {
		userDAO.updateNoticeViewCount(map);
	}
	
	public HashMap<String, Object> getUserContactInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int total = userDAO.getUserContactInfoCount(map);
		List<UserContactModel> list = userDAO.getUserContactlist(map);
		result.put("data", list);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	
}

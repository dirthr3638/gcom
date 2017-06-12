package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;
import gcom.user.model.UserPolicyListModel;

public interface UserServiceInterface {

	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map);

	public UserInfoModel getUserInfo(HashMap<String, Object> map);
	
	public int getUserNoticeListCount(HashMap<String, Object> map);

	public List<UserNoticeModel> getUserNoticeList(HashMap<String, Object> map);
	
}

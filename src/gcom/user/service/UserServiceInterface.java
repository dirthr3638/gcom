package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.user.model.MemberPolicyModel;
import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;

public interface UserServiceInterface {

	//public List<HashMap<String, Object>> getUserSystemPolicyList(String code);
	
	public MemberPolicyModel getMemberPolicyInfo(HashMap<String, Object> map);

	public UserInfoModel getUserInfo(HashMap<String, Object> map);
	
	public int getUserNoticeListCount(HashMap<String, Object> map);

	public List<UserNoticeModel> getUserNoticeList(HashMap<String, Object> map);

	public UserNoticeModel getUserNoticeDetail(HashMap<String, Object> map);

	public HashMap<String, Object> getUserContactInfo(HashMap<String, Object> map);

	public void updateNoticeViewCount(HashMap<String, Object> map) throws Exception;

	public List<HashMap<String, Object>> getMemberPolicyDetail(HashMap<String, Object> map);

	public HashMap<String, Object> insertContactSave(HashMap<String, Object> map) throws Exception;

}

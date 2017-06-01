package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.user.model.UserPolicyListModel;

public interface UserServiceInterface {

	public List<UserPolicyListModel> getUserPolicySetInfo(HashMap<String, Object> map);
}

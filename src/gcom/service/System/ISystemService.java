package gcom.service.System;

import java.util.HashMap;
import java.util.List;

import gcom.Model.LoginLogModel;
import gcom.Model.SystemInfoModel;
import gcom.Model.UserAgentModel;
import gcom.Model.UserPolicyModel;

public interface ISystemService {
	public List<SystemInfoModel> getSystemInfoList(HashMap<String, Object> map);
	public int getSystemInfoListCount(HashMap<String, Object> map);

}

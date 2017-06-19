package gcom.service.management;

import java.util.HashMap;
import java.util.List;

import gcom.Model.SubAdminModel;

public interface IManagementService {
	public List<SubAdminModel> getAdminList(HashMap<String, Object> map);
	public int getAdminListCount(HashMap<String, Object> map);
}

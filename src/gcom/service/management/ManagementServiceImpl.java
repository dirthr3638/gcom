package gcom.service.management;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.ManagementDataDAO;
import gcom.Model.SubAdminModel;

public class ManagementServiceImpl implements IManagementService {
	
	ManagementDataDAO poDao = new ManagementDataDAO();
	
	public List<SubAdminModel> getAdminList(HashMap<String, Object> map){
		return poDao.getAdminList(map);
		
	}
	public int getAdminListCount(HashMap<String, Object> map){
		return poDao.getAdminListCount(map);		
	}
	
	public SubAdminModel getAdminUserInfo(HashMap<String, Object> map) {
		return poDao.getAdminUserInfo(map);	
	}

}

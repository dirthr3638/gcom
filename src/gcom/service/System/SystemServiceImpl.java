package gcom.service.System;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.SystemInfoDAO;
import gcom.Model.SystemInfoModel;

public class SystemServiceImpl implements ISystemService {
	
	SystemInfoDAO siDao = new SystemInfoDAO();
	
	//사용자에이전트 정보
	public List<SystemInfoModel> getSystemInfoList(HashMap<String, Object> map){		
		return siDao.getSystemInfoList(map);
	}
	
	public int getSystemInfoListCount(HashMap<String, Object> map){		
		return siDao.getSystemInfoListCount(map);		
	}
	
	public HashMap<String, Object> updateSystemInfo(HashMap<String, Object> map){
		return siDao.updateSystemInfo(map);				
	}
	
	public int serverLogoutTimeInfo() {
		return siDao.serverLogoutTimeInfo();				
	}
}

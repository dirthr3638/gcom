package gcom.service.common;

import gcom.DAO.CommonDAO;

public class CommonServiceImpl implements ICommonService {
	
	CommonDAO comDao = new CommonDAO();
	
	public void setPolicyUpdateToInsertLog(int key) {
		comDao.setPolicyUpdateToInsertLog(key);
	}
}

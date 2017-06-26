package gcom.controller.action;

import java.util.HashMap;

import gcom.DAO.LoginDAO;

public class commonAction {
	
	LoginDAO logDao = new LoginDAO();
	
	public HashMap<String, Object> getLoginCheckResult(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String loginType = map.get("loginType").toString();
		String goUrl = "";
		
		if ("U".equals(loginType)) {
			goUrl = "/main";
			result = logDao.selectUserLoginCheck(map);
		} else if ("C".equals(loginType)) {
			goUrl = "/dashboard";
			result = logDao.selectConsoleLoginCheck(map);
		}
		
		result.put("goUrl", goUrl);
		
		return result;
	}

	
}

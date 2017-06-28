package gcom.controller.action.admin;

import java.util.HashMap;

import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;

public class insertAdminAction {

	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map) {
		IPersonalService as = new PersonalServiceImpl();
		return as.insertNoticeWriteSave(map);
	}

}

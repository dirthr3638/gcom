package gcom.service.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.DAO.ContactDataDAO;
import gcom.Model.statistic.ContactSimpleModel;

public class RequestServiceImpl implements IRequestService {
	
	ContactDataDAO contDao = new ContactDataDAO();
	
	public List<ContactSimpleModel> getSimpleContactList(Map<String, Object> map){
		return contDao.getSimpleContactList(map);
	}
	
}

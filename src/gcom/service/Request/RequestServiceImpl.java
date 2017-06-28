package gcom.service.Request;

import java.util.List;
import java.util.Map;

import gcom.DAO.ContactDataDAO;
import gcom.DAO.RequestDataDAO;
import gcom.Model.statistic.ContactSimpleModel;
import gcom.Model.statistic.RequestSimpleModel;

public class RequestServiceImpl implements IRequestService {
	
	ContactDataDAO contDao = new ContactDataDAO();
	RequestDataDAO reqDao = new RequestDataDAO();
	
	public List<ContactSimpleModel> getSimpleContactList(Map<String, Object> map){
		return contDao.getSimpleContactList(map);
	}
	public List<RequestSimpleModel> getSimpleRequestList(Map<String, Object> map){
		return reqDao.getSimpleRequestList(map);
	}
}
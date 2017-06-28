package gcom.service.Request;

import java.util.List;
import java.util.Map;

import gcom.Model.statistic.ContactSimpleModel;
import gcom.Model.statistic.RequestSimpleModel;


public interface IRequestService {
	
	public List<ContactSimpleModel> getSimpleContactList(Map<String, Object> map);
	public List<RequestSimpleModel> getSimpleRequestList(Map<String, Object> map);
	
}

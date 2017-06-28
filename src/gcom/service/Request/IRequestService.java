package gcom.service.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.Model.statistic.ContactSimpleModel;


public interface IRequestService {
	
	public List<ContactSimpleModel> getSimpleContactList(Map<String, Object> map);
	
}

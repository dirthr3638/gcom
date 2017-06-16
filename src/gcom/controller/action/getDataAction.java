package gcom.controller.action;

import gcom.service.Policy.IPolicyService;
import gcom.service.Policy.PolicyServiceImpl;

public class getDataAction {
	public String getServerAuditWorkData(int key){
		IPolicyService as = new PolicyServiceImpl();
		return as.getAuditServerWorkData(key);						
	}
}

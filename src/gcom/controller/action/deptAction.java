package gcom.controller.action;

import java.util.List;

import gcom.Model.*;
import gcom.service.DeptServiceImpl;
import gcom.service.IDeptService;

public class deptAction {

	public List<DeptModel> getDeptList(int adminNumber){
		IDeptService dsv = new DeptServiceImpl();

		return dsv.getDeptList(adminNumber);
	}

	public List<DeptTreeModel> getDeptListForJSTree(int adminNumber){
		IDeptService dsv = new DeptServiceImpl();

		return dsv.getDeptListForJSTree(adminNumber);
	}
	
}
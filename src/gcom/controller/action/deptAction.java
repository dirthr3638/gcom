package gcom.controller.action;

import java.util.List;

import gcom.Model.DeptModel;
import gcom.Model.DeptTreeModel;
import gcom.service.Department.DeptServiceImpl;
import gcom.service.Department.IDeptService;

public class deptAction {

	public List<DeptModel> getDeptList(int adminNumber){
		IDeptService dsv = new DeptServiceImpl();

		return dsv.getDeptList(adminNumber);
	}

	public List<DeptTreeModel> getDeptListForJSTree(int adminNumber){
		IDeptService dsv = new DeptServiceImpl();

		return dsv.getDeptListForJSTree(adminNumber);
	}

	public List<DeptTreeModel> getSelectDeptListForJSTree(int adminNumber){
		IDeptService dsv = new DeptServiceImpl();

		return dsv.getSelectDeptListForJSTree(adminNumber);
	}
}

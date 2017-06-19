package gcom.service.Department;

import java.util.List;

import gcom.DAO.DeptDAO;
import gcom.Model.DeptModel;
import gcom.Model.DeptTreeModel;

public class DeptServiceImpl implements IDeptService {
	
	public List<DeptModel> getDeptList(int adminNumber){
		
		DeptDAO dao = new DeptDAO();
		
		List<DeptModel> result = dao.getDeptList(adminNumber);
		
		return result;
		
	}

	public List<DeptTreeModel> getDeptListForJSTree(int adminNumber){
		
		DeptDAO dao = new DeptDAO();
		
		List<DeptTreeModel> result = dao.getDeptListForJSTree(adminNumber);
		
		return result;
		
	}	

	public List<DeptTreeModel> getSelectDeptListForJSTree(int adminNumber){
		
		DeptDAO dao = new DeptDAO();
		
		List<DeptTreeModel> result = dao.getSelectDeptListForJSTree(adminNumber);
		
		return result;
		
	}	

}

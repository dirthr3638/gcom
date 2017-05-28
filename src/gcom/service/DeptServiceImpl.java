package gcom.service;

import java.util.List;

import gcom.DAO.DeptDAO;
import gcom.Model.DeptModel;

public class DeptServiceImpl implements IDeptService {
	
	public List<DeptModel> getDept(int adminNumber){
		
		DeptDAO dao = new DeptDAO();
		
		List<DeptModel> result = dao.getDeptList(adminNumber);
		
		return result;
		
	}

	
}

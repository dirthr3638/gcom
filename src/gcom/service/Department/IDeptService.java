package gcom.service.Department;

import java.util.List;

import gcom.Model.DeptModel;
import gcom.Model.DeptTreeModel;

public interface IDeptService {
	List<DeptModel> getDeptList(int adminNumber);
	List<DeptTreeModel> getDeptListForJSTree(int adminNumber);

}

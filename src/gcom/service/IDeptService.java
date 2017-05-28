package gcom.service;

import java.util.List;

import gcom.Model.DeptModel;

public interface IDeptService {
	List<DeptModel> getDept(int adminNumber);
}

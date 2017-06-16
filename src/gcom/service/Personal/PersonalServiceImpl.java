package gcom.service.Personal;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.PersonalDataDAO;
import gcom.Model.MailExportModel;
import gcom.Model.MsnFileModel;
import gcom.Model.MsnTalkModel;
import gcom.Model.PrivacyLogModel;

public class PersonalServiceImpl implements IPersonalService {
	
	PersonalDataDAO poDao = new PersonalDataDAO();
	
	public List<MailExportModel> getMailExportList(HashMap<String, Object> map){
		return poDao.getMailExportList(map);
	}
	public int getMailExportListCount(HashMap<String, Object> map){
		return poDao.getMailExportListCount(map);
	}

	public List<MsnTalkModel> getMsnTalkList(HashMap<String, Object> map){
		return poDao.getMsnTalkList(map);
	}
	public int getMsnTalkListCount(HashMap<String, Object> map){
		return poDao.getMsnTalkListCount(map);
	}

	public List<MsnFileModel> getMsnFileList(HashMap<String, Object> map){
		return poDao.getMsnFileList(map);		
	}
	public int getMsnFileListCount(HashMap<String, Object> map){
		return poDao.getMsnFileListCount(map);		
	}
	
	public List<PrivacyLogModel> getPrivacyFileList(HashMap<String, Object> map){		
		return poDao.getPrivacyFileList(map);		
	}
	public int getPrivacyFileListCount(HashMap<String, Object> map){
		return poDao.getPrivacyFileListCount(map);		
	}

}

package gcom.service.Personal;

import java.util.HashMap;
import java.util.List;

import gcom.DAO.PersonalDataDAO;
import gcom.Model.FileInfoModel;
import gcom.Model.MailExportModel;
import gcom.Model.MsnFileModel;
import gcom.Model.MsnTalkModel;
import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PrivacyLogModel;
import gcom.Model.UserInfoModel;
import gcom.common.services.ConfigInfo;
import gcom.user.model.UserContactModel;

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
	
	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map) {
		return poDao.insertNoticeWriteSave(map);	
	}
	
	public FileInfoModel getAttFileInfo(HashMap<String, Object> map) {
		return poDao.getAttFileInfo(map);	
	}
	
	public HashMap<String, Object> applyPolicyDataSave(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<HashMap<String, Object>> apply_list = (List<HashMap<String, Object>>) map.get("apply_list");
		String returnCode = ConfigInfo.RETURN_CODE_SUCCESS;
		
		for(HashMap<String, Object> data : apply_list) {
			if(Integer.parseInt(data.get("policy_no").toString()) == 0) {
				map.put("agnet_no", data.get("agent_no"));
				map.put("user_no", data.get("user_no"));
				
				returnCode =  poDao.insertPolicyDataSave(map);
			} else {
				map.put("agnet_no", data.get("agent_no"));
				map.put("user_no", data.get("user_no"));
				map.put("policy_no", data.get("policy_no"));
				
				returnCode = poDao.updatePolicyDataSave(map);
			}
		}
		
		result.put("returnCode", returnCode);
		
		return result;
	}
	
	public HashMap<String, Object> updateNoticeModifyUpdate(HashMap<String, Object> map) {
		return poDao.updateNoticeModifyUpdate(map);	
	}
	
	public List<UserContactModel> getAdminContactList(HashMap<String, Object> map){
		return poDao.getAdminContactList(map);
	}
	
	public int getAdminContactListCount(HashMap<String, Object> map){
		return poDao.getAdminContactListCount(map);
	}
	
	public HashMap<String, Object> insertContactCommentSave(HashMap<String, Object> map){
		return poDao.insertContactCommentSave(map);
	}
	
	public HashMap<String, Object> getCommentInfo(HashMap<String, Object> map) {
		return poDao.getCommentInfo(map);
	}
	
	public HashMap<String, Object> updateContactCommentUpdate(HashMap<String, Object> map) {
		return poDao.updateContactCommentUpdate(map);	
	}
	
}

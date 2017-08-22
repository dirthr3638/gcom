package gcom.service.Personal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.DAO.PersonalDataDAO;
import gcom.Model.FileInfoModel;
import gcom.Model.MailExportContentModel;
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
import gcom.common.util.ConfigInfo;
import gcom.user.model.UserContactModel;

public class PersonalServiceImpl implements IPersonalService {
	
	PersonalDataDAO poDao = new PersonalDataDAO();
	
	public List<MailExportModel> getMailExportList(HashMap<String, Object> map){
		return poDao.getMailExportList(map);
	}
	public int getMailExportListCount(HashMap<String, Object> map){
		return poDao.getMailExportListCount(map);
	}

	public MailExportContentModel getMailExportContent(HashMap<String, Object> map){
		return poDao.getMailExportContent(map);
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
			HashMap<String, Object> value = new HashMap<String, Object>();
			if(Integer.parseInt(data.get("policy_no").toString()) == 0) {
				value.put("agent_no", data.get("agent_no"));
				value.put("user_no", data.get("user_no"));
				
				value.put("isUninstall"			, Integer.parseInt(map.get("isUninstall").toString()) == -1 ? 0 : map.get("isUninstall"));
				value.put("isFileEncryption"	, Integer.parseInt(map.get("isFileEncryption").toString()) == -1 ? 0 : map.get("isFileEncryption"));
				value.put("isCdEncryption"		, Integer.parseInt(map.get("isCdEncryption").toString()) == -1 ? 0 : map.get("isCdEncryption"));
				value.put("isPrint"				, Integer.parseInt(map.get("isPrint").toString()) == -1 ? 0 : map.get("isPrint"));
				value.put("isCdEnabled"			, Integer.parseInt(map.get("isCdEnabled").toString()) == -1 ? 0 : map.get("isCdEnabled"));
				value.put("isCdExport"			, Integer.parseInt(map.get("isCdExport").toString()) == -1 ? 0 : map.get("isCdExport"));
				value.put("isWlan"				, Integer.parseInt(map.get("isWlan").toString()) == -1 ? 0 : map.get("isWlan"));
				value.put("isNetShare"			, Integer.parseInt(map.get("isNetShare").toString()) == -1 ? 0 : map.get("isNetShare"));
				value.put("isWebExport"			, Integer.parseInt(map.get("isWebExport").toString()) == -1 ? 0 : map.get("isWebExport"));
				
				value.put("isSensitiveDirEnabled"	, Integer.parseInt(map.get("isSensitiveDirEnabled").toString()) == -1 ? 0 : map.get("isSensitiveDirEnabled"));
				value.put("isSensitiveFileAccess"	, Integer.parseInt(map.get("isSensitiveFileAccess").toString()) == -1 ? 0 : map.get("isSensitiveFileAccess"));
				value.put("isStorageExport"			, Integer.parseInt(map.get("isStorageExport").toString()) == -1 ? 0 : map.get("isStorageExport"));
				value.put("isStorageAdmin"			, Integer.parseInt(map.get("isStorageAdmin").toString()) == -1 ? 0 : map.get("isStorageAdmin"));
				value.put("isUsbControlEnabled"		, Integer.parseInt(map.get("isUsbControlEnabled").toString()) == -1 ? 0 : map.get("isUsbControlEnabled"));
				
				value.put("patternFileControl"	, Integer.parseInt(map.get("patternFileControl").toString()) == -1 ? 0 : map.get("patternFileControl"));
				value.put("printLogDesc"		, Integer.parseInt(map.get("printLogDesc").toString()) == -1 ? 0 : map.get("printLogDesc"));
				
				value.put("isUsbBlock"			, "".equals(map.get("isUsbBlock").toString())? "N" : map.get("isUsbBlock"));
				value.put("isComPortBlock"		, "".equals(map.get("isComPortBlock").toString())? "N" : map.get("isComPortBlock"));
				value.put("isNetPortBlock"		, "".equals(map.get("isNetPortBlock").toString())? "N" : map.get("isNetPortBlock"));
				value.put("isProcessList"		, map.get("isProcessList"));
				value.put("isFilePattern"		, map.get("isFilePattern"));
				value.put("isWebAddr"			, "".equals(map.get("isWebAddr").toString())? "N" : map.get("isWebAddr"));
				value.put("isMsgBlock"			, "".equals(map.get("isMsgBlock").toString())? "N" : map.get("isMsgBlock"));
				value.put("waterMark"			, map.get("waterMark"));
												
				returnCode =  poDao.insertPolicyDataSave(value);
			} else {
				map.put("agent_no", data.get("agent_no"));
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
	
	public HashMap<String, Object> getCurrentPolicyCheck(List<Map<String, Object>> apply_list) {
		HashMap<String, Object> policy = new HashMap<String, Object>();
		
		//여러명일 경우를 위한 초기 값 세팅
		policy.put("isUninstall", false);
		policy.put("isFileEncryption", false);
		policy.put("isCdEncryption", false);
		policy.put("isPrint", false);
		policy.put("isCdEnabled", false);
		policy.put("isCdExport", false);
		policy.put("isWlan", false);
		policy.put("isNetShare", false);
		policy.put("isWebExport", false);
		
		policy.put("isSensitiveDirEnabled", false);
		policy.put("isSensitiveFileAccess", false);
		policy.put("isStorageExport", false);
		policy.put("isStorageAdmin", false);
		policy.put("isUsbControlEnabled", false);
		
		policy.put("isStorageExport", false);
		policy.put("isStorageAdmin", false);
		
		policy.put("isUsbBlock", false);
		policy.put("isComPortBlock", false);
		policy.put("isNetPortBlock", false);
		policy.put("isProcessList", false);
		policy.put("isFilePattern", false);
		policy.put("isWebAddr", false);
		policy.put("isMsgBlock", false);
		
		policy.put("usbBlockCode", "");
		policy.put("comPortBlockCode", "");
		policy.put("netPortBlockCode", "");
		policy.put("processListCode", "");
		policy.put("filePatternCode", "");
		policy.put("webAddrCode", "");
		policy.put("msgBlockCode", "");
		
		policy.put("isWaterMark", false);
		policy.put("waterMarkEndDate", "");
		policy.put("waterMarkType", 0);
		policy.put("printLogDesc", 0);
		policy.put("patternFileControl", 0);
		
		if (apply_list.size() > 1) {
		
			for(Map<String, Object> temp : apply_list) {
				if (Boolean.parseBoolean(temp.get("isUninstall").toString())) {
					policy.put("isUninstall", true);
				} 
				
				if (Boolean.parseBoolean(temp.get("isFileEncryption").toString())) {
					policy.put("isFileEncryption", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isCdEncryption").toString())) {
					policy.put("isCdEncryption", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isPrint").toString())) {
					policy.put("isPrint", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isCdEnabled").toString())) {
					policy.put("isCdEnabled", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isCdExport").toString())) {
					policy.put("isCdExport", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isWlan").toString())) {
					policy.put("isWlan", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isNetShare").toString())) {
					policy.put("isNetShare", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isWebExport").toString())) {
					policy.put("isWebExport", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isSensitiveDirEnabled").toString())) {
					policy.put("isSensitiveDirEnabled", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isSensitiveFileAccess").toString())) {
					policy.put("isSensitiveFileAccess", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isStorageExport").toString())) {
					policy.put("isStorageExport", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isStorageAdmin").toString())) {
					policy.put("isStorageAdmin", true);
				}
				
				if (Boolean.parseBoolean(temp.get("isUsbControlEnabled").toString())) {
					policy.put("isUsbControlEnabled", true);
				}
				
				if (Integer.parseInt(temp.get("patternFileControl").toString()) == 1) {
					policy.put("patternFileControl", 1);
				}


			}
		} else {
			Map<String, Object> temp = apply_list.get(0);
			
			policy.put("isUninstall"		, temp.get("isUninstall"));
			policy.put("isFileEncryption"	, temp.get("isFileEncryption"));
			policy.put("isCdEncryption"		, temp.get("isCdEncryption"));
			policy.put("isPrint"			, temp.get("isPrint"));
			policy.put("isCdEnabled"		, temp.get("isCdEnabled"));
			policy.put("isCdExport"			, temp.get("isCdExport"));
			policy.put("isWlan"				, temp.get("isWlan"));
			policy.put("isNetShare"			, temp.get("isNetShare"));
			policy.put("isWebExport"		, temp.get("isWebExport"));
			
			policy.put("isSensitiveDirEnabled", temp.get("isSensitiveDirEnabled"));
			policy.put("isSensitiveFileAccess", temp.get("isSensitiveFileAccess"));
			policy.put("isStorageExport", temp.get("isStorageExport"));
			policy.put("isStorageAdmin", temp.get("isStorageAdmin"));
			policy.put("isUsbControlEnabled", temp.get("isUsbControlEnabled"));
			
			policy.put("isUsbBlock"			, temp.get("isUsbBlock"));
			policy.put("isComPortBlock"		, temp.get("isComPortBlock"));
			policy.put("isNetPortBlock"		, temp.get("isNetPortBlock"));
			policy.put("isProcessList"		, temp.get("isProcessList"));
			policy.put("isFilePattern"		, temp.get("isFilePattern"));
			policy.put("isWebAddr"			, temp.get("isWebAddr"));
			policy.put("isMsgBlock"			, temp.get("isMsgBlock"));
			
			policy.put("usbBlockCode"		, temp.get("usbBlockCode"));
			policy.put("comPortBlockCode"	, temp.get("comPortBlockCode"));
			policy.put("netPortBlockCode"	, temp.get("netPortBlockCode"));
			policy.put("processListCode"	, temp.get("processListCode"));
			policy.put("filePatternCode"	, temp.get("filePatternCode"));
			policy.put("webAddrCode"		, temp.get("webAddrCode"));
			policy.put("msgBlockCode"		, temp.get("msgBlockCode"));
			
			policy.put("isWaterMark"		, temp.get("isWaterMark"));
			policy.put("waterMarkEndDate"	, temp.get("waterMarkEndDate"));
			policy.put("waterMarkType"		, temp.get("waterMarkType"));
			policy.put("printLogDesc"		, temp.get("printLogDesc"));
			policy.put("patternFileControl"	, temp.get("patternFileControl"));
		}
		
		
		return policy;
	}
	
	public HashMap<String, Object> updateNoticeDelete(HashMap<String, Object> map) {
		return poDao.updateNoticeDelete(map);	
	}
	
}

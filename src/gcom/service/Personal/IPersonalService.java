package gcom.service.Personal;

import java.util.HashMap;
import java.util.List;

import gcom.Model.FileInfoModel;
import gcom.Model.MailExportModel;
import gcom.Model.MsnFileModel;
import gcom.Model.MsnTalkModel;
import gcom.Model.PrivacyLogModel;
import gcom.Model.UserInfoModel;

public interface IPersonalService {
	public List<MailExportModel> getMailExportList(HashMap<String, Object> map);
	public int getMailExportListCount(HashMap<String, Object> map);
	public List<MsnTalkModel> getMsnTalkList(HashMap<String, Object> map);
	public int getMsnTalkListCount(HashMap<String, Object> map);
	public List<MsnFileModel> getMsnFileList(HashMap<String, Object> map);
	public int getMsnFileListCount(HashMap<String, Object> map);
	public List<PrivacyLogModel> getPrivacyFileList(HashMap<String, Object> map);
	public int getPrivacyFileListCount(HashMap<String, Object> map);
	public FileInfoModel getAttFileInfo(HashMap<String, Object> map);
	public HashMap<String, Object> insertNoticeWriteSave(HashMap<String, Object> map);
	public HashMap<String, Object> applyPolicyDataSave(HashMap<String, Object> map);
}

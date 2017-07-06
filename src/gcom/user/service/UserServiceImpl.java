package gcom.user.service;

import java.util.HashMap;
import java.util.List;

import gcom.Model.PolicyMessengerModel;
import gcom.Model.PolicyNetworkModel;
import gcom.Model.PolicyPatternModel;
import gcom.Model.PolicyProcessModel;
import gcom.Model.PolicySerialModel;
import gcom.Model.PolicyWebSiteBlocklModel;
import gcom.Model.UsbDevInfoModel;
import gcom.user.dao.UserDAO;
import gcom.user.model.MemberPolicyModel;
import gcom.user.model.UserContactModel;
import gcom.user.model.UserInfoModel;
import gcom.user.model.UserNoticeModel;

public class UserServiceImpl implements UserService {

	UserDAO userDAO = new UserDAO();
	
	/*
	public List<HashMap<String, Object>> getUserSystemPolicyList(String code) {
		return  userDAO.getUserSystemPolicyList(code);
	}
	*/
	
	public MemberPolicyModel getMemberPolicyInfo(HashMap<String, Object> map) {
		return userDAO.getMemberPolicyInfo(map);
	}
	
	public List<HashMap<String, Object>> getMemberPolicyDetail(HashMap<String, Object> map) {
		return userDAO.getMemberPolicyDetail(map);
	}
	
	public UserInfoModel getUserInfo(HashMap<String, Object> map) {
		return userDAO.getUserInfo(map);
	}
	
	public int getUserNoticeListCount(HashMap<String, Object> map) {
		return userDAO.getUserNoticeListCount(map);
	}
	
	public List<UserNoticeModel> getUserNoticeList(HashMap<String, Object> map) {
		return userDAO.getUserNoticeList(map);
	}
	
	public UserNoticeModel getUserNoticeDetail(HashMap<String, Object> map) {
		return userDAO.getUserNoticeDetail(map);
	}
	
	public void updateNoticeViewCount(HashMap<String, Object> map) throws Exception {
		userDAO.updateNoticeViewCount(map);
	}
	
	public HashMap<String, Object> getUserContactInfo(HashMap<String, Object> map) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int total = userDAO.getUserContactInfoCount(map);
		List<UserContactModel> list = userDAO.getUserContactlist(map);
		result.put("data", list);
		result.put("recordsTotal", total);
		result.put("recordsFiltered", total);
		
		return result;
	}
	
	public HashMap<String, Object> insertContactSave(HashMap<String, Object> map) throws Exception {
		return userDAO.insertContactSave(map);
	}
	
	/* =========================================================================== */
	
	public List<UsbDevInfoModel> getPolicyUsbBlockList(HashMap<String, Object> map) {
		return userDAO.getPolicyUsbBlockList(map);
	}
	
	public int getPolicyUsbBlockListCount(HashMap<String, Object> map) {
		return userDAO.getPolicyUsbBlockListCount(map);
	}
	
	public List<PolicySerialModel> getPolicySerialList(HashMap<String, Object> map){
		return userDAO.getPolicySerialList(map);
	}

	public int getPolicySerialListCount(HashMap<String, Object> map){
		return userDAO.getPolicySerialListCount(map);
	}
	
	public List<PolicyNetworkModel> getPolicyNetworkList(HashMap<String, Object> map){
		return userDAO.getPolicyNetworkList(map);
	}

	public int getPolicyNetworkListCount(HashMap<String, Object> map){
		return userDAO.getPolicyNetworkListCount(map);
	}
	
	public List<PolicyProcessModel> getPolicyProcessList(HashMap<String, Object> map){
		return userDAO.getPolicyProcessList(map);
	}

	public int getPolicyProcessListCount(HashMap<String, Object> map){
		return userDAO.getPolicyProcessListCount(map);
	}
	
	public List<PolicyPatternModel> getPolicyPatternList(HashMap<String, Object> map){
		return userDAO.getPolicyPatternList(map);
	}

	public int getPolicyPatternListCount(HashMap<String, Object> map){
		return userDAO.getPolicyPatternListCount(map);
	}
	
	public List<PolicyWebSiteBlocklModel> getPolicyWebSiteBlockList(HashMap<String, Object> map){
		return userDAO.getPolicyWebSiteBlockList(map);
	}

	public int getPolicyWebSiteBlockListCount(HashMap<String, Object> map){
		return userDAO.getPolicyWebSiteBlockListCount(map);
	}
	
	public List<PolicyMessengerModel> getPolicyMessengerList(HashMap<String, Object> map) {
		return userDAO.getPolicyMessengerList(map);
	}

	public int getPolicyMessengerListCount(HashMap<String, Object> map) {
		return userDAO.getPolicyMessengerListCount(map);
	}
	
	public UserContactModel getUserContactDetail(HashMap<String, Object> map) {
		return userDAO.getUserContactDetail(map);
	}
	
}

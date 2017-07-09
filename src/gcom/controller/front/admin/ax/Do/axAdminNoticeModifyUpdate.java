package gcom.controller.front.admin.ax.Do;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gcom.Model.ServerAuditModel;
import gcom.Model.SubAdminModel;
import gcom.common.services.ConfigInfo;
import gcom.controller.action.admin.insertAdminAction;
import gcom.controller.action.admin.updateAdminAction;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.model.UserInfoModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserService;

/**
 * Servlet implementation class axCommonUI
 */
@WebServlet("/admin/user/notice/update")
public class axAdminNoticeModifyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
    	HttpSession session = httpReq.getSession(false);
    	
    	String user_id = (String)session.getAttribute("user_id");
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("user_id", user_id);
    	
    	IManagementService managementService = new ManagementServiceImpl();
    	SubAdminModel admin = managementService.getAdminUserInfo(param);
		
		
		param.put("upd_staf_no", admin.getAdminNo());
		param.put("bbs_id", request.getParameter("bbsId"));
		param.put("file_id", request.getParameter("fileId"));
		param.put("bbs_title", request.getParameter("title"));
		param.put("bbs_body", request.getParameter("body"));
		param.put("special_type", request.getParameter("special"));
		param.put("bbs_body_trim", request.getParameter("body_trim"));
		param.put("save_file_nm", request.getParameter("save_file_nm"));
		param.put("view_file_nm", request.getParameter("view_file_nm"));
		param.put("att_file_path", request.getParameter("file_path"));
		param.put("attfile_yn", request.getParameter("attfile_yn"));
		
		updateAdminAction action = new updateAdminAction();
		
		HashMap<String, Object> data =  new HashMap<String, Object>();
		try {
			data = action.updateNoticeModifyUpdate(param);
			ServerAuditModel model = new ServerAuditModel();
			model.setAdminId((String)session.getAttribute("user_id"));
			model.setActionId(1301);
			model.setWorkIp(httpReq.getRemoteAddr());
			model.setDescription("공지사항 수정");
			model.setParameter(param.toString());
	 		model.setStatus(data.get("returnCode").equals(ConfigInfo.RETURN_CODE_SUCCESS) ? "성공" : "실패");

			insertAdminAction aud = new insertAdminAction();
			aud.insertServeriAudit(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

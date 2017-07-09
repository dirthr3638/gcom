package gcom.controller.front.admin.ax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gcom.Model.SubAdminModel;
import gcom.common.util.JSONUtil;
import gcom.controller.action.admin.insertAdminAction;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.model.UserInfoModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserService;

/**
 * Servlet implementation class axCommonUI
 */
@WebServlet("/admin/user/apply/save")
public class axAdminApplyPolicySave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Object> param = JSONUtil.convertJsonToHashMap(request.getParameter("apply_policy").toString());
		
		insertAdminAction action = new insertAdminAction();
		
		HashMap<String, Object> data =  new HashMap<String, Object>();
		try {
			data = action.applyPolicyDataSave(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

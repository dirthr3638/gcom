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

import gcom.common.util.JSONUtil;
import gcom.service.Personal.IPersonalService;
import gcom.service.Personal.PersonalServiceImpl;


/**
 * Servlet implementation class axCommonUI
 */
@WebServlet("/admin/user/assign/applyAll")
public class axAdminAssignPolicyApplyAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Search param
		map.put("dept", request.getParameterValues("dept[]"));
		map.put("user_id", request.getParameter("user_id").toString());
		map.put("user_name", request.getParameter("user_name").toString());
		map.put("user_phone", request.getParameter("user_phone").toString());
		map.put("user_duty", request.getParameter("user_duty").toString());
		map.put("user_rank", request.getParameter("user_rank").toString());
		map.put("user_number", request.getParameter("user_number").toString());
		map.put("user_pc", request.getParameter("user_pc").toString());
		map.put("user_ip", request.getParameter("user_ip").toString());
		
		//List<Map<String, Object>> apply_list = JSONUtil.convertJsonToHashListMap(request.getParameter("apply_list").toString());
		boolean onlyFlag = true;
		
		IPersonalService as = new PersonalServiceImpl();
		HashMap<String, Object> data = as.getApplyPolicyAllUserInfo(map);	
		//HashMap<String, Object> current_policy = as.getCurrentPolicyCheck(apply_list);	
		
		request.setAttribute("only_flag", onlyFlag);
		request.setAttribute("current_policy", (HashMap<String, Object>)data.get("current_policy"));
		request.setAttribute("apply_list", (List<Map<String, Object>>)data.get("apply_list"));
		request.getRequestDispatcher("/WEB-INF/admin/user_manage/ax/pop_assign_policy_apply.jsp").forward(request, response);
	}
}

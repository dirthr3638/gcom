package gcom.controller.front.admin.ax;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.common.services.JSONUtil;


/**
 * Servlet implementation class axCommonUI
 */
@WebServlet("/admin/user/assign/apply")
public class axAdminAssignPolicyApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Map<String, Object>> apply_list = JSONUtil.convertJsonToHashListMap(request.getParameter("apply_list").toString());
		
		request.setAttribute("list", apply_list);
		request.getRequestDispatcher("/WEB-INF/admin/user_manage/ax/pop_assign_policy_apply.jsp").forward(request, response);
	}
}

package gcom.controller.front.admin.ax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class axCommonUI
 */
@WebServlet("/ax/admin/policy/device")
public class axAdminPolicyDevice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tabCode = request.getParameter("tabCode").toString();
		
		request.setAttribute("tabCode", tabCode);
		request.getRequestDispatcher("/WEB-INF/admin/policy_manage/ax/admin_policy_device_ax.jsp").forward(request, response);
	}
}

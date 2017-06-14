package gcom.controller.front.ax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import gcom.controller.action.deptAction;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

/**
 * Servlet implementation class axDeptController
 */
@WebServlet("/ax/main/sys")
public class axUserSystemPolicyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axUserSystemPolicyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code").toString();
		
		UserServiceInterface userService = new UserServiceImpl();
		List<HashMap<String, Object>> systemPolicyInfo = userService.getUserSystemPolicyList(code);
		
		request.setAttribute("code", code);
		request.setAttribute("systemPolicyInfo", systemPolicyInfo);
		request.getRequestDispatcher("/WEB-INF/user/ax/main_sys_policy_ax.jsp").forward(request, response);
	}

}

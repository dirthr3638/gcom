package gcom.controller.front.ax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

/**
 * Servlet implementation class axDeptController
 */
@WebServlet("/ax/main/policy/modal")
public class axUserMemberPolicyModalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axUserMemberPolicyModalController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String group = request.getParameter("group").toString();
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("group_id", group);
    	param.put("key", request.getParameter("key_code").toString());
    	
    	UserServiceInterface userService = new UserServiceImpl();
    	List<HashMap<String, Object>> list = userService.getMemberPolicyDetail(param);
    	
    	request.setAttribute("group", group);
    	request.setAttribute("userPolicyDetail", list);
		request.getRequestDispatcher("/WEB-INF/user/ax/main_user_policy_modal_ax.jsp").forward(request, response);
	}

}

package gcom.controller.front.admin.ax;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.controller.action.deptAction;
import gcom.controller.action.admin.getAdminAction;


@WebServlet("/ax/admin/userinput/*")
public class userInfoInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestUri = request.getRequestURI();
		HashMap<String, Object> param =  new HashMap<String, Object>();

		
		deptAction act = new deptAction();
		request.setAttribute("deptList", act.getDeptList(1));
				
		if(request.getParameterMap().containsKey("user_no"))
			param.put("user_no",  request.getParameter("user_no"));

		
		if(requestUri.equals("/ax/admin/userinput/modify")){
			request.setAttribute("popup_type", "modify");
			
			getAdminAction uAction = new getAdminAction();
			request.setAttribute("userInfo", uAction.getUserInfo(param));

			request.getRequestDispatcher("/WEB-INF/admin/user_manage/ax/pop_user_info_input.jsp").forward(request, response);

		}else if(requestUri.equals("/ax/admin/userinput/create")){
			request.setAttribute("popup_type", "create");    		
        	request.getRequestDispatcher("/WEB-INF/admin/user_manage/ax/pop_user_info_input.jsp").forward(request, response);
    	
		}
		
	}
}

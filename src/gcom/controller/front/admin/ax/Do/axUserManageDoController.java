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

import gcom.Model.SubAdminModel;
import gcom.controller.action.admin.getAdminAction;
import gcom.controller.action.admin.insertAdminAction;
import gcom.controller.action.admin.updateAdminAction;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.model.UserInfoModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;


@WebServlet(urlPatterns={"/admin/user/manage/do/*"} )	//save, reject, view
public class axUserManageDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("user_no", request.getParameter("user_no"));

    	String requestUri = request.getRequestURI();
		HashMap<String, Object> data =  new HashMap<String, Object>();;
		
		if(requestUri.equals("/admin/user/manage/do/create")){
	    	param.put("dept_no", request.getParameter("user_dept"));
	    	param.put("duty", request.getParameter("user_duty"));
	    	param.put("rank", request.getParameter("user_rank"));
	    	param.put("number", request.getParameter("user_number"));
	    	param.put("name", request.getParameter("user_name"));
	    	param.put("phone", request.getParameter("user_phone"));
	    	param.put("id", request.getParameter("user_id"));
	    	param.put("password", request.getParameter("user_password"));

			insertAdminAction action = new insertAdminAction();
        	data = action.insertUserInfo(param);

		}else if(requestUri.equals("/admin/user/manage/do/update")){
	    	param.put("dept_no", request.getParameter("user_dept"));
	    	param.put("duty", request.getParameter("user_duty"));
	    	param.put("rank", request.getParameter("user_rank"));
	    	param.put("number", request.getParameter("user_number"));
	    	param.put("name", request.getParameter("user_name"));
	    	param.put("phone", request.getParameter("user_phone"));
	    	param.put("id", request.getParameter("user_id"));
	    	param.put("password", request.getParameter("user_password"));

    		updateAdminAction action = new updateAdminAction();
   			data = action.updateUserInfo(param);    		
    	
		}else if(requestUri.equals("/admin/user/manage/do/remove")){
    		updateAdminAction action = new updateAdminAction();
    		data = action.removeUserInfo(param);    		
    	}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

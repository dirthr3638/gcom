package gcom.controller.front.admin.ax;

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


@WebServlet(urlPatterns={"/admin/enroll/do/*"} )	//save, reject, view
public class axAdminEnrollDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
    	HttpSession session = httpReq.getSession(false);
    	
    	String admin_id = (String)session.getAttribute("user_id");
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("admin_id", admin_id);
    	param.put("req_id", request.getParameter("req_id"));

    	String requestUri = request.getRequestURI();
		HashMap<String, Object> data =  new HashMap<String, Object>();;
		
		if(requestUri.equals("/admin/enroll/do/dupcheck")){
        	getAdminAction action = new getAdminAction();
        	data = action.getEnrollRequestCheckDupl(param);
    	}else if(requestUri.equals("/admin/enroll/do/save")){
        	insertAdminAction action = new insertAdminAction();
   			data = action.insertUserInfoFromRequest(param);    		
    	}else if(requestUri.equals("/admin/enroll/do/reject")){
    		updateAdminAction action = new updateAdminAction();
    		data = action.updateEnrollRequestReject(param);    		
    	}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

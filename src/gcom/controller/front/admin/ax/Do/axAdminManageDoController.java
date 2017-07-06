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
import gcom.controller.action.deptAction;
import gcom.controller.action.admin.getAdminAction;
import gcom.controller.action.admin.insertAdminAction;
import gcom.controller.action.admin.updateAdminAction;
import gcom.service.management.IManagementService;
import gcom.service.management.ManagementServiceImpl;
import gcom.user.model.UserInfoModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;


@WebServlet(urlPatterns={"/admin/admin/manage/do/*"} )	//save, reject, view
public class axAdminManageDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> param = new HashMap<String, Object>();

    	String requestUri = request.getRequestURI();
		HashMap<String, Object> data =  new HashMap<String, Object>();;


		
		//관리자생성
		if(requestUri.equals("/admin/admin/manage/do/create")){
			deptAction act = new deptAction();
			request.setAttribute("deptList", act.getDeptList(1));

			param.put("id", request.getParameter("admin_id"));
	    	param.put("pw", request.getParameter("admin_password"));
	    	param.put("dept", request.getParameter("admin_dept"));
	    	param.put("ip1", request.getParameter("admin_ip0"));
	    	param.put("ip2", request.getParameter("admin_ip1"));

			insertAdminAction action = new insertAdminAction();
        	data = action.insertAdminUserInfo(param);

   		//관리자수정
		}else if(requestUri.equals("/admin/admin/manage/do/update")){
			deptAction act = new deptAction();
			request.setAttribute("deptList", act.getDeptList(1));

			param.put("no", request.getParameter("admin_no"));
			param.put("id", request.getParameter("admin_id"));
	    	param.put("pw", request.getParameter("admin_password"));
	    	param.put("dept", request.getParameter("admin_dept"));
	    	param.put("ip1", request.getParameter("admin_ip0"));
	    	param.put("ip2", request.getParameter("admin_ip1"));

    		updateAdminAction action = new updateAdminAction();
   			data = action.updateAdminUserInfo(param);    		

   		//관리자삭제
		}else if(requestUri.equals("/admin/admin/manage/do/remove")){
	    	param.put("no", request.getParameter("admin_no"));

			updateAdminAction action = new updateAdminAction();
    		data = action.deleteAdminUserInfo(param);    		
    	}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

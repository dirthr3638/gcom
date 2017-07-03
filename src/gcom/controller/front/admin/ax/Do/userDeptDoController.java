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

import gcom.Model.DeptModel;
import gcom.controller.action.deptAction;
import gcom.controller.action.admin.getAdminAction;
import gcom.controller.action.admin.insertAdminAction;
import gcom.controller.action.admin.updateAdminAction;

@WebServlet("/admin/do/dept/*")
public class userDeptDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userDeptDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DeptModel data = new DeptModel();
    	HashMap<String, Object> result = null;

		if (request.getParameterMap().containsKey("no")){
			data.setDeptNo(Integer.parseInt(request.getParameter("no")));
		}
		if (request.getParameterMap().containsKey("parent")){
			data.setParent(Integer.parseInt(request.getParameter("parent")));			
		}
		if (request.getParameterMap().containsKey("name")){
			data.setName(request.getParameter("name"));			
		}
		if (request.getParameterMap().containsKey("short_name")){
			data.setShortName(request.getParameter("short_name"));
		}
    	
    	String requestUri = request.getRequestURI();
    	deptAction action = new deptAction();
		
    	
		if(requestUri.equals("/admin/do/dept/create")){
			result = action.insertDeptInfo(data);
    	}else if(requestUri.equals("/admin/do/dept/update")){
    		result = action.updateDeptNameInfo(data);    		
    	}else if(requestUri.equals("/admin/do/dept/remove")){
    		result = action.removeDeptInfo(data.getDeptNo());    		
    	}
		    	
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(result));
    }
}

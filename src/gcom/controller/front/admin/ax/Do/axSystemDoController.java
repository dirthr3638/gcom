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

@WebServlet("/admin/system/update")
public class axSystemDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axSystemDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> data = new HashMap<String, Object>();
    	HashMap<String, Object> result = null;

    	data.put("value", request.getParameter("value"));
    	data.put("system_no", request.getParameter("system_no"));

    	updateAdminAction action = new updateAdminAction();
    	
		result = action.updateSystemInfo(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(result));
    }
}
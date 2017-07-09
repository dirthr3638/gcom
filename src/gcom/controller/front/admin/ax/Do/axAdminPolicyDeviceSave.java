package gcom.controller.front.admin.ax.Do;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gcom.common.util.JSONUtil;
import gcom.controller.action.admin.insertAdminAction;

@WebServlet("/admin/policy/device/save")
public class axAdminPolicyDeviceSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axAdminPolicyDeviceSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String deviceId = request.getParameter("code").toString();
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("deviceId", deviceId);
    	
		insertAdminAction action = new insertAdminAction();
		HashMap<String, Object> data =  new HashMap<String, Object>();
		try {
			data = action.insertPolicyDeviceSave(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

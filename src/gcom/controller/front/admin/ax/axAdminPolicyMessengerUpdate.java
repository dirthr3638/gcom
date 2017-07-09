package gcom.controller.front.admin.ax;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gcom.common.services.JSONUtil;
import gcom.controller.action.admin.updateAdminAction;

@WebServlet("/admin/policy/msg/modify")
public class axAdminPolicyMessengerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axAdminPolicyMessengerUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> param = JSONUtil.convertJsonToHashMap(request.getParameter("data").toString());
    	
		updateAdminAction action = new updateAdminAction();
		HashMap<String, Object> data =  new HashMap<String, Object>();
		try {
			data = action.updatePolicyMsgUpdate(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

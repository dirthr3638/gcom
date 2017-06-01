package gcom.controller.front.ax;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.Model.UserAgentModel;

import com.google.gson.Gson;

import gcom.controller.action.getAction;

/**
 * Servlet implementation class axUserAgentController
 */
@WebServlet("/ax/useragent/list")
public class axUserAgentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axUserAgentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", Integer.parseInt( request.getParameter("start").toString()) );
		map.put("length", Integer.parseInt( request.getParameter("length").toString()) );

		getAction action = new getAction();
		
		
		map.put("startRow", Integer.parseInt(map.get("start").toString()));
		map.put("endRow", Integer.parseInt(map.get("length").toString()));

		HashMap<String, Object> data = action.getUserAgentList(map);

		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
		
	}
/*	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		getAction action = new getAction();
		List<UserAgentModel> lst = action.getUserAgentList(map);
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("data", lst);
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(data));

	}*/
}

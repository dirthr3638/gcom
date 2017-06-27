package gcom.controller.front.admin.ax.list;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange.Map;

import gcom.controller.action.getStatisticAction;
import gcom.controller.action.admin.getAdminAction;

@WebServlet("/ax/simplecantact/list")
public class axSimpleContactListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axSimpleContactListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		getStatisticAction action = new getStatisticAction();
		//map.put("dept", request.getParameterValues("dept[]"));

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson( action.getSimpleContactList(map)));
		
	}

}
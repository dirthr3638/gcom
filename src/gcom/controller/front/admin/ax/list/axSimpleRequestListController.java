package gcom.controller.front.admin.ax.list;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gcom.controller.action.deptAction;
import gcom.controller.action.getStatisticAction;

@WebServlet("/ax/simplerequest/list")
public class axSimpleRequestListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axSimpleRequestListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpSession session = httpReq.getSession(false);

    	deptAction deptAction = new deptAction();
    	List<Integer> dept = deptAction.getDeptIntList(Integer.parseInt(session.getAttribute("dept_no").toString()));
    	map.put("dept", dept);
		
		getStatisticAction action = new getStatisticAction();

		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson( action.getSimpleRequestList(map)));
		
	}
}

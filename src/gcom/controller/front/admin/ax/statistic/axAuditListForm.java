package gcom.controller.front.admin.ax.statistic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import gcom.controller.action.getStatisticAction;

@WebServlet("/ax/admin/statistic/audit")
public class axAuditListForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axAuditListForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		getStatisticAction action = new getStatisticAction();

/*		map.put("setValue", request.getParameter("setValue"));
		map.put("setRange", request.getParameter("setRange"));
		map.put("setType", request.getParameter("setType"));
*/				
		request.setAttribute("list", action.getSimpleAuditList(map));
		request.getRequestDispatcher("/WEB-INF/dashboard/axAuditListForm.jsp").forward(request, response);

	}

}

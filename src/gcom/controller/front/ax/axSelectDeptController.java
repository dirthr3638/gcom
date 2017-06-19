package gcom.controller.front.ax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import gcom.controller.action.deptAction;

/**
 * Servlet implementation class axDeptController
 */
@WebServlet("/common/tree/selectdept")
public class axSelectDeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axSelectDeptController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		deptAction da = new deptAction();
		
		String json = new Gson().toJson(da.getDeptListForJSTree(1));
		request.setAttribute("deptJson", json);
		
		request.getRequestDispatcher("/WEB-INF/common/tree_select_dept.jsp").forward(request, response);
		
	}

}

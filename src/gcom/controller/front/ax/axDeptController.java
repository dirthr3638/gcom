package gcom.controller.front.ax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import gcom.controller.action.deptAction;

@WebServlet("/common/tree/dept")
public class axDeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axDeptController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/common/tree_dept.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deptAction da = new deptAction();
		String json = new Gson().toJson(da.getDeptListForJSTree(1));
		request.setAttribute("deptJson", json);		
		request.getRequestDispatcher("/WEB-INF/common/tree_dept.jsp").forward(request, response);		
	}
}

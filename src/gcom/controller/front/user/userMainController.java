package gcom.controller.front.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gcom.user.model.MemberPolicyModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/main")
public class userMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/main.jsp").forward(request, response);
	}
}

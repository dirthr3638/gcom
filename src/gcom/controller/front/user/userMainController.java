package gcom.controller.front.user;

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

import gcom.user.model.UserPolicyListModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/main")
public class userMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpServletRequest httpReq = (HttpServletRequest)request;
    	HttpSession session = httpReq.getSession(false);
    	
    	String user_id = (String)session.getAttribute("user_id");
    	 
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("user_id", user_id);
    	
    	UserServiceInterface userService = new UserServiceImpl();
    	List<UserPolicyListModel> list = userService.getUserPolicySetInfo(param);
    	
    	HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("data",list);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
    	
		request.getRequestDispatcher("WEB-INF/user/main.jsp").forward(request, response);
	}
}

package gcom.controller.front.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.user.model.UserNoticeModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserService;

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/notice/view")
public class userNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userNoticeViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("bbs_id", request.getParameter("att_bbs_id"));
		
		UserService userService = new UserServiceImpl();
			
		try {
			UserNoticeModel model = userService.getUserNoticeDetail(param);
			request.setAttribute("UserNoticeDetail", model);
			
			userService.updateNoticeViewCount(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
    	request.getRequestDispatcher("/WEB-INF/user/notice_view.jsp").forward(request, response);
	}
}

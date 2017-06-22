package gcom.controller.front.admin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.user.model.UserNoticeModel;
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserServiceInterface;

//공지사항보기
@WebServlet("/admin/user/notice/view")
public class userNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userNoticeViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("bbs_id", request.getParameter("bbsId"));
		
		UserServiceInterface userService = new UserServiceImpl();
			
		try {
			UserNoticeModel model = userService.getUserNoticeDetail(param);
			request.setAttribute("UserNoticeDetail", model);
			
			userService.updateNoticeViewCount(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		request.getRequestDispatcher("/WEB-INF/admin/user_manage/admin_user_notice_view.jsp").forward(request, response);
	}
}

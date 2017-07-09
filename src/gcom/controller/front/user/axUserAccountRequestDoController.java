package gcom.controller.front.user;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gcom.user.model.UserInfoModel;
import gcom.user.service.UserService;
import gcom.user.service.UserServiceImpl;

@WebServlet("/account/request/do")
public class axUserAccountRequestDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public axUserAccountRequestDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		param.put("user_password1", request.getParameter("user_password1"));
		param.put("user_password2", request.getParameter("user_password2"));

		param.put("user_number", request.getParameter("user_number"));
		param.put("user_name", request.getParameter("user_name"));
		param.put("user_duty", request.getParameter("user_duty"));
		param.put("user_rank", request.getParameter("user_rank"));
		param.put("user_dept", request.getParameter("user_dept"));
		param.put("user_id", request.getParameter("user_id"));
		param.put("user_phone", request.getParameter("user_phone"));
		
		
		UserService userService = new UserServiceImpl();
		
		HashMap<String, Object> data =  new HashMap<String, Object>();;
		try {
			data = userService.insertAccountReqeust(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
    		
	}

}

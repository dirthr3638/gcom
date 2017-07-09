package gcom.common.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gcom.controller.action.commonAction;
import gcom.controller.action.admin.insertAdminAction;

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/login/check")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpServletRequest httpReq = (HttpServletRequest)request;
         HttpSession session = httpReq.getSession();
 		insertAdminAction audit = new insertAdminAction();
         
    	String loginType = request.getParameter("loginType");
    	String userId = request.getParameter("att_staf_id");
    	String userPw = request.getParameter("att_staf_pwd");
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("loginType", loginType);
		param.put("userId", userId);
		param.put("userPw", userPw);
		
		commonAction comm = new commonAction();
		
		HashMap<String, Object> data = comm.getLoginCheckResult(param);
		
		
		//로그인 성공시
		if ("S".equals(data.get("returnCode"))) {
			session.setAttribute("user_id", data.get("userId"));
			if ("U".equals(loginType)){
				session.setAttribute("user_nm", data.get("userName"));
			}
			session.setAttribute("dept_no", data.get("deptNo"));
		    session.setAttribute("login_root", data.get("goUrl"));
		}else{
			//로그인 실패시
		}
		
		data.putAll(data);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

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

import gcom.Model.ServerAuditModel;
import gcom.common.services.ConfigInfo;
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
        ServerAuditModel aModel = new ServerAuditModel();
 		aModel.setActionId(1);
        aModel.setWorkIp(request.getRemoteAddr());
		aModel.setDescription("로그인작업");
		
    	String loginType = request.getParameter("loginType");
    	String userId = request.getParameter("att_staf_id");
    	String userPw = request.getParameter("att_staf_pwd");
    	
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("loginType", loginType);
		param.put("userId", userId);
		param.put("userPw", userPw);
		param.put("userIp", request.getRemoteAddr());
		
		commonAction comm = new commonAction();
		
		HashMap<String, Object> data = comm.getLoginCheckResult(param);
		
		String ReturnCode = data.get("returnCode").toString();
		
		//로그인 성공시
		if (ConfigInfo.RETURN_CODE_SUCCESS.equals(ReturnCode)) {
			session.setAttribute("user_id", data.get("userId"));
			if ("U".equals(loginType)){
				session.setAttribute("user_nm", data.get("userName"));

			}else{	//관리자 로그인 성공시
				aModel.setStatus("성공");
				aModel.setParameter("아이디 : " + data.get("userId").toString());
				aModel.setAdminId(data.get("userId").toString());
				audit.insertServeriAudit(aModel);
			}
			session.setAttribute("dept_no", data.get("deptNo"));
		    session.setAttribute("login_root", data.get("goUrl"));
		}else{
			//로그인 실패시
			if ("C".equals(loginType)){
				//IP실패/ 패스워드 실패
				aModel.setStatus("실패");
				aModel.setAdminId(data.get("userId").toString());
				
				if(ReturnCode.equals(ConfigInfo.NOT_CORRECT_IP) ){	//IP실패일경우
					aModel.setParameter("(아이디 : " + data.get("userId").toString() + ") 비인가 단말기에서 접속시도 - " + request.getRemoteAddr());
					audit.insertServeriAudit(aModel);
				}else if(ReturnCode.equals(ConfigInfo.NOT_EXIST_USER)){
					aModel.setParameter("존재하지 않는 아이디 (" + data.get("userId").toString() + ") 로그인 시도");
					audit.insertServeriAudit(aModel);									
					ReturnCode = ConfigInfo.NOT_CORRECT_PASSWORD_ID;

				}else if(ReturnCode.equals(ConfigInfo.NOT_CORRECT_PASSWORD)){
					aModel.setParameter("아이디 : " + data.get("userId").toString() + " 패스워드가 불일치 ");
					audit.insertServeriAudit(aModel);									
					ReturnCode = ConfigInfo.NOT_CORRECT_PASSWORD_ID;

				}				
			}
		}
		
		data.put("returnCode", ReturnCode);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(new Gson().toJson(data));
	}
}

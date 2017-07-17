package gcom.controller.front.user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
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
import gcom.user.service.UserServiceImpl;
import gcom.user.service.UserService;

/**
 * Servlet implementation class axDeptController
 */
@WebServlet("/getpic")
public class axUserContactSaveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axUserContactSaveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
    	HttpSession session = httpReq.getSession(false);
    	
    	/*String user_id = (String)session.getAttribute("user_id");
    	HashMap<String, Object> param = new HashMap<String, Object>();
    	param.put("user_id", user_id);
    	
    	UserService userService = new UserServiceImpl();
    	
    
		UserInfoModel user = userService.getUserInfo(param);
		param.put("user_no", user.getUserNo());
		param.put("titel", request.getParameter("contact_subject"));
		param.put("body", request.getParameter("contact_subject"));
		param.put("eMail", request.getParameter("user_mail"));
		param.put("conType", request.getParameter("contact_type"));
		
		HashMap<String, Object> data =  new HashMap<String, Object>();;
		try {
			data = userService.insertContactSave(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		data.putAll(data);
		response.setContentType("Content-Type","image/png");
		response.getWriter().write("/imag");*/
		
		String dFileName = request.getParameter("param1");
		response.setHeader("Content-Type","image/png");
		String filename2 = new String(dFileName.getBytes("8859_1"),"euc-kr");
		String path = "/Users/inswave/upload/";
		java.io.File file = new java.io.File(path+dFileName);
		byte b[] = new byte[(int)file.length()];
		response.setHeader("Content-Disposition","attachement:filename="+new String(dFileName.getBytes("euc-kr"))+";");
		if(file.isFile()){
		    BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
		    BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
		    int read = 0;
		    while ((read=fin.read(b))!=-1){
		        outs.write(b,0,read);
		    }
		    outs.close();
		    fin.close();
		}
		
		
    		
	}

}

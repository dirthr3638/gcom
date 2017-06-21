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

/**
 * Servlet implementation class dashboardServlet
 */
@WebServlet("/logout")
public class LoginOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpServletRequest httpReq = (HttpServletRequest)request;
         HttpSession session = httpReq.getSession();
         
         session.invalidate();
         request.getRequestDispatcher("/").forward(request, response);
	}
}
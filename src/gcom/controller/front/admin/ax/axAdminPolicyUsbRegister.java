package gcom.controller.front.admin.ax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gcom.Model.UsbDevInfoModel;
import gcom.controller.action.admin.getAdminAction;

@WebServlet("/admin/policy/usb/register")
public class axAdminPolicyUsbRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public axAdminPolicyUsbRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int code = Integer.parseInt(request.getParameter("code").toString());
    	
    	if (code != 0) {
    		getAdminAction action = new getAdminAction();
    		UsbDevInfoModel model = action.getUsbInfo(code);
    		request.setAttribute("data", model);
    	}
    	
    	request.setAttribute("keyNo", code);
		request.getRequestDispatcher("/WEB-INF/admin/device_manage/ax/popup/pop_policy_usb_register_ax.jsp").forward(request, response);
	}
}
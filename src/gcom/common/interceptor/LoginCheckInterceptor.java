package gcom.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import gcom.DAO.AuditDataDAO;
import gcom.service.System.ISystemService;
import gcom.service.System.SystemServiceImpl;

/**
 * @author gillee
 * @since 2017-05-29 마지막 수정 로그인 check filter
 */
public class LoginCheckInterceptor implements Filter {

	List<String> adminUrls = new ArrayList<>();
	List<String> reportUrls = new ArrayList<>();
	List<String> userUrls = new ArrayList<>();

	public void init(FilterConfig config) throws ServletException {

		adminUrls.add("/dashboard");
		adminUrls.add("/admin/device/usb");
		adminUrls.add("/admin/device/usbblock");
		adminUrls.add("/admin/policy/export");
		adminUrls.add("/admin/policy/person");
		adminUrls.add("/admin/subadmin");
		adminUrls.add("/admin/system/manage");
		adminUrls.add("/admin/user/contac");
		adminUrls.add("/admin/user/contact/view");
		adminUrls.add("/admin/user/dept");
		adminUrls.add("/admin/user/enroll");
		adminUrls.add("/admin/user/manage");
		adminUrls.add("/admin/user/notice");
		adminUrls.add("/admin/user/notice/modify");
		adminUrls.add("/admin/user/notice/view");
		adminUrls.add("/admin/user/notice/write");
		adminUrls.add("/admin/user/assign");
		adminUrls.add("/admin/user/request");

		reportUrls.add("/report/adminprofile");
		reportUrls.add("/report/audit/agent");
		reportUrls.add("/report/audit/server");
		reportUrls.add("/report/dashboard");
		reportUrls.add("/report/disktran");
		reportUrls.add("/report/print");
		reportUrls.add("/report/usbblock");
		reportUrls.add("/report/usbunauth");
		reportUrls.add("/report/login");
		reportUrls.add("/report/mail");
		reportUrls.add("/report/msnfile");
		reportUrls.add("/report/msntalk");
		reportUrls.add("/report/policydefault");
		reportUrls.add("/report/policy");
		reportUrls.add("/report/privacy");
		reportUrls.add("/report/users");

		userUrls.add("/account/request/view");
		userUrls.add("/contact");
		userUrls.add("/userinfo");
		userUrls.add("/main");
		userUrls.add("/notice");
		userUrls.add("/notice/view");

	}

	public boolean checkUri(String requestUrl, List<String> urls) {
		for (int i = 0; i < urls.size(); i++) {
			if (requestUrl.matches(urls.get(i))) {
				return true;
			}
		}

		return false;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session = httpReq.getSession(false);

		httpReq.setCharacterEncoding("UTF-8");
		boolean loginFlag = false;

		if (session != null) {
			String userId = (String) session.getAttribute("user_id");

			if (userId != null) {

				loginFlag = true;

				int admin_mode = (int) session.getAttribute("admin_mode");

				ISystemService sys = new SystemServiceImpl();
				session.setMaxInactiveInterval(sys.serverLogoutTimeInfo());
				// session.setMaxInactiveInterval(1);

				if (admin_mode == 1) { // 콘솔권한
					if (checkUri(httpReq.getRequestURI(), reportUrls) || checkUri(httpReq.getRequestURI(), userUrls)) {
						request.getRequestDispatcher("/WEB-INF/common/unauth.jsp").forward(request, response);
					}

				} else if (admin_mode == 2) { // 레포트권한
					if (checkUri(httpReq.getRequestURI(), adminUrls) || checkUri(httpReq.getRequestURI(), userUrls)) {
						request.getRequestDispatcher("/WEB-INF/common/unauth.jsp").forward(request, response);
					}

				} else if (admin_mode == 0) { // 관리자 권한
					if (checkUri(httpReq.getRequestURI(), userUrls)) {
						request.getRequestDispatcher("/WEB-INF/common/unauth.jsp").forward(request, response);
					}
				} else if (admin_mode == -1) { // 사용자권한
					if (checkUri(httpReq.getRequestURI(), adminUrls) || checkUri(httpReq.getRequestURI(), reportUrls)) {
						request.getRequestDispatcher("/WEB-INF/common/unauth.jsp").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("/WEB-INF/common/unauth.jsp").forward(request, response);
				}
			}
		}

		String[] uris = { "/report", "/report/users", "/assets", "/login/check", "/test/hash", "/account/request/view",
				"/account/request/do" }; // check URL - ex) 건너뛰거나 체크에서 제외될 URL
		String uri = httpReq.getRequestURI(); // 요청 URL
		for (String s : uris) {
			if (uri.indexOf(s) != -1) { // 요청 URL 과 체크 URL 을 비교 로그인 페이지 호출 또는 제외
				loginFlag = true;
				break;
			}
		}

		if (loginFlag) {
			if (uri.equals("/") || uri.equals("")) {
				// 로그인 상태에서 루트 실행시 메인 페이지로 넘김
				String url = (String) session.getAttribute("login_root");
				httpRes.sendRedirect(url);
			} else {
				// 로그인 되어있고 루트디렉토리가 아닐시 리퀘스트 받은 결과 넘김
				request.setAttribute("dept_no", session.getAttribute("dept_no"));
				chain.doFilter(request, response);
			}
		} else {
			// 세션없어서 로그인 풀릴때

			// ajax일경우
			if (uri.equals("/logout")) {
				chain.doFilter(request, response);
			} else if ("XMLHttpRequest".equals(httpReq.getHeader("X-Requested-With"))) {
				httpRes.sendRedirect("/logout");
			} else {
				request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
			}
		}
	}

	public void destroy() {
	}

}

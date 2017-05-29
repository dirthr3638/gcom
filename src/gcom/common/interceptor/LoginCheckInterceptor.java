package gcom.common.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author gillee
 * @since 2017-05-29 마지막 수정
 * 로그인 check filter
 */
public class LoginCheckInterceptor implements Filter {
	
	public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpSession session = httpReq.getSession(false);
        
        httpReq.setCharacterEncoding("UTF-8");
        boolean loginFlag = false;
        
        if (session != null) {
        	String userId = (String)session.getAttribute("user");
        	if(userId != null) {
        		loginFlag = true;
        	}
        }
        
        /* 통과 URL 체크 임시*/
        String[] uris = {"/dashboard"};		//check URL - ex) 건너뛰거나 체크에서 제외될 URL
        String uri = httpReq.getRequestURI();					//요청 URL
        for(String s : uris) {
            if(uri.indexOf(s) != -1) {							//요청 URL 과 체크 URL 을 비교 로그인 페이지 호출 또는 제외
                loginFlag = true;
                break;
            }
        }
        
        if (loginFlag) {
        	chain.doFilter(request, response);
        } else {
        	request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
        }
        
       
    }

    public void destroy(){
    }
	
}

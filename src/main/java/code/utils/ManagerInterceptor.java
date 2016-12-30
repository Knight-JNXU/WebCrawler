package code.utils;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Knight_JXNU on 2016/12/30.
 */
public class ManagerInterceptor implements AsyncHandlerInterceptor {
    public void afterConcurrentHandlingStarted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String userName = (String) httpServletRequest.getSession().getAttribute("username");
        if(userName==null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/htmls/login.html");
            return false;
        }else{
            return true;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

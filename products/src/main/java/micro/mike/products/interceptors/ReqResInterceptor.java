package micro.mike.products.interceptors;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;


@Component("reqResInterceptor")
@Log4j2
public class ReqResInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UUID uuid = UUID.randomUUID();
        MDC.put("id", uuid.toString());
        String path = request.getMethod() + "-" + request.getServletPath();
        String entry = "";
        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            entry = res.getMethod().getDeclaringClass().getSimpleName() + "-" + res.getMethod().getName();
        }
        MDC.put("entry", entry);
        MDC.put("path", path);
        MDC.put("time", String.valueOf(currentTimeMillis()));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String path = request.getMethod() + "-" + request.getServletPath();
        String entry = "";
        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            entry = res.getMethod().getDeclaringClass().getSimpleName() + "-" + res.getMethod().getName();
        }
        MDC.put("entry", entry);
        MDC.put("path", path);
    }

}

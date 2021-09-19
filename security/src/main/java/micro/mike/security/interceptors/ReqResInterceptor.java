package micro.mike.security.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;


@Component("reqResInterceptor")
public class ReqResInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ReqResInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UUID uuid = UUID.randomUUID();
        String log = "[REQUEST|".concat(uuid.toString()).concat("][").concat(request.getMethod()).concat("|").concat(request.getServletPath()).concat("]");
        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            log = log.concat("[").concat(res.getMethod().getDeclaringClass().getSimpleName());
            log = log.concat("|").concat(res.getMethod().getName()).concat("]");
        }
        logger.info(log);
        request.setAttribute("time", currentTimeMillis());
        request.setAttribute("uuid", uuid.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long time = currentTimeMillis() - ((long) request.getAttribute("time"));
        String uuid = (String) request.getAttribute("uuid");
        String log = "[RESPONSE|".concat(uuid).concat("][").concat(request.getMethod()).concat("|").concat(request.getServletPath()).concat("]");
        if (handler instanceof HandlerMethod) {
            HandlerMethod res = (HandlerMethod) handler;
            log = log.concat("[").concat(res.getMethod().getDeclaringClass().getSimpleName());
            log = log.concat("|").concat(res.getMethod().getName()).concat("]");
        }
        log = log.concat("[TIME|").concat(String.valueOf(time)).concat("ms]");
        logger.info(log);
    }

}

package micro.mike.products.interceptors;

import lombok.extern.log4j.Log4j2;
import micro.mike.commons.http.interceptors.annotations.ResponseSuccess;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

@ControllerAdvice
@Log4j2
public class ChangeResponseInterceptor implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.hasMethodAnnotation(ResponseSuccess.class);
    }

    @Override
    public Object beforeBodyWrite(
            Object o,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse
    ) {
        log.info("RESPONSE: {}", o);
        ResponseSuccess s = methodParameter.getMethodAnnotation(ResponseSuccess.class);
        long time = System.currentTimeMillis() - Long.parseLong(MDC.get("time"));
        HashMap<String, Object> res = new HashMap<>();
        log.info("RESPONSE: {}, TIME:{}ms", o, time);
        res.put("data", o);
        assert s != null;
        res.put("message", s.message());
        return res;
    }
}

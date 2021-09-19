package micro.mike.security.interceptors;

import micro.mike.commons.http.interceptors.annotations.ResponseSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;

@ControllerAdvice
public class ChangeResponseInterceptor implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ChangeResponseInterceptor.class);
    private ServerHttpRequest serverHttpRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethodAnnotation(ResponseSuccess.class) != null;
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
        ResponseSuccess s = methodParameter.getMethodAnnotation(ResponseSuccess.class);
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", o);
        assert s != null;
        res.put("message", s.message());
        return res;
    }
}

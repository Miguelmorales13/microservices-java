package micro.mike.security.api.users.http;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Service
public class CrudService<Model> {

    @Autowired
    private RestTemplate client;

    private static final Logger logger = LoggerFactory.getLogger(CrudService.class);

    @SneakyThrows
    public Model get() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String classs = elements[2].getClassName();
        String method = elements[2].getMethodName();
        Class aClass = elements[2].getClass();
        Method method1 = aClass.getMethod(method);
        Annotation[] declaredAnnotations = method1.getDeclaredAnnotations();
        logger.info("{}:{}", classs, method);
        return null;
    }


}

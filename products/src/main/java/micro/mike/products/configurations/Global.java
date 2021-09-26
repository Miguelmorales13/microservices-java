package micro.mike.products.configurations;

import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Global implements WebMvcConfigurer {

    @Autowired
    @Qualifier("reqResInterceptor")
    private HandlerInterceptor reqResInterceptor;


    @Bean
    Logger.Level FeignClientLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(reqResInterceptor);
    }
}

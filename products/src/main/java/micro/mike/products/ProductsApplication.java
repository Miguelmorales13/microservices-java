package micro.mike.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients()
@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EntityScan({
        "micro.mike.commons.db.entities",
        "micro.mike.commons.db.crud",
        "micro.mike.commons.converts",
        "micro.mike.commons.converts",
        "micro.mike.commons.http.exceptions",
        "micro.mike.commons.http.feign",
        "micro.mike.commons.http.interceptors.annotations",
        "micro.mike.commons.http.models",
})
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }

}

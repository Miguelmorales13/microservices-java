package micro.mike.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
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
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}

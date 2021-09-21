package micro.mike.products.feign;

import micro.mike.commons.http.feign.UsersFeign;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;

@RefreshScope
@FeignClient(name = "${api.users.name}", path = "${api.users.path}", url = "${api.users.url}")
public interface UsersHttp extends UsersFeign {
}

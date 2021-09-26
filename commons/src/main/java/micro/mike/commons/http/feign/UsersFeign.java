package micro.mike.commons.http.feign;

import micro.mike.commons.db.entities.UserEntity;
import micro.mike.commons.http.models.CreateUserDto;
import micro.mike.commons.http.models.ResponseService;
import micro.mike.commons.http.models.UpdateUserDto;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@FeignClient(name = "${api.users.name}", path = "${api.users.path}", url = "${api.users.url}")
public interface UsersFeign {

    @GetMapping("")
    ResponseService<List<UserEntity>> getAll();

    @PostMapping("")
    ResponseService<UserEntity> create(@RequestBody CreateUserDto user);

    @PatchMapping(path = "/{id}")
    ResponseService<UserEntity> update(@RequestBody UpdateUserDto user, @PathVariable("id") Long id);

    @DeleteMapping(path = "/{id}")
    ResponseService<Integer> delete(@PathVariable("id") Long id);
}
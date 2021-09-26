package micro.mike.security.api.users;

import micro.mike.commons.db.entities.UserEntity;
import micro.mike.commons.http.interceptors.annotations.ResponseSuccess;
import micro.mike.security.api.users.dto.CreateUserDto;
import micro.mike.security.api.users.dto.UpdateUserDto;
import micro.mike.security.api.users.http.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CrudService<Object> crudService;

    @GetMapping()
//    @TrackTime()
    @ResponseSuccess(message = "getter successful")
    public List<UserEntity> getAll() {
        List<UserEntity> users = userService.getAll();
        return users;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseSuccess(message = "Creation successful")
    public UserEntity create(@Valid @RequestBody CreateUserDto user) {
        return userService.create(user);
    }

    @PatchMapping(path = "/{id}")
    @ResponseSuccess(message = "Updating successful")
    public UserEntity update(@RequestBody UpdateUserDto user, @PathVariable("id") Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseSuccess(message = "Deleted successful")
    public int delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}

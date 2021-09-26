package micro.mike.security.api.users;

import micro.mike.commons.db.crud.HibernateServiceImpl;
import micro.mike.commons.db.entities.UserEntity;
import micro.mike.security.api.users.dto.CreateUserDto;
import micro.mike.security.api.users.dto.UpdateUserDto;
import micro.mike.security.api.users.http.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends HibernateServiceImpl<UserEntity, CreateUserDto, UpdateUserDto, Long, UserRepository> {

    public UserService(@Autowired UserRepository repository) {
        super(repository);
    }

    @Autowired
    CrudService<Object> crudService;

    @Override
    public List<UserEntity> getAll() {
        return super.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> getOneByUser(String user) {
        return super.getRepository().findFirstByEmail(user);
    }

}

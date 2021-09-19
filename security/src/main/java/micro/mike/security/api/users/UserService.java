package micro.mike.security.api.users;

import micro.mike.commons.db.crud.HibernateServiceImpl;
import micro.mike.commons.db.entities.UserEntity;
import micro.mike.security.api.users.dto.CreateUserDto;
import micro.mike.security.api.users.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends HibernateServiceImpl<UserEntity, CreateUserDto, UpdateUserDto, Long, UserRepository> {

    public UserService(@Autowired UserRepository repository) {
        super(repository);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> getOneByUser(String user) {
        return super.getRepository().findFirstByEmail(user);
    }

}

package micro.mike.security.api.users;

import micro.mike.commons.db.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEmail(String email);
//    List<UserEntity> findAllBy/(String email);
}

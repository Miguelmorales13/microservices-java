package micro.mike.products.api.products;

import micro.mike.commons.db.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Optional<ProductEntity> findFirstByName(String name);
//    List<UserModel> findAllBy/(String email);
}

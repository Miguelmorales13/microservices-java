package micro.mike.products.api.products;

import micro.mike.commons.db.crud.HibernateServiceImpl;
import micro.mike.commons.db.entities.ProductEntity;
import micro.mike.commons.db.entities.UserEntity;
import micro.mike.products.api.products.dto.CreateProductDto;
import micro.mike.products.api.products.dto.UpdateProductDto;
import micro.mike.products.feign.UsersHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@ImportAutoConfiguration({FeignClientsConfiguration.class})
public class ProductService extends HibernateServiceImpl<ProductEntity, CreateProductDto, UpdateProductDto, Long, ProductRepository> {
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private UsersHttp usersFeign;

    public ProductService(@Autowired ProductRepository repository) {
        super(repository);
    }

    @Override
    public List<ProductEntity> getAll() {
        List<UserEntity> users = usersFeign.getAll().getData();
        logger.info("  {}", users);
        return super.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> getOneByName(String user) {
        return super.getRepository().findFirstByName(user);
    }

}

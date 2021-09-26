package micro.mike.products.api.products;

import lombok.extern.log4j.Log4j2;
import micro.mike.commons.aspects.TrackTime;
import micro.mike.commons.db.crud.HibernateServiceImpl;
import micro.mike.commons.db.entities.ProductEntity;
import micro.mike.commons.http.feign.UsersFeign;
import micro.mike.products.api.products.dto.CreateProductDto;
import micro.mike.products.api.products.dto.UpdateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ProductService extends HibernateServiceImpl<ProductEntity, CreateProductDto, UpdateProductDto, Long, ProductRepository> {

    @Autowired
    private UsersFeign usersFeign;


    public ProductService(@Autowired ProductRepository repository) {
        super(repository);
    }

    @Override
    @TrackTime
    public List<ProductEntity> getAll() {

        usersFeign.getAll();
        return super.getAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> getOneByName(String user) {
        return super.getRepository().findFirstByName(user);
    }

}

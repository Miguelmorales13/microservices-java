package micro.mike.products.api.products;

import micro.mike.commons.db.entities.ProductEntity;
import micro.mike.commons.http.interceptors.annotations.ResponseSuccess;
import micro.mike.products.api.products.dto.CreateProductDto;
import micro.mike.products.api.products.dto.UpdateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    @ResponseSuccess(message = "getter successful")
    public List<ProductEntity> getAll() {

        List<ProductEntity> products = productService.getAll();

        return products;
    }

    @GetMapping(path = "/{id}")
    @ResponseSuccess(message = "getter successful")
    public Optional<ProductEntity> getOne(@PathVariable("id") Long id) {

        Optional<ProductEntity> products = productService.getOne(id);

        return products;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseSuccess(message = "Creation successful")
    public ProductEntity create(@Valid @RequestBody CreateProductDto product) {
        return productService.create(product);
    }

    @PatchMapping(path = "/{id}")
    @ResponseSuccess(message = "Updating successful")
    public ProductEntity update(@RequestBody UpdateProductDto product, @PathVariable("id") Long id) {
        return productService.update(product, id);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseSuccess(message = "Deleted successful")
    public int delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }
}

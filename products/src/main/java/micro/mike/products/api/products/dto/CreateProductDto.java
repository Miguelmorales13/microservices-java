package micro.mike.products.api.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.mike.commons.db.crud.ModelDto;
import micro.mike.commons.db.entities.ProductEntity;
import micro.mike.products.api.products.validators.ProductUnique;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto implements ModelDto<ProductEntity> {
    @NotEmpty
    @ProductUnique
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String secondLastName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    public ProductEntity toEntity() {
        return null;
    }
}

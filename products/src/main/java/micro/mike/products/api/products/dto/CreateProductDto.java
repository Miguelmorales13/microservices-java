package micro.mike.products.api.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.mike.commons.db.crud.ModelDto;
import micro.mike.commons.db.entities.ProductEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductDto implements ModelDto<ProductEntity> {
    private String name;
    private String photo;
    private Boolean active;

    public ProductEntity toEntity() {
        return ProductEntity
                .builder()
                .name(name)
                .active(active)
                .photo(photo)
                .build();
    }
}

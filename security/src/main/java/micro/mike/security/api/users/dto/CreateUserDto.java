package micro.mike.security.api.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.mike.commons.db.crud.ModelDto;
import micro.mike.commons.db.entities.UserEntity;
import micro.mike.security.api.users.validators.UserUnique;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto implements ModelDto<UserEntity> {
    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String secondLastName;
    @NotEmpty
    @UserUnique
    private String email;
    @NotEmpty
    private String password;

    public UserEntity toEntity() {
        return null;
    }
}

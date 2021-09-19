package micro.mike.commons.http.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import micro.mike.commons.db.crud.ModelDto;
import micro.mike.commons.db.entities.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto implements ModelDto<UserEntity> {
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String password;

    @Override
    public UserEntity toEntity() {
        return null;
    }
}


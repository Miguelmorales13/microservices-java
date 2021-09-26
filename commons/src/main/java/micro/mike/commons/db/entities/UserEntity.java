package micro.mike.commons.db.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "bigint")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "second_last_name", nullable = false)
    private String secondLastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore()
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;


    @PrePersist
    public void prePersist() {
        createdAt = new Date();
        updatedAt = new Date();
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
    }


    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Map<String, Object> toMap() throws IllegalAccessException {
        HashMap<String, Object> json = new HashMap<String, Object>();
        Field[] allFields = this.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = field.get(this);
            json.put(field.getName(), value);
        }
        return json;
    }

}

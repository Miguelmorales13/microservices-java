package micro.mike.commons.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted_at = NOW() WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted_at is null")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;


    @Column(name = "active")
    private Boolean active = true;

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

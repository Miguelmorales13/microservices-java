package micro.mike.commons.db.crud;

import java.util.List;
import java.util.Optional;

public interface HibernateService<T, C extends ModelDto<T>, U, ID> {
    List<T> getAll();

    Optional<T> getOne(ID id);

    T create(C item);

    T update(U item, ID id);

    int delete(ID id);
}

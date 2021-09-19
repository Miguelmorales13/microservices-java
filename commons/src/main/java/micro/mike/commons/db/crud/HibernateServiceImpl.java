package micro.mike.commons.db.crud;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
public class HibernateServiceImpl<M, C extends ModelDto<M>, U extends ModelDto<M>, ID, R extends CrudRepository<M, ID>> implements HibernateService<M, C, U, ID> {

    private R repository;

    public HibernateServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<M> getAll() {
        return (List<M>) repository.findAll();
    }

    @Override
    public Optional<M> getOne(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public M create(C item) {
        return repository.save(item.toEntity());
    }

    @Override
    public M update(U item, ID id) {
        if (repository.findById(id).isPresent()) {
            return repository.save(item.toEntity());
        }
        return null;
    }

    @Override
    public int delete(ID id) {
        try {
            repository.deleteById(id);
            return 1;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}

package micro.mike.commons.db.crud;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import micro.mike.commons.aspects.TrackTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Data
@Log4j2
public class HibernateServiceImpl<M, C extends ModelDto<M>, U extends ModelDto<M>, ID, R extends CrudRepository<M, ID>> implements HibernateService<M, C, U, ID> {

    private R repository;

    public HibernateServiceImpl(R repository) {
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    @TrackTime
    public List<M> getAll() {
        return (List<M>) repository.findAll();
    }

    @Override
    @TrackTime
    public Optional<M> getOne(ID id) {
        return repository.findById(id);
    }

    @Override
    @TrackTime
    public M create(C item) {


        try {

            return repository.save(item.toEntity());
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage(), e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    @TrackTime
    public M update(U item, ID id) {
        if (repository.findById(id).isPresent()) {
            return repository.save(item.toEntity());
        }
        return null;
    }

    @Override
    @TrackTime
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

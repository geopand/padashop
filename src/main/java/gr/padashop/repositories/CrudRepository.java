package gr.padashop.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    public List<T> getAll();

    public void create(T t);

    public void update(T t);

    public void delete(String id);

    public Optional<T> getById(String id);


}

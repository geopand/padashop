package gr.padashop.repositories;

import gr.padashop.models.Category;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductCategoryRepository implements CrudRepository<Category> {

    private final JdbcClient jdbcClient;

    public ProductCategoryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    @Override
    public List<Category> getAll() {
        String sql = "SELECT * FROM categories";
        return jdbcClient.sql(sql).query(Category.class).list();
    }

    @Override
    public void create(Category category) {
        String sql;
        if (category.getParent() == null) {
            sql = "INSERT into categories (name, description, slug) VALUES(?,?,?)";
            jdbcClient.sql(sql).params(List.of(category.getName(), category.getDescription(), category.getSlug())).update();
        } else {
            sql = "INSERT into categories (name, description, parent, slug) VALUES(?,?,?,?)";
            jdbcClient.sql(sql).params(List.of(category.getName(), category.getDescription(), category.getParent(), category.getSlug())).update();
        }

    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE categories set  name= ?, description=?";

        jdbcClient.sql(sql)
                .params(List.of(category.getName(), category.getDescription()))
                .update();

    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM categories where id= :id";
        jdbcClient.sql(sql)
                .param("id", id)
                .update();

    }

    @Override
    public Optional<Category> getById(String id) {
        String sql = "SELECT id, name, description, slug WHERE id= :id";
        return jdbcClient
                .sql(sql)
                .param("id", id)
                .query(Category.class)
                .optional();
    }

}

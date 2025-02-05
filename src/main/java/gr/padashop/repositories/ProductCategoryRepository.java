package gr.padashop.repositories;

import gr.padashop.models.ProductCategory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductCategoryRepository implements CrudRepository<ProductCategory> {

    private final JdbcClient jdbcClient;

    public ProductCategoryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    @Override
    public List<ProductCategory> getAll() {
        String sql = "SELECT * FROM categories";
        return jdbcClient.sql(sql).query(ProductCategory.class).list();
    }

    @Override
    public void create(ProductCategory category) {
        String sql;
        if (category.getParent() == null) {
            sql = "INSERT into categories (name, description) VALUES(?,?)";
            jdbcClient.sql(sql).params(List.of(category.getName(), category.getDescription())).update();
        } else {
            sql = "INSERT into categories (name, description, parent) VALUES(?,?,?)";
            jdbcClient.sql(sql).params(List.of(category.getName(), category.getDescription(), category.getParent())).update();
        }

    }

    @Override
    public void update(ProductCategory category) {
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
    public Optional<ProductCategory> getById(String id) {
        String sql = "SELECT id, name, description WHERE id= :id";
        return jdbcClient
                .sql(sql)
                .param("id", id)
                .query(ProductCategory.class)
                .optional();
    }

}

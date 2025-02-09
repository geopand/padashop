package gr.padashop.repositories;


import gr.padashop.models.Product;
import gr.padashop.models.ProductCategory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProductRepository implements CrudRepository<Product> {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        return jdbcClient.sql(sql).query(Product.class).list();
    }

    //todo get all by product

    //todo getBySlug

    @Override
    public void create(Product product) {

        String sql = "INSERT into products (name, description, slug, category, picture, price) VALUES(?,?,?,?,?,?)";
        jdbcClient.sql(sql).params(List.of(
                product.getName(),
                product.getDescription(),
                product.getSlug(),
                product.getCategory(),
                product.getPicture() == null ? "" : product.getPicture(),
                product.getPrice()))
                .update();
}

@Override
public void update(Product product) {

}

@Override
public void delete(String id) {

}

@Override
public Optional<Product> getById(String id) {
    return Optional.empty();
}
}

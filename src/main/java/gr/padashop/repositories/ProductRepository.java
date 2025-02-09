package gr.padashop.repositories;


import gr.padashop.models.Category;
import gr.padashop.models.Product;
import gr.padashop.models.ProductCategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = """
                SELECT
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    slug,
                    picture,
                    price,
                    status,
                    c.id AS categoryId,
                    c.name AS categoryName,
                    c.description AS categoryDescription,
                    parent
                FROM
                    products AS p
                        LEFT JOIN
                    categories AS c ON p.category = c.id;
                """;
        return jdbcClient.sql(sql).query(new ProductMapper()).list();
    }

    public Optional<Product> getBySlug(String slug) {
        String sql = """
                SELECT
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    slug,
                    picture,
                    price,
                    status,
                    c.id AS categoryId,
                    c.name AS categoryName,
                    c.description AS categoryDescription,
                    parent
                FROM
                    products AS p
                        LEFT JOIN
                    categories AS c ON p.category = c.id
                WHERE slug = :slug
                limit 1;
                """;

        return jdbcClient.sql(sql).param("slug", slug).query(new ProductMapper()).optional();

    }

    public List<Product> getAllByCategoryId(Long categoryId) {
        String sql = """
                SELECT
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    slug,
                    picture,
                    price,
                    status,
                    c.id AS categoryId,
                    c.name AS categoryName,
                    c.description AS categoryDescription,
                    parent
                FROM
                    products AS p
                        LEFT JOIN
                    categories AS c ON p.category = c.id
                WHERE c.id = :categoryId
                """;
        return jdbcClient.sql(sql).param("categoryId", categoryId).query(new ProductMapper()).list();
    }

    //todo get all by product

    //todo getBySlug

    @Override
    public void create(Product product) {

        String sql = "INSERT into products (name, description, slug, category, picture, price) VALUES(?,?,?,?,?,?)";
        jdbcClient.sql(sql).params(List.of(product.getName(), product.getDescription(), product.getSlug(), product.getCategory(), product.getPicture() == null ? "" : product.getPicture(), product.getPrice())).update();
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

    class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p = new Product();
            Category c = new Category();
            p.setCategory(c);
            p.setId(rs.getLong("productId"));
            p.setName(rs.getString("productName"));
            p.setDescription(rs.getString("productDescription"));
            p.setSlug(rs.getString("slug"));
            p.setPicture(rs.getString("picture"));
            p.setPrice(rs.getBigDecimal("price"));
            p.setStatus(rs.getString("status"));
            c.setId(rs.getLong("categoryId"));
            c.setName(rs.getString("categoryName"));
            c.setDescription(rs.getString("categoryDescription"));
            c.setParent(rs.getLong("parent"));

            return p;
        }
    }

}



package gr.padashop.repositories;

import gr.padashop.models.Cart;
import gr.padashop.models.Product;
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
public class CartRepository implements CrudRepository<Cart> {

    private final JdbcClient jdbcClient;

    CartRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Cart> getAll() {
        String sql = """
                 SELECT
                    c.id AS cartId,
                    c.user_id AS userId,
                    c.product_id AS productId,
                    quantity,
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    p.slug AS productSlug,
                    p.brand AS productBrand,
                    p.price AS productPrice,
                    p.stock AS productStock,
                    p.picture AS productPicture
                FROM
                    cart AS c
                        INNER JOIN
                    products AS p ON c.product_id = p.id;
                """;
        return jdbcClient.sql(sql).query(Cart.class).list();
    }

    public List<Cart> getAllByUserId(long userId) {

        String sql = """
                SELECT
                    c.id AS cartId,
                    c.user_id AS userId,
                    c.product_id AS productId,
                    quantity,
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    p.slug AS productSlug,
                    p.brand AS productBrand,
                    p.price AS productPrice,
                    p.stock AS productStock,
                    p.picture AS productPicture
                FROM
                    cart AS c
                        INNER JOIN
                    products AS p ON c.product_id = p.id
                WHERE c.user_id = :userId
                """;

        return jdbcClient.sql(sql).param("userId", userId).query(new CartMapper()).list();

    }

    public int incrementQuantityByUserAndProduct(long userId, long productId) {
        String sql = """
                UPDATE cart
                SET
                    quantity = quantity + 1
                WHERE
                    user_id = :userId AND product_id = :productId;
                """;

       return jdbcClient.sql(sql)
                .param("userId", userId)
                .param("productId", productId)
                .update();

    }

    public int decrementQuantityByUserAndProduct(long userId, long productId) {
        String sql = """
                UPDATE cart
                SET
                    quantity = quantity - 1
                WHERE
                    user_id = :userId AND product_id = :productId;
                """;

        return jdbcClient.sql(sql)
                .param("userId", userId)
                .param("productId", productId)
                .update();
    }

    @Override
    public void create(Cart cart) {
//        String sql = "INSERT into users (first_name, last_name, email, country, street_address,city,region,postal_code)VALUES(?,?,?,?,?,?,?,?)";
//
//        jdbcClient.sql(sql)
//                .params(List.of(user.getfName(), user.getsName(), user.getEmail(), user.getCountry(), user.getStrAddress(), user.getCity(), user.getRegion(), user.getZipCode()))
//                .update();
    }

    @Override
    public void update(Cart cart) {
//        String sql = "UPDATE users set  first_name= ? , last_name=?, email = ?, country = ?, street_address = ?,city = ?" +
//                ",region = ?, postal_code = ?";
//
//        jdbcClient.sql(sql)
//                .params(List.of(user.getfName(), user.getsName(), user.getEmail(), user.getCountry(), user.getStrAddress(), user.getCity(), user.getRegion(), user.getZipCode()))
//                .update();
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM cart where id= :id";

        jdbcClient.sql(sql)
                .param("id", id)
                .update();
    }

    @Override
    public Optional<Cart> getById(String id) {
        String sql = """
                 SELECT
                    c.id AS cartId,
                    c.user_id AS userId,
                    c.product_id AS productId,
                    quantity,
                    p.id AS productId,
                    p.name AS productName,
                    p.description AS productDescription,
                    p.slug AS productSlug,
                    p.brand AS productBrand,
                    p.price AS productPrice,
                    p.stock AS productStock,
                    p.picture AS productPicture
                FROM
                    cart AS c
                        INNER JOIN
                    products AS p ON c.product_id = p.id
                WHERE c.id = :id    ;
                """;
        return jdbcClient
                .sql(sql)
                .param("id", Long.getLong(id))
                .query(Cart.class)
                .optional();
    }

    class CartMapper implements RowMapper<Cart> {

        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart = new Cart();
            Product p = new Product();
            cart.setId(rs.getLong("cartId"));
            cart.setUserId(rs.getLong("userId"));
            cart.setQuantity(rs.getInt("quantity"));
            cart.setProduct(p);

            p.setId(rs.getLong("productId"));
            p.setName(rs.getString("productName"));
            p.setDescription(rs.getString("productDescription"));
            p.setSlug(rs.getString("productSlug"));
            p.setPicture(rs.getString("productPicture"));
            p.setPrice(rs.getBigDecimal("productPrice"));
//            p.setStatus(rs.getString("status"));
            p.setBrand(rs.getString("productBrand"));
            p.setStock(rs.getLong("productStock"));

            return cart;
        }
    }


}

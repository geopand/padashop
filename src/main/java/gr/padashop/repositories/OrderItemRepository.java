package gr.padashop.repositories;

import gr.padashop.models.Category;
import gr.padashop.models.OrderItem;
import gr.padashop.models.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderItemRepository {

    private final JdbcClient jdbcClient;

    public OrderItemRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void createWithOrderId(OrderItem item) {
        String sql = """
                INSERT INTO eshop.order_items
                (order_Id,
                product_id,
                quantity,
                price)
                VALUES(?,?,?,?);
                """;

        jdbcClient.sql(sql)
                .params(List.of(
                        item.getOrderId(),
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getPrice()))
                .update();
    }

    public List<OrderItem> getAllByOrderId(Long orderId) {
        String sql = """
                SELECT
                    order_items.id AS orderItemId,
                    order_items.order_Id AS orderId,
                    order_items.product_id AS productId,
                    order_items.quantity AS quantity,
                    order_items.status AS orderItemStatus,
                    order_items.price AS orderItemPrice,
                    products.brand AS productBrand,
                    products.name AS productName,
                    products.picture AS productPicture,
                    products.slug AS productSlug,
                    products.status AS productStatus,
                    products.stock AS productStock,
                    products.description AS productDescription,
                    c.name AS categoryName,
                    c.id AS categoryId,
                    c.description AS categoryDescription,
                    c.slug AS categorySlug,
                    c.parent AS parent
                FROM
                    eshop.order_items
                        LEFT JOIN
                    eshop.products ON order_items.product_id = products.id
                        LEFT JOIN
                    categories AS c ON products.category = c.id
                WHERE
                    order_Id = :orderId;
                """;

        return jdbcClient.sql(sql)
                .param("orderId", orderId)
                .query(new OrderItemMapper()).list();
    }

    class OrderItemMapper implements RowMapper<OrderItem> {

        @Override
        public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(rs.getLong("orderItemId"));
            orderItem.setOrderId(rs.getLong("orderId"));
            orderItem.setQuantity(rs.getInt("quantity"));
            orderItem.setItemStatus(rs.getString("orderItemStatus"));
            orderItem.setPrice(rs.getBigDecimal("orderItemPrice"));

            Product product = new Product();
            product.setId(rs.getLong("productId"));
            product.setName(rs.getString("productName"));
            product.setDescription(rs.getString("productDescription"));
            product.setSlug(rs.getString("productSlug"));
            product.setPicture(rs.getString("productPicture"));
            product.setPrice(rs.getBigDecimal("orderItemPrice")); //the product price is frozen
            product.setStatus(rs.getString("productStatus"));
            product.setBrand(rs.getString("productBrand"));
            product.setStock(rs.getLong("productStock"));

            Category category = new Category();
            category.setId(rs.getLong("categoryId"));
            category.setName(rs.getString("categoryName"));
            category.setDescription(rs.getString("categoryDescription"));
            category.setSlug(rs.getString("categorySlug"));
            category.setParent(rs.getLong("parent"));

            orderItem.setProduct(product);
            product.setCategory(category);

            return orderItem;
        }
    }


}

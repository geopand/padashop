package gr.padashop.repositories;

import gr.padashop.models.OrderItem;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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


}

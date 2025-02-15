package gr.padashop.repositories;


import gr.padashop.models.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements CrudRepository<Order> {

    private final JdbcClient jdbcClient;

    public OrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public void create(Order order) {
        String sql = """
                INSERT INTO eshop.orders(
                    user_id,
                    ccName,
                    ccType,
                    ccNumber,
                    cc_exp_month,
                    cc_exp_year,
                    ccCVC,
                    street,
                    city,state,
                    zipCode,
                    country
                ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .params(List.of(order.getUserId(), order.getCcName(), order.getCcType(),
                        order.getCcNumber(), order.getCcExpiryMoth(),
                        order.getCcExpiryYear(), order.getCcCVC(), order.getStreet(),
                        order.getCity(), order.getState(), order.getZipCode(), order.getCountry()))
                .update(keyHolder);
        Number orderId = keyHolder.getKey();
    }

    public Number createAndGetId(Order order) {
        String sql = """
                INSERT INTO eshop.orders(
                    user_id,
                    ccName,
                    ccType,
                    ccNumber,
                    cc_exp_month,
                    cc_exp_year,
                    ccCVC,
                    street,
                    city,state,
                    zipCode,
                    country
                ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .params(List.of(order.getUserId(), order.getCcName(), order.getCcType(),
                        order.getCcNumber(), order.getCcExpiryMoth(),
                        order.getCcExpiryYear(), order.getCcCVC(), order.getStreet(),
                        order.getCity(), order.getState(), order.getZipCode(), order.getCountry()))
                .update(keyHolder, "id");

        return keyHolder.getKey();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Optional<Order> getById(String id) {
        return Optional.empty();
    }


    class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();


            return null;
        }
    }
}

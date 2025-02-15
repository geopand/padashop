package gr.padashop.repositories;


import gr.padashop.models.CCType;
import gr.padashop.models.Order;
import gr.padashop.models.User;
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
public class OrderRepository {

    private final JdbcClient jdbcClient;

    public OrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

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


    public Optional<Order> getById(long orderId) {
        String sql = """
                SELECT
                    id,
                    user_id,
                    ccName,
                    ccType,
                    ccNumber,
                    cc_exp_month,
                    cc_exp_year,
                    ccCVC,
                    is_self_pickup,
                    street,
                    city,
                    state,
                    zipCode,
                    country,
                    createdAt,
                    type_name
                FROM
                    eshop.orders
                LEFT JOIN cc_types
                ON ccType = cc_types.type_id
                WHERE id = :orderId ;
                """;
        return jdbcClient
                .sql(sql)
                .param("orderId", orderId)
                .query(new OrderRowMapper())
                .optional();
    }


    class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            CCType ccType = new CCType();
            ccType.setId(rs.getLong("ccType"));
            ccType.setName(rs.getString("type_name"));

            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setCcName(rs.getString("ccName"));
            order.setCcNumber(rs.getString("ccNumber"));
            order.setCcType(ccType);
            order.setCcExpiryMoth(rs.getInt("cc_exp_month"));
            order.setCcExpiryMoth(rs.getInt("cc_exp_year"));
            order.setCcCVC(rs.getInt("ccCVC"));
            order.setSelfPickUp(false);
            order.setStreet(rs.getString("street"));
            order.setCity(rs.getString("city"));
            order.setState(rs.getString("state"));
            order.setZipCode(rs.getString("zipCode"));
            order.setCountry(rs.getString("country"));

            return order;
        }
    }
}

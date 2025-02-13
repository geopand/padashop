package gr.padashop.repositories;


import gr.padashop.models.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
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

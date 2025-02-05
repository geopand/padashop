package gr.padashop.repositories;


import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductRepository {

    private final JdbcClient jdbcClient;

    ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
}

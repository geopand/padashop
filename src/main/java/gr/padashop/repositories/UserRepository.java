package gr.padashop.repositories;

import gr.padashop.models.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class UserRepository implements CrudRepository<User> {

    private final JdbcClient jdbcClient;

    UserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return jdbcClient.sql(sql).query(User.class).list();
    }

    @Override
    public void create(User user) {
        String sql = "INSERT into users (first_name, last_name, email, country, street_address,city,region,postal_code)VALUES(?,?,?,?,?,?,?,?)";

        jdbcClient.sql(sql)
                .params(List.of(user.getfName(), user.getsName(), user.getEmail(), user.getCountry(), user.getStrAddress(), user.getCity(), user.getRegion(), user.getZipCode()))
                .update();
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users set  first_name= ? , last_name=?, email = ?, country = ?, street_address = ?,city = ?" +
                ",region = ?, postal_code = ?";

        jdbcClient.sql(sql)
                .params(List.of(user.getfName(), user.getsName(), user.getEmail(), user.getCountry(), user.getStrAddress(), user.getCity(), user.getRegion(), user.getZipCode()))
                .update();
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM users where id= :id";

        jdbcClient.sql(sql)
                .param("id", id)
                .update();
    }

    @Override
    public Optional<User> getById(String id) {
        String sql = "SELECT id, first_name, last_name, email, country, street_address,city,region,postal_code WHERE id= :id";
        return jdbcClient
                .sql(sql)
                .param("id", id)
                .query(User.class)
                .optional();
    }


}

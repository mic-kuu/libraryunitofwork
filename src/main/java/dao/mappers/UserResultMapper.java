package dao.mappers;

import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultMapper implements ResultSetMapper<User> {

    public User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setCard(rs.getString("card"));
        return u;
    }
}

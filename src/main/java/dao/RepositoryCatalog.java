package dao;

import dao.mappers.UserResultMapper;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class RepositoryCatalog {

    Connection connection;

    public RepositoryCatalog(Connection connection){
        this.connection = connection;
    }

    public Repository<User> users(){
        try {
            return new UserRepository(connection, new UserResultMapper());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

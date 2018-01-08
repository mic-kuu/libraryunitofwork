package dao;

import dao.mappers.ResultSetMapper;
import dao.mappers.UserResultMapper;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class UserRepository extends RepositoryBase<User> {
    public UserRepository(Connection connection, ResultSetMapper<User> mapper) throws SQLException {
        super(connection, mapper);
    }

    protected void setupUpdate(User user) throws SQLException {

    }

    protected void setupInsert(User person) throws SQLException {

    }

    protected String tableName() {
        return null;
    }

    protected String createTableSql() {
        return null;
    }

    protected String updateSql() {
        return null;
    }

    protected String insertSql() {
        return null;
    }
}

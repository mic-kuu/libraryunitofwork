package dao;

import dao.mappers.ResultSetMapper;
import dao.uow.UnitOfWork;
import domain.Library;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class LibraryRepository extends RepositoryBase<Library> {
    private static final String TABLE_NAME = "libraries";


    public LibraryRepository(Connection connection, ResultSetMapper<Library> mapper, UnitOfWork uow) throws SQLException {
        super(connection, mapper, uow);
    }

    protected void setupUpdate(Library library) throws SQLException {
        update.setString(1, library.getName());
        update.setString(2, library.getType());
        update.setInt(3, library.getId());
    }

    protected void setupInsert(Library library) throws SQLException {
        insert.setString(1, library.getName());
        insert.setString(2, library.getType());
    }

    protected String tableName() {
        return TABLE_NAME;
    }

    protected String createTableSql() {
        return "CREATE TABLE " + tableName() + "(" +
                "id INT GENERATED BY DEFAULT AS IDENTITY, " +
                "name VARCHAR(50), " +
                "type VARCHAR(50)" + ")";
    }

    protected String updateSql() {
        return "UPDATE " + tableName() + " SET (name, type) = (?, ?)" +
                " WHERE id = ?";
    }

    protected String insertSql() {
        return "INSERT INTO " + tableName() + "(name, type) VALUES(?, ?)";
    }

}

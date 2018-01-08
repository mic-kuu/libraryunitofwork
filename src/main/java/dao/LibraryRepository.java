package dao;

import dao.mappers.ResultSetMapper;
import domain.Library;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class LibraryRepository extends RepositoryBase<Library> {

    public LibraryRepository(Connection connection, ResultSetMapper<Library> mapper) throws SQLException {
        super(connection, mapper);
    }

    protected void setupUpdate(Library library) throws SQLException {

    }

    protected void setupInsert(Library person) throws SQLException {

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

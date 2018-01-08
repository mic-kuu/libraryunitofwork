package dao;

import dao.mappers.ResultSetMapper;
import domain.Book;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class BookRepository extends RepositoryBase<Book> {

    public BookRepository(Connection connection, ResultSetMapper<Book> mapper) throws SQLException {
        super(connection, mapper);
    }

    protected void setupUpdate(Book book) throws SQLException {

    }

    protected void setupInsert(Book person) throws SQLException {

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

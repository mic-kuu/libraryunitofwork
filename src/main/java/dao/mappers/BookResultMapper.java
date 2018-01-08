package dao.mappers;

import domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookResultMapper implements ResultSetMapper<Book> {

    public Book map(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setId(rs.getInt("id"));
        b.setAuthorFirstName(rs.getString("author_first_name"));
        b.setAuthorLastName(rs.getString("author_last_name"));
        b.setISBN(rs.getString("ISBN"));
        b.setTitle(rs.getString("title"));
        return b;
    }
}

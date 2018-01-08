package dao;

import dao.mappers.BookResultMapper;
import dao.mappers.LibraryResultMapper;
import dao.mappers.UserResultMapper;
import domain.Book;
import domain.Library;
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

    public Repository<Book> books(){
        try {
            return new BookRepository(connection, new BookResultMapper());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Repository<Library> libraries(){
        try {
            return new LibraryRepository(connection, new LibraryResultMapper());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package dao;

import dao.mappers.BookResultMapper;
import dao.mappers.LibraryResultMapper;
import dao.mappers.UserResultMapper;
import dao.uow.UnitOfWork;
import domain.Book;
import domain.Library;
import domain.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Michal on 08.01.2018.
 */
public class JdbcRepositoryCatalog implements RepositoryCatalog {

    Connection connection;
    UnitOfWork uow;

    public JdbcRepositoryCatalog(Connection connection, UnitOfWork uow){
        this.connection = connection;
        this.uow = uow;
    }

    public Repository<User> users(){
        try {
            return new UserRepository(connection, new UserResultMapper(), uow);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Repository<Book> books(){
        try {
            return new BookRepository(connection, new BookResultMapper(), uow);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Repository<Library> libraries(){
        try {
            return new LibraryRepository(connection, new LibraryResultMapper(), uow);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveChanges(){
        uow.saveChanges();
    }
}

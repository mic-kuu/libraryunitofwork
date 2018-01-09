package dao;

import domain.Book;
import domain.Library;
import domain.User;

/**
 * Created by Michal on 09.01.2018.
 */
public interface RepositoryCatalog {
    Repository<User> users();

    Repository<Book> books();

    Repository<Library> libraries();

    void saveChanges();
}

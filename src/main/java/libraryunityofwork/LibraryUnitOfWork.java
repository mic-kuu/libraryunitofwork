package libraryunityofwork;

import dao.JdbcCatalogFactory;
import dao.RepositoryCatalog;
import domain.User;

public class LibraryUnitOfWork {

    public static void main( String[] args ) {

        User jankowalski = new User();

        jankowalski.setFirstName("Jan");
        jankowalski.setLastName("Kowalski");

        RepositoryCatalog workdb = new JdbcCatalogFactory().HsqlDbWorkDb();


        workdb.users().add(jankowalski);

        workdb.saveChanges();
    }

}

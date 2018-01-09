package dao;

import dao.uow.JdbcUnitOfWork;
import dao.uow.UnitOfWork;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Michal on 09.01.2018.
 */
public class JdbcCatalogFactory implements DbCatalogFactory {

    public RepositoryCatalog HsqlDbWorkDb() {
        String url = "jdbc:hsqldb:hsql://localhost/workdb";
        try {
            Connection connection = DriverManager.getConnection(url);
            UnitOfWork uow = new JdbcUnitOfWork(connection);
            return new JdbcRepositoryCatalog(connection, uow);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}

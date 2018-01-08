package dao;

/**
 * Created by Michal on 08.01.2018.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.IHaveID;
import dao.mappers.ResultSetMapper;

public abstract class RepositoryBase<TEntity extends IHaveID> implements Repository<TEntity> {


    protected Connection connection;
    protected Statement createTable;
    protected PreparedStatement insert;
    protected PreparedStatement selectAll;
    protected PreparedStatement update;
    protected PreparedStatement delete;


    ResultSetMapper<TEntity> mapper;

    protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper) throws SQLException {
        this.mapper = mapper;
        this.connection = connection;

        createTable = connection.createStatement();
        insert = connection.prepareStatement(insertSql());
        update = connection.prepareStatement(updateSql());
        delete = connection.prepareStatement(deleteSql());
        selectAll = connection.prepareStatement(selectAllSql());
    }

    public List<TEntity> getAll() {
        List<TEntity> result = new ArrayList<TEntity>();
        try {
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                result.add(mapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void createTable() {
        try {

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()) {
                if (rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())) {
                    tableExists = true;
                    break;
                }
            }
            if (!tableExists)
                createTable.executeUpdate(createTableSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String selectAllSql() {
        return "SELECT * FROM " + tableName();
    }

    protected String deleteSql() {
        return "DELETE FROM "
                + tableName()
                + " WHERE id=?";
    }


    public void add(TEntity entity) {

        try {
            setupInsert(entity);
            insert.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void update(TEntity entity) {

        try {
            setupUpdate(entity);
            update.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(TEntity entity) {

        try {
            delete.setInt(1, entity.getId());
            delete.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    protected abstract void setupUpdate(TEntity entity) throws SQLException;

    protected abstract void setupInsert(TEntity person) throws SQLException;

    protected abstract String tableName();

    protected abstract String createTableSql();

    protected abstract String updateSql();

    protected abstract String insertSql();
}

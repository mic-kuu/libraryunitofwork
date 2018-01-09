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

import dao.uow.Entity;
import dao.uow.UnitOfWork;
import dao.uow.UnitOfWorkRepository;
import domain.IHaveID;
import dao.mappers.ResultSetMapper;

public abstract class RepositoryBase<TEntity extends IHaveID> implements Repository<TEntity>, UnitOfWorkRepository {

    protected Connection connection;
    protected Statement createTable;
    protected PreparedStatement insert;
    protected PreparedStatement selectAll;
    protected PreparedStatement update;
    protected PreparedStatement delete;

    ResultSetMapper<TEntity> mapper;
    UnitOfWork uow;

    protected RepositoryBase(Connection connection, ResultSetMapper<TEntity> mapper, UnitOfWork uow) throws SQLException {
        this.mapper = mapper;
        this.connection = connection;
        this.uow = uow;

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

    public void persistAdd(Entity entity) {
        try {
            setupInsert((TEntity) entity.getEntity());
            insert.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void persistUpdate(Entity entity) {
        try {
            setupUpdate((TEntity) entity.getEntity());
            update.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void persistDelete(Entity entity) {
        try {
            delete.setInt(1, entity.getEntity().getId());
            delete.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void add(TEntity entity) {
        Entity ent = new Entity();
        ent.setEntity(entity);
        uow.markAsNew(ent);
    }

    public void update(TEntity entity) {
        Entity ent = new Entity();
        ent.setEntity(entity);
        uow.markAsChanged(ent);
    }

    public void delete(TEntity entity) {
        Entity ent = new Entity();
        ent.setEntity(entity);
        uow.markAsDeleted(ent);
    }


    protected abstract void setupUpdate(TEntity entity) throws SQLException;

    protected abstract void setupInsert(TEntity person) throws SQLException;

    protected abstract String tableName();

    protected abstract String createTableSql();

    protected abstract String updateSql();

    protected abstract String insertSql();
}

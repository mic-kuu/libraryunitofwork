package dao.uow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 08.01.2018.
 */
public class JdbcUnitOfWork implements UnitOfWork {

    private Connection connection;
    private List<Entity> entities = new ArrayList<Entity>();

    public JdbcUnitOfWork(Connection connection) throws SQLException {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    public void markAsNew(Entity entity) {
        entity.setState(EntityState.New);
        entities.add(entity);
    }

    public void markAsDeleted(Entity entity) {
        entity.setState(EntityState.Deleted);
        entities.add(entity);
    }

    public void markAsChanged(Entity entity) {
        entity.setState(EntityState.Changed);
        entities.add(entity);
    }

    public void saveChanges() {
        for (Entity entity : entities) {
            entity.persistChange();

            try {
                connection.commit();
                entities.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback() {
        entities.clear();
    }
}

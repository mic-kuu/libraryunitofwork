package dao.uow;

/**
 * Created by Michal on 08.01.2018.
 */
public interface UnitOfWork {
    void markAsNew(Entity entity);
    void markAsDeleted(Entity entity);
    void markAsChanged(Entity entity);
    void saveChanges();
    void rollback();
}

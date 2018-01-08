package dao.uow;

/**
 * Created by Michal on 08.01.2018.
 */
public interface UnitOfWorkRepository {
    void persistAdd(Entity entity);
    void persistUpdate(Entity entity);
    void persistDelete(Entity entity);
}


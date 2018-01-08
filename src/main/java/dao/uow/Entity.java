package dao.uow;

import domain.IHaveID;

/**
 * Created by Michal on 08.01.2018.
 */
public class Entity {
    private IHaveID entity;
    private UnitOfWorkRepository repository;
    private EntityState state;

    public UnitOfWorkRepository getRepository() {
        return repository;
    }

    public void setRepository(UnitOfWorkRepository repository) {
        this.repository = repository;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    public IHaveID getEntity() {
        return entity;
    }

    public void setEntity(IHaveID entity) {
        this.entity = entity;
    }

    public void persistChange() {
        switch (state) {
            case Changed:
                repository.persistUpdate(this);
                break;
            case Deleted:
                repository.persistDelete(this);
                break;
            case New:
                System.out.println(this.toString().length());
                repository.persistDelete(this);
                break;
            default:
                break;
        }
    }

}


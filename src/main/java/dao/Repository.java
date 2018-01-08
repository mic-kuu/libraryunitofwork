package dao;

/**
 * Created by Michal on 08.01.2018.
 */

import java.util.List;

import domain.IHaveID;

public interface Repository<TEntity extends IHaveID> {

    void delete(TEntity entity);

    List<TEntity> getAll();

    void add(TEntity entity);

    void update(TEntity entity);

    void createTable();

}
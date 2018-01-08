package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<TEntity> {

    TEntity map(ResultSet rs) throws SQLException;
}

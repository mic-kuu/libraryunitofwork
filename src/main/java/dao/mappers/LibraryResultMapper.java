package dao.mappers;

import domain.Library;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryResultMapper implements ResultSetMapper<Library> {

    public Library map(ResultSet rs) throws SQLException {
        Library l = new Library();
        l.setId(rs.getInt("id"));
        l.setName(rs.getString("name"));
        l.setType(rs.getString("type"));
        return l;
    }
}


package next.dao;

import core.jdbc.ConnectionManager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rm) throws SQLException {
        //Connection con = null;
        //PreparedStatement pstmt = null;
        List<T> objs = query(sql,pss,rm);
        return objs.get(0);
    }

    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rm) throws SQLException {
        List<T> objs = new ArrayList<>();
        //Connection con = null;
        //PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pss.setValues(pstmt);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                objs.add(rm.mapRow(rs));
            }

            return objs;

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void update(String sql, PreparedStatementSetter pss) {

        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

            pss.setValues(pstmt);
            pstmt.executeUpdate();

        }
        catch( SQLException e) {
            throw new DataAccessException(e);
        }
    }
}

    //protected abstract String createQuery();
    //public abstract void setValues(PreparedStatement pstmt) throws SQLException;
    //public abstract Object mapRow(ResultSet rs) throws SQLException;


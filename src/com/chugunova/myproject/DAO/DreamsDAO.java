package com.chugunova.myproject.DAO;

import com.chugunova.myproject.mapper.DreamsMapper;
import com.chugunova.myproject.model.Dream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DreamsDAO extends JdbcDaoSupport {
    @Autowired
    public DreamsDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Dream> getUserDreams(String username) {
        String sql = DreamsMapper.GET_ALL_USER_DREAMS;
        Object[] params = new Object[]{username};

        assert this.getJdbcTemplate() != null;

        return this.getJdbcTemplate().query(sql, params, (resultSet, i) -> {
            Integer dreamId = resultSet.getInt("dreamId");
            String dreamName = resultSet.getString("dreamName");
            String dreamDate = resultSet.getString("dreamDate");
            String dreamText = resultSet.getString("dreamText");
            Double dreamDuration = resultSet.getDouble("dreamDuration");
            return new Dream(dreamId, dreamName, dreamDate, dreamText, dreamDuration);
        });
    }

    public void addUserDreams(String dreamName, String dreamText, String username, Double dreamDuration) {
        String sql = DreamsMapper.ADD_NEW_DREAM;
        Object[] params = new Object[]{dreamName, dreamText, username, dreamDuration};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " dreams send");
    }

    public void deleteUserDreams(Integer dreamId) {
        String sql = DreamsMapper.DELETE_DREAM;
        Object[] params = new Object[]{dreamId};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " dreams delete");
    }

}

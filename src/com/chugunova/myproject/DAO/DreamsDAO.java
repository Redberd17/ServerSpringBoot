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
            String dreamName = resultSet.getString("dreamName");
            String dreamDate = resultSet.getString("dreamDate");
            String dreamText = resultSet.getString("dreamText");
            Double dreamDuration = resultSet.getDouble("dreamDuration");
            return new Dream(dreamName, dreamDate, dreamText, dreamDuration);
        });
    }
}

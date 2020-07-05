package com.chugunova.myproject.DAO;

import com.chugunova.myproject.mapper.AdviceDurationMapper;
import com.chugunova.myproject.model.AdviceDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdviceDurDAO extends JdbcDaoSupport {
    @Autowired
    public AdviceDurDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<AdviceDuration> getAdviceDur() {
        String sql = AdviceDurationMapper.GET_ADVICE_DURATION;
        Object[] params = new Object[]{};
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().query(sql, params, (resultSet, i) -> {
                String adviceDurName = resultSet.getString("adviceDurName");
                Integer adviceDurValue = resultSet.getInt("adviceDurValue");
                String adviceDurText = resultSet.getString("adviceDurText");
                Integer adviceDurGrade = resultSet.getInt("adviceDurGrade");
                return new AdviceDuration(adviceDurName, adviceDurValue, adviceDurText, adviceDurGrade);
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

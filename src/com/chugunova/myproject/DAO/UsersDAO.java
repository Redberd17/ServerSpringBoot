package com.chugunova.myproject.DAO;

import com.chugunova.myproject.mapper.UserMapper;
import com.chugunova.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UsersDAO extends JdbcDaoSupport {
    @Autowired
    public UsersDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public User getUser(String username) {
        String sql = UserMapper.GET_USER_BY_USERNAME;
        Object[] params = new Object[]{username};
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, (resultSet, i) -> {
                String userName = resultSet.getString("userName");
                return new User(userName);
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}

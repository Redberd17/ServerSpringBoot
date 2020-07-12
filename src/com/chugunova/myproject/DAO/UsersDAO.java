package com.chugunova.myproject.DAO;

import com.chugunova.myproject.mapper.UserMapper;
import com.chugunova.myproject.model.Role;
import com.chugunova.myproject.model.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UsersDAO extends JdbcDaoSupport {
    @Autowired
    public UsersDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public UserSecurity userForSecurity(String login) {
        String sql = UserMapper.USER_FOR_SPRING_SECURITY;
        Object[] params = new Object[]{login};
        try {
            assert this.getJdbcTemplate() != null;
            return this.getJdbcTemplate().queryForObject(sql, params, (resultSet, i) -> {
                String username = resultSet.getString("login");
                List<Role> role = new ArrayList<>();
                Role role1 = new Role(resultSet.getString("role"));
                role.add(role1);
                String password = resultSet.getString("password");
                return new UserSecurity(username, password, role);
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addNewUser(String username, String password) {
        String sql = UserMapper.USER_FOR_REGISTRATION;
        Object[] params = new Object[]{username, password};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " user was added");
    }
}

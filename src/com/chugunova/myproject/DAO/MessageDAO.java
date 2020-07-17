package com.chugunova.myproject.DAO;

import com.chugunova.myproject.mapper.MessageMapper;
import com.chugunova.myproject.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MessageDAO extends JdbcDaoSupport {
    @Autowired
    public MessageDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Message> getAllMessage() {
        String sql = MessageMapper.GET_ALL_MESSAGE;
        Object[] params = new Object[]{};

        assert this.getJdbcTemplate() != null;

        return this.getJdbcTemplate().query(sql, params, (resultSet, i) -> {
            String mesFrom = resultSet.getString("mesFrom");
            String mesText = resultSet.getString("mesText");
            String mesDateTime = resultSet.getString("mesDateTime");
            return new Message(mesFrom, mesText, mesDateTime);
        });
    }

    public void sendMessage(String mesFrom, String mesText) {
        String sql = MessageMapper.SEND_MESSAGE;
        Object[] params = new Object[]{mesFrom, mesText};
        assert this.getJdbcTemplate() != null;
        int rows = this.getJdbcTemplate().update(sql, params);
        System.out.println(rows + " message send");
    }

}

package org.example.controller;

import org.example.dao.entity.UserEntity;
import org.example.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/list1")
    public List<UserEntity> getUserListByJpa(){
        return this.userRepository.findAll();
    }

    @GetMapping("/list2")
    public List<UserEntity> getUserListByJdbc(){
        return this.jdbcTemplate.query("select id, username, mobile, email from t_user", new UserRowMapper());
    }

    static class UserRowMapper implements RowMapper<UserEntity> {
        @Override
        public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserEntity user = new UserEntity();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

}

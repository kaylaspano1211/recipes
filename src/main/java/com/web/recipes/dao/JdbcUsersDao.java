package com.web.recipes.dao;

import com.web.recipes.model.Images;
import com.web.recipes.model.Users;
import com.web.recipes.security.UsernameNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcUsersDao implements UsersDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcUsersDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Users addUser(Users user) {
        return null;
    }

    @Override
    public Users getUserById(int id) {
        return null;
    }

    @Override
    public int findIdByUsername(String username) throws UsernameNotFoundException {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        int userId;
        try {
            userId = jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User " + username + " was not found.");
        }

        return userId;
    }


    @Override
    public List<Users> getAllUsers() {
        return null;
    }

    @Override
    public Users deleteUser(int id) {
        return null;
    }

    public Users mapRowToUsers(SqlRowSet result) {
        Users user = new Users();

        user.setUserId(result.getInt("user_id"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));

        return user;
    }
}

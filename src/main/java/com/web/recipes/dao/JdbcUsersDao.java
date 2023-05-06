package com.web.recipes.dao;

import com.web.recipes.model.Users;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class JdbcUsersDao implements UsersDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcUsersDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?,?)";
        String password_hash = new BCryptPasswordEncoder().encode(password);

        return jdbcTemplate.update(sql, username, password_hash) == 1;
    }

    @Override
    public Users getUserById(int id) {
        String sql = "SELECT user_id, username, password_hash FROM users WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToUsers(results);
        } else {
            return null;
        }
    }

    @Override
    public Users findByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");

        for (Users user : this.getAllUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public int findIdByUsername(String username) {
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
        List<Users> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Users user = mapRowToUsers(results);
            users.add(user);
        }
        return users;
    }



    public Users mapRowToUsers(SqlRowSet result) {
        Users user = new Users();

        user.setUserId(result.getInt("user_id"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));

        return user;
    }
}

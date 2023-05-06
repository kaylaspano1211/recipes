package com.web.recipes.dao;

import com.web.recipes.model.Users;

import java.util.List;

public interface UsersDao {

    Users getUserById(int id);

    Users findByUsername(String username);

    int findIdByUsername(String username);

    List<Users> getAllUsers();

    boolean addUser(String username, String password, String role);
}

package com.web.recipes.dao;

import com.web.recipes.model.Users;
import com.web.recipes.security.UsernameNotFoundException;

import java.util.List;

public interface UsersDao {

    Users addUser(Users user);

    Users getUserById(int id);

    int findIdByUsername(String username) throws UsernameNotFoundException;

    List<Users> getAllUsers();

    Users deleteUser(int id);
}

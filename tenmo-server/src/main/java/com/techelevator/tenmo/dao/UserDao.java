package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao
{

    List<User> findAll();

    User getUserById(int id);

    User findByUsername(String username);

    //principal
    int findIdByUsername(String username);

    BigDecimal getCurrentBalance();

    boolean create(String username, String password);

    BigDecimal getCurrentBalance();
}

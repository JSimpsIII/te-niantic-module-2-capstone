package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController
{
    private UserDao userDao;

    public UserController(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void balance(@PathVariable int id)
    {
        userDao.getCurrentBalance();
    }
}

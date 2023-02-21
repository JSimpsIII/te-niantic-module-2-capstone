package com.techelevator.tenmo.controller;

<<<<<<< HEAD
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
=======

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
>>>>>>> kayla
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
<<<<<<< HEAD
public class UserController
{
=======
public class UserController {

>>>>>>> kayla
    private UserDao userDao;

    public UserController(UserDao userDao)
    {
        this.userDao = userDao;
    }

<<<<<<< HEAD
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void balance(@PathVariable int id)
    {
        userDao.getCurrentBalance();
    }
=======
    @GetMapping()
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        users = userDao.findAll();
        return users;
    }


//    @GetMapping("account/{id}")
//    public Account getAccountById(@PathVariable int id){
//
//        Account account = accountDao.getAccountById(id);
//        return account;
//    }

>>>>>>> kayla
}

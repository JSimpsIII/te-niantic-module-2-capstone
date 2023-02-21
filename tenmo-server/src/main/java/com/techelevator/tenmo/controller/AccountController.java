package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
//@RequestMapping(value = "/account" )
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping("accounts")
    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }


//    @GetMapping("accounts/{name})
//    public List<Account> getAccountByUsername(){
//        return accountDao.getAllAccounts();
//    }

    @GetMapping("account/{name}/balance")
    public BigDecimal getBalance(@PathVariable String name, Principal principal){
        var userName = principal.getName();
        int id = userDao.findIdByUsername(userName);
        Account account = accountDao.getAccountByUserId(id);
        BigDecimal balance = account.getBalance();
        return balance;

    }

    //get account by userId
    @GetMapping("account/{id}")
    public Account getAccountById(@PathVariable int id){

       Account account = accountDao.getAccountByUserId(id);
       return account;
    }

//    @RequestMapping(path = "/account/{id}", method = RequestMethod.PUT)
//    public Account update(@PathVariable int id) {
//        Account updatedAccount = accountDao.getAccountByUserId(id);
//        updatedAccount.addBalance(money);
//        return updatedAccount;
//    }




}

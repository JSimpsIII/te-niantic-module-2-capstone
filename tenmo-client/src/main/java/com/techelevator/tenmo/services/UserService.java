package com.techelevator.tenmo.services;

<<<<<<< HEAD
import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.User;
=======
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.User;
import com.techelevator.util.BasicLogger;
>>>>>>> kayla
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
<<<<<<< HEAD
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserService extends AuthenticatedApiService<User>{


    public BigDecimal getCurrentBalance(){

        BigDecimal balance;

        var url = baseUrl + "account";
        var entity = makeAuthEntity();
        ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, entity, Account.class);
        balance = Objects.requireNonNull(response.getBody()).getBalance();

        return balance;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService extends AuthenticatedApiService<User> {

//    public UserService(String url) {
//        super(url);
//    }

    public List<User> getAllUsers(){

        List<User> userList = new ArrayList<>();

        try{
            String url = baseUrl + "users";
            var entity = makeAuthEntity();
            ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);
            userList = Arrays.asList(response.getBody());

        }catch (Exception ex){
            userList = new ArrayList<>();
            BasicLogger.log(ex.getMessage());
        }
        return userList;
>>>>>>> kayla
    }
}

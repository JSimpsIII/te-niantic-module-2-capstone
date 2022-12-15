package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
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
    }
}

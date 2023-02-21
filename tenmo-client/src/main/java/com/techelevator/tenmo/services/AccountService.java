package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import java.math.BigDecimal;

public class AccountService extends AuthenticatedApiService<Account>{


    public BigDecimal getCurrentBalance(String userName){
        try{
            var url = baseUrl + "account/" + userName + "/balance";
            //var url = baseUrl + "account/" + userName;

            var entity = makeAuthEntity();
            ResponseEntity<BigDecimal> response = restTemplate.exchange(url, HttpMethod.GET, entity,BigDecimal.class);
            //Account account = response.getBody();
            //return account.getBalance();
           // System.out.println("balance" + response.getBody().getBalance());
            return response.getBody();

        }catch (Exception ex){
            BasicLogger.log(ex.getMessage());
        }
        return null;

    }

    //get account by userId
    public Account getAccountById(int id){

        try{
            var url = baseUrl + "account/" + id;
            var entity = makeAuthEntity();
            ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, entity, Account.class);

            return response.getBody();

        }catch (Exception ex){
            BasicLogger.log(ex.getMessage());
        }
        return null;
    }

//    public void sendMoney(int id, BigDecimal money) {
//        Account accountDto = new Account();
//        accountDto.setBalance(getAccountById(id).getBalance().add(money));
//        try {
//            var url = baseUrl + "account/" + id;
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<Account> entity = new HttpEntity<>(accountDto, headers);
//            ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Account.class);
//        } catch (Exception ex) {
//            BasicLogger.log(ex.getMessage());
//        }
//    }
}


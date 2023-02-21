package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    Account getAccountById(int id);
    Account getAccountByUserId(int userId);
    List<Account> getAllAccounts();
    BigDecimal getCurrentBalance(int userId);
//    BigDecimal accountUpdate(int userId, BigDecimal money);
    void updateBalance(Transfer transfer);
}

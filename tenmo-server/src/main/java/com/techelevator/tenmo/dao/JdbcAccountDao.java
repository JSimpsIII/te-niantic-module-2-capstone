package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Account> getAllAccounts() {

        var accounts = new ArrayList<Account>();

        String sql = "select user_id, account_id, balance \n" +
                "from account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Account account =  mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int id) {
        String sql = "select user_id, account_id, balance \n" +
                "from account\n" +
                "where account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            return mapRowToAccount(results);
        }
        return null;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        String sql = "select a.user_id, a.account_id, a.balance from account as a\n" +
                "join tenmo_user as tu on a.user_id = tu.user_id\n" +
                "where a.user_id = ?;\n ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()){
            return mapRowToAccount(results);
        }
        return null;
    }

//    @Override
//    public BigDecimal accountUpdate(int userID, BigDecimal money) {
//        Account account = getAccountByUserId(userID);
//        account.addBalance(money);
//        return account.getBalance();
//    }

    @Override
    public BigDecimal getCurrentBalance(int userId) {
        Account account;
        String sql = "select a.balance from account as a\n" +
                "join tenmo_user as tu on a.user_id = tu.user_id\n" +
                "where a.user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()){
            account = mapRowToAccount(results);
            return account.getBalance();
        }
        return null;
    }

    @Override
    public void updateBalance(Transfer transfer) {

        String transferSql = "SELECT * FROM account WHERE account_id = ?;";
        BigDecimal transferBalance = transfer.getAmount();
        BigDecimal fromBalance = BigDecimal.ZERO;
        BigDecimal toBalance = BigDecimal.ZERO;

        //get send from account balance
        SqlRowSet resultFrom = jdbcTemplate.queryForRowSet(transferSql, transfer.getAccountFrom());
        while(resultFrom.next()){
            fromBalance = resultFrom.getBigDecimal("balance");
        }

        //get receive to account balance
        SqlRowSet resultTo = jdbcTemplate.queryForRowSet(transferSql, transfer.getAccountTo());
        while (resultTo.next()){
            toBalance = resultTo.getBigDecimal("balance");
        }

        //calculate both accounts balance
        fromBalance = fromBalance.subtract(transferBalance);
        toBalance = toBalance.add(transferBalance);

        //update accounts balance
        String updateSql = "UPDATE account SET balance = ? WHERE account_id = ?;";

        //accountFrom
        jdbcTemplate.update(updateSql, fromBalance, transfer.getAccountFrom());
        //accountTo
        //String sqlTransferTo = "UPDATE account SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(updateSql, toBalance, transfer.getAccountTo());

    }


    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setUserId(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }


}

package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Transfer> findAll()
    {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next())
        {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }

        return transfers;
    }

    @Override
    public Transfer createTransfer(int transfer_type_id, int transfer_status_id, int account_from, int account_to, BigDecimal amount) {

        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?) RETURNING transfer_id;";

        //jdbcTemplate.update(sql, transfer_type_id, transfer_status_id, account_from, account_to, amount);
        //get new transfer_id
        var transferId = jdbcTemplate.queryForObject(sql, Integer.class, transfer_type_id, transfer_status_id, account_from, account_to, amount);

        //jdbcTemplate.queryForRowSet()

        Transfer transferNew = new Transfer(transferId, transfer_type_id, transfer_status_id, account_from, account_to, amount);

        transferNew.setTransferId(transferId);

        return transferNew;
    }

    @Override
    public Transfer viewTransfer(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        if (result.next()){
            transfer = mapRowToTransfer(result);
        }
        return transfer;
    }

    @Override
    public List<Transfer> getMyTransfer(int accountId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE account_to = ? OR account_from = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
        while(results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
    }


    private Transfer mapRowToTransfer(SqlRowSet rs)
    {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferTypeId(rs.getInt("transfer_type_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}


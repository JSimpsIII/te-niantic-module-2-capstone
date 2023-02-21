package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;


public interface TransferDao {

   List<Transfer> findAll();

   Transfer createTransfer(int transferTypeId, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount);

   Transfer viewTransfer(int transferId);

   List<Transfer> getMyTransfer(int accountId);
}

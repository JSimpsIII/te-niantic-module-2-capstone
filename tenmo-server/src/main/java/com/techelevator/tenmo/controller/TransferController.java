package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private TransferDao transferDao;
    private AccountDao accountDao;


    public TransferController(TransferDao transferDao, AccountDao accountDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "new-transfer", method = RequestMethod.POST)
    public Transfer newTransfer(@RequestBody Transfer transfer){
        //create new transfer
        Transfer newTransfer = transferDao.createTransfer(transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
        //update balance
       accountDao.updateBalance(newTransfer);

        return newTransfer;
    }

    @RequestMapping(path = "/transfers", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(){
        List<Transfer> transfers = new ArrayList<>();
        transfers = transferDao.findAll();
        return transfers;
    }

    @RequestMapping(path = "/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable("id") int transferId){
        return transferDao.viewTransfer(transferId);

    }

    @RequestMapping(path = "/transfers/my_transfer/{id}", method = RequestMethod.GET)
    public List<Transfer> getMyTransfers(@PathVariable("id") int accountId){
        return transferDao.getMyTransfer(accountId);
    }


//    public void createSendTransfer(int transferUserId, BigDecimal transferBalance){
//        BigDecimal currentUserBalance = getCurrentBalance();
//        int currentUserId = currentUser.getUser().getId();
//
//        if(transferUserId != currentUser.getUser().getId()){
//            if(currentUserBalance.compareTo(transferBalance) > 0){
//                Transfer transfer = new Transfer(1, 1,currentUserId , transferUserId, transferBalance);
//            }else{
//                System.out.println("You don't have enough money to send");
//            }
//        }else{
//            System.out.println("You can not send money to yourself.");
//        }


}

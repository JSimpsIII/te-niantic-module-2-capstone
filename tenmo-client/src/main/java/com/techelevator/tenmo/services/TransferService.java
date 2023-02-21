package com.techelevator.tenmo.services;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransferService extends AuthenticatedApiService<Transfer> {

    private AuthenticatedUser currentUser;
    private static final int INITIAL_TRANSFER_TYPE_SEND = 1;
    private static final int INITIAL_TRANSFER_TYPE_REQUEST = 0;
    private static final int INITIAL_TRANSFER_STATUS_APPROVE = 1;
    private static final int INITIAL_TRANSFER_STATUS_REJECT = 0;



    //get account balance
    public BigDecimal getCurrentBalance(){
        try{
            var url = baseUrl + "account/" + currentUser.getUser().getUsername() + "/balance";
            var entity = makeAuthEntity();
            ResponseEntity<BigDecimal> response = restTemplate.exchange(url, HttpMethod.GET, entity,BigDecimal.class);

            return response.getBody();

        }catch (Exception ex){
            BasicLogger.log(ex.getMessage());
        }
        return null;

    }


    public Transfer createSendTransfer(Transfer newTransfer){

        //BigDecimal currentUserBalance = getCurrentBalance();
        //int currentUserId = currentUser.getUser().getId();
        Transfer returnedTransfer = new Transfer();
        var url = baseUrl + "new-transfer";

        try {
            var entity = makeAuthEntity(newTransfer);
            returnedTransfer = restTemplate.postForObject(url, entity, Transfer.class);
        }catch (Exception ex){
            BasicLogger.log(ex.getMessage());
        }

        return returnedTransfer;

    }


    public List<Transfer> getAllTransfers(int accountId) {
        List<Transfer> transferList = new ArrayList<>();

        try{
            String url = baseUrl + "transfers/my_transfer/" + accountId;
            var entity = makeAuthEntity();
            ResponseEntity<Transfer[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Transfer[].class);
            transferList = Arrays.asList(response.getBody());

        }catch (Exception ex){
            transferList = new ArrayList<>();
            BasicLogger.log(ex.getMessage());
        }
        return transferList;
    }

    public Transfer checkTransfer(int transferId){
        try{
            var url = baseUrl + "/transfers/" + transferId;
            var entity = makeAuthEntity();
            ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.GET, entity,Transfer.class);

            return response.getBody();

        }catch (Exception ex){
            BasicLogger.log(ex.getMessage());
        }
        return null;
    }
}

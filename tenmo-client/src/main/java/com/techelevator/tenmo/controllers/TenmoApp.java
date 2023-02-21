package com.techelevator.tenmo.controllers;

<<<<<<< HEAD
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AuthenticatedApiService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.UserService;
=======
import com.techelevator.tenmo.models.*;
import com.techelevator.tenmo.services.*;
>>>>>>> kayla
import com.techelevator.tenmo.views.UserOutput;

import java.math.BigDecimal;
import java.util.List;

public class TenmoApp
{
<<<<<<< HEAD
    public TenmoApp() {
        String API_BASE_URL = "http://localhost:8080/";
        AuthenticatedApiService.setBaseUrl(API_BASE_URL);
    }

    private final UserOutput userOutput = new UserOutput();
    private final AuthenticationService authenticationService = new AuthenticationService();

=======

    //private static final String API_BASE_URL = "http://localhost:8080/";
    //AuthenticatedApiService.setBaseUrl(API_BASE_URL);
    private final UserOutput userOutput = new UserOutput();
    private final AuthenticationService authenticationService = new AuthenticationService();
    private final UserService userService = new UserService();
>>>>>>> kayla
    private AuthenticatedUser currentUser;
    private final AccountService accountService = new AccountService();
    private final TransferService transferService = new TransferService();
//
//
    public TenmoApp() {
        String API_BASE_URL = "http://localhost:8080/";
        AuthenticatedApiService.setBaseUrl(API_BASE_URL);
    }

    private UserService userService = new UserService();




    public void run()
    {
        userOutput.printGreeting();
        loginMenu();
        if (currentUser != null)
        {
            mainMenu();
        }
    }

    private void loginMenu()
    {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null)
        {
            userOutput.printLoginMenu();
            menuSelection = userOutput.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1)
            {
                handleRegister();
            }
            else if (menuSelection == 2)
            {
                handleLogin();
            }
            else if (menuSelection != 0)
            {
                System.out.println("Invalid Selection");
                userOutput.pause();
            }
        }
    }

    private void handleRegister()
    {
        System.out.println("Please register a new user account");
        UserCredentials credentials = userOutput.promptForCredentials();
        if (authenticationService.register(credentials))
        {
            System.out.println("Registration successful. You can now login.");
        }
        else
        {
            userOutput.printErrorMessage();
        }
    }

    private void handleLogin()
    {
        UserCredentials credentials = userOutput.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        AuthenticatedApiService.setAuthToken(currentUser.getToken());
        if (currentUser == null)
        {
            userOutput.printErrorMessage();
        }
    }

    private void mainMenu()
    {
        int menuSelection = -1;
        while (menuSelection != 0)
        {
            userOutput.printMainMenu();
            menuSelection = userOutput.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1)
            {
                viewCurrentBalance();
            }
            else if (menuSelection == 2)
            {
                int userId = currentUser.getUser().getId();
                int accountId = accountService.getAccountById(userId).getAccountId();
                viewTransferHistory(accountId);
            }
            else if (menuSelection == 3)
            {
                viewPendingRequests();
            }
            else if (menuSelection == 4)
            {
                if (accountService.getCurrentBalance(currentUser.getUser().getUsername()).equals(BigDecimal.ZERO)){
                    System.out.println("Account empty. Please choose a different option");
                    mainMenu();
                }
                else {
                    sendBucks();
                }
            }
            else if (menuSelection == 5)
            {
                requestBucks();
            }
            else if (menuSelection == 0)
            {
                continue;
            }
            else
            {
                System.out.println("Invalid Selection");
            }
            userOutput.pause();
        }
    }

    private void viewCurrentBalance()
    {
<<<<<<< HEAD
        System.out.println(userService.getCurrentBalance());
=======
        BigDecimal balance = accountService.getCurrentBalance(currentUser.getUser().getUsername());
        //BigDecimal balance = transferService.getCurrentBalance(currentUser.getUser().getUsername());

        userOutput.showBalance(balance);
>>>>>>> kayla
    }

    //id, stats, type is 0
    private void viewTransferHistory(int accountId)
    {
        // TODO Auto-generated method stub
        List<Transfer> transfers = transferService.getAllTransfers(accountId);

        userOutput.displayTransfers(transfers);


        int transferSelection = userOutput.promptForInt("Select transfer ID to view more details.");

        Transfer transfer = transferService.checkTransfer(transferSelection);

        userOutput.displayThisTransfer(transfer);
    }

    private void viewPendingRequests()
    {
        // TODO Auto-generated method stub

    }


    //can not create transfer
    private void sendBucks()
    {
        // TODO Auto-generated method stub
        int userSelection = -1;

        BigDecimal moneyToSend;

        BigDecimal userBalance = accountService.getCurrentBalance(currentUser.getUser().getUsername());

        List<User> users = userService.getAllUsers();

        userOutput.displayUsers(users);

        while (userSelection != 0)
        {
            userSelection = userOutput.promptForInt("Enter ID of user you are sending to (0 to cancel): ");

            //User user = users.get(menuSelection);

            //change number 0
            if(userSelection != 0) {
                if(userSelection == currentUser.getUser().getId()){
                    System.out.println("Invalid Option");
                    sendBucks();
                }
                moneyToSend = userOutput.promptForBigDecimal("Enter amount to send: ");

                BigDecimal validMoneyAmount = userOutput.verifyValidMoneyAmount(userBalance, moneyToSend);

//                for (User user : users) {
//
//                    if (menuSelection == user.getId()) {
//                        accountService.sendMoney(menuSelection, moneyToSend);
//                        System.out.println(accountService.getCurrentBalance(user.getUsername()));
////                        transferMoney(menuSelection);
////                        System.out.println("++++++++++++++++++");
////                        BigDecimal n = accountService.getAccountById(menuSelection).getBalance();
////                        System.out.println();
//                    }
//                }
                //get current user account
                Account currentAccount = accountService.getAccountById(currentUser.getUser().getId());
                //get receive user account
                Account receiverAccount = accountService.getAccountById(userSelection);

                Transfer newTransfer = new Transfer(currentAccount.getAccountId(), receiverAccount.getAccountId(), validMoneyAmount);

                transferService.createSendTransfer(newTransfer);


            }
            else
            {
                mainMenu();
            }

            //System.out.println("invalid input");


//            for (User user : users) {
//
//                if (menuSelection == user.getId())
//                {
////                    transferMoney(menuSelection);
//                    System.out.println(menuSelection);
//                }
//                else if (menuSelection == 2)
//                {
//                    mainMenu();
//                }
//                else
//                {
//                    System.out.println("Invalid Selection");
//                }
//            }


            userOutput.pause();
            System.out.println("Transfer Approved!");
            mainMenu();
        }

    }


//    private BigDecimal transferMoney(int id) {
//        String value = userOutput.askMoney();
//        BigDecimal transferAmount = BigDecimal.valueOf(Long.parseLong(value));
//        Account account = accountService.getAccountById(id);
//        BigDecimal updatedBalance = account.addBalance(transferAmount);
//        return updatedBalance;
//
//
//    }

    private void requestBucks()
    {
        // TODO Auto-generated method stub

    }

}

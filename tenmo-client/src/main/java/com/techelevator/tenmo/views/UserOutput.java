package com.techelevator.tenmo.views;


import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserOutput
{

    private final Scanner scanner = new Scanner(System.in);
    private final TransferService transferService = new TransferService();



    public int promptForMenuSelection(String prompt)
    {
        int menuSelection;
        System.out.print(prompt);
        try
        {
            menuSelection = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting()
    {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu()
    {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu()
    {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials()
    {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt)
    {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt)
    {
        System.out.print(prompt);
        while (true)
        {
            try
            {
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt)
    {
        System.out.print(prompt);
        while (true)
        {
            try
            {
                return new BigDecimal(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause()
    {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage()
    {
        System.out.println("An error occurred. Check the log for details.");
    }


    public void showBalance(BigDecimal balance){
        System.out.println();
        System.out.println("Your current balance is: $" + balance);

    }

    public void displayUsers(List<User> users){

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("Users ");
        System.out.println("ID" + "        Name");
        System.out.println("-------------------------------------------");
        users.forEach(user -> {
            System.out.print(user.getId() + "      ");
            System.out.println(user.getUsername());
        });
        System.out.println();
    }

    public void displayTransfers(List<Transfer> transfers){
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println("Transfers ");
        System.out.println("ID" + "        From/To" + "        Amount");
        System.out.println("-------------------------------------------");
        transfers.forEach(transfer -> {
            System.out.print(transfer.getId() + "      ");
            System.out.print("From: " + transfer.getAccountFrom()+ " | To: " + transfer.getAccountTo() + "      ");
            System.out.println(transfer.getAmount());
        });
        System.out.println();
    }

    public String askMoney(){
        System.out.println();
        System.out.print("Please enter the transfer amount: ");
        return scanner.nextLine();
    }

    public BigDecimal verifyValidMoneyAmount(BigDecimal userBalance, BigDecimal moneyToSend) {
        BigDecimal transferAmount = moneyToSend;

        if (moneyToSend.signum() > 0 && userBalance.subtract(moneyToSend).signum() >= 0) {
            return moneyToSend;
        }
        else if (moneyToSend.signum() <= 0) {
            transferAmount = promptForBigDecimal("Please enter an amount greater than zero: ");
            verifyValidMoneyAmount(userBalance, transferAmount);
        }
        else if (userBalance.subtract(moneyToSend).signum() < 0) {
            transferAmount = promptForBigDecimal("Not enough money for transfer. Select a smaller amount: ");
            verifyValidMoneyAmount(userBalance, transferAmount);
        }

        return transferAmount;
    }

    //fix this later
    public void displayThisTransfer(Transfer transfer) {

        //id is zero
        System.out.println("id:" + transfer.getId());
        System.out.println("account_to: " + transfer.getAccountTo());
        System.out.println("account_from: " + transfer.getAccountFrom());
        System.out.println("balance: " + transfer.getAmount());
    }

//    public Transfer checkTransfer(int transferId){
//
//        List<Transfer> transfers = transferService.getAllTransfers(accountId);
//
//    }
}

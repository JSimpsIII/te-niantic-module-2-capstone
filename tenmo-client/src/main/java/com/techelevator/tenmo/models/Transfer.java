package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfer {
    private int id;

    private int typeId;
    private int statusId;
    private int accountFrom;
    private int accountTo;
    private BigDecimal amount;

    public Transfer() {

    }

    public Transfer(int id, int typeId, int statusId, int accountFrom, int accountTo, BigDecimal amount) {
        this.id = id;
        this.typeId = typeId;
        this.statusId = statusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfer(int typeId, int statusId, int accountFrom, int accountTo, BigDecimal amount) {
        this.typeId = typeId;
        this.statusId = statusId;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfer(int accountFrom, int accountTo, BigDecimal amount) {

        this.typeId = 1;
        this.statusId = 1;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

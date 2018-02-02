package com.example.kirazavrik.cashmanager;


import com.example.kirazavrik.cashmanager.models.Balance;

/**
 * Created by kirazavrik on 5.1.18.
 */

public class BalanceManager {

    private static BalanceManager balanceManager;

    private Balance balance;

    private BalanceManager() {
        balance = new Balance();
    }

    public static BalanceManager getInstance() {
        if(balanceManager == null) {
            balanceManager = new BalanceManager();
        }
        return balanceManager;
    }

    public Double getBalance() {
        return balance.getAmount();
    }

    public void setBalance(Double newAmount) {
        balance.setAmount(newAmount);
    }




}

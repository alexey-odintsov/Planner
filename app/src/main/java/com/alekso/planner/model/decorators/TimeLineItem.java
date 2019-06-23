package com.alekso.planner.model.decorators;

import com.alekso.planner.model.Account;
import com.alekso.planner.model.Transaction;

public class TimeLineItem {
    private Transaction transaction;
    private Account account;

    public Transaction getTransaction() {
        return transaction;
    }

    public Account getAccount() {
        return account;
    }

    public TimeLineItem(Transaction transaction, Account account) {
        this.transaction = transaction;
        this.account = account;
    }
}

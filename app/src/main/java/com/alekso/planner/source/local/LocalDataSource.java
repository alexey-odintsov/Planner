package com.alekso.planner.source.local;

import com.alekso.planner.model.Account;
import com.alekso.planner.model.Transaction;
import com.alekso.planner.model.decorators.TimeLineItem;

import java.util.ArrayList;

public interface LocalDataSource {
    long addAccount(Account account);

    boolean deleteAccount(long accountId);

    ArrayList<Account> getAccounts();

    ArrayList<TimeLineItem> getTimeLine(long dt);

    long addTransaction(Transaction transaction);
}
